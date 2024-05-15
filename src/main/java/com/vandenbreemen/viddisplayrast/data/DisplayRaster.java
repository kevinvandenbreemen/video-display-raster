package com.vandenbreemen.viddisplayrast.data;

/**
 * 2 dimensional array of bytes corresponding to pixels on the screen
 */
public class DisplayRaster {

    //  Private x and y dimensions
    private final int xDim;
    private final int yDim;

    //  Raster proper
    private final byte[][] raster;

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

    /**
     * Generates a view of the raster from the specified coordinates
     * @param fromX
     * @param fromY
     * @param toXInclusive
     * @param toYInclusive
     * @return
     */
    public DisplayRaster view(int fromX, int fromY, int toXInclusive, int toYInclusive) {

        //  Validate incoming parameters
        if(fromX < 0 || fromY < 0 || toXInclusive >= xDim || toYInclusive >= yDim){
            throw new IllegalArgumentException(
                    "Invalid view coordinates (fromX: " + fromX + ", fromY: " + fromY + ", toX: " + toXInclusive + ", toY: "
                            + toYInclusive + "), vs actual dimensions (x: " + xDim + ", y: " + yDim + ")");
        }

        DisplayRaster view = new DisplayRaster(toXInclusive - fromX + 1, toYInclusive - fromY + 1);

        //  Copy over using System.arrayCopy
        for(int x=fromX; x<=toXInclusive; x++){
            System.arraycopy(raster[x], fromY, view.raster[x-fromX], 0, toYInclusive - fromY + 1);
        }

        return view;
    }
}
