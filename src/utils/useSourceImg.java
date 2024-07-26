package utils;

import pages.Page;

// Swing
import javax.swing.JFrame;

// Img
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class useSourceImg {
    public JFrame getSourceImage(JFrame frame, String imgResource) {
        try (InputStream is = Page.class.getClassLoader().getResourceAsStream("resource/images/" + imgResource)) {
            if (is == null) {
                System.out.println("Image not found");
            } else {
                BufferedImage iconImage = ImageIO.read(is);
                frame.setIconImage(iconImage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return frame;
    }
}
