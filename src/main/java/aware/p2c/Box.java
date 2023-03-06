package aware.p2c;

import java.awt.image.BufferedImage;

public class Box {

    private int topLeftX;
    private int topLeftY;
    private int width;
    private int height;

    public Box(int topLeftX, int topLeftY, int width, int height) {
        this.topLeftX = topLeftX;
        this.topLeftY = topLeftY;
        this.width = width;
        this.height = height;
    }

    public int getTopLeftX() {
        return topLeftX;
    }

    public int getTopLeftY() {
        return topLeftY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean fits(BufferedImage image) {
        return width >= image.getWidth() && height >= image.getHeight();
    }

    @Override
    public String toString() {
        return "Box{" +
                "topLeftX=" + topLeftX +
                ", topLeftY=" + topLeftY +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
