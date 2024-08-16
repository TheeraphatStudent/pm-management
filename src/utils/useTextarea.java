package utils;

import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.BorderFactory;

import java.awt.Font;
import java.awt.Color;

public class useTextarea {
    public JTextPane createSimpleTextPane(String text, Color color) {
        JTextPane textPane = new JTextPane();
        textPane.setFont(new Font("Arial", Font.PLAIN, 20));
        textPane.setText(text);
        textPane.setEditable(false);

        Color setColor = (color != null) ? color : null;
        textPane.setBackground(setColor);

        return textPane;
    }

    public JTextField createTextField(String text, Color color, int fontSize, boolean isEditable) {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, fontSize));
        textField.setText(text);
        textField.setEditable(isEditable);
        textField.setBorder(
                BorderFactory.createBevelBorder(BevelBorder.RAISED, color.darker(), color.darker().darker()));

        if (color != null) {
            textField.setBackground(color.brighter());

        }
        return textField;
    }
}
