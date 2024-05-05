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

    private byte[] spriteData;

    public GameDataRequirements(int screenWidth, int screenHeight, int spriteWidth, int spriteHeight, int maxBytes){
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
        this.maxBytes = maxBytes;
        this.spriteData = new byte[maxBytes];   //  XXX Where does this actually come from???
    }

    @Override
    public String toString() {
        return (getClass().getSimpleName() + ":  Game data requirements: screenDim:  " + screenWidth + "x" + screenHeight + " -  sprites: " + spriteWidth + "x" + spriteHeight + " max bytes: " + maxBytes + ", allowing for a total of " + (maxBytes / (spriteWidth * spriteHeight)) + " sprites");
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

    /**
     * Add raw game data to the requirements
     * @param location
     * @param data
     */
    public void setData(int location, byte[] data){
        //  Verify the incoming data matches sprite size
        if(data.length != spriteWidth * spriteHeight){
            throw new IllegalArgumentException("Data does not match sprite size");
        }
        System.arraycopy(data, 0, spriteData, location, data.length);
    }

    public byte[] getSpriteData() {
        return spriteData;
    }
}
