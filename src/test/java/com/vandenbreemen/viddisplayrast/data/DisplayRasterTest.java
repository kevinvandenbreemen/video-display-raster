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
        raster.setPixel(3, 4, (byte) 15);
        raster.setPixel(5, 5, (byte) 30);

        System.out.println(new TextRender().renderRaster(raster));

        DisplayRaster view = raster.view(2, 2, 4, 4);
        assertNotNull(view);

        System.out.println("View from 2,2 to 4,4:\n"+new TextRender().renderRaster(view));

        byte[][] data = new RawByteDataRasterRenderer().renderRaster(view);
        byte[][] expected = new byte[][]{
                {40, 0,},
                {0, 10,},
        };

        assertArrayEquals(expected, data);

    }

    @Test
    public void shouldPreventAttemptToCreateViewOutOfBounds() {
        DisplayRaster raster = new DisplayRaster(SCREEN_WIDTH, SCREEN_HEIGHT);
        assertThrows(IllegalArgumentException.class, () -> raster.view(0, 0, 9, 9));
    }

    @Test
    public void shouldPreventAttemptToCreateViewOutOfBoundsMessageShouldBeNice() {
        DisplayRaster raster = new DisplayRaster(SCREEN_WIDTH, SCREEN_HEIGHT);
        try {
            raster.view(0, 0, 9, 9);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            assertEquals("Invalid view coordinates (fromX: 0, fromY: 0, toX: 9, toY: 9), vs actual dimensions (x: 8, y: 8)", e.getMessage());
        }
    }

    @Test
    public void shouldProperlyAddressXAndYWhenCreatingViews() {
        DisplayRaster raster = new DisplayRaster(10, 10);

        raster.setPixel(2, 4, (byte)100);
        raster.setPixel(3, 4, (byte)132);
        raster.setPixel(4, 4, (byte)100);
        raster.setPixel(5, 4, (byte)100);
        raster.setPixel(5, 5, (byte)120);
        raster.setPixel(4, 5, (byte)188);

        DisplayRaster view = raster.view(2, 4, 4, 6);

        assertEquals((byte)100, view.getPixel(0, 0));
        assertEquals( (byte)132, view.getPixel(1, 0));
        assertEquals((byte)0, view.getPixel(0, 1));
        assertEquals((byte)0, view.getPixel(1, 1));
    }

    @Test
    public void shouldPreventCreatingViewsWithInvalidCoordinates() {
        DisplayRaster raster = new DisplayRaster(SCREEN_WIDTH, SCREEN_HEIGHT);
        assertThrows(IllegalArgumentException.class, () -> raster.view(2, 2, 1, 1));
    }

    @Test
    public void shouldProvideAHash() {
        DisplayRaster raster = new DisplayRaster(10, 10);

        raster.setPixel(2, 4, (byte)100);

        int hash1 = raster.hashCode();

        raster.setPixel(3, 4, (byte)132);

        int hash2 = raster.hashCode();

        assertNotEquals(hash1, hash2);

        System.out.println("Hash1: "+hash1);
        System.out.println("Hash2: "+hash2);
    }

}