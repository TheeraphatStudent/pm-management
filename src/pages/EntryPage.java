package pages;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import java.io.IOException;
import java.io.InputStream;

import javax.swing.JPanel;
import components.Entrypagepanel;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;

import resource.colors.MainColor;
import resource.environment.WindowClosingFrameEvent;
import resource.environment.WindowEntryScreen;

public class EntryPage extends JFrame {
    public EntryPage() {
        JPanel epanel = new Entrypagepanel(this,"Program","page",0,0,0,0);
        JPanel epanel2 = new Entrypagepanel(this,"Develops","member",0,0,0,0);
        setTitle("PM 2.5 Management");
        setSize(500, 500);
        setLayout(new GridBagLayout());

        // Location
        // int width = (int)(Math.ceil(gd.getDisplayMode().getWidth() / 2.5));
        // int height = (int)(Math.ceil(gd.getDisplayMode().getHeight() / 4));
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

        // new useSourceImg().getSourceImage(this, getName());

        // Menu Hamburger
        // ButtonNavigate navigateToPage = new ButtonNavigate("Program", this, new
        // Page());
        // ButtonNavigate navigateToMember = new ButtonNavigate("Member", this, new
        // Member());

        // JButton navigateToPage = new JButton("Program");
        // navigateToPage.addActionListener(e -> {
        //     JFrame page = new Page();
        //     new WindowClosingFrameEvent(this, page);
        //     page.setVisible(true);
        //     dispose();
        // });

        // JButton navigateToMember = new JButton("Develops");
        // navigateToMember.addActionListener(e -> {
        //     JFrame member = new Member();
        //     new WindowClosingFrameEvent(this, member);
        //     member.setVisible(true);
        //     dispose();
        // });

        // JPanel panel = new JPanel(new GridLayout(4, 2));
        // panel.add(navigateToPage);
        // panel.add(navigateToMember);
        // panel.setBackground(MainColor.secondary());

        // add(panel);
        GridBagConstraints gridConst = new GridBagConstraints();
        gridConst.gridx = 0;
        gridConst.gridy = 1;
        gridConst.weightx = 1.0;
        gridConst.weighty = 0.7;
        gridConst.fill = GridBagConstraints.BOTH;
        gridConst.insets = new Insets(10,100,200,100);
        add(epanel,gridConst);
        gridConst.gridx = 0;
        gridConst.gridy = 1;
        gridConst.weightx = 1.0;
        gridConst.weighty = 0.7;
        gridConst.fill = GridBagConstraints.BOTH;
        gridConst.insets = new Insets(300,100,10,100);
        add(epanel2,gridConst);


        new WindowClosingFrameEvent(this);
        // setUndecorated(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
