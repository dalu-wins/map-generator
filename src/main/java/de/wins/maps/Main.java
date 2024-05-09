package de.wins.maps;

import de.wins.maps.mapbuilder.DiamondSquareMapBuilder;

/***
 * @author Daniil Wins
 * @version 1.0
 *
 * A procedural map generation algorithm based on the diamond-square algorithm
 */
public class Main {
    public static void main(String[] args) {
        //  This is an example of how to build a simple map
        new DiamondSquareMapBuilder(257)
                .setRoughness(220)
                .setAvgHeight(100)
                .generate().save("maps/diamondsquare/basic")
                .colorize().save("maps/diamondsquare/colorized")
                .smooth().save("maps/diamondsquare/smoothed")
                .upscale().save("maps/diamondsquare/upscaled");
    }
}