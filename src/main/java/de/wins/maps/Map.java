package de.wins.maps;

public class Map {

    private final int width;
    private final int height;
    private int [][][] pixelColors;

    public Map(int width, int height, int[][][] pixelColors) {
        this.width = width;
        this.height = height;
        this.pixelColors = pixelColors;
    }

    public void setPixelColors(int[][][] pixelColors) {
        this.pixelColors = pixelColors;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[][][] getPixelColors() {
        return pixelColors;
    }
}
