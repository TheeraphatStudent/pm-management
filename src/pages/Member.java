package pages;


import java.awt.Font;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

import javax.swing.JFrame;

import components.Devpanel;
import resource.environment.WindowClosingFrameEvent;
import resource.environment.WindowEntryScreen;

public class Member extends JFrame {
    public Member() {
        setTitle("Developer Member");
        setLocation((int) (Math.floor(new WindowEntryScreen().getWidthCenter() / 2.5)),
                (int) (Math.floor(new WindowEntryScreen().getHeight()) / 6));
        setSize((int) (Math.floor(new WindowEntryScreen().getWidth() / 1.4)),
                (int) (Math.floor(new WindowEntryScreen().getHeight() / 1.4)));
        setLayout(new GridLayout(1, 1));

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

        Devpanel dev = new Devpanel(100, 200, 100, 100, "Theeraphat Chueanokkhum", "66011212103");
        Devpanel dev2 = new Devpanel(100, 100, 100, 200, "Boonnisa Pitchawong", "66011212184");

        new WindowClosingFrameEvent(this, new EntryPage());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        add(dev);
        add(dev2);
    }

}
