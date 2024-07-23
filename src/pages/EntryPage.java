package pages;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;

import resource.environment.WindowClosingFrameEvent;
import resource.environment.WindowEntryScreen;

public class EntryPage extends JFrame {
    public EntryPage() {
        setTitle("PM 2.5 Management");
        // setSize(new WindowEntryScreen().getWidth(), new WindowEntryScreen().getHeight());
        setSize(500, 500);
        setLayout(new GridLayout());

        // Location
        // int width = (int)(Math.ceil(gd.getDisplayMode().getWidth() / 2.5));
        // int height = (int)(Math.ceil(gd.getDisplayMode().getHeight() / 4));
        setLocation(new WindowEntryScreen().getWidthCenter(), new WindowEntryScreen().getHeightCenter());

        // System.out.println(width + " | " + height);

        // Menu Hamburger
        // ButtonNavigate navigateToPage = new ButtonNavigate("Program", this, new
        // Page());
        // ButtonNavigate navigateToMember = new ButtonNavigate("Member", this, new
        // Member());
        JButton navigateToPage = new JButton("Program");
        navigateToPage.addActionListener(e -> {
            JFrame page = new Page();
            new WindowClosingFrameEvent(this, page);
            page.setVisible(true);
            dispose();
        });

        JButton navigateToMember = new JButton("Develops");
        navigateToMember.addActionListener(e -> {
            JFrame member = new Member();
            new WindowClosingFrameEvent(this, member);
            member.setVisible(true);
            dispose();
        });

        JPanel panel = new JPanel(new GridLayout(4,2));
        panel.add(navigateToPage);
        panel.add(navigateToMember);
        panel.setBackground(Color.LIGHT_GRAY);

        add(panel);

        new WindowClosingFrameEvent(this);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
