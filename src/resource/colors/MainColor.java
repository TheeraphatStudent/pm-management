package resource.colors;

import java.awt.Color;

public class MainColor {
    public static Color primary() {
        return new Color(165, 221, 155);

    }

    public static Color secondary() {
        return new Color(228, 239, 231);

    }

    public static Color trinary() {
        return new Color(74, 169, 108);

    }

    public static Color access(String choose) {
        switch (choose) {
            case "red":
                return new MainColor().red();
            case "green":
                return primary();
            case "blue":
                return new MainColor().blue();
            case "orange":
                return new MainColor().orange();
            case "yellow":
                return new MainColor().yellow();

            default:
                return new Color(0);
        }

    }

    public static Color getOriginalColor(int value) {
        if (value < 0 || value > 250) {
            return MainColor.access("-");
        } else if (value <= 50) {
            return MainColor.access("green");
        } else if (value <= 100) {
            return MainColor.access("yellow");
        } else if (value <= 150) {
            return MainColor.access("orange");
        } else {
            return MainColor.access("red");
        }
    }

    private Color red() {
        return new Color(255, 104, 104);

    }

    private Color blue() {
        return new Color(102, 123, 198);

    }

    private Color orange() {
        return new Color(255, 163, 71);

    }

    private Color yellow() {
        return new Color(219, 219, 92);

    }
}