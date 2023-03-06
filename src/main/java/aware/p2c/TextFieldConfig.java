package aware.p2c;

import java.awt.*;

public class TextFieldConfig {

    private final Box box;
    private final Font font;
    private final Color color;
    public TextFieldConfig(Box box, Font font, Color color) {

        this.box = box;
        this.font = font;
        this.color = color;
    }


    public Box getBox() {
        return box;
    }

    public Font getFont() {
        return font;
    }

    public Color getColor() {
        return color;
    }
}
