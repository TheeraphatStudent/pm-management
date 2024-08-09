package utils;

import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;

import java.io.InputStream;
import java.io.IOException;
import java.awt.FontFormatException;
import java.awt.Font;
import java.awt.Color;

public class useTextPane {
    public JTextPane createSimpleTextPane(String text, Color color) {
        JTextPane textPane = new JTextPane();
        SimpleAttributeSet attributes = new SimpleAttributeSet();
        textPane.setCharacterAttributes(attributes, true);
        InputStream is = useTextPane.class.getResourceAsStream("resource/font/KhaoklongThin.ttf");
        try {
            textPane.setFont(Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.PLAIN, 60));
        } catch (FontFormatException | IOException e) {
            // e.printStackTrace();
        }

        textPane.setFont(new Font("Arial", Font.PLAIN, 20));
        textPane.setText(text);
        textPane.setEditable(false);

        Color setColor = (color != null) ? color : null;
        textPane.setBackground(setColor);

        return textPane;
    }
}
