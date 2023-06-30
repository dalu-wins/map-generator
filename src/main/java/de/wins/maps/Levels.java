package de.wins.maps;

/***
 * @author Daniil Wins
 * @version 1.0
 */
public class Levels {
    public static final Landscape DEEP_OCEAN = new Landscape(50, Colors.DEEP_OCEAN_COLOR);
    public static final Landscape OCEAN = new Landscape(80, Colors.OCEAN_COLOR);
    public static final Landscape SHORE = new Landscape(100, Colors.SHORE_COLOR);
    public static final Landscape LAND = new Landscape(160, Colors.LAND_COLOR);
    public static final Landscape MOUNTAIN = new Landscape(200, Colors.MOUNTAIN_COLOR);
    public static final Landscape SNOWY_MOUNTAIN = new Landscape(255, Colors.SNOWY_MOUNTAIN_COLOR);

    private static final Landscape[] landscapes = {
        Levels.DEEP_OCEAN,
        Levels.OCEAN,
        Levels.SHORE,
        Levels.LAND,
        Levels.MOUNTAIN,
        Levels.SNOWY_MOUNTAIN
    };

    public static Landscape getLandscapeByHeight(int height) {
        for (Landscape landscape : landscapes) {
            if (height <= landscape.minHeight()) return landscape;
        }
        return DEEP_OCEAN;
    }
}
