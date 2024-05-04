package com.vandenbreemen.viddisplayrast.game;

import com.vandenbreemen.viddisplayrast.data.DisplayRaster;
import com.vandenbreemen.viddisplayrast.data.GameDataRequirements;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RunnerTest {

    static final int SCREEN_WIDTH = 800;
    static final int SCREEN_HEIGHT = 600;
    static final int SPRITE_WIDTH = 8;
    static final int SPRITE_HEIGHT = 8;
    static final int MAX_BYTES = 1024;

    GameDataRequirements requirements =
            new GameDataRequirements(SCREEN_WIDTH, SCREEN_HEIGHT, SPRITE_WIDTH, SPRITE_HEIGHT, MAX_BYTES);

    @Test
    public void testNewFrame() {
        Runner runner = new Runner(requirements);
        DisplayRaster displayRaster = runner.newFrame();
        assertEquals(SCREEN_WIDTH, displayRaster.getXDim());
        assertEquals(SCREEN_HEIGHT, displayRaster.getYDim());
    }


}