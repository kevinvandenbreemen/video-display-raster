package com.vandenbreemen.viddisplayrast.data;

import com.vandenbreemen.viddisplayrast.view.RasterRender;

public class RawByteDataRasterRenderer implements RasterRender<byte[][]> {

    @Override
    public byte[][] renderRaster(DisplayRaster raster) {
        byte[][] data = new byte[raster.getXDim()][raster.getYDim()];
        for(int x=0; x<raster.getXDim(); x++){
            for(int y=0; y<raster.getYDim(); y++){
                data[x][y] = raster.getPixel(x, y);
            }
        }
        return data;
    }

}
