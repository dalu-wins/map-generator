package de.wins.maps;

import de.wins.maps.mapbuilder.HeightMapBuilder;
import de.wins.maps.mapbuilder.MapBuilder;

/***
 * @author Daniil Wins
 * @version 1.0
 *
 * A procedural map generation algorithm based on the diamond-square algorithm
 */
public class Main {
    public static void main(String[] args) {

        new MapBuilder(257, 257)
            .generate().save("maps/default/basic")
            .colorize().save("maps/default/colorized");

        new HeightMapBuilder(257)
            .generate().save("maps/diamondsquare/basic")
            .colorize().save("maps/diamondsquare/colorized")
            .smooth().save("maps/diamondsquare/smoothed")
            .upscale().save("maps/diamondsquare/upscaled");


    }
}