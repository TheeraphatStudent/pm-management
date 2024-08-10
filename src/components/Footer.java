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
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;

// - Event
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

// nio
import java.nio.file.Path;
import java.nio.file.Paths;

// Img
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

import pages.Page;

// Resource
import resource.colors.MainColor;
import resource.environment.WindowEntryScreen;

import utils.useAlert;
import utils.useButton;

public class Footer extends JPanel {
    private int minrange = 0;
    private int maxrange = 0;
    private boolean israndomrangechicked = false;
    private String filePath = "";
    private Page parentPage;

    private boolean artificialRainState = false;

    public Footer() {
    }

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
            // chooserFile.setControlButtonsAreShown(true);

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
                this.parentPage.setFilePathInDashboard(this.filePath, false);
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
        JDialog dialogRange = new Footer().createModal(new GridBagLayout(), MainColor.secondary(), 500, 250,
                "Random Peoples Range");
        JPanel randomAction = createPanel(new GridLayout(1, 2, 20, 20), MainColor.primary());
        JButton randomRange = new useButton().createButton("-", "Random Range", MainColor.trinary(), 100, 20);
        JButton random = new useButton().createButton("-", "Random", MainColor.trinary(), 100, 20);

        randomRange.addActionListener((e -> {
            dialogRange.setVisible(true);

        }));

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
        JButton rainSimulate = new useButton().createButton("rain", "", MainColor.trinary(), 60, 60);

        rainSimulate.addActionListener((e -> {
            this.parentPage.reduceDustActions("all", false);

        }));

        // Artificial Rain
        JButton artificialRainSimulate = new useButton().createButton("air", "", MainColor.trinary(), 60, 60);

        artificialRainSimulate.addActionListener((e -> {
            if (!artificialRainState) {
                artificialRainSimulate.setBackground(MainColor.access("orange"));
                this.parentPage.reduceDustActions("area", true);

            } else {
                artificialRainSimulate.setBackground(MainColor.trinary());
                this.parentPage.reduceDustActions("area", false);

            }

            this.artificialRainState = !this.artificialRainState;

        }));

        // Back
        JButton back = new useButton().createButton("-", "Back", MainColor.access("red"), 0, 0, "hand", parentPage,
                "entry");

        gridConst.insets = new Insets(20, 20, 20, 10);
        gridConst.weightx = 0.3;
        gridConst.weighty = 1;
        gridConst.gridx = 0;
        gridConst.fill = GridBagConstraints.BOTH;
        rainPanel.add(rainSimulate, gridConst);

        gridConst.insets = new Insets(20, 10, 20, 20);
        gridConst.weightx = 0.3;
        gridConst.weighty = 1;
        gridConst.gridx = 1;
        gridConst.fill = GridBagConstraints.BOTH;
        rainPanel.add(artificialRainSimulate, gridConst);

        gridConst.insets = new Insets(20, 0, 20, 20);
        gridConst.weightx = 0.3;
        gridConst.weighty = 1;
        gridConst.gridx = 2;
        gridConst.fill = GridBagConstraints.BOTH;
        rainPanel.add(back, gridConst);

        add(rainPanel);
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

