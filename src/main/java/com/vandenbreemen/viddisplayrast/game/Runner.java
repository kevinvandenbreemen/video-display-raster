package com.vandenbreemen.viddisplayrast.game;

import com.vandenbreemen.viddisplayrast.data.DisplayRaster;
import com.vandenbreemen.viddisplayrast.data.GameDataRequirements;

/**
 * Master execution of the game.  Draws each frame etc
 */
public class Runner {

    private GameDataRequirements requirements;

    public Runner(GameDataRequirements requirements) {
        this.requirements = requirements;
    }

    /**
     * Gets a new frame for use in rendering objects
     * @return
     */
    public DisplayRaster newFrame() {
        return new DisplayRaster(requirements.getScreenWidth(), requirements.getScreenHeight());
    }

}
