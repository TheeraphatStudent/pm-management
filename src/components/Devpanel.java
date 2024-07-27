package components;

import pages.Member;

// AWT
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

// Image
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Image;

// Swing
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JButton;

// Utils
import utils.useButton;

// Resource
import resource.environment.WindowEntryScreen;

public class Devpanel extends JPanel {
    public Devpanel() {
    }

    public Devpanel(int top, int left, int bottom, int right, String name, String id) {
        setLayout(new GridBagLayout());
        GridBagConstraints gridConst = new GridBagConstraints();
        setBackground(Color.YELLOW);

        JPanel head = new JPanel();
        head.setPreferredSize(new Dimension(250, 250));
        head.setBackground(Color.white);

        JPanel panelbox2 = new JPanel();
        panelbox2.setBackground(Color.green);
        panelbox2.setLayout(new GridBagLayout());

        JButton createImg = new useButton().createButton("dev", "", 250, 250, "-");
        head.add(createImg);

        gridConst.gridx = -1;
        gridConst.gridy = 1;
        gridConst.weightx = 1.0;
        gridConst.weighty = 1.0;
        gridConst.fill = GridBagConstraints.BOTH;
        gridConst.insets = new Insets(40, 30, 10, 30);

        JTextPane textname = new JTextPane();
        textname.setText("Name : " + name + "\nID : " + id);
        textname.setEditable(false);

        panelbox2.add(textname, gridConst);

        gridConst.gridx = -1;
        gridConst.gridy = 0;
        gridConst.weightx = 1.0;
        gridConst.weighty = 1.0;
        gridConst.fill = GridBagConstraints.BOTH;
        gridConst.insets = new Insets(30, 30, 20, 30);

        panelbox2.add(head, gridConst);

        gridConst.gridx = 0;
        gridConst.gridy = 0;
        gridConst.weightx = 1.0;
        gridConst.weighty = 0.7;
        gridConst.fill = GridBagConstraints.BOTH;
        gridConst.insets = new Insets(top, left, bottom, right);

        add(panelbox2, gridConst);
    }
}
