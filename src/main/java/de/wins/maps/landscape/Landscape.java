package de.wins.maps.landscape;

import de.wins.maps.color.Color;
import de.wins.maps.color.LandscapeColors;

/***
 * @author Daniil Wins
 * @version 1.0
 */
public record Landscape(int minHeight, Color color) {

    public static final Landscape DEEP_OCEAN = new Landscape(50, LandscapeColors.DEEP_OCEAN_COLOR);
    public static final Landscape OCEAN = new Landscape(80, LandscapeColors.OCEAN_COLOR);
    public static final Landscape SHORE = new Landscape(100, LandscapeColors.SHORE_COLOR);
    public static final Landscape LAND = new Landscape(160, LandscapeColors.LAND_COLOR);
    public static final Landscape MOUNTAIN = new Landscape(200, LandscapeColors.MOUNTAIN_COLOR);
    public static final Landscape SNOWY_MOUNTAIN = new Landscape(255, LandscapeColors.SNOWY_MOUNTAIN_COLOR);

    private static final Landscape[] landscapes = {
        DEEP_OCEAN,
        OCEAN,
        SHORE,
        LAND,
        MOUNTAIN,
        SNOWY_MOUNTAIN
    };

    public static Landscape getLandscapeByHeight(int height) {
        for (Landscape landscape : landscapes) {
            if (height <= landscape.minHeight()) return landscape;
        }
        return DEEP_OCEAN;
    }}
