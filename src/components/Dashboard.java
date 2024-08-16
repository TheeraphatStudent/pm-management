package components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;

import pages.Page;

import javax.swing.JButton;
import javax.swing.JPanel;

import resource.colors.MainColor;

import utils.useAlert;
import utils.useRandom;

interface DashboardProps {
  public JPanel panel = new JPanel(new GridLayout(10, 20, 2, 2));
  public Map<JButton, Color> buttonColors = new HashMap<>();
  public Map<JButton, Integer> buttonValues = new HashMap<>();
  public Map<JButton, Integer> buttonPatentRate = new HashMap<>();
  public int[][] buttonPeoples = new int[10][20];
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
  private Page parentPage;

  // People
  private boolean isUpdatePeople = false;
  private int minrange = 0;
  private int maxrange = 0;

  public Dashboard(Page page) {
    this.parentPage = page;

  }

  public Dashboard() {
  }

  public JPanel getDashboard() {
    panel.setBackground(MainColor.secondary().darker());
    return panel;
  }

  private void reloadContent() {
    //เช็คความถูกต้อง
    panel.revalidate();
    //วาดใหม่
    panel.repaint();

  }

  private void updatedRain(String reduceDustOps) {
    for (int row = 0; row < 10; row++) {
      for (int col = 0; col < 20; col++) {
        int content = buttonValuesArray[row][col];
        // System.out.println("Content: " + content);
        JButton btn;

        if (reduceDustOps.equals("all")) {
          btn = createButton(row, col, reduceDustInArea(content, 50), buttonPeoples[row][col]);

        } else {
          btn = createButton(row, col, content, buttonPeoples[row][col]);

        }

        panel.add(btn);
      }
    }

  }

  // Main Content
  private void updateDashboard(boolean simulate) {
    System.out.println("Is Updated People: " + this.isUpdatePeople);
    panel.removeAll();

    try {
      if (!this.fileContent.equals("")) {
        if (preloadDashboard()) {
          // simulate - จำลองฝน
          if (!this.isFileAlreadyExit|| !simulate) {
            Scanner fr = new Scanner(new File(this.fileContent));
            int row = 0;
            while (fr.hasNextLine()) {
              String[] readLine = fr.nextLine().split("\\s+");
              int col = 0;
              for (String content : readLine) {
                // System.out.println("Btn Content: " + content);

                JButton btn;
                if (!this.isUpdatePeople) {
                  System.out.println("Get People");
                  btn = createButton(row, col, Integer.parseInt(content), buttonPeoples[row][col]);

                } else {
                  int randomPeople = new useRandom().randomRange(this.minrange, this.maxrange);
                  System.out.println("Get New People: " + buttonPeoples[row][col]);
                  btn = createButton(row, col, Integer.parseInt(content), randomPeople);

                }

                panel.add(btn);
                col++;
              }
              row++;
            }

            reloadContent();
            fr.close();
            this.isFileAlreadyExit = true;
          } else {
            // reduceDustOps - ดำเนินการลดฝุ่น
            updatedRain(this.reduceDustOps);
            reloadContent();
          }

          this.parentPage.setFileFeedback(false);
          this.isUpdatePeople = true;

        } else {
          new useAlert().warringAlert("File Must Be Formatted & File Size Was 10 * 20!");
          this.parentPage.setFileFeedback(true);

        }

      } else {
        new useAlert().warringAlert("Please Select File First!");
        this.parentPage.setFileFeedback(true);

      }

    } catch (Exception e) {
      System.err.println("Something went wrong!: " + e);
    }
  }

