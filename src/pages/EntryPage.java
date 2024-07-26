package pages;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;

import resource.colors.MainColor;
import resource.environment.WindowClosingFrameEvent;
import resource.environment.WindowEntryScreen;
import utils.useSourceImg;

public class EntryPage extends JFrame {
    public EntryPage() {
        setTitle("PM 2.5 Management");
        // setSize(new WindowEntryScreen().getWidth(), new
        // WindowEntryScreen().getHeight());
        setSize(500, 500);
        setLayout(new GridLayout());

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
        JButton navigateToPage = new JButton("Program");

        navigateToPage.addActionListener(e -> {
            JFrame page = new Page();
            new WindowClosingFrameEvent(this, page);
            page.setVisible(true);
            dispose();
        });

        JButton navigateToMember = new JButton("Develops");
        navigateToMember.addActionListener(e -> {
            JFrame member = new Member();
            new WindowClosingFrameEvent(this, member);
            member.setVisible(true);
            dispose();
        });

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(navigateToPage);
        panel.add(navigateToMember);
        panel.setBackground(MainColor.secondary());

        add(panel);

        new WindowClosingFrameEvent(this);
        // setUndecorated(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
