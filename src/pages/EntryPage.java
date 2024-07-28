package pages;


import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

// Img
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

import javax.swing.JPanel;
import components.Entrypagepanel;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import resource.colors.MainColor;
import resource.environment.WindowClosingFrameEvent;
import resource.environment.WindowEntryScreen;
import utils.useButton;

public class EntryPage extends JFrame {
    public EntryPage() {
        // JPanel epanel = new Entrypagepanel(this,"Program","page",0,0,0,0);
        // JPanel epanel2 = new Entrypagepanel(this,"Develops","member",0,0,0,0);
        JButton navToPage = new useButton().createButton("-", "Program", 0, 0, "hand", this, "page");
        JButton navToDev = new useButton().createButton("-", "Develop", 0, 0, "hand", this, "member");
        JButton navToExit = new useButton().createButton("-", "Exit", 0, 0, "hand", this, "exit");
        setTitle("PM 2.5 Management");
        setSize(500, 500);
        setLayout(new GridBagLayout());

        // Location
        
        setLocation(new WindowEntryScreen().getWidthCenter(), new WindowEntryScreen().getHeightCenter());

        // System.out.println(width + " | " + height);

        try (InputStream is = Page.class.getClassLoader().getResourceAsStream("resource/images/icon.png")) {
            if (is == null) {
                System.out.println("Image not found");
            } else {
                BufferedImage iconImage = ImageIO.read(is);
                setIconImage(iconImage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        JPanel panel = new JPanel(new GridLayout(3, 1));
        panel.setBackground(MainColor.trinary());

        JPanel panel2 = new JPanel();
        panel2.setBackground(MainColor.primary());

        //Button manu
        GridBagConstraints gridConst = new GridBagConstraints();
        gridConst.gridx = 0;
        gridConst.gridy = 0;
        gridConst.weightx = 1.0;
        gridConst.weighty = 1.0;
        gridConst.fill = GridBagConstraints.BOTH;

        panel.add(navToPage,gridConst);

        gridConst.gridx = 0;
        gridConst.gridy = 1;
        gridConst.weightx = 1.0;
        gridConst.weighty = 1.0;
        gridConst.fill = GridBagConstraints.BOTH;

        panel.add(navToDev,gridConst);
        panel.add(navToExit,gridConst);

        gridConst.gridx = 0;
        gridConst.gridy = 1;
        gridConst.weightx = 1.0;
        gridConst.weighty = 0.5;
        gridConst.fill = GridBagConstraints.BOTH;
        gridConst.insets = new Insets(300,150,50,150);

        add(panel,gridConst);
        
        //Picture

        gridConst.gridx = 0;
        gridConst.gridy = 1;
        gridConst.weightx = 1.0;
        gridConst.weighty = 0.5;
        gridConst.fill = GridBagConstraints.BOTH;
        gridConst.insets = new Insets(50,50,180,50);

        add(panel2,gridConst);

        new WindowClosingFrameEvent(this);
        // setUndecorated(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
