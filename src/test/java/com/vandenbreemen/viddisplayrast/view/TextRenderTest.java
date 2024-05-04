package com.vandenbreemen.viddisplayrast.view;

import com.vandenbreemen.viddisplayrast.data.DisplayRaster;
import org.junit.jupiter.api.Test;

class TextRenderTest {

    @Test
    public void shouldRenderRasterAsAsciiArt() {
        //  Given
        DisplayRaster raster = new DisplayRaster(10, 10);

        raster.setPixel(5, 5, (byte)1);
        raster.setPixel(6, 5, (byte)1);
        raster.setPixel(5, 6, (byte)1);
        raster.setPixel(6, 6, (byte)1);

        //  When
        String asciiArt = new TextRender().renderRaster(raster);

        //  Then
        System.out.println(asciiArt);
    }

}