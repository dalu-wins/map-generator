package de.wins.maps.mapbuilder;

import de.wins.maps.color.Color;
import de.wins.maps.Map;

import java.util.Arrays;
import java.util.Random;

/***
 * @author Daniil Wins
 * @version 1.0
 */
public class HeightMapBuilder extends MapBuilder {

    private int roughness = 128;

    private final Random random = new Random();

    private final int size;

    public HeightMapBuilder(int size) {
        super(size, size);
        this.size = size;

        if ((size - 1 & size - 2) != 0) throw new RuntimeException("Map size must equal (2 to the n-th power) + 1");
    }

    public HeightMapBuilder setRoughness(int roughness) {
        this.roughness = roughness;
        return this;
    }

    private void setRandomCorners(Color[][] colors) {

        int topLeft = random.nextInt(0, Color.MAX_VALUE + 1);
        int topRight = random.nextInt(0, Color.MAX_VALUE + 1);
        int bottomLeft = random.nextInt(0, Color.MAX_VALUE + 1);
        int bottomRight = random.nextInt(0, Color.MAX_VALUE + 1);

        colors[0][0] = Color.toGrayscale(topLeft);
        colors[0][size - 1] = Color.toGrayscale(topRight);
        colors[size - 1][0] = Color.toGrayscale(bottomLeft);
        colors[size - 1][size - 1] = Color.toGrayscale(bottomRight);

    }

    @Override
    public Map generate() {

        Color [][] colors = super.generate().getPixels();

        setRandomCorners(colors);

        int currentChunkSize = size - 1;
        int currentRoughness = roughness;
        while (currentChunkSize > 1) {
            int half = currentChunkSize / 2;
            squareStep(colors, currentChunkSize, half, currentRoughness);
            diamondStep(colors, currentChunkSize, half, currentRoughness);
            currentChunkSize = half;
            if (currentRoughness >= 2) currentRoughness /= 2;
        }

        alignHeight(colors);

        return new Map(colors);

    }

    private void alignHeight(Color[][] colors) {
        int currentAvgHeight = getCurrentAvgHeight(colors);
        int shift = avgHeight - currentAvgHeight;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                colors[y][x].shift(shift);
            }
        }
    }

    private int getCurrentAvgHeight(Color[][] colors) {
        int currentHeightSum = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                currentHeightSum += colors[y][x].getGrayscale();
            }
        }
        return currentHeightSum / (colors.length*colors.length);
    }

    private void diamondStep(Color [][] colors, int chunkSize, int half, int roughness) {

        for (int y = 0; y < size; y += half) {
            for (int x = (y+half) % chunkSize; x < size; x += chunkSize) {

                int[] values = {
                    getValue(colors, x, y - half),
                    getValue(colors, x - half, y),
                    getValue(colors, x + half, y),
                    getValue(colors, x, y + half)
                };

                // remove zeros to calculate avg
                int[] filtered = Arrays.stream(values).filter(num -> num != -1).toArray();
                int average = getAverage(filtered);

                // add random roughness
                int val = average + random.nextInt(-roughness, roughness);
                colors[y][x] = Color.toGrayscale(val);

            }
        }

    }

    private void squareStep(Color [][] colors, int chunkSize, int half, int roughness) {

        for (int y = 0; y < size - 1; y += chunkSize) {
            for (int x = 0; x < size - 1; x += chunkSize) {

                int [] values = {
                    getValue(colors, x, y),
                    getValue(colors, x + chunkSize, y),
                    getValue(colors, x, y + chunkSize),
                    getValue(colors, x + chunkSize, y + chunkSize)
                };

                // remove zeros to calculate avg
                int [] filtered = Arrays.stream(values).filter(num -> num != 0).toArray();
                int average = getAverage(filtered);

                // add random roughness
                int val = average + random.nextInt(-roughness, roughness);
                colors[y+half][x+half] = Color.toGrayscale(val);

            }
        }

    }

    public int getValue(Color[][] colors, int x, int y){
        try {
            return colors[y][x].getGrayscale();
        } catch (IndexOutOfBoundsException e) {
            return -1;
        }
    }

    public int getAverage(int[] array) {
        if (array.length == 0) return 0;

        int result = 0;
        for (int i : array) result += i;
        return result / array.length;
    }
}
