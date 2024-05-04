package com.vandenbreemen.viddisplayrast.data;

/**
 * 2 dimensional array of bytes corresponding to pixels on the screen
 */
public class DisplayRaster {

    //  Private x and y dimensions
    private int xDim;
    private int yDim;

    //  Raster proper
    private byte[][] raster;

    //  Constructor
    public DisplayRaster(int xDim, int yDim){
        this.xDim = xDim;
        this.yDim = yDim;

        this.raster = new byte[xDim][yDim];
    }


    public int getYDim() {
        return yDim;
    }

    public int getXDim() {
        return xDim;
    }

    public byte getPixel(int x, int y) {
        return raster[x][y];
    }

    public void setPixel(int x, int y, byte b) {
        raster[x][y] = b;
    }
}
