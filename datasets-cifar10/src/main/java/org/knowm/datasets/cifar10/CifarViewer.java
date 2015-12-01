package org.knowm.datasets.cifar10;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author timmolter
 */
public class CifarViewer extends JFrame {

  public CifarViewer(JPanel jPanel, String title) {

    add(jPanel, BorderLayout.CENTER);
    pack();

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setTitle(title);
    setResizable(false);
    setVisible(true);
  }

}