  //? ทดลองโหลดไฟล์ / ส่งค่ากลับไปเป็นboolean
  private boolean preloadDashboard() throws FileNotFoundException {
    Scanner fr = new Scanner(new File(this.fileContent));

    int rowComplete = 0, colComplete = 0;

    int row = 0;
    //ถ้าไฟล์มีบรรทัดถัดไป
    while (fr.hasNextLine()) {
      // 38 22 55 34 63 59 78 87 97 79 61 63 77 78 214 192 224 156 181 79 (ตัวอย่างไฟล์)
      String[] readLine = fr.nextLine().split("\\s+");

      int col = 0;
      for(;col<=readLine.length;col++){
        colComplete = col ;
      }
      
      row++;

      rowComplete = row;
    }

    System.out.printf("Row Content: %d\nCol Content: %d", rowComplete, colComplete);

    fr.close();
    // ? ?คือเป็นจริง :ถ้าไม่เป็นจริง
    return (rowComplete == 10 && colComplete == 20) ? true : false;

  }

  // Create Button
  private JButton createButton(int row, int col, int dust, int people) {
    JButton btn = new JButton();

    System.out.println("People: " + people);

    // Dashboard Content
    buttonValuesArray[row][col] = dust;
    // btn.setSize(50, 50);

    if (dust >= 0 && dust <= 250) {
      Color buttonColor = MainColor.getOriginalColor(dust);

      buttonColors.put(btn, buttonColor);
      buttonValues.put(btn, dust);
      buttonPatentRate.put(btn, getPatentRate(dust));

      // People
      buttonPeoples[row][col] = people;

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
          getSurroundingContent(dust, btn);

        } else {
          // Send Content In Area Was Clicked Here!
          System.out.println(">>>>>>>>>>>>>>>> Not Get Surrounding Content!");

          System.out.println("Button / People: " + buttonPeoples[currentRow][currentCol]);
          this.parentPage.setStatisticData(dust, buttonPatentRate.get(btn), buttonPeoples[currentRow][currentCol]);

        }

      });

      btn.setToolTipText(String.valueOf(dust));

    } else {
      btn.setBackground(MainColor.access("-"));
      btn.setEnabled(false);
    }

    btn.setPreferredSize(new Dimension(30, 30));
    btn.setFocusPainted(false);
    btn.setContentAreaFilled(true);
    btn.setOpaque(true);

    return btn;
  }

  private int reduceDustInArea(int dust, int percentage) {
    return (dust >= 0 && dust <= 250) ? ((dust) - ((int) (Math.floor((dust * percentage) / 100)))) : dust;

  }

  private int setSelectedContent(int content, int row, int col, boolean isSurrounding) {
    int percentage = (isSurrounding) ? 50 : 30;
    return new Dashboard().reduceDustInArea(content, percentage);

  }

  private void getSurroundingContent(int content, JButton btn) {
    if (this.clickedRow == -1 || this.clickedCol == -1) {
      return;
    }

    System.out.println("Surrounding content: " + content);

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

      updateDashboard(true);
    }
  }

  // Patent Rate
  private int getPatentRate(int dust) {
    // System.out.println("Patent Rate Work!");

    if (dust <= 50) {
      return new useRandom().randomRange(0, 9);
    } else if (dust <= 100) {
      return new useRandom().randomRange(10, 19);
    } else if (dust <= 150) {
      return new useRandom().randomRange(20, 29);
    } else {
      return new useRandom().randomRange(30, 50);
    }

  }

  // Public
  public void setPeopleRange(int min, int max) {
    System.out.println("Set People Range On Dashboard Work!");
    this.minrange = min;
    this.maxrange = max;

    System.out.println("Min: " + this.minrange);
    System.out.println("Max: " + this.maxrange);

    updateDashboard(false);

  }

  public void setFile(String _File, boolean isFileExit) {
    // thisเรียกใช้งานattibute
    this.isFileAlreadyExit = isFileExit;

    System.out.println("Dashboard Set File Work! -> " + _File);
    this.fileContent = _File;

    this.isUpdatePeople = false;

    updateDashboard(false);
  }

  public void reduceDust(String options, boolean isActive) {
    this.isActive = isActive;
    this.isUpdatePeople = true;

    this.parentPage.resetStatistic();

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