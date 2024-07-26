package components;

// Swing
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JDialog;

// Awt
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.nio.file.Path;
import java.nio.file.Paths;

// Img
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import pages.EntryPage;
import pages.Page;

// Resource
import resource.colors.MainColor;
import resource.environment.WindowClosingFrameEvent;
import resource.environment.WindowEntryScreen;

import utils.useButton;

public class Footer extends JPanel {
    private String filePath = "";
    private Page parentPage;

    public Footer() {}

    public Footer(Color getColor, Page parentPage) {
        this.parentPage = parentPage;
        setBackground(getColor.brighter());
        setLayout(new GridLayout(1, 4, 20, 0));

        // File Panel
        JPanel filePanel = createPanel(new GridBagLayout(), MainColor.primary());
        filePanel.setBackground(MainColor.primary());

        GridBagConstraints gridConst = new GridBagConstraints();
        gridConst.fill = GridBagConstraints.BOTH;

        JButton fileBtn = new useButton().createButton("-", "Select File", MainColor.trinary(), 100, 50);
        fileBtn.setToolTipText("Select dust .txt file");
        // fileBtn.setFocusPainted(false);
        // fileBtn.setContentAreaFilled(true);
        // fileBtn.setOpaque(true);

        JTextField fileSelected = new Footer().createTextField("Select File First!", MainColor.primary(), 12);

        fileBtn.addActionListener((e -> {
            String currentDir = System.getProperty("user.dir");
            Path destDir = Paths.get(currentDir).getParent().getParent();

            JFileChooser chooserFile = new JFileChooser(String.valueOf(destDir));
            FileNameExtensionFilter restrict = new FileNameExtensionFilter(".txt", "txt");
            chooserFile.setFileFilter(restrict);
            chooserFile.setDialogTitle("Select a dust file!");
            chooserFile.setControlButtonsAreShown(false);

            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // อ่าน File ที่ Resource โดยจะเข้าถึงผ่าน class
            try (InputStream is = Footer.class.getClassLoader().getResourceAsStream("resource/images/file.png")) {
                if (is == null) {
                    System.out.println("Image not found");
                } else {
                    BufferedImage iconImage = ImageIO.read(is);
                    frame.setIconImage(iconImage);
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }

            int content = chooserFile.showSaveDialog(frame);

            if (content == JFileChooser.APPROVE_OPTION) {
                this.filePath = chooserFile.getSelectedFile().getAbsolutePath().replace("\\", "/");
                fileSelected.setText(filePath);
                System.out.println("Chosen File: " + this.filePath);

                // ส่งค่ากลับไปที่ Parent Component
                this.parentPage.setFilePathInDashboard(this.filePath);
            }

            frame.dispose();
        }));

        gridConst.weightx = 0;
        gridConst.weighty = 1;
        gridConst.insets = new Insets(20, 20, 20, 20);
        filePanel.add(fileBtn, gridConst);

        gridConst.weightx = 1;
        gridConst.weighty = 1;
        gridConst.insets = new Insets(20, 0, 20, 20);
        filePanel.add(fileSelected, gridConst);
        add(filePanel);

        // Random Panel
        JPanel randomPanel = createPanel(new GridBagLayout(), MainColor.primary());

        // Action
        JPanel randomAction = createPanel(new GridLayout(1, 2, 20, 20), MainColor.primary());
        JButton randomRange = new useButton().createButton("-", "Random Range", MainColor.trinary(), 100, 20);
        JDialog dialogRange = new Footer().createDialog(new GridBagLayout(), 500, 500, "Random Peoples Range");

        randomRange.addActionListener((e -> {
            dialogRange.setVisible(true);
            dialogRange.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        }));

        JButton random = new useButton().createButton("-", "Random", MainColor.trinary(), 100, 20);

        randomPanel.setBackground(MainColor.primary());
        randomAction.setBackground(MainColor.primary());

        randomAction.add(randomRange);
        randomAction.add(random);

        JTextField randomValue = new Footer().createTextField("0", MainColor.primary(), 12);

        gridConst.insets = new Insets(20, 20, 20, 20);
        gridConst.weightx = 1;
        gridConst.weighty = 1;
        gridConst.gridx = 0;
        gridConst.gridy = 0;
        gridConst.fill = GridBagConstraints.BOTH;
        randomPanel.add(randomValue, gridConst);

        gridConst.insets = new Insets(0, 20, 20, 20);
        gridConst.weightx = 1;
        gridConst.weighty = 1;
        gridConst.gridx = 0;
        gridConst.gridy = 1;
        gridConst.fill = GridBagConstraints.BOTH;
        randomPanel.add(randomAction, gridConst);

        add(randomPanel);

        // Rain Panel
        JPanel rainPanel = new Footer().createPanel(new GridBagLayout(), MainColor.primary());
        JButton rainSimulate = new useButton().createButton("rain", "", MainColor.trinary(), 80, 80);
        JButton artificialRainSimulate = new useButton().createButton("air", "", MainColor.trinary(), 80, 80);

        gridConst.insets = new Insets(20, 20, 20, 10);
        gridConst.weightx = 0.5;
        gridConst.weighty = 1;
        gridConst.gridx = 0;
        gridConst.gridy = 0;
        gridConst.fill = GridBagConstraints.BOTH;
        rainPanel.add(rainSimulate, gridConst);

        gridConst.insets = new Insets(20, 10, 20, 20);
        gridConst.weightx = 0.5;
        gridConst.weighty = 1;
        gridConst.gridx = 1;
        gridConst.gridy = 0;
        gridConst.fill = GridBagConstraints.BOTH;
        rainPanel.add(artificialRainSimulate, gridConst);

        add(rainPanel);

        // Start
        // JButton
    }

    public String getFile() {
        return this.filePath;
    }

    private JPanel createPanel(LayoutManager getLayout, Color bgColor) {
        JPanel panel = new JPanel();
        panel.setLayout(getLayout);
        panel.setBackground(bgColor);
        // panel.setCursor(Cursor.HAND_CURSOR);

        return panel;
    }

    private JPanel createPanel(LayoutManager getLayout, Color bgColor, String title) {
        JPanel panel = new JPanel();
        JTextPane textPane = new JTextPane();
        textPane.setText(title);

        panel.setLayout(getLayout);
        panel.setBackground(bgColor);
        // panel.setCursor(Cursor.HAND_CURSOR);

        panel.add(textPane);

        return panel;
    }

    private JTextField createTextField(String text, Color color, int fontSize) {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, fontSize));
        textField.setText(text);
        textField.setEditable(false);
        textField.setBorder(
                BorderFactory.createBevelBorder(BevelBorder.RAISED, color.darker(), color.darker().darker()));

        if (color != null) {
            textField.setBackground(color.brighter());

        }
        return textField;
    }

    private JDialog createDialog(LayoutManager layout, int width, int height, String title) {
        GridBagConstraints gridConst = new GridBagConstraints();

        JDialog dialog = new JDialog();
        JPanel startPanel = new JPanel(new GridBagLayout());

        dialog.setSize(width, height);
        dialog.setLocation(new WindowEntryScreen().getWidthCenter(), new WindowEntryScreen().getHeightCenter());
        dialog.setTitle(title);

        // ถ้าไม่ Focus Dialog, จะถูกปิดอัตโนมัติ
        try (InputStream is = Footer.class.getClassLoader().getResourceAsStream("resource/images/random.png")) {
            if (is == null) {
                System.out.println("Image not found");
            } else {
                BufferedImage iconImage = ImageIO.read(is);
                dialog.setIconImage(iconImage);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        dialog.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                System.out.println("On focus!");

            }

            @Override
            public void focusLost(FocusEvent e) {
                System.out.println("Not focus!");
                dialog.dispose();

            }
        });

        dialog.add(startPanel);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        return dialog;

    }
}
