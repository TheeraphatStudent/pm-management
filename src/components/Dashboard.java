package components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Dashboard {
    // private String getFileContent = "";

    // public Dashboard() {
        

    // }

    // public Dashboard(String _getFileContent) {
    //     this.getFileContent = _getFileContent;

    // }

    JPanel panel = new JPanel(new GridLayout(10, 20, 3, 3));

    public JPanel getDashboard() {
        panel.setSize(new Dimension(500, 500));
        panel.setCursor(Cursor.getDefaultCursor());

        panel.setBackground(new Color(53, 21, 59, 100));
        
        // Content
        try {
            Scanner fr = new Scanner(new File("C:/Users/ASUS/Desktop/Code/Learn/learn-java/Java-OOP/pm-management/src/pm2.5.txt"));

            while (fr.hasNextLine()) {
                // System.out.println(fr.nextLine());
                String readLine[] = fr.nextLine().split("\\s+");
                for (String content : readLine) {
                    // System.out.printf("%s", content);
                    JButton btn = new JButton(content);
                    btn.setText(content);
                    btn.setSize(80, 160);

                    panel.add(btn);

                }
            }
            fr.close();

        } catch (Exception e) {
            System.err.println("Something went wrong!: " + e);
        }

        return this.panel;

    }
}
