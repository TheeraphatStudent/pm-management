package components;

import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;


public class Footer {
    public Footer(Color getColor) {
        this.bgColor = getColor.brighter();

    }

    private Color bgColor = new Color(0);
    private JPanel panel = new JPanel(new GridLayout(0, 3,10,10));

    public JPanel getFooter() {
        panel.setBackground(bgColor);

        JButton btn = new JButton("Hello World");
        JButton btn2 = new JButton("Hello Human");
        JButton btn3 = new JButton("Hello rain");
        btn3.setSize(50, 50);
        JButton btn4 = new JButton("Hello ");
        JButton btn5 = new JButton("Hello file ");
        JButton btn6 = new JButton("Helloll");
        JButton btn7 = new JButton("Hello stop");
        JTextField t1 = new JTextField();
        JTextField t2 = new JTextField();
       
        panel.add(t1);
        panel.add(t2);
        panel.add(btn);
        panel.add(btn2);
        panel.add(btn3);
        panel.add(btn4);
        panel.add(btn5);
        panel.add(btn6);
        panel.add(btn7);

        return this.panel;

    }
}
