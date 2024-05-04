package com.vandenbreemen.viddisplayrast.data;

/**
 * Details on the requirements for the game.  These include the dimensions of the screen and
 * the size of the sprites as well as the maximum number of bytes that can be stored in the game
 */
public class GameDataRequirements {

    private int screenWidth;
    private int screenHeight;
    private int spriteWidth;
    private int spriteHeight;

    private int maxBytes;

    public GameDataRequirements(int screenWidth, int screenHeight, int spriteWidth, int spriteHeight, int maxBytes){
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
        this.maxBytes = maxBytes;

        System.out.println("Game data requirements: " + screenWidth + "x" + screenHeight + " sprites: " + spriteWidth + "x" + spriteHeight + " max bytes: " + maxBytes + ", allowing for a total of " + (maxBytes / (spriteWidth * spriteHeight)) + " sprites");
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getSpriteWidth() {
        return spriteWidth;
    }

    public int getSpriteHeight() {
        return spriteHeight;
    }

    public int getMaxBytes() {
        return maxBytes;
    }
}
