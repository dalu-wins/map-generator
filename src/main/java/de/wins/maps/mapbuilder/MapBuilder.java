package de.wins.maps.mapbuilder;

import de.wins.maps.color.Color;
import de.wins.maps.Map;

/***
 * @author Daniil Wins
 * @version 1.0
 */
public class MapBuilder {

    protected int avgHeight = 100;
    protected int width;
    protected int height;

    public MapBuilder(int width, int height) {
        this.width = width;
        this.height = height;
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
