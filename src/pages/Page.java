package pages;

import java.awt.Dimension;
// Awt
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

// Img
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

// Swing
import javax.swing.JFrame;
import javax.swing.JPanel;

// Component
import components.Dashboard;
import components.Statistic;
import components.Footer;

// Resource
import resource.colors.MainColor;
import resource.environment.WindowClosingFrameEvent;
import resource.environment.WindowEntryScreen;

public class Page extends JFrame {
  JPanel panel = new JPanel(new GridBagLayout());
  // components
  Dashboard dashboard = new Dashboard(this);
  Statistic statistic = new Statistic();
  Footer footer = new Footer(MainColor.primary(), this);

  // page constructor สืบทอดคุณสมบัติจาก JFrame
  public Page() {
    setTitle("PM 2.5 Reporter");
    getContentPane().setBackground(MainColor.secondary());
    setLayout(new GridBagLayout());

    try (InputStream is = Page.class.getClassLoader().getResourceAsStream("resource/images/icon.png")) {
      if (is == null) {
        System.out.println("Image not found");
      } else {
        BufferedImage iconImage = ImageIO.read(is);
        setIconImage(iconImage);
        System.out.println(iconImage);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Set Size
    setSize((int) (Math.floor(new WindowEntryScreen().getWidth() / 1.4)),
        (int) (Math.floor(new WindowEntryScreen().getHeight() / 1.4)));
    setMinimumSize(new Dimension(850, 550));

    setLocation((int) (Math.floor(new WindowEntryScreen().getWidthCenter() / 2.5)),
        (int) (Math.floor(new WindowEntryScreen().getHeight()) / 6));
    GridBagConstraints gridConst = new GridBagConstraints();

    gridConst.fill = GridBagConstraints.BOTH;
    panel.setBackground(MainColor.secondary());
    // gridConst.gridx = 0;
    gridConst.gridy = 0;
    gridConst.weightx = 0.6;//colum
    gridConst.weighty = 1;//row
    gridConst.insets = new Insets(20, 20, 20, 20);
    panel.add(dashboard.getDashboard(), gridConst);

    gridConst.gridx = 1;
    gridConst.gridy = 0;
    gridConst.weightx = 0.4;
    gridConst.weighty = 1;
    panel.add(statistic.getStatistic(), gridConst);

    // Grid Bag Container 1
    gridConst.gridx = 0;
    gridConst.gridy = 0;
    gridConst.weightx = 1.0;
    gridConst.weighty = 0.9;
    add(panel, gridConst);

    // Grid Bag Container 2
    gridConst.gridx = 0;
    gridConst.gridy = 1;
    gridConst.weightx = 1.0;
    gridConst.weighty = 0.1;
    gridConst.insets = new Insets(0, 20, 20, 20);
    add(footer, gridConst);

    new WindowClosingFrameEvent(this, new EntryPage());
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }

  private void reloadContent() {
    this.revalidate();//ตรวจสอบความถูกต้อง
    this.repaint();//วาดใหม่

  }

  // Dashboard
  public void setFilePathInDashboard(String filePath,boolean isFileExit) 
  {
    dashboard.setFile(filePath, isFileExit);
    reloadContent();
  }

  public void setrandomrange(int min, int max) {
    System.out.println("Set Random Range Work!");
    System.out.println("Min: " + min);
    System.out.println("Max: " + max);

    dashboard.setPeopleRange(min, max);

  }
// ใช้ใน dashbord
  public void setFileFeedback(boolean feedback) {
    footer.getFileFeedback(feedback);

  }

  // Rain รีดิ้วส์ ใช้ในfooter
  public void reduceDustActions(String reduceOps, boolean isActive) {
    System.out.println("Reduce Dust Action: " + reduceOps);
    dashboard.reduceDust(reduceOps, isActive);

    reloadContent();

  }

  // Statistic / patentRate - คนป่วย 
  public void setStatisticData(int dust, int patentRate, int people) {
    statistic.setStatistic(dust, patentRate, people);

    reloadContent();

  }

  public void resetStatistic() {
    statistic.resetStatistic();

    reloadContent();

  }

}
