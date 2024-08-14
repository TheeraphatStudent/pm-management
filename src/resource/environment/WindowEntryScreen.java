package resource.environment;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class WindowEntryScreen {
    private GraphicsDevice graphicEnv = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

    public int getWidth() {
        return graphicEnv.getDisplayMode().getWidth();
    }

    public int getHeight() {
        return graphicEnv.getDisplayMode().getHeight();
    }

    public int getWidthCenter() {
        return (int) (Math.ceil(graphicEnv.getDisplayMode().getWidth() / 2.5));
    }

    public int getHeightCenter() {
        return (int) (Math.ceil(graphicEnv.getDisplayMode().getHeight() / 4));
    }
}

