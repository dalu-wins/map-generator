# ProceduralMaps
A java program to procedurally generate maps for video games.

## Map creation algorithm
The algorithm is based on the diamond-square algorithm as described in [this YouTube video](https://www.youtube.com/watch?v=4GuAV1PnurU&t=619s&ab_channel=KlaytonKowalski).

### Black & White
First the program creates a black and white noise map using the diamond square algorithm:

![black and white phase](https://github.com/dalu-wins/ProceduralMaps/blob/master/maps/diamondsquare/basic.bmp)

### Colorizing
The brightness of each pixel represents a height on the map (ocean, shore, land, etc.):

![colorizing phase](https://github.com/dalu-wins/ProceduralMaps/blob/master/maps/diamondsquare/colorized.bmp)

### Smoothing
As you can see the image has some single pixels colorized in a different color as their neighbors. This looks pretty strange, so we run a smoothing algorithm:

![smoothing phase](https://github.com/dalu-wins/ProceduralMaps/blob/master/maps/diamondsquare/smoothed.bmp)

### Upscaling
You can always call the upscaling method in any phase of the map creation. It doubles the resolution of the previous phase's map.
In this example we call it last and get the following result:

![upscaling phase](https://github.com/dalu-wins/ProceduralMaps/blob/master/maps/diamondsquare/upscaled.bmp)

## Setting special variables

| Function                                        | Effect                                                              |
|-------------------------------------------------|---------------------------------------------------------------------|
| `MapBuilder.setAvgHeight(int height)`           | sets the average height of the created map                          |
| `HeightMapBuilder.setRoughness(int roughness)`  | sets a terrain roughness, the lower the value the flatter the map   |
