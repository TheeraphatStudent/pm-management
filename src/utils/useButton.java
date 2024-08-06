package utils;

import pages.EntryPage;
import pages.Member;
import pages.Page;
import resource.environment.WindowClosingFrameEvent;

// Swing
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

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

        } else if (btnCase != "") {
            icon += btnCase + ".png";

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

    public JButton createButton(String btnCase, String title, int width, int height, String cursorCase) {
        JButton btn = new JButton(title);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setContentAreaFilled(false);

        btn.setPreferredSize(new Dimension(width, height));

        switch (cursorCase) {
            case "hand":
                btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                break;

            default:
                btn.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                break;
        }

        // Set Icon
        String icon = "";

        if (btnCase.equals("rain")) {
            icon = "cloudy.png";

        } else if (btnCase.equals("air")) {
            icon = "airplane.png";

        } else if (btnCase != "") {
            icon += btnCase + ".png";
            System.out.println(icon);

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

    public JButton createButton(
            String btnCase,
            String title,
            Color color,
            int width,
            int height,
            String cursorCase,
            JFrame thispage,
            String destpage) {
        JButton btn = new JButton(title);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setContentAreaFilled(true);
        btn.setBackground(color);

        btn.setPreferredSize(new Dimension(width, height));

        switch (cursorCase) {
            case "hand":
                btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                break;

            default:
                btn.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                break;
        }

        // Set Icon
        String icon = "";

        if (btnCase.equals("rain")) {
            icon = "cloudy.png";

        } else if (btnCase.equals("air")) {
            icon = "airplane.png";

        } else if (btnCase != "") {
            icon += btnCase + ".png";

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

        btn.addActionListener((e -> {
            if (destpage.equals("page")) {
                JFrame page = new Page();
                new WindowClosingFrameEvent(thispage, page);
                page.setVisible(true);
                thispage.dispose();
            } else if (destpage.equals("member")) {
                JFrame member = new Member();
                new WindowClosingFrameEvent(thispage, member);
                member.setVisible(true);
                thispage.dispose();
            } else if (destpage.equals("entry")) {
                JFrame entry = new EntryPage();
                new WindowClosingFrameEvent(thispage, entry);
                entry.setVisible(true);
                thispage.dispose();
            } else if (destpage.equals("exit")) {
                new WindowClosingFrameEvent().windowClosingFrameEvent(thispage);
            } else {
                System.out.println("Page not found");
            }
        }));

        return btn;
    }

}
