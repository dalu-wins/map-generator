package de.wins.maps;

/***
 * @author Daniil Wins
 * @version 1.0
 *
 * A procedural map generation algorithm based on the diamond-square algorithm
 */
public class Main {
    public static void main(String[] args) {

        Map map1 = new MapBuilder(5, 5)
            .setAvgHeight(100)
            .generate()
            .colorize()
            .save("flatMap");

        Map map2 = new HeightMapBuilder(257)
            .setRoughness(170)
            .setAvgHeight(100)
            .generate()
            .colorize()
            .smooth()
            .save("diamondSquare");

    }
}