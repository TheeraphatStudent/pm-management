package resource.environment;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class WindowEntryScreen {
    private GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

    public int getWidth() {
        return gd.getDisplayMode().getWidth();
    }

    public int getHeight() {
        return gd.getDisplayMode().getHeight();
    }

    public int getWidthCenter() {
        return (int) (Math.ceil(gd.getDisplayMode().getWidth() / 2.5));
    }

    public int getHeightCenter() {
        return (int) (Math.ceil(gd.getDisplayMode().getHeight() / 4));
    }
}

