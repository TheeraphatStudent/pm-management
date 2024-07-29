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

    Dashboard dashboard = new Dashboard();
    Statistic statistic = new Statistic();
    Footer footer;

    public Page() {
        setTitle("PM 2.5 Reporter");

        // getClassLoader() -> เพื่อจะเช็คให้มั่นใจว่าใน Class นั้นๆ มี Content พร้อมที่จะแสดง Content แล้ว
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

        setSize((int)(Math.floor(new WindowEntryScreen().getWidth() / 1.4)), (int)(Math.floor(new WindowEntryScreen().getHeight() / 1.4)));
        setMinimumSize(new Dimension(850, 550));
        setLocation((int)(Math.floor(new WindowEntryScreen().getWidthCenter() / 2.5)), (int)(Math.floor(new WindowEntryScreen().getHeight()) / 6));
        setLayout(new GridBagLayout());
        GridBagConstraints gridConst = new GridBagConstraints();
        gridConst.fill = GridBagConstraints.BOTH;

        getContentPane().setBackground(MainColor.secondary());

        panel.setBackground(MainColor.secondary());

        gridConst.gridx = 0;
        gridConst.gridy = 0;
        gridConst.weightx = 2.4;
        gridConst.weighty = 1;
        gridConst.insets = new Insets(20, 20, 20, 20);
        panel.add(dashboard.getDashboard(), gridConst);

        gridConst.gridx = 1;
        gridConst.gridy = 0;
        gridConst.weightx = 1.4;
        gridConst.weighty = 1;
        panel.add(statistic.getStatistic(), gridConst);

        gridConst.gridx = 0;
        gridConst.gridy = 0;
        gridConst.weightx = 1.0;
        gridConst.weighty = 0.9;
        add(panel, gridConst);

        gridConst.gridx = 0;
        gridConst.gridy = 1;
        gridConst.weightx = 1.0;
        gridConst.weighty = 0.1;
        gridConst.insets = new Insets(0, 20, 20, 20);

        // Passing ref from footer to parent frame
        footer = new Footer(MainColor.primary(), this);
        add(footer, gridConst);

        new WindowClosingFrameEvent(this, new EntryPage());
        // setUndecorated(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void setFilePathInDashboard(String filePath) {
        dashboard.setFile(filePath);
        // Update Parent Frame
        revalidate();
        repaint();
    }
}
