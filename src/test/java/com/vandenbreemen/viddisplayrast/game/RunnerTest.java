package com.vandenbreemen.viddisplayrast.game;

import com.vandenbreemen.viddisplayrast.data.DisplayRaster;
import com.vandenbreemen.viddisplayrast.data.GameDataRequirements;
import com.vandenbreemen.viddisplayrast.view.swing.SwingRasterRender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

        runner.newFrame();

        //  Draw the first sprite asset at coord 200, 100 on the screen
        runner.drawSpriteAt(0, 50, 60);

        DisplayRaster raster = runner.newFrame();

        //  Display the frame for test
        SwingRasterRender.showTestRenderWindow(raster);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

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


}