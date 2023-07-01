package de.wins.maps;

import de.wins.maps.color.Color;
import de.wins.maps.color.NullColor;
import de.wins.maps.landscape.Landscape;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/***
 * @author Daniil Wins
 * @version 1.0
 */
public class Map {

    private final int width;
    private final int height;
    private final Color[][] pixels;

    private boolean isColorized = false;

    public Map(Color [][] pixels) {
        this.width = pixels[0].length;
        this.height = pixels.length;
        this.pixels = pixels;
    }

    public Color [][] getPixels() {
        return pixels;
    }

    public Map save(String fileName) {

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                image.setRGB(x, y, pixels[y][x].getRGBAsInt());
            }
        }

        File outputFile = new File(String.format("./%s.bmp", fileName));
        try {
            ImageIO.write(image, "bmp", outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return this;

    }

    public void setColorized(boolean colorized) {
        isColorized = colorized;
    }

    public Map colorize() {

        if (isColorized) return this;       // only colorize if not yet colorized

        Map map = new Map(new Color[height][width]);
        map.setColorized(true);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Landscape landscape = Landscape.getLandscapeByHeight(pixels[y][x].getGrayscale());
                Color color = landscape.color();
                map.pixels[y][x] = color;
            }
        }

        return map;
    }

    public Map smooth() {

        if (!isColorized) return this;      // only colorized maps can be smoothed

        Map map = new Map(new Color[height][width]);
        map.setColorized(true);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                // Get all neighbors
                Color[] neighbors = {
                    getValue(pixels, x-1, y-1),
                    getValue(pixels, x-1, y),
                    getValue(pixels, x-1, y+1),
                    getValue(pixels, x, y-1),
                    getValue(pixels, x, y+1),
                    getValue(pixels, x+1, y-1),
                    getValue(pixels, x+1, y),
                    getValue(pixels, x+1, y+1),
                };


                // Get most frequent neighbor
                Color mostFreq = freq(neighbors, pixels[y][x]);

                // Set new maps value
                map.getPixels()[y][x] = mostFreq;

            }
        }

        return map;

    }

    public Map upscale() {

        Map map = new Map(new Color[height*2][width*2]);
        map.setColorized(this.isColorized);
        Color[][] colors = map.getPixels();
        for (int y = 0; y < height*2; y++) {
            for (int x = 0; x < width*2; x++) {
                colors[y][x] = this.getPixels()[y/2][x/2];
            }
        }

        return map.smooth();

    }

    private Color freq(Color[] arr, Color initialColor) {
        int maxCount = 0;
        Color maxFreq = new NullColor();

        for (Color color1 : arr) {
            int count = 0;
            for (Color color2 : arr) {
                if (color1 == color2 && !color1.equals(new NullColor())) {
                    count++;
                }
            }

            if (count > maxCount) {
                maxCount = count;
                maxFreq = color1;
            }
        }

        if (maxCount > 4) return maxFreq;
        else return initialColor;
    }

    private Color getValue(Color[][] colors, int x, int y){
        try {
            return colors[y][x];
        } catch (IndexOutOfBoundsException e) {
            return new NullColor();
        }
    }

}
