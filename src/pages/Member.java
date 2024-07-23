package pages;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import resource.environment.WindowClosingFrameEvent;
import resource.environment.WindowEntryScreen;

public class Member extends JFrame {
    public Member() {
        setTitle("Developer Member");
        setSize(new WindowEntryScreen().getWidth(), new WindowEntryScreen().getHeight());
        // setSize(500, 500);
        setLocation(new WindowEntryScreen().getWidthCenter(), new WindowEntryScreen().getHeightCenter());

        new WindowClosingFrameEvent(this, new EntryPage());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel1 = new JPanel(new GridLayout(1, 0,10,10));
        panel1.setBackground(Color.YELLOW);
        panel1.setBounds(1019, 0, 1000, 100);
        JButton btn = new JButton("Name : Boonnisa Pitchawong");
        
        panel1.add(btn);

        JPanel panel2 = new JPanel(new GridLayout(1, 0,10,10));
        panel2.setBackground(Color.GRAY);
        panel2.setBounds(1019, 100, 1000, 100);
        JButton btn2 = new JButton("ID : 66011212184");

        panel2.add(btn2);
        
        JPanel panel3 = new JPanel();
        panel3.setBackground(Color.GREEN);
        panel3.setBounds(0,0,500,500);
        add(panel1);
        add(panel2);
        add(panel3);
    }

}
