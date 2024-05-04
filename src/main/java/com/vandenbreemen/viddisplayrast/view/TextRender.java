package com.vandenbreemen.viddisplayrast.view;

import com.vandenbreemen.viddisplayrast.data.DisplayRaster;

/**
 * More of a test of rendering items on the screen, renders
 * the entire raster as ascii art
 */
public class TextRender implements RasterRender<String> {


    @Override
    public String renderRaster(DisplayRaster raster) {
        //  Generate a grid of ascii squares for pixels with a value greater than 0
        StringBuilder builder = new StringBuilder();
        for(int y=0; y<raster.getYDim(); y++){
            for(int x=0; x<raster.getXDim(); x++){
                builder.append(raster.getPixel(x, y) > 0 ? "X" : " ");
            }
            builder.append("\n");
        }

        return builder.toString();
    }
}
