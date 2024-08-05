package utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class useAlert {
    public void warringAlert(String message) {
        JOptionPane.showMessageDialog(null, message, "Warning", JOptionPane.WARNING_MESSAGE);

    }

    public void successAlert(String message) {
        try (InputStream is = useAlert.class.getClassLoader().getResourceAsStream("resource/images/success.png")) {
            if (is == null) {
                System.out.println("Image not found");

            } else {
                BufferedImage successImage = ImageIO.read(is);
                Image scaledImage = successImage.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
                ImageIcon successIcon = new ImageIcon(scaledImage);

                JOptionPane.showMessageDialog(null, message, "Success", JOptionPane.INFORMATION_MESSAGE, successIcon);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
