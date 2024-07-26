package components;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.IOException;
import java.io.InputStream;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;

import resource.colors.MainColor;


public class Statistic {
    JPanel panel = new JPanel(new GridBagLayout());
    JPanel toolPanel = new JPanel(new GridLayout(4, 1, 20, 20));
    JPanel statisticPanel = new JPanel(new GridLayout(5, 1, 50, 20));

    public JPanel getStatistic() {
        GridBagConstraints gridConst = new GridBagConstraints();
        GridBagConstraints gridConstColorPanel = new GridBagConstraints();
        gridConstColorPanel.anchor = GridBagConstraints.CENTER;

        panel.setBackground(MainColor.secondary());
        toolPanel.setBackground(MainColor.secondary().darker());
        statisticPanel.setBackground(MainColor.secondary().brighter().brighter());

        JTextPane textPaneRed = new Statistic().createTextPane("Patient of 30%", MainColor.access("red"));
        JTextPane textPaneOrange = new Statistic().createTextPane("Patient of 20 - 29%", MainColor.access("orange"));
        JTextPane textPaneYellow = new Statistic().createTextPane("Patient of 10 - 19%", MainColor.access("yellow"));
        JTextPane textPaneGreen = new Statistic().createTextPane("Patient of 0 - 9%", MainColor.access("green"));

        // Tool Panel Content
        JPanel colorPanelRed = new Statistic().createPanel(MainColor.access("red"));
        colorPanelRed.add(textPaneRed, gridConstColorPanel);
        toolPanel.add(colorPanelRed);

        JPanel colorPanelOrange = new Statistic().createPanel(MainColor.access("orange"));
        colorPanelOrange.add(textPaneOrange, gridConstColorPanel);
        toolPanel.add(colorPanelOrange);

        JPanel colorPanelYellow = new Statistic().createPanel(MainColor.access("yellow"));
        colorPanelYellow.add(textPaneYellow, gridConstColorPanel);
        toolPanel.add(colorPanelYellow);

        JPanel colorPanelGreen = new Statistic().createPanel(MainColor.access("green"));
        colorPanelGreen.add(textPaneGreen, gridConstColorPanel);
        toolPanel.add(colorPanelGreen);

        // Statistic Panel
        /*
         * Peoples -> 5000
         * Dust -> 51
         * Patient -> 600
         * Patient rate -> 12%
         * Health -> 5400
         */

        JPanel peopleStatistic = new Statistic().createStatisticPanel("Peoples", 0);
        statisticPanel.add(peopleStatistic);

        JPanel dustStatistic = new Statistic().createStatisticPanel("Dust", 0);
        statisticPanel.add(dustStatistic);

        JPanel patent = new Statistic().createStatisticPanel("Patent", 0);
        statisticPanel.add(patent);

        JPanel patentRateStatistic = new Statistic().createStatisticPanel("Patent rate", 0);
        statisticPanel.add(patentRateStatistic);

        JPanel healthStatistic = new Statistic().createStatisticPanel("Health", 0);
        statisticPanel.add(healthStatistic);

        gridConst.weightx = 0.4;
        gridConst.weighty = 1;
        gridConst.fill = GridBagConstraints.BOTH;
        // gridConst.anchor = GridBagConstraints.CENTER;
        gridConst.insets = new Insets(20, 20, 20, 0);
        panel.add(toolPanel, gridConst);

        gridConst.weightx = 1.6;
        gridConst.weighty = 1;
        gridConst.fill = GridBagConstraints.BOTH;
        gridConst.insets = new Insets(20, 20, 20, 20);
        panel.add(statisticPanel, gridConst);

        return this.panel;

    }

    private JTextPane createTextPane(String text, Color color) {
        JTextPane textPane = new JTextPane();
        SimpleAttributeSet attributes = new SimpleAttributeSet();
        textPane.setCharacterAttributes(attributes, true);
        InputStream is = Statistic.class.getResourceAsStream("resource/font/KhaoklongThin.ttf");
        try {
            textPane.setFont(Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.PLAIN, 60));
        } catch (FontFormatException | IOException e) {
            // e.printStackTrace();
        }

        textPane.setFont(new Font("Arial", Font.PLAIN, 20));
        textPane.setText(text);
        textPane.setEditable(false);
        if (color != null) {
            textPane.setBackground(color);

        } else {
            textPane.setBackground(null);

        }
        return textPane;
    }

    private JPanel createPanel(Color color) {
        JPanel colorPanel = new JPanel(new GridBagLayout());
        colorPanel.setBackground(color);

        return colorPanel;

    }

    private JPanel createStatisticPanel(String title, int value) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gridConst = new GridBagConstraints();

        JTextPane titlePane = createTextPane(title, null);
        JTextPane valuePane = createTextPane(String.valueOf(value), null);

        gridConst.weightx = 0.5;
        gridConst.gridx = 0;
        panel.add(titlePane, gridConst);

        gridConst.weightx = 0.5;
        gridConst.gridx = 1;
        panel.add(valuePane, gridConst);

        return panel;

    }
}
