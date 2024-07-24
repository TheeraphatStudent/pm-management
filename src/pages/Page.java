package pages;

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
    JPanel panel = new JPanel(new GridLayout(1, 2, 20, 20));

    Dashboard dashboard = new Dashboard();
    Footer footer = new Footer(MainColor.primary());
    Statistic statistic = new Statistic();

    public Page() {
        setTitle("PM 2.5 Reporter");

        try (InputStream is = Page.class.getClassLoader().getResourceAsStream("resource/images/icon.png")) {
            if (is == null) {
                System.out.println("Image not found");
            } else {
                BufferedImage iconImage = ImageIO.read(is);
                setIconImage(iconImage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        setSize(new WindowEntryScreen().getWidth(), new WindowEntryScreen().getHeight());
        // setLocation(new WindowEntryScreen().getWidthCenter(), new WindowEntryScreen().getHeightCenter());
        setLayout(new GridBagLayout());
        GridBagConstraints gridConst = new GridBagConstraints();

        getContentPane().setBackground(MainColor.secondary());

        // Panel Content
        panel.setBackground(MainColor.secondary());
        panel.add(dashboard.getDashboard());
        panel.add(statistic.getStatistic());

        // Main Content
        gridConst.gridx = 0;
        gridConst.gridy = 0;
        gridConst.weightx = 1.0;
        gridConst.weighty = 0.7;
        gridConst.fill = GridBagConstraints.BOTH;
        gridConst.insets = new Insets(20, 20, 20, 20);
        add(panel, gridConst);

        gridConst.gridx = 0;
        gridConst.gridy = 1;
        gridConst.weightx = 1.0;
        gridConst.weighty = 0.3;
        gridConst.fill = GridBagConstraints.BOTH;
        gridConst.insets = new Insets(0, 20, 20, 20);
        add(footer.getFooter(), gridConst);

        new WindowClosingFrameEvent(this, new EntryPage());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // new WindowClosingFrameEvent(this, new Member());
        // new WindowClosingFrameEvent(this);

    }
}
