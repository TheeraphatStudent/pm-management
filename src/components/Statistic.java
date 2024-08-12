package components;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JTextPane;

import resource.colors.MainColor;

import utils.usePanel;
import utils.useTextPane;

interface StatisticProps {
    public JPanel panel = new JPanel(new GridBagLayout());
    public JPanel toolPanel = new JPanel(new GridLayout(5, 1, 20, 20));
    public JPanel statisticPanel = new JPanel(new GridLayout(5, 1, 50, 20));

}

public class Statistic implements StatisticProps {
    public int sPeople = 5000;
    public int sDust = 0;
    public int sPatent = 0;
    public int sPatentRate = 0;
    public int sHealth = 0;

    public JPanel getStatistic() {
        updateStatistics();

        return panel;

    }

    private void updateStatistics() {
        reloadContent();

        Color bgContent = MainColor.getOriginalColor(this.sDust);

        panel.setBackground(bgContent);
        toolPanel.setBackground(bgContent);
        statisticPanel.setBackground(bgContent);

        GridBagConstraints gridConst = new GridBagConstraints();
        GridBagConstraints gridConstColorPanel = new GridBagConstraints();
        gridConstColorPanel.anchor = GridBagConstraints.CENTER;

        // Tool Panel Content

        /*
         * LinkedHashMap * เมื่อใช้ Loop จะเรียงละดับแบบ FCFS: เข้าก่อน ออกก่อน
         * 1. Before -> Key, Value -> After
         * 2. Before -> Key, Value -> After
         * n. ...
         */
        LinkedHashMap<String, String> toolsContent = new LinkedHashMap<String, String>();
        toolsContent.put("Patient of 30%", "red");
        toolsContent.put("Patient of 20 - 29%", "orange");
        toolsContent.put("Patient of 10 - 19%", "yellow");
        toolsContent.put("Patient of 0 - 9%", "green");

        JPanel statisticImage = new usePanel().createPanelImage(getStatisticImage(this.sDust), bgContent.darker());
        toolPanel.add(statisticImage);

        for (Map.Entry<String, String> entry : toolsContent.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            System.out.println(key);

            JTextPane statisticPane = new useTextPane().createSimpleTextPane(key, MainColor.access(value));
            JPanel colorPanel = new usePanel().createSimplePanel(MainColor.access(value));

            colorPanel.add(statisticPane, gridConstColorPanel);
            toolPanel.add(colorPanel);
        }

        // Statistic Panel
        /*
         * Peoples -> 5000
         * Dust -> 51
         * Patient -> 600
         * Patient rate -> 12%
         * Health -> 5400
         */

        LinkedHashMap<String, Integer> statisticContent = new LinkedHashMap<String, Integer>();
        statisticContent.put("People", this.sPeople);
        statisticContent.put("Dust", this.sDust);
        statisticContent.put("Patent", this.sPatent);
        statisticContent.put("Patent Rate", this.sPatentRate);
        statisticContent.put("Health", this.sHealth);

        for (Map.Entry<String, Integer> entry : statisticContent.entrySet()) {
            String key = entry.getKey();
            int value = (int) (entry.getValue());

            JPanel statistic = new usePanel().createStatisticPanel(key, value);
            statisticPanel.add(statistic);

        }

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

    }

    private void reloadContent() {
        panel.removeAll();
        toolPanel.removeAll();
        statisticPanel.removeAll();

        panel.revalidate();
        panel.repaint();

    }

    private String getStatisticImage(int dust) {
        String result = "health";

        if (dust <= 50) {
            return result += 1;
        } else if (dust <= 100) {
            return result += 2;
        } else if (dust <= 150) {
            return result += 3;
        } else {
            return result += 4;
        }

    }

    // Public
    public void setStatistic(int dust, int patentRate) {
        int patent = ((this.sPeople * patentRate) / 100);

        // this.sPeople
        this.sDust = dust;
        this.sPatent = patent;
        this.sPatentRate = patentRate;
        this.sHealth = this.sPeople - patent;

        updateStatistics();

    }

    public void resetStatistic() {
        // this.sPeople = 0;
        this.sDust = 0;
        this.sPatent = 0;
        this.sPatentRate = 0;
        this.sHealth = 0;

        updateStatistics();

    }
}
