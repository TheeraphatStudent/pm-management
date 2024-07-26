package components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JLabel;

import pages.Member;

public class Devpanel extends JPanel{
    public Devpanel(int top,int left,int bottom,int right,String name,String id){
        GridBagConstraints gridConst = new GridBagConstraints();
        setBackground(Color.YELLOW);
        setPreferredSize(new Dimension(500,700));
        setLayout(new GridBagLayout());

        JPanel head = new JPanel();
        head.setBackground(Color.white);

        JPanel panelbox2 = new JPanel();
        panelbox2.setBackground(Color.green);
        panelbox2.setLayout(new GridBagLayout());

        try (InputStream is = Devpanel.class.getClassLoader().getResourceAsStream("resource/images/airplane.png")) {
    if (is == null) {
        System.out.println("Image not found");
    } else {
        BufferedImage iconImage = ImageIO.read(is);
        ImageIcon icon = new ImageIcon(iconImage);
        JLabel lb = new JLabel(icon);
        lb.setPreferredSize(new Dimension(250,250));
        head.add(lb);
    }
} catch (IOException e) {
    e.printStackTrace();
}

        gridConst.gridx = -1;
        gridConst.gridy = 1;
        gridConst.weightx = 1.0;
        gridConst.weighty = 1.0;
        gridConst.fill = GridBagConstraints.BOTH;
        gridConst.insets = new Insets(40, 30, 10, 30);

        JTextPane textname = new JTextPane ();
        textname.setText("Name : "+name+"\nID : "+id);
        textname.setEditable(false);

        panelbox2.add(textname,gridConst);

        gridConst.gridx = -1;
        gridConst.gridy = 0;
        gridConst.weightx = 1.0;
        gridConst.weighty = 1.0;
        gridConst.fill = GridBagConstraints.BOTH;
        gridConst.insets = new Insets(30, 30, 20, 30);

        panelbox2.add(head,gridConst);

        gridConst.gridx = 0;
        gridConst.gridy = 0;
        gridConst.weightx = 1.0;
        gridConst.weighty = 0.7;
        gridConst.fill = GridBagConstraints.BOTH;
        gridConst.insets = new Insets(top, left, bottom, right);

        add(panelbox2,gridConst);
    }
}
