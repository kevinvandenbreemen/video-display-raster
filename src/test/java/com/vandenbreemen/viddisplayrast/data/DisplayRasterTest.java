package com.vandenbreemen.viddisplayrast.data;

import com.vandenbreemen.viddisplayrast.view.TextRender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DisplayRasterTest {

    static final int SCREEN_WIDTH = 8;
    static final int SCREEN_HEIGHT = 8;
    static final int SPRITE_WIDTH = 2;
    static final int SPRITE_HEIGHT = 2;
    static final int MAX_BYTES = 8;

    GameDataRequirements requirements;

    @BeforeEach
    public void setup() {
        requirements =
                new GameDataRequirements(SCREEN_WIDTH, SCREEN_HEIGHT, SPRITE_WIDTH, SPRITE_HEIGHT, MAX_BYTES);

        System.out.println("Setup complete with requirements:\n" + requirements.toString());
    }

    @Test
    public void shouldSetPixel() {
        DisplayRaster raster = new DisplayRaster(SCREEN_WIDTH, SCREEN_HEIGHT);
        raster.setPixel(0, 0, (byte) 1);
        assertEquals(1, raster.getPixel(0, 0));
    }

    @Test
    public void shouldCreateDisplayRasterView() {
        DisplayRaster raster = new DisplayRaster(SCREEN_WIDTH, SCREEN_HEIGHT);

        raster.setPixel(2, 2, (byte) 40);
        raster.setPixel(3, 3, (byte) 10);
        raster.setPixel(4, 4, (byte) 20);
        raster.setPixel(5, 5, (byte) 30);

        System.out.println(new TextRender().renderRaster(raster));

        DisplayRaster view = raster.view(2, 2, 4, 4);
        assertNotNull(view);

        System.out.println("View from 2,2 to 4,4:\n"+new TextRender().renderRaster(view));

        byte[][] data = new RawByteDataRasterRenderer().renderRaster(view);
        byte[][] expected = new byte[][]{
                {40, 0, 0},
                {0, 10, 0},
                {0, 0, 20}
        };

        assertArrayEquals(expected, data);

    }

}