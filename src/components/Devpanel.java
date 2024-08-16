package components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridLayout;

// Swing
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JButton;

import resource.colors.MainColor;
import utils.useButton;

public class Devpanel extends JPanel {
    public Devpanel() {
    }

    public Devpanel(String name, String id, String imgName) {
        setLayout(new GridBagLayout());
        GridBagConstraints gridConst = new GridBagConstraints();
        setPreferredSize(new Dimension(500, 700));
        setLayout(new GridBagLayout());

        JPanel head = new JPanel();
        head.setBackground(MainColor.trinary());

        JPanel panelbox2 = new JPanel();
        panelbox2.setBackground(MainColor.trinary());
        panelbox2.setLayout(new GridBagLayout());

        JButton createImg = new useButton().createButton(imgName, "", 225, 225, "-");
        head.add(createImg);

        JTextPane textlabel = new Devpanel().createTextPane("Name: ");
        JTextPane textname = new Devpanel().createTextPane(name);
        JPanel namepanel = new Devpanel().createTextPaneWithLabel(textlabel, textname, null);

        JTextPane textlabelid = new Devpanel().createTextPane("Id: ");
        JTextPane textid = new Devpanel().createTextPane(id);
        JPanel idpanel = new Devpanel().createTextPaneWithLabel(textlabelid, textid, null);

        JPanel compresspanel = new JPanel(new GridLayout(2, 1, 50, 20));
        compresspanel.setBackground(MainColor.primary());
        compresspanel.add(namepanel);
        compresspanel.add(idpanel);

        gridConst.gridy = 1;
        gridConst.weightx = 1.0;
        gridConst.weighty = 1.0;
        gridConst.fill = GridBagConstraints.BOTH;
        gridConst.insets = new Insets(10, 20, 20, 20);
        panelbox2.add(compresspanel, gridConst);

        gridConst.gridy = 0;
        gridConst.weightx = 1.0;
        gridConst.weighty = 1.0;
        gridConst.fill = GridBagConstraints.BOTH;
        gridConst.insets = new Insets(20, 20, 10, 20);
        panelbox2.add(head, gridConst);

        gridConst.gridx = 0;
        gridConst.gridy = 0;
        gridConst.weightx = 1.0;
        gridConst.weighty = 0.7;
        gridConst.fill = GridBagConstraints.BOTH;
        add(panelbox2, gridConst);
    }

    private JTextPane createTextPane(String text) {
        JTextPane textPane = new JTextPane();

        textPane.setFont(new Font("Arial", Font.PLAIN, 20));
        textPane.setText(text);
        textPane.setEditable(false);
        textPane.setBackground(null);

        return textPane;
    }

    private JPanel createTextPaneWithLabel(JTextPane label, JTextPane content, Color color) {
        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.setMinimumSize(new Dimension(225, 60));
        panel.setBackground(color);
      
        panel.add(label);
        panel.add(content);

        return panel;

    }
}
