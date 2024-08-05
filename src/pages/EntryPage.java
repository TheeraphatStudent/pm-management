package pages;

import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Color;

// Img
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;

// Swing
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import resource.colors.MainColor;
import resource.environment.WindowClosingFrameEvent;
import resource.environment.WindowEntryScreen;
import utils.useButton;

public class EntryPage extends JFrame {
    public EntryPage() {
        JButton navToPage = new useButton().createButton("-", "Program", MainColor.primary(), 200, 100, "hand", this,
                "page");
        JButton navToDev = new useButton().createButton("-", "Develop", MainColor.primary(), 200, 100, "hand", this,
                "member");
        JButton navToExit = new useButton().createButton("-", "Exit", MainColor.access("red"), 200, 100, "hand", this,
                "exit");
        setTitle("PM 2.5 Management");
        setSize(800, 650);
        setMinimumSize(new Dimension(800, 650));
        setLayout(new GridBagLayout());
        getContentPane().setBackground(MainColor.secondary());

        GridBagConstraints gridConst = new GridBagConstraints();

        // Location
        setLocation((int) (Math.floor(new WindowEntryScreen().getWidthCenter() / 1.45)),
                (int) (Math.floor(new WindowEntryScreen().getHeightCenter() / 1.4)));

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

        JPanel panel = new JPanel(new GridLayout(2, 1, 20, 20));
        panel.setBackground(null);

        JPanel panel2 = new JPanel(new GridLayout());
        panel2.setBackground(null);

        panel.add(navToPage);
        panel.add(navToDev);
        panel.add(navToExit);

        gridConst.gridx = 0;
        gridConst.gridy = 1;
        gridConst.weightx = 0;
        gridConst.weighty = 0;
        gridConst.ipadx = 200;
        gridConst.ipady = 0;
        gridConst.fill = GridBagConstraints.BOTH;
        gridConst.insets = new Insets(25, 50, 50, 50);

        add(panel, gridConst);

        JLabel imgLabel = new JLabel();
        try (InputStream is = EntryPage.class.getClassLoader().getResourceAsStream("resource/images/dust.png")) {
            if (is == null) {
                System.out.println("Image not found");
            } else {
                BufferedImage iconImage = ImageIO.read(is);
                Image scaledImage = iconImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                imgLabel.setIcon(new ImageIcon(scaledImage));

                panel2.add(imgLabel);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Picture
        gridConst.gridx = 0;
        gridConst.gridy = 0;
        gridConst.weightx = 1.0;
        gridConst.weighty = 0.1;
        gridConst.ipadx = 0;
        gridConst.ipady = 0;
        gridConst.fill = GridBagConstraints.CENTER;
        gridConst.insets = new Insets(50, 50, 25, 50);
        add(panel2, gridConst);;

        new WindowClosingFrameEvent(this);
        // setUndecorated(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