    private JPanel createrangePanel(Color bgColor, String title, boolean ismaxrange) {
        JPanel panel = new JPanel();
        JTextPane textPane = new JTextPane();
        JTextField textField = new JTextField();
        GridBagConstraints gridConst = new GridBagConstraints();

        textPane.setText(title);
        textPane.setEditable(false);
        textPane.setBackground(bgColor);

        textField.setSize(100, 50);
        textField.setText("0");

        if (israndomrangechicked) {
            if (ismaxrange) {
                this.maxrange = Integer.parseInt(textField.getText());
            } else {
                this.minrange = Integer.parseInt(textField.getText());
            }
        }

        panel.setLayout(new GridBagLayout());
        panel.setBackground(bgColor);

        gridConst.weightx = 1;
        gridConst.weighty = 0;
        gridConst.gridx = 0;
        gridConst.gridy = 0;
        gridConst.fill = GridBagConstraints.BOTH;
        gridConst.insets = new Insets(10, 10, 0, 10);
        panel.add(textPane, gridConst);

        gridConst.weightx = 1;
        gridConst.weighty = 1;
        gridConst.gridx = 0;
        gridConst.gridy = 1;
        gridConst.fill = GridBagConstraints.BOTH;
        gridConst.insets = new Insets(10, 10, 10, 10);
        panel.add(textField, gridConst);

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

    private JDialog createModal(LayoutManager layout, Color color, int width, int height, String title) {
        GridBagConstraints gridConst = new GridBagConstraints();

        JDialog dialog = new JDialog();

        // Random action
        JButton random = new useButton().createButton("-", "Random", MainColor.trinary(), 100, 20);

        // Panel range
        JPanel rangePanel = createPanel(new GridBagLayout(), MainColor.secondary());
        JPanel startPanel = createrangePanel(MainColor.primary(), "Min Peoples Range", false);
        JPanel stopPanel = createrangePanel(MainColor.primary(), "Max Peoples Range", true);

        dialog.setLayout(layout);
        dialog.setSize(width, height);
        dialog.getContentPane().setBackground(color);
        dialog.setLocation(new WindowEntryScreen().getWidthCenter(), new WindowEntryScreen().getHeightCenter());
        dialog.setTitle(title);

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

        // เมื่อเปลี่ยนที่สนใจอย่างอื่น จะทำให้ Dialog ปิดลง
        dialog.addWindowFocusListener(new WindowFocusListener() {

            @Override
            public void windowGainedFocus(WindowEvent e) {
                System.out.println("On focus!");
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                System.out.println("Not focus!");
                dialog.dispose();
                dialog.setLocation(new WindowEntryScreen().getWidthCenter(), new WindowEntryScreen().getHeightCenter());
            }
        });

        random.addActionListener((e -> {
            this.israndomrangechicked = true;

            Component startComponent = startPanel.getComponent(1);
            // System.out.println("Get Min Panel: " + startComponent);

            // instanceof คือการเช็ค Component ที่อยู่ภายใน Compoonent นั้นๆ
            // เช่น startComponent คือ Panel ที่มี Component ย่อยคือ 
            /*
             * JPanel panel = new JPanel();
             * JTextPane textPane = new JTextPane();
             * JTextField textField = new JTextField();
             */
            // จากการใช้ startComponent instanceof JTextField คือการเช็คว่ามี JTextField รึเปล่า
            // ถ้ามี startComponent จะกลายเป็น ref ของ component ที่เช็ค
            // สามารถเรียกใช้งาน Method ต่างๆได้เหมือนกับ Component นั้นๆได้
            if (startComponent instanceof JTextField) {
                JTextField startField = (JTextField) startComponent;
                this.minrange = Integer.parseInt(startField.getText());
            }

            Component stopComponent = stopPanel.getComponent(1);
            // System.out.println("Get Max Panel: " + stopComponent);

            if (stopComponent instanceof JTextField) {
                JTextField stopField = (JTextField) stopComponent;
                this.maxrange = Integer.parseInt(stopField.getText());
            }

            
            if(this.minrange>this.maxrange){
                dialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
                new useAlert().warringAlert("Min must be lessest Max people range");
            }
            else{
                System.out.println("Min Range: " + this.minrange);
                System.out.println("Max Range: " + this.maxrange);

                // this.parentPage.setrandomrange(this.minrange, this.maxrange);

                dialog.dispose();
            }
            // new useAlert().successAlert("Random People is \"" + 0 + "\"");
        }));

        gridConst.weightx = 1;
        gridConst.weighty = 0.35;
        gridConst.fill = GridBagConstraints.BOTH;
        gridConst.insets = new Insets(20, 20, 20, 10);
        rangePanel.add(startPanel, gridConst);

        gridConst.weightx = 1;
        gridConst.weighty = 0.35;
        gridConst.fill = GridBagConstraints.BOTH;
        gridConst.insets = new Insets(20, 10, 20, 20);
        rangePanel.add(stopPanel, gridConst);

        gridConst.gridy = 0;
        gridConst.insets = new Insets(10, 10, 5, 10);
        dialog.add(rangePanel, gridConst);

        gridConst.gridy = 1;
        gridConst.insets = new Insets(5, 10, 10, 10);
        dialog.add(random, gridConst);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        return dialog;
    }

}
