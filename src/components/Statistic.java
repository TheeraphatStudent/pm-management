package components;

import java.awt.GridLayout;

import javax.swing.JPanel;
import java.awt.Color;

public class Statistic {
    JPanel panel = new JPanel(new GridLayout(1, 2, 10, 10));

    public JPanel getStatistic() {
        panel.setToolTipText("Hello World");
        panel.setBackground(Color.getHSBColor(255, 0, 0));

        return this.panel;

    }
}
