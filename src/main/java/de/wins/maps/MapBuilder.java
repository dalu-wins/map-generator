package de.wins.maps;

/***
 * @author Daniil Wins
 * @version 1.0
 */
public class MapBuilder {

    protected int avgHeight;
    protected int width;
    protected int height;

    public MapBuilder(int width, int height) {
        this.width = width;
        this.height = height;
        this.avgHeight = 0;
    }

    public Map generate() {
        Color[][] colors = new Color[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                colors[y][x] = Color.toGrayscale(avgHeight);
            }
        }
        return new Map(colors);
    }

    public MapBuilder setAvgHeight(int avgHeight) {
        this.avgHeight = avgHeight;
        return this;
    }

}
