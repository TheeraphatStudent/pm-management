package components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import resource.colors.MainColor;

public class Dashboard {
    private String fileContent = "";
    private JPanel panel = new JPanel(new GridLayout(10, 20, 3, 3));
    private JButton lastClickedButton = null;
    private final Map<JButton, Color> buttonColors = new HashMap<>();
    private final Map<JButton, Integer> buttonValues = new HashMap<>();
    private int[][] buttonValuesArray = new int[10][20];
    private int clickedRow = -1;
    private int clickedCol = -1;
    private int[][] updatedContent = new int[3][3];

    public JPanel getDashboard() {
        panel.setBackground(MainColor.secondary());
        return this.panel;
    }

    private void updateDashboard() {
        panel.removeAll();

        try {
            Scanner fr = new Scanner(new File(fileContent));

            int row = 0;
            while (fr.hasNextLine()) {
                String[] readLine = fr.nextLine().split("\\s+");
                int col = 0;
                for (String content : readLine) {
                    JButton btn = new JButton();
                    int getDust = Integer.parseInt(content);

                    if (getDust >= 0 && getDust <= 250) {
                        Color buttonColor = getOriginalColor(getDust);

                        buttonColors.put(btn, buttonColor);
                        buttonValues.put(btn, getDust);

                        System.out.printf("%d | %d > %d\n", row, col, getDust);
                        buttonValuesArray[row][col] = getDust;

                        btn.setBackground(buttonColor);
                        btn.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, buttonColor.darker(),
                                buttonColor.darker().darker()));

                        // เพื่อให้ส่ง Row, Col เข้าไปใน event ได้
                        // ต้องทำตัวแปรมาเก็บเพื่อให้ส่งเข้าไปได้
                        int currentRow = row;
                        int currentCol = col;

                        btn.addActionListener(e -> {
                            if (lastClickedButton != null) {
                                lastClickedButton.setBackground(buttonColors.get(lastClickedButton));
                                lastClickedButton.setText("");
                            }

                            btn.setBackground(btn.getBackground().darker());
                            btn.setText(content);
                            lastClickedButton = btn;

                            this.clickedRow = currentRow;
                            this.clickedCol = currentCol;

                            System.out.println("Clicked: " + content);
                            System.out.println("Clicked Row: " + currentRow);
                            System.out.println("Clicked Col: " + currentCol);
                            surroundingContent(content);
                        });

                        btn.setToolTipText(content);
                        btn.setPreferredSize(new Dimension(10, 20));
                        btn.setFocusPainted(false);
                        btn.setContentAreaFilled(true);
                        btn.setOpaque(true);

                    } else {
                        btn.setBackground(new Color(0));
                        btn.setEnabled(false);
                    }

                    panel.add(btn);

                    // Content สำหรับ Col ที่ 0 ... 20
                    col++;
                }

                // Content สำหรับ Row ที่ 0
                row++;
            }
            fr.close();

        } catch (Exception e) {
            System.err.println("Something went wrong!: " + e);
        }

        // Reset Panel
        panel.revalidate();
        panel.repaint();
    }

    private Color getOriginalColor(int value) {
        if (value <= 50) {
            return MainColor.access("green");
        } else if (value <= 100) {
            return MainColor.access("yellow");
        } else if (value <= 150) {
            return MainColor.access("orange");
        } else {
            return MainColor.access("red");
        }
    }

    private void surroundingContent(String content) {
        if (this.clickedRow == -1 || this.clickedCol == -1) {
            return;
        }

        System.out.println("Surrounding content:");

        // Math min, max สำหรับกันไม่ให้จำนวนเกินขนาดของ Array
        for (int i = Math.max(
                0, (this.clickedRow - 1)); i <= Math.min(
                        9, (this.clickedRow + 1)); i++) {
            System.out.printf("Row >%d : ", i);

            for (int j = Math.max(
                    0, (this.clickedCol - 1)); j <= Math.min(
                            19, (this.clickedCol + 1)); j++) {
                System.out.printf("Col >%d :", j);
                if ((i == this.clickedRow) && (j == this.clickedCol)) {
                    System.out.print(content + " ");
                } else {
                    System.out.print(buttonValuesArray[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public void setFile(String _File) {
        System.out.println("Dashboard Set File Work! -> " + _File);
        this.fileContent = _File;
        updateDashboard();
    }

    public void setSelectedContent() {


    }
}
