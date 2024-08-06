package components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JDialog;

import resource.colors.MainColor;
import utils.useAlert;

interface DashboardProps {
    public JPanel panel = new JPanel(new GridLayout(10, 20, 3, 3));
    public Map<JButton, Color> buttonColors = new HashMap<>();
    public Map<JButton, Integer> buttonValues = new HashMap<>();
    public int[][] buttonValuesArray = new int[10][20];
    public boolean simulateAreaActive = false;

}

public class Dashboard implements DashboardProps {

    // State
    private JButton lastClickedButton = null;
    private int clickedRow = -1;
    private int clickedCol = -1;
    private String reduceDustOps = "all";
    private String fileContent = "";
    private boolean isFileAlreadyExit = false;
    private boolean isActive = false;

    public JPanel getDashboard() {
        panel.setBackground(MainColor.secondary().darker());
        return panel;
    }

    private void reloadContent() {
        panel.revalidate();
        panel.repaint();

    }

    private void updatedRain(String reduceDustOps) {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 20; col++) {
                int content = buttonValuesArray[row][col];
                // System.out.println("Content: " + content);

                if (reduceDustOps.equals("all")) {
                    JButton btn = createButton(row, col, reduceDustInArea(content, 50));
                    panel.add(btn);

                } else {
                    JButton btn = createButton(row, col, content);
                    panel.add(btn);

                }
            }
        }

    }

    // Main Content
    private void updateDashboard(boolean simulate) {

        panel.removeAll();

        try {
            if (!fileContent.equals("")) {
                if (this.isFileAlreadyExit) {
                    updatedRain(this.reduceDustOps);
                    reloadContent();

                } else {
                    Scanner fr = new Scanner(new File(fileContent));
                    int row = 0;
                    while (fr.hasNextLine()) {
                        String[] readLine = fr.nextLine().split("\\s+");
                        int col = 0;
                        for (String content : readLine) {
                            // System.out.println("Btn Content: " + content);

                            JButton btn = createButton(row, col, Integer.parseInt(content));
                            panel.add(btn);
                            col++;
                        }
                        row++;
                    }

                    reloadContent();
                    fr.close();
                    this.isFileAlreadyExit = true;
                }

            } else {
                new useAlert().warringAlert("Please Select File First!");

            }
        } catch (Exception e) {
            System.err.println("Something went wrong!: " + e);
        }
    }

    private JButton createButton(int row, int col, int dust) {
        JButton btn = new JButton();

        // Dashboard Content
        buttonValuesArray[row][col] = dust;

        if (dust >= 0 && dust <= 250) {
            Color buttonColor = getOriginalColor(dust);

            buttonColors.put(btn, buttonColor);
            buttonValues.put(btn, dust);

            btn.setBackground(buttonColor);
            btn.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, buttonColor.darker(),
                    buttonColor.darker().darker()));

            int currentRow = row;
            int currentCol = col;

            btn.addActionListener(e -> {
                // Reset Button Content After Clicked Another Button
                if (this.lastClickedButton != null) {
                    this.lastClickedButton.setBackground(buttonColors.get(this.lastClickedButton));
                    this.lastClickedButton.setText("");
                }

                btn.setBackground(btn.getBackground().darker());
                btn.setText(String.valueOf(dust));
                this.lastClickedButton = btn;

                this.clickedRow = currentRow;
                this.clickedCol = currentCol;

                System.out.println("Clicked: " + dust);
                System.out.println("Clicked Row: " + currentRow);
                System.out.println("Clicked Col: " + currentCol);

                if (this.isActive) {
                    System.out.println("<<<<<<<<<<<<<<< Get Surrounding Content Work!");
                    // getSurroundingContent(String.valueOf(dust));
                    getSurroundingContent(String.valueOf(dust), btn);

                } else {
                    // Send Content In Area Was Clicked Here!
                    System.out.println(">>>>>>>>>>>>>>>> Not Get Surrounding Content!");

                    

                }

            });

            btn.setToolTipText(String.valueOf(dust));
            btn.setPreferredSize(new Dimension(10, 20));
            btn.setFocusPainted(false);
            btn.setContentAreaFilled(true);
            btn.setOpaque(true);
        } else {
            btn.setBackground(MainColor.access("-"));
            btn.setEnabled(false);
        }

        return btn;
    }

    private int reduceDustInArea(int dust, int percentage) {
        return (dust >= 0 && dust <= 250) ? ((dust) - ((int) (Math.floor((dust * percentage) / 100)))) : dust;

    }

    private Color getOriginalColor(int value) {
        if (value < 0 || value > 250) {
            return MainColor.access("-");
        } else if (value <= 50) {
            return MainColor.access("green");
        } else if (value <= 100) {
            return MainColor.access("yellow");
        } else if (value <= 150) {
            return MainColor.access("orange");
        } else {
            return MainColor.access("orange");
        }
    }

    public int setSelectedContent(int content, int row, int col, boolean isSurrounding) {
        int percentage = (isSurrounding) ? 50 : 30;
        return new Dashboard().reduceDustInArea(content, percentage);

    }

    private void getSurroundingContent(String content, JButton btn) {
        if (this.clickedRow == -1 || this.clickedCol == -1) {
            return;
        }

        System.out.println("Surrounding content:");

        // Math min, max สำหรับกันไม่ให้จำนวนเกินขนาดของ Array
        for (int i = Math.max(
                0, (this.clickedRow - 1)); i <= Math.min(
                        9, (this.clickedRow + 1)); i++) {
            // System.out.printf("Row >%d : ", i);

            for (int j = Math.max(
                    0, (this.clickedCol - 1)); j <= Math.min(
                            19, (this.clickedCol + 1)); j++) {
                // System.out.printf("Col >%d :", j);

                boolean isSurrounding = (i == this.clickedRow) && (j == this.clickedCol);
                buttonValuesArray[i][j] = setSelectedContent(buttonValuesArray[i][j], i, j, isSurrounding);
            }

            updateDashboard(false);
        }
    }

    public void setFile(String _File, boolean isFileExit) {
        this.isFileAlreadyExit = isFileExit;

        System.out.println("Dashboard Set File Work! -> " + _File);
        this.fileContent = _File;
        updateDashboard(false);
    }

    public void reduceDust(String options, boolean isActive) {

        this.isActive = isActive;

        switch (options) {
            case "all":
                // System.out.println("Reduce Dust In Dashboard Work!");
                this.reduceDustOps = "all";
                updateDashboard(true);
                break;

            case "area":
                // System.out.println("Reduce Dust By Area In Dashboard Work!");
                this.reduceDustOps = "area";
                updateDashboard(true);

                break;

            default:
                break;
        }

    }

}