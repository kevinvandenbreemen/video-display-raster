package com.vandenbreemen.viddisplayrast.game;

import com.vandenbreemen.viddisplayrast.data.DisplayRaster;
import com.vandenbreemen.viddisplayrast.data.GameDataRequirements;
import com.vandenbreemen.viddisplayrast.data.RawByteDataRasterRenderer;
import com.vandenbreemen.viddisplayrast.view.TextRender;
import com.vandenbreemen.viddisplayrast.view.swing.SwingRasterRender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RunnerTest {

    static final int SCREEN_WIDTH = 100;
    static final int SCREEN_HEIGHT = 75;
    static final int SPRITE_WIDTH = 8;
    static final int SPRITE_HEIGHT = 8;
    static final int MAX_BYTES = 1024;

    GameDataRequirements requirements;

    @BeforeEach
    public void setup() {
        requirements =
                new GameDataRequirements(SCREEN_WIDTH, SCREEN_HEIGHT, SPRITE_WIDTH, SPRITE_HEIGHT, MAX_BYTES);

        System.out.println("Setup complete with requirements:\n" + requirements.toString());
    }


    @Test
    public void testNewFrame() {
        Runner runner = new Runner(requirements);
        DisplayRaster displayRaster = runner.newFrame();
        assertEquals(SCREEN_WIDTH, displayRaster.getXDim());
        assertEquals(SCREEN_HEIGHT, displayRaster.getYDim());
    }

    //  @Test
    public void testAddSpriteAndRender() {
        Runner runner = new Runner(requirements);
        requirements.setData(0, new byte[]{
                0, 0, 100, 100, 100, 0, 0, 0,
                0, 0, 100, 127, 100, 127, 0, 0,
                0, 0,  100, 100, 100, 100, 0, 0,
                0, 100, 80, 80, 80, 100, 100, 100,
                0, 100, 100, 100, 80, 100, 0, 100,
                100, 100, 100, 100, 100, 100, 0, 0,
                100, 100, 100, 100, 100, 100, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0
        });
        requirements.setData(1, new byte[]{
                //  Bytes arranged in the shape of a cross, 8 by 8
                0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 100, 100, 100, 0, 0, 0,
                0, 0, 100, 127, 100, 0, 0, 0,
                0, 0, 100, 100, 100, 0, 0, 0,
                0, 0, 100, 100, 100, 0, 0, 0,
                0, 0, 100, 100, 100, 0, 0, 0,
                0, 0, 100, 100, 100, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0
        });

        runner.newFrame();

        //  Draw the first sprite asset at coord 200, 100 on the screen
        runner.drawSpriteAt(0, 50, 60);
        runner.drawSpriteAt(1, 70, 60);

        DisplayRaster raster = runner.newFrame();

        //  Display the frame for test
        SwingRasterRender.showTestRenderWindow(raster);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    //  Just a test for me to verify a known good state before I go on with trying to add shifting the screen around
    @Test
    public void shouldAddSpritesInAWayThatIsTestableUsingByteArrayComparisons() {
        GameDataRequirements simpleScreen = new GameDataRequirements(
                8, 8,
                2, 2,
                8
        );

        simpleScreen.setData(0, new byte[]{
                0, 10,
                10, 0
        });
        simpleScreen.setData(1, new byte[]{
                30, 20,
                20, 30
        });

        Runner runner = new Runner(simpleScreen);
        runner.newFrame();

        runner.drawSpriteAt(0, 0, 0);
        runner.drawSpriteAt(1, 2, 2);
        byte[][] expected = new byte[][]{
                { 0, 10, 0, 0, 0, 0, 0, 0 },
                { 10, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 30, 20, 0, 0, 0, 0 },
                { 0, 0, 20, 30, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 }
        };

        DisplayRaster raster = runner.newFrame();
        byte[][] result = new RawByteDataRasterRenderer().renderRaster(raster);

        System.out.println(new TextRender().renderRaster(raster));

        assertArrayEquals(expected, result, "Should have rendered the sprites in the correct locations");

    }

    @Test
    public void shouldAllowForSkippingDrawingSpecificBytesWhenDrawing() {
        GameDataRequirements simpleScreen = new GameDataRequirements(
                8, 8,
                2, 2,
                8
        );

        simpleScreen.setData(0, new byte[]{
                0, 10,
                10, 0
        });
        simpleScreen.setData(1, new byte[]{
                30, 20,
                20, 30
        });

        Runner runner = new Runner(simpleScreen);
        runner.newFrame();

        runner.drawSpriteAt(0, 0, 0);
        runner.drawSpriteAt(1, 2, 2);
        byte[][] expected = new byte[][]{
                { 0, 10, 0, 0, 0, 0, 0, 0 },
                { 10, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 30, 20, 0, 0, 0, 0 },
                { 0, 0, 20, 30, 10, 0, 0, 0 },
                { 0, 0, 0, 10, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 }
        };

        //  Draw first sprite, but asking the runner not to set bytes with a value of 0
        runner.drawSpriteAt(0, 3, 3, (byte) 0);

        DisplayRaster raster = runner.newFrame();
        byte[][] result = new RawByteDataRasterRenderer().renderRaster(raster);

        System.out.println(new TextRender().renderRaster(raster));

        assertArrayEquals(expected, result, "Should have rendered the sprites in the correct locations");
    }


    //  Verify we get an illegal argument exception
    @Test
    public void testShouldStopAddingSpriteOutsideBounds() {
        Runner runner = new Runner(requirements);
        requirements.setData(0, new byte[]{
                0, 0, 100, 100, 100, 0, 0, 0,
                0, 0, 100, 127, 100, 127, 0, 0,
                0, 0,  100, 100, 100, 100, 0, 0,
                0, 100, 80, 80, 80, 100, 100, 100,
                0, 100, 100, 100, 80, 100, 0, 100,
                100, 100, 100, 100, 100, 100, 0, 0,
                100, 100, 100, 100, 100, 100, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0
        });

        runner.newFrame();

        //  Draw the first sprite asset at coord 200, 100 on the screen
        assertThrows(IllegalArgumentException.class, () -> runner.drawSpriteAt(0, 200, 100));


    }

    @Test
    public void shouldDrawRawRasterAtLocation() {
        Runner runner = new Runner(requirements);
        DisplayRaster rawRaster = new DisplayRaster(3, 3);

        //  Draw an X

        rawRaster.setPixel(0, 0, (byte) 1);
        rawRaster.setPixel(1, 1, (byte) 1);
        rawRaster.setPixel(2, 2, (byte) 1);
        rawRaster.setPixel(0, 2, (byte) 1);
        rawRaster.setPixel(2, 0, (byte) 1);

        System.out.println(new TextRender().renderRaster(rawRaster));

        runner.drawRasterAt(rawRaster, 1, 1);

        DisplayRaster rendered = runner.newFrame();

        System.out.println(new TextRender().renderRaster(rendered));

        //  Verify the X is displayed
        assertEquals(0, rendered.getPixel(0, 0));
        assertEquals(1, rendered.getPixel(1, 1));
        assertEquals(1, rendered.getPixel(2, 2));
        assertEquals(1, rendered.getPixel(3, 3));
        assertEquals(1, rendered.getPixel(1, 3));
        assertEquals(1, rendered.getPixel(3, 1));
        assertEquals(0, rendered.getPixel(4, 4));
        assertEquals(0, rendered.getPixel(4, 1));


    }


}