package de.wins.maps;

import java.util.Arrays;
import java.util.Iterator;

/***
 * @author Daniil Wins
 * @version 1.0
 */
public class Color implements Iterable<Integer> {

    public static final int MAX_VALUE = 255;
    public static final int MIN_VALUE = 0;
    private final int[] rgb = new int[3];

    public Color(int red, int green, int blue) {
        this.rgb[0] = red;
        this.rgb[1] = green;
        this.rgb[2] = blue;
        correctValueRange();
    }

    /***
     * Checks all values if they are in range 0 - 255
     * and sets them in bounds if they aren't
     */
    public void correctValueRange() {
        for (int i = 0; i < 3; i++) {
            int color = Math.min(this.rgb[i], MAX_VALUE);
            this.rgb[i] = Math.max(color, MIN_VALUE);
        }
    }

    public int getRGBAsInt() {
        int rgb = getRed();
        rgb = (rgb << 8) +  getGreen();
        rgb = (rgb << 8) +  getBlue();
        return rgb;
    }

    public int getGrayscale() {
        return (getRed() + getGreen() + getBlue()) / 3;
    }

    public static Color toGrayscale(int val) {
        return new Color(val, val, val);
    }

    public int getRed() {
        return rgb[0];
    }

    public void setRed(int red) {
        this.rgb[0] = red;
        correctValueRange();
    }

    public int getGreen() {
        return rgb[1];
    }

    public void setGreen(int green) {
        this.rgb[1] = green;
        correctValueRange();
    }

    public int getBlue() {
        return rgb[2];
    }

    public void setBlue(int blue) {
        this.rgb[2] = blue;
        correctValueRange();
    }

    public void shift(int shift) {
        setRed(getRed() + shift);
        setGreen(getGreen() + shift);
        setBlue(getBlue() + shift);
    }

    @Override
    public Iterator<Integer> iterator() {
        return Arrays.stream(rgb).iterator();
    }

}

