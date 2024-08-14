package components;

// Swing
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;
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
import java.lang.NumberFormatException;

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
import utils.usePanel;
import utils.useRandom;
import utils.useTextarea;

public class Footer extends JPanel {
    // Protected - สามารถทำให้ Class ที่อยูใน Package เดียวกันสามารถเข้าถึงได้
    protected String filePath = "";
    protected boolean isFileError = false;

    private int minrange = 0;
    private int maxrange = 0;
    private boolean israndomrangechicked = false;

    protected boolean artificialRainState = false;

    public Footer() {
    }

    public Footer(Color getColor, Page page) {
        setBackground(getColor.brighter());
        setLayout(new GridLayout(1, 4, 20, 0));

        FilePanel filePanel = new FilePanel(page);
        add(filePanel.getPanel());

        RandomPanel randomPanel = new RandomPanel(page);
        add(randomPanel.getPanel());

        RainPanel rainPanel = new RainPanel(page);
        add(rainPanel.getPanel());
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

    protected JDialog createRandomModal(LayoutManager layout, Color color, int width, int height, String title,
            Page page) {
        GridBagConstraints gridConst = new GridBagConstraints();

        JDialog dialog = new JDialog();

        // Random action
        JButton random = new useButton().createButton("-", "Random", MainColor.trinary(), 100, 20);

        // Panel range
        JPanel rangePanel = new usePanel().createSimplePanelWithLayout(new GridBagLayout(), MainColor.secondary());
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
            // เช่น startComponent คือ Panel ที่มี Component ย่อยคือ \
            /*
             * JPanel panel = new JPanel(); -> Component เป้าหมาย จึงไม่นับ
             * JTextPane textPane = new JTextPane(); -> 0
             * JTextField textField = new JTextField(); -> 1
             */
            // จากการใช้ startComponent instanceof JTextField คือการเช็คว่ามี JTextField
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

            if (this.minrange > this.maxrange) {
                dialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
                new useAlert().warringAlert("Min must be lessest Max people range");
            } else {
                System.out.println("Min Range: " + this.minrange);
                System.out.println("Max Range: " + this.maxrange);

                page.setrandomrange(this.minrange, this.maxrange);

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

    // >>>>>>>>>>>>>>>>>>>> Get File Feedback From Dashboard
    public void getFileFeedback(boolean feedback) {
        this.isFileError = feedback;

    }

}

class FilePanel extends Footer {
    private JPanel filePanel = new usePanel().createSimplePanelWithLayout(new GridBagLayout(), MainColor.primary());

    public FilePanel(Page parentPage) {
        // >>>>>>>>>>>>>>>>>>>> File Panel >>>>>>>>>>>>>>>>>>>>
        filePanel.setBackground(MainColor.primary());

        GridBagConstraints gridConst = new GridBagConstraints();
        gridConst.fill = GridBagConstraints.BOTH;

        JButton fileBtn = new useButton().createButton("-", "Select File", MainColor.trinary(), 100, 50);
        fileBtn.setToolTipText("Select dust .txt file");
        // fileBtn.setFocusPainted(false);
        // fileBtn.setContentAreaFilled(true);
        // fileBtn.setOpaque(true);

        JTextField fileSelected = new useTextarea().createTextField("Select File First!", MainColor.primary(), 12,
                false);

        fileBtn.addActionListener((e -> {
            String currentDir = System.getProperty("user.dir");

            // ขึ้นไปที่ Folder ก่อนหน้า 1 ระดับ
            Path destDir = Paths.get(currentDir).getParent();

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
                parentPage.setFilePathInDashboard(this.filePath, false);
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

    }

    public JPanel getPanel() {
        return this.filePanel;

    }

}

class RandomPanel extends Footer {
    private JPanel panel = new usePanel().createSimplePanelWithLayout(new GridBagLayout(), MainColor.primary());

    public RandomPanel(Page parentPage) {
        GridBagConstraints gridConst = new GridBagConstraints();

        // Random Value
        JTextField randomValue = new useTextarea().createTextField(String.valueOf(0),
                MainColor.primary(), 12, true);

        // Action
        JDialog dialogRange = new Footer().createRandomModal(new GridBagLayout(), MainColor.secondary(), 500, 250,
                "Random Peoples Range", parentPage);
        JPanel randomAction = new usePanel().createSimplePanelWithLayout(new GridLayout(1, 2, 20, 20),
                MainColor.primary());

        // Button
        JButton randomRange = new useButton().createButton("-", "Random Range", MainColor.trinary(), 100, 20);
        JButton random = new useButton().createButton("-", "Random", MainColor.trinary(), 100, 20);

        random.addActionListener(e -> {
            System.out.println("Random Work!");
            int randomPeoplePerArea = new useRandom().randomRange(0, 5000);
            System.out.println("Random People Per Area: " + randomPeoplePerArea);

            if (this.isFileError) {
                parentPage.setrandomrange(0, 0);

            } else {
                parentPage.setrandomrange(0, randomPeoplePerArea);

            }

            // Set new random value content
            randomValue.setText(String.valueOf(randomPeoplePerArea));
        });

        randomRange.addActionListener(e -> dialogRange.setVisible(true));

        panel.setBackground(MainColor.primary());
        randomAction.setBackground(MainColor.primary());

        randomAction.add(randomRange);
        randomAction.add(random);

        // >>>>>>>>>>>>>>>>>>>> Random value >>>>>>>>>>>>>>>>>>>>
        randomValue.addActionListener(e -> {
            String content = e.getActionCommand();

            try {
                int people = Integer.parseInt(content);
                System.out.println("Get Content: " + people);

                if (this.isFileError) {
                    parentPage.setrandomrange(0, 0);

                } else {
                    parentPage.setrandomrange(0, people);

                }

            } catch (NumberFormatException err) {
                // TODO: handle exception
                System.out.println(err);
            }
        });

        gridConst.insets = new Insets(20, 20, 20, 20);
        gridConst.weightx = 1;
        gridConst.weighty = 1;
        gridConst.gridx = 0;
        gridConst.gridy = 0;
        gridConst.fill = GridBagConstraints.BOTH;
        panel.add(randomValue, gridConst);

        gridConst.insets = new Insets(0, 20, 20, 20);
        gridConst.weightx = 1;
        gridConst.weighty = 1;
        gridConst.gridx = 0;
        gridConst.gridy = 1;
        gridConst.fill = GridBagConstraints.BOTH;
        panel.add(randomAction, gridConst);
    }

    public JPanel getPanel() {
        return this.panel;
    }
}

class RainPanel extends Footer {
    JPanel rainPanel = new usePanel().createSimplePanelWithLayout(new GridBagLayout(), MainColor.primary());

    public RainPanel(Page parentPage) {
        // >>>>>>>>>>>>>>>>>>>> Rain Panel >>>>>>>>>>>>>>>>>>>>
        GridBagConstraints gridConst = new GridBagConstraints();

        JButton rainSimulate = new useButton().createButton("rain", "", MainColor.trinary(), 60, 60);

        rainSimulate.addActionListener((e -> {
            parentPage.reduceDustActions("all", false);

        }));

        // Artificial Rain
        JButton artificialRainSimulate = new useButton().createButton("air", "", MainColor.trinary(), 60, 60);

        artificialRainSimulate.addActionListener((e -> {
            if (!this.artificialRainState) {
                artificialRainSimulate.setBackground(MainColor.access("orange"));
                parentPage.reduceDustActions("area", true);

            } else {
                artificialRainSimulate.setBackground(MainColor.trinary());
                parentPage.reduceDustActions("area", false);

            }

            this.artificialRainState = !this.artificialRainState;

        }));

        // >>>>>>>>>>>>>>>>>>>> Back >>>>>>>>>>>>>>>>>>>>
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

    }

    public JPanel getPanel() {
        return this.rainPanel;

    }

}
