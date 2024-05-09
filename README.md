# Map Generator
Map generator based on the diamond-square algorithm as explained in [this](https://www.youtube.com/watch?v=4GuAV1PnurU&t=619s&ab_channel=KlaytonKowalski) YouTube video.
## Map Generation Process
```java
// This is an example of how to build a simple map

new DiamondSquareMapBuilder(257)
    .setRoughness(220)
    .setAvgHeight(100)
    
    .generate().save("maps/diamondsquare/basic")
    .colorize().save("maps/diamondsquare/colorized")
    .smooth().save("maps/diamondsquare/smoothed")
    .upscale().save("maps/diamondsquare/upscaled");
```
<table>
<tr>
    <td>basic</td>
    <td>colorized</td>
    <td>smoothed</td>
    <td>upscaled</td>
  </tr>
  <tr>
    <td><img src="https://github.com/dalu-wins/procedural-maps/blob/master/maps/diamondsquare/basic.bmp"></td>
    <td><img src="https://github.com/dalu-wins/procedural-maps/blob/master/maps/diamondsquare/colorized.bmp"></td>
    <td><img src="https://github.com/dalu-wins/procedural-maps/blob/master/maps/diamondsquare/smoothed.bmp"></td>
    <td><img src="https://github.com/dalu-wins/procedural-maps/blob/master/maps/diamondsquare/upscaled.bmp"></td>
  </tr>
</table>
