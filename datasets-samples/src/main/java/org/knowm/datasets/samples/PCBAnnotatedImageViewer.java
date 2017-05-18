/**
 * (The MIT License)
 *
 * Copyright 2015-2017 Knowm Inc. (http://knowm.org) and contributors.
 * Copyright 2013-2015 Xeiam LLC (http://xeiam.com) and contributors.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do
 * so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
/**
 * This product currently only contains code developed by authors
 * of specific components, as identified by the source code files.
 *
 * Since product implements StAX API, it has dependencies to StAX API
 * classes.
 *
 * For additional credits (generally to people who reported problems)
 * see CREDITS file.
 */
package org.knowm.datasets.samples;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import org.knowm.datasets.pcb.PCB;
import org.knowm.datasets.pcb.PCBAnnotation;
import org.knowm.datasets.pcb.PCBAnnotationDAO;
import org.knowm.datasets.pcb.PCBDAO;

/**
 * @author timmolter
 */
public class PCBAnnotatedImageViewer extends JPanel implements TreeSelectionListener {

  /** The main split frame */
  private JSplitPane splitPane;

  /** The tree */
  private JTree tree;

  /** The panel for images */
  private ImagePanel bottomPanel = new ImagePanel();
  private JScrollPane bottomScrollPane;
  private Font font = new Font(Font.SANS_SERIF, Font.BOLD, 20);

  /**
   * Constructor
   */
  public PCBAnnotatedImageViewer() {

    super(new GridLayout(1, 0));

    // Create the nodes.
    DefaultMutableTreeNode top = new DefaultMutableTreeNode("PCB Annotated Image Explorer");
    createNodes(top);

    // Create a tree that allows one selection at a time.
    tree = new JTree(top);
    tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

    // Listen for when the selection changes.
    tree.addTreeSelectionListener(this);

    // Create the scroll pane and add the tree to it.
    JScrollPane treeScrollPane = new JScrollPane(tree);

    // Create Image Panel
    PCB pcb = PCBDAO.selectSingle(1);
    List<PCBAnnotation> pcbAnnotations = PCBAnnotationDAO.selectList(1);
    bottomPanel.update(pcb, pcbAnnotations);
    bottomScrollPane = new JScrollPane(bottomPanel);

    // Add the scroll panes to a split pane.
    splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    splitPane.setTopComponent(treeScrollPane);
    splitPane.setBottomComponent(bottomScrollPane);

    Dimension minimumSize = new Dimension(130, 160);
    treeScrollPane.setMinimumSize(minimumSize);
    splitPane.setPreferredSize(new Dimension(700, 700));

    // Add the split pane to this panel.
    add(splitPane);

  }

  @Override
  public void valueChanged(TreeSelectionEvent e) {

    DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

    if (node == null) {
      return;
    }

    Object nodeInfo = node.getUserObject();
    // tree leaf
    if (node.isLeaf()) {

      PCB pcb = (PCB) nodeInfo;
      List<PCBAnnotation> pcbAnnotations = PCBAnnotationDAO.selectList(pcb.getId());
      bottomPanel.update(pcb, pcbAnnotations);
      bottomScrollPane = new JScrollPane(bottomPanel);
      splitPane.setBottomComponent(bottomScrollPane);

    }
  }

  /**
   * Create the tree
   *
   * @param top
   */
  private void createNodes(DefaultMutableTreeNode top) {

    // categories
    DefaultMutableTreeNode category = null;
    // leaves
    DefaultMutableTreeNode defaultMutableTreeNode = null;

    // PCB Files
    category = new DefaultMutableTreeNode("PCB Images");
    top.add(category);

    long count = PCBDAO.selectCount();
    for (int i = 1; i <= count; i++) {
      PCB pcb = PCBDAO.selectSingle(i);
      defaultMutableTreeNode = new DefaultMutableTreeNode(pcb);
      category.add(defaultMutableTreeNode);
    }
  }

  /**
   * Create the GUI and show it. For thread safety, this method should be invoked from the event dispatch thread.
   */
  private static void createAndShowGUI() {

    // Create and set up the window.
    JFrame frame = new JFrame("PCB Annotated Image Explorer");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Add content to the window.
    frame.add(new PCBAnnotatedImageViewer());

    Runtime.getRuntime().addShutdownHook(new Thread() {

      @Override
      public void run() {

        System.out.println("****************************");
        PCBDAO.release(); // release data resources

      }
    });
    // Display the window.
    frame.pack();
    frame.setVisible(true);
  }

  public static void main(String[] args) {

    // Schedule a job for the event dispatch thread:
    // creating and showing this application's GUI.
    javax.swing.SwingUtilities.invokeLater(new Runnable() {

      @Override
      public void run() {

        try {
          PCBDAO.init("/usr/local/Datasets/"); // setup data
          createAndShowGUI();
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
        }
      }
    });
  }

  public class ImagePanel extends JPanel {

    private BufferedImage bufferedImage;
    private List<PCBAnnotation> pcbAnnotations;

    @Override
    public Dimension getPreferredSize() {

      return new Dimension(bufferedImage.getWidth(), bufferedImage.getHeight());
    }

    @Override
    protected void paintComponent(Graphics g) {

      super.paintComponent(g);

      Graphics2D g2 = (Graphics2D) g;

      g.drawImage(bufferedImage, 0, 0, null); // see javadoc for more info on the parameters

      // annotations
      if (pcbAnnotations != null) {

        for (PCBAnnotation pcbAnnotation : pcbAnnotations) {

          AffineTransform orig = g2.getTransform();
          AffineTransform at = new AffineTransform();

          at.rotate(Math.toRadians(pcbAnnotation.getRotation()), pcbAnnotation.getX(), pcbAnnotation.getY());

          Shape rect = new Rectangle2D.Double(pcbAnnotation.getX() - pcbAnnotation.getWidth() / 2,
              pcbAnnotation.getY() - pcbAnnotation.getHeight() / 2, pcbAnnotation.getWidth(), pcbAnnotation.getHeight());

          g2.transform(at);

          g2.setStroke(new BasicStroke(12));
          g2.setColor(Color.RED);
          g2.draw(rect);

          g2.setStroke(new BasicStroke(12, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 12, 12 }, 0));
          g2.setColor(Color.WHITE);
          g2.draw(rect);

          g2.setTransform(orig);

          if (pcbAnnotation.getName() != null) {

            FontRenderContext frc = g2.getFontRenderContext();
            TextLayout textLayout = new TextLayout(pcbAnnotation.getName(), font, frc);
            textLayout.draw(g2, pcbAnnotation.getX() - pcbAnnotation.getWidth() / 2 + 16, pcbAnnotation.getY() - pcbAnnotation.getHeight() / 2 + 22);

          }

        }

      }

    }

    private void update(PCB pcb, List<PCBAnnotation> pcbAnnotations) {

      // image
      try (InputStream bytes = pcb.getImgbytes().getBinaryStream();) {

        this.bufferedImage = ImageIO.read(bytes);
      } catch (Exception e) {
        e.printStackTrace();
      }

      this.pcbAnnotations = pcbAnnotations;
      repaint();

    }

  }
}
