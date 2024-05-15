# video-display-raster
Simple project to experiment with storing pixelated image data and using it for rendering

# Colors
You can use the ```ByteColorDataInteractor``` to work with colors.  For now all sprites' pixels are stored as single bytes, resulting in greater challenge in coloring them

# Displaying the Screen
Pixels are displayed on the screen in the form of a ```DisplayRaster```.  A DisplayRaster is a 2D array of bytes, where each byte represents a color.

## Rendering the DisplayRaster
You can render to any format you like by implementing the ```RasterRender``` interface.  This project provides you with a couple of test renderers to help get you started:

### TextRender
Displays the raster as a text-based grid with filled in tiles corresponding to locations where the byte value is non-zero.

### SwingRasterRender
Renders the raster using a JFrame.