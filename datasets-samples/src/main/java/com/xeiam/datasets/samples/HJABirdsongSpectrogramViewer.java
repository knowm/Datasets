/**
 * Copyright 2011 - 2014 Xeiam LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xeiam.datasets.samples;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.sql.SQLException;

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

import com.xeiam.datasets.common.musicg.wave.Spectrogram;
import com.xeiam.datasets.common.musicg.wave.SpectrogramRender;
import com.xeiam.datasets.common.musicg.wave.Wave;
import com.xeiam.datasets.common.musicg.wave.WaveformRender;
import com.xeiam.datasets.hjabirdsong.HJABirdSong;
import com.xeiam.datasets.hjabirdsong.HJABirdsongDAO;

/**
 * Class containing all XChart example charts
 * 
 * @author timmolter
 */
public class HJABirdsongSpectrogramViewer extends JPanel implements TreeSelectionListener {

  /** The main split frame */
  private JSplitPane splitPane;

  /** The tree */
  private JTree tree;

  /** The panel for chart */
  private JPanel bottomPanel = new JPanel();

  /**
   * Constructor
   */
  public HJABirdsongSpectrogramViewer() {

    super(new GridLayout(1, 0));

    // Create the nodes.
    DefaultMutableTreeNode top = new DefaultMutableTreeNode("HJA Birdsong Spectrogram Explorer");
    createNodes(top);

    // Create a tree that allows one selection at a time.
    tree = new JTree(top);
    tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

    // Listen for when the selection changes.
    tree.addTreeSelectionListener(this);

    // Create the scroll pane and add the tree to it.
    JScrollPane treeView = new JScrollPane(tree);

    // Create Chart Panel
    HJABirdSong hJABirdsong = HJABirdsongDAO.selectSingle(1);
    bottomPanel.add(getSoundPanel(hJABirdsong));

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

      HJABirdSong hJABirdsong = (HJABirdSong) nodeInfo;
      bottomPanel = new JPanel();
      bottomPanel.add(getSoundPanel(hJABirdsong));
      splitPane.setBottomComponent(bottomPanel);

    }
  }

  private JPanel getSoundPanel(HJABirdSong hJABirdsong) {

    // create a wave object
    Wave wave = null;
    try {
      wave = new Wave(hJABirdsong.getWavbytes().getBinaryStream());
    } catch (SQLException e1) {
      e1.printStackTrace();
    }

    // System.out.println(wave.toString());

    JPanel soundPanel = new JPanel();
    soundPanel.setLayout(new BorderLayout());

    // spectrogram
    Spectrogram spectrogram = wave.getSpectrogram();
    SpectrogramRender spectrogramRender = new SpectrogramRender();
    BufferedImage spectrogramBI = spectrogramRender.renderSpectrogram(spectrogram);
    soundPanel.add(new ImagePanel(spectrogramBI), BorderLayout.CENTER);

    // waveform
    WaveformRender waveformRender = new WaveformRender();
    BufferedImage waveformBI = waveformRender.renderWaveform(wave, spectrogram.getNormalizedSpectrogramData().length);
    soundPanel.add(new ImagePanel(waveformBI), BorderLayout.PAGE_END);

    return soundPanel;
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

    // Wav Files
    category = new DefaultMutableTreeNode("Wav Files");
    top.add(category);

    long count = HJABirdsongDAO.selectCount();
    for (int i = 1; i <= count; i++) {
      HJABirdSong hJABirdsong = HJABirdsongDAO.selectSingle(i);
      hJABirdsong.getWavfilename();
      defaultMutableTreeNode = new DefaultMutableTreeNode(hJABirdsong);
      category.add(defaultMutableTreeNode);
    }

  }

  /**
   * Create the GUI and show it. For thread safety, this method should be invoked from the event dispatch thread.
   */
  private static void createAndShowGUI() {

    // Create and set up the window.
    JFrame frame = new JFrame("HJA Birdsong Spectrogram Explorer");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Add content to the window.
    frame.add(new HJABirdsongSpectrogramViewer());

    Runtime.getRuntime().addShutdownHook(new Thread() {

      @Override
      public void run() {

        System.out.println("****************************");
        HJABirdsongDAO.release(); // release data resources

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
          HJABirdsongDAO.init("/usr/local/Datasets/"); // setup data
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
