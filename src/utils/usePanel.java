package utils;

import javax.swing.JPanel;
import javax.swing.JTextPane;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;

public class usePanel {
    public JPanel createSimplePanel(Color color) {
        JPanel colorPanel = new JPanel(new GridBagLayout());
        colorPanel.setBackground(color);

        return colorPanel;

    }

    public JPanel createSimplePanelWithLayout(LayoutManager getLayout ,Color color) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(color);
        panel.setLayout(getLayout);

        return panel;

    }

    public JPanel createStatisticPanel(String title, int value) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gridConst = new GridBagConstraints();

        JTextPane titlePane = new useTextarea().createSimpleTextPane(title, null);
        JTextPane valuePane = new useTextarea().createSimpleTextPane(String.valueOf(value), null);

        gridConst.insets = new Insets(0, 20, 0, 0);
        gridConst.gridx = 0;
        gridConst.gridy = 0;
        gridConst.weightx = 1;
        gridConst.anchor = GridBagConstraints.WEST;
        panel.add(titlePane, gridConst);

        gridConst.insets = new Insets(0, 0, 0, 20);
        gridConst.gridx = 1;
        gridConst.weightx = 1;
        gridConst.anchor = GridBagConstraints.EAST;
        // gridConst.fill = GridBagConstraints.NONE;
        panel.add(valuePane, gridConst);

        return panel;
    }

    public JPanel createPanelImage(String imgName, Color bg) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(bg);

        GridBagConstraints gridConst = new GridBagConstraints();
        gridConst.fill = GridBagConstraints.BOTH;

        panel.add(new useButton().createButton(imgName, "", 50, 50, ""), gridConst);

        return panel;
    }

}
