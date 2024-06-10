package com.vandenbreemen.viddisplayrast.data;

/**
 * Details on the requirements for the game.  These include the dimensions of the screen and
 * the size of the sprites as well as the maximum number of bytes that can be stored in the game
 */
public class GameDataRequirements {

    private final int screenWidth;
    private final int screenHeight;
    private final int spriteWidth;
    private final int spriteHeight;

    private final int maxBytes;

    private final byte[] spriteData;

    /**
     * A default sprite for highlighting error cases
     */
    private final byte[] defaultSprite;

    public GameDataRequirements(int screenWidth, int screenHeight, int spriteWidth, int spriteHeight, int maxBytes){
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
        this.maxBytes = maxBytes;
        this.spriteData = new byte[maxBytes];   //  XXX Where does this actually come from???

        this.defaultSprite = new byte[spriteWidth * spriteHeight];
        //  Draw a black X on a white background
        for(int y=0; y<spriteHeight; y++){
            for(int x=0; x<spriteWidth; x++){
                if(x == y || x == spriteWidth - y - 1){
                    defaultSprite[y * spriteWidth + x] = 127;
                }
                else{
                    defaultSprite[y * spriteWidth + x] = 1;
                }
            }
        }
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
     * Get default sprite tile for use in error cases
     * @return
     */
    public byte[] getDefaultSprite() {
        return defaultSprite;
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

        //  Verify the location is valid
        if(location >= maxBytes / (spriteWidth * spriteHeight)){
            throw new IllegalArgumentException("Location is invalid (" + location + "), max is " + (maxBytes / (spriteWidth * spriteHeight)));
        }

        System.arraycopy(data, 0, spriteData, location * (spriteHeight*spriteWidth), data.length);
    }

    public byte[] getSpriteData() {
        return spriteData;
    }

    public byte[] getSpriteData(int location) {

        //  Verify the location is valid
        if(location >= maxBytes / (spriteWidth * spriteHeight)){
            throw new IllegalArgumentException("Location is invalid (" + location + "), max is " + (maxBytes / (spriteWidth * spriteHeight)));
        }

        byte[] data = new byte[spriteWidth * spriteHeight];
        System.arraycopy(spriteData, location * (spriteHeight*spriteWidth), data, 0, data.length);
        return data;
    }
}
