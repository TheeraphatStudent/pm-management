package pages;

import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import components.Devpanel;
import resource.colors.MainColor;
import resource.environment.WindowClosingFrameEvent;
import resource.environment.WindowEntryScreen;

import utils.useButton;

public class Member extends JFrame {
    public Member() {
        setTitle("Developer Member");
        setLayout(new GridBagLayout());
        setLocation((int) (Math.floor(new WindowEntryScreen().getWidthCenter() / 2.5)),
                (int) (Math.floor(new WindowEntryScreen().getHeight()) / 6));
        setSize((int) (Math.floor(new WindowEntryScreen().getWidth() / 1.4)),
                (int) (Math.floor(new WindowEntryScreen().getHeight() / 1.4)));
        setMinimumSize(new Dimension(850, 550));
        getContentPane().setBackground(MainColor.secondary());

        try (InputStream is = Page.class.getClassLoader().getResourceAsStream("resource/images/dev.png")) {
            if (is == null) {
                System.out.println("Image not found");
            } else {
                BufferedImage iconImage = ImageIO.read(is);
                setIconImage(iconImage);
                System.out.println(iconImage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel devContain = new JPanel(new GridBagLayout());
        devContain.setBackground(null);

        GridBagConstraints gridConst = new GridBagConstraints();

        JPanel dev = new Devpanel(0, 0, 0, 0, "Theeraphat Chueanokkhum", "66011212103", "theeraphat");
        JPanel dev2 = new Devpanel(0, 0, 0, 0, "Boonnisa Pitchawong", "66011212184", "boonisa");
        JButton back = new useButton().createButton("-", "<< Roll Back", MainColor.access("red"), 0, 0, "hand", this, "entry");

        gridConst.insets = new Insets(50, 50, 50, 25);
        devContain.add(dev, gridConst);

        gridConst.insets = new Insets(50, 25, 50, 50);
        devContain.add(dev2, gridConst);

        gridConst.gridx = 0;
        gridConst.gridy = 0;
        gridConst.insets = new Insets(0, 0, 0, 0);
        add(devContain, gridConst);

        gridConst.gridy = 1;
        gridConst.weightx = 1;
        gridConst.weighty = 1;
        gridConst.fill = GridBagConstraints.BOTH;
        add(back, gridConst);

        new WindowClosingFrameEvent(this, new EntryPage());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

}
