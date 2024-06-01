package com.vandenbreemen.viddisplayrast.data;

import java.util.Arrays;

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
     * @param toX (non-inclusive)
     * @param toY (non-inclusive)
     * @return
     */
    public DisplayRaster view(int fromX, int fromY, int toX, int toY) {

        //  Validate incoming parameters
        if(fromX < 0 || fromY < 0 || toX >= xDim || toY >= yDim){
            throw new IllegalArgumentException(
                    "Invalid view coordinates (fromX: " + fromX + ", fromY: " + fromY + ", toX: " + toX + ", toY: "
                            + toY + "), vs actual dimensions (x: " + xDim + ", y: " + yDim + ")");
        }

        //  Ensure fromX and fromY are less than toX and toY
        if(fromX >= toX || fromY >= toY){
            throw new IllegalArgumentException("Invalid view coordinates (fromX: " + fromX + ", fromY: " + fromY + ", toX: " + toX + ", toY: " + toY + ")");
        }

        DisplayRaster view = new DisplayRaster(toX - fromX, toY - fromY);

        //  Copy over using System.arrayCopy
        for(int x=fromX; x<toX; x++){
            System.arraycopy(raster[x], fromY, view.raster[x-fromX], 0, toY - fromY);
        }

        return view;
    }

    private void copyRow(byte[] source, byte[] dest, int to){
        System.arraycopy(source, 0, dest, to, source.length);
    }

    public void writeRaster(int x, int y, DisplayRaster anotherRaster) {

        //  Do this with system array copy for each row:
        for (int j = 0; j < anotherRaster.getYDim(); j++) {
            copyRow(anotherRaster.raster[j], raster[j + y], x);
        }
    }

    /**
     * Provides a hash based on the contents of the raster
     * @return a hash based on the contents of the raster
     */
    @Override
    public int hashCode() {
        return Arrays.deepHashCode(raster);
    }
}
