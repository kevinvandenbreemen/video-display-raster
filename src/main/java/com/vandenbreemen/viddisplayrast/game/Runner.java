package com.vandenbreemen.viddisplayrast.game;

import com.vandenbreemen.viddisplayrast.data.DisplayRaster;
import com.vandenbreemen.viddisplayrast.data.GameDataRequirements;

/**
 * Master execution of the game.  Draws each frame etc
 */
public class Runner {

    private final GameDataRequirements requirements;

    /**
     * Current frame being drawn
     */
    private DisplayRaster raster;

    public Runner(GameDataRequirements requirements) {
        this.requirements = requirements;
        raster = new DisplayRaster(requirements.getScreenWidth(), requirements.getScreenHeight());
    }

    /**
     * Gets a new frame for use in rendering objects, returning the current frame for use in rendering
     * @return
     */
    public DisplayRaster newFrame() {
        DisplayRaster currentRaster = raster;

        raster = new DisplayRaster(requirements.getScreenWidth(), requirements.getScreenHeight());

        return currentRaster;
    }

    public void drawSpriteAt(int spriteIndex, int x, int y) {
        //  Verify screen position is valid
        if(x < 0 || y < 0 || x + requirements.getSpriteWidth() > requirements.getScreenWidth() || y + requirements.getSpriteHeight() > requirements.getScreenHeight()){
            throw new IllegalArgumentException("Sprite position is invalid (x: " + x + ", y: " + y + ")");
        }

        //  Get the sprite data
        byte[] spriteData = new byte[requirements.getSpriteWidth() * requirements.getSpriteHeight()];
        System.arraycopy(requirements.getSpriteData(),
                spriteIndex * requirements.getSpriteWidth() * requirements.getSpriteHeight(), spriteData, 0, spriteData.length);

        //  Draw the sprite
        for(int spriteY=0; spriteY<requirements.getSpriteHeight(); spriteY++){
            for(int spriteX=0; spriteX<requirements.getSpriteWidth(); spriteX++){
                raster.setPixel(x + spriteX, y + spriteY, spriteData[spriteY * requirements.getSpriteWidth() + spriteX]);
            }
        }
    }
}
