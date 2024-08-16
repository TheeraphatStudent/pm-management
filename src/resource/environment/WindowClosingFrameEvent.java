package resource.environment;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WindowClosingFrameEvent {
    private JFrame currentFrame;
    private JFrame destFrame;

    public WindowClosingFrameEvent() {
    }

    public WindowClosingFrameEvent(JFrame _currentFrame) {
        this.currentFrame = _currentFrame;
        this.currentFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                JFrame frame = (JFrame) e.getSource();

                int result = JOptionPane.showConfirmDialog(
                        frame,
                        "Are you sure you want to exit the program?",
                        "Exit Program",
                        JOptionPane.YES_NO_OPTION);

                if (result == JOptionPane.YES_OPTION) {
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    changeFrameEvent(_currentFrame);
                } else {
                    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });
    }

    public void windowClosingFrameEvent(JFrame _currentFrame) {
        this.currentFrame = _currentFrame;
        JFrame frame = new JFrame();

        int result = JOptionPane.showConfirmDialog(
                frame,
                "Are you sure you want to exit the program?",
                "Exit Program",
                JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            changeFrameEvent(_currentFrame);

        } else {
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
    }

    public WindowClosingFrameEvent(JFrame _currentFrame, JFrame _destFrame) {
        this.currentFrame = _currentFrame;
        this.destFrame = _destFrame;

        this.currentFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                System.out.println("Window Closing Work!");
                System.out.println(event);
                changeFrameEvent();
            }

        });

    }

    private void changeFrameEvent() {
        if (!this.destFrame.isActive()) {
            System.out.println("Change frame event work!");
            this.destFrame.setVisible(true);

        }

    }

    private void changeFrameEvent(JFrame breakDownFrame) {
        if (!breakDownFrame.isActive() && breakDownFrame != null) {
            System.out.println("Break down work!");
            breakDownFrame.dispose();
            System.exit(0);

        }

    }
}