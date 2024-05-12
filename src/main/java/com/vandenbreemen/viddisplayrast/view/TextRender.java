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
        StringBuilder builder = new StringBuilder().append(raster.getXDim()).append("x").append(raster.getYDim()).append(" Raster, non-zero bytes marked:").append("\n");

        for(int y=0; y<raster.getYDim(); y++){
            builder.append("+ - ".repeat(Math.max(0, raster.getXDim()))).append("+").append("\n");
            for(int x=0; x<raster.getXDim(); x++){
                builder.append("| ");
                builder.append(raster.getPixel(x, y) > 0 ? "X" : " ").append(" ");
            }
            builder.append("|");
            builder.append("\n");
        }

        builder.append("+ - ".repeat(Math.max(0, raster.getXDim()))).append("+");

        return builder.toString();
    }
}
