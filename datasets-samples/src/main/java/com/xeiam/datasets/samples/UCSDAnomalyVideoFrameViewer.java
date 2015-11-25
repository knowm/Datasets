/**
 * Copyright (C) 2013-2014 Xeiam LLC http://xeiam.com
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
package com.xeiam.datasets.samples;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import com.xeiam.datasets.ucsdanomaly.UCSDAnomaly;
import com.xeiam.datasets.ucsdanomaly.UCSDAnomalyDAO;

/**
 * @author timmolter
 */
public class UCSDAnomalyVideoFrameViewer extends JPanel implements TreeSelectionListener {

  /** The main split frame */
  private JSplitPane splitPane;

  /** The tree */
  private JTree tree;

  /** The panel for chart */
  private JPanel bottomPanel = new JPanel();

  /**
   * Constructor
   */
  public UCSDAnomalyVideoFrameViewer() {

    super(new GridLayout(1, 0));

    // Create the nodes.
    DefaultMutableTreeNode top = new DefaultMutableTreeNode("UCSD Anomaly PED1");
    createNodes(top);

    // Create a tree that allows one selection at a time.
    tree = new JTree(top);
    tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

    // Listen for when the selection changes.
    tree.addTreeSelectionListener(this);

    // Create the scroll pane and add the tree to it.
    JScrollPane treeView = new JScrollPane(tree);

    // Create Chart Panel
    UCSDAnomaly uCSDAnomaly = UCSDAnomalyDAO.selectSingle(1, 1);
    bottomPanel.add(getImagePanel(uCSDAnomaly));

    // Add the scroll panes to a split pane.
    splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    splitPane.setTopComponent(treeView);
    splitPane.setBottomComponent(bottomPanel);

    Dimension minimumSize = new Dimension(130, 160);
    treeView.setMinimumSize(minimumSize);
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

      UCSDAnomaly uCSDAnomaly = (UCSDAnomaly) nodeInfo;
      bottomPanel = new JPanel();
      bottomPanel.add(getImagePanel(uCSDAnomaly));
      splitPane.setBottomComponent(bottomPanel);

    }
  }

  private JPanel getImagePanel(UCSDAnomaly uCSDAnomaly) {

    JPanel tifPanel = new JPanel();

    InputStream bytes;
    try {
      bytes = uCSDAnomaly.getTifbytes().getBinaryStream();

      // got an image file
      // String filename = "./raw/001.png";
      // File f = new File(filename);
      // System.out.println(f.canRead());
      // System.out.println(Arrays.toString(ImageIO.getReaderFileSuffixes()));
      BufferedImage bufferedImage = ImageIO.read(bytes);
      // bufferedImage is the RenderedImage to be written

      // Graphics2D g2 = bufferedImage.createGraphics();
      // g2.drawImage(image, null, null);

      tifPanel.add(new ImagePanel(bufferedImage), BorderLayout.PAGE_END);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return tifPanel;
  }

  /**
   * Create the tree
   * 
   * @param top
   */
  private void createNodes(DefaultMutableTreeNode top) {

    // leaves
    DefaultMutableTreeNode defaultMutableTreeNode = null;

    // Train Files
    DefaultMutableTreeNode trainCategory = new DefaultMutableTreeNode("Train");
    top.add(trainCategory);

    // Test Files
    DefaultMutableTreeNode testCategory = new DefaultMutableTreeNode("Test");
    top.add(testCategory);

    // long count = UCSDAnomalyDAO.selectCount();
    int trainTestSplit = UCSDAnomalyDAO.getTrainTestSplit();
    int index = 1;
    do {

      DefaultMutableTreeNode category = new DefaultMutableTreeNode(index);
      if (index <= trainTestSplit) {
        trainCategory.add(category);
      } else {
        testCategory.add(category);
      }

      List<UCSDAnomaly> fames = UCSDAnomalyDAO.selectClip(index);
      for (UCSDAnomaly ucsdAnomaly : fames) {
        // ucsdAnomaly.getTifid();
        defaultMutableTreeNode = new DefaultMutableTreeNode(ucsdAnomaly);
        category.add(defaultMutableTreeNode);
      }
    } while (UCSDAnomalyDAO.selectClip(++index).size() > 0);

  }

  /**
   * Create the GUI and show it. For thread safety, this method should be invoked from the event dispatch thread.
   */
  private static void createAndShowGUI() {

    // Create and set up the window.
    JFrame frame = new JFrame("UCSD Anomaly Video Explorer");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Add content to the window.
    frame.add(new UCSDAnomalyVideoFrameViewer());

    Runtime.getRuntime().addShutdownHook(new Thread() {

      @Override
      public void run() {

        System.out.println("****************************");
        UCSDAnomalyDAO.release(); // release data resources

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
          UCSDAnomalyDAO.init("/usr/local/Datasets/"); // setup data
          createAndShowGUI();
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
        }
      }
    });
  }

  public class ImagePanel extends JPanel {

    public ImagePanel(BufferedImage bi) {

      setLayout(new BorderLayout());
      ImageIcon icon = null;
      try {
        icon = new ImageIcon(bi);
      } catch (Exception e) {
        e.printStackTrace();
      }
      add(new JLabel(icon));
    }

  }
}
