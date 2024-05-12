package com.vandenbreemen.viddisplayrast.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

}