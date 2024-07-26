package pages;


import java.awt.GridLayout;
import javax.swing.JFrame;



import components.Devpanel;
import resource.environment.WindowClosingFrameEvent;
import resource.environment.WindowEntryScreen;

public class Member extends JFrame {
    public Member() {
        Devpanel dev = new Devpanel(100,200,100,100,"Theeraphat Chueanokkhum","66011212103");
        Devpanel dev2 = new Devpanel(100,100,100,200,"Boonnisa Pitchawong","66011212184");
        setTitle("Developer Member");
        setSize(new WindowEntryScreen().getWidth(), new WindowEntryScreen().getHeight());
        setLayout(new GridLayout(1,1));
        setLocation(new WindowEntryScreen().getWidthCenter(), new WindowEntryScreen().getHeightCenter());

        new WindowClosingFrameEvent(this, new EntryPage());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        add(dev);
        add(dev2);

    }

}
