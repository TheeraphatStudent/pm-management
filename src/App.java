import javax.swing.SwingUtilities;

import pages.EntryPage;

public class App {
    public static void main(String[] args) throws Exception {
        // Swing Utilities ลดความผิดพลาดที่อาจเกิดขึ้น
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EntryPage().setVisible(true);
            }
        });

        // new EntryPage().setVisible(true);

    }
}
