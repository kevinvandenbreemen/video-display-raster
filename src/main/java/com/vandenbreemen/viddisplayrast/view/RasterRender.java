package com.vandenbreemen.viddisplayrast.view;

import com.vandenbreemen.viddisplayrast.data.DisplayRaster;

/**
 * Renders a raster as the given output type
 * @param <OutputType>
 */
public interface RasterRender<OutputType> {

    public OutputType renderRaster(DisplayRaster raster);

}
