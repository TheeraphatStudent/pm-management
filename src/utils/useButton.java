package utils;

// Swing
import javax.swing.JButton;

// AWT
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Cursor;

// Img
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import components.Footer;

public class useButton {
    public JButton createButton(String btnCase, String title, Color bgColor, int width, int height) {
        JButton btn = new JButton(title);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        // btn.setContentAreaFilled(false);
        btn.setBackground(bgColor);
        btn.setPreferredSize(new Dimension(width, height));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Set Icon
        String icon = "";

        if (btnCase.equals("rain")) {
            icon = "cloudy.png";

        } else if (btnCase.equals("air")) {
            icon = "airplane.png";

        } else {
            icon = null;

        }

        try (InputStream is = Footer.class.getClassLoader().getResourceAsStream("resource/images/" + icon)) {
            if (is == null) {
                System.out.println("Image not found");
            } else {
                BufferedImage iconImage = ImageIO.read(is);
                int buttonWidth = btn.getPreferredSize().width;
                int buttonHeight = btn.getPreferredSize().height;
                Image resizedImage = iconImage.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
                btn.setIcon(new ImageIcon(resizedImage));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return btn;
    }
}
