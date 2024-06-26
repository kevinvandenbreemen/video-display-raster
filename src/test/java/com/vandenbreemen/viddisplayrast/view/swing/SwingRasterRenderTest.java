package com.vandenbreemen.viddisplayrast.view.swing;

import com.vandenbreemen.viddisplayrast.data.DisplayRaster;

import javax.swing.*;

public class SwingRasterRenderTest {

    public static void main(String[] args) {
        //  Given
        DisplayRaster raster = new DisplayRaster(10, 10);

        raster.setPixel(2, 4, (byte)100);
        raster.setPixel(3, 4, (byte)132);
        raster.setPixel(4, 4, (byte)100);
        raster.setPixel(5, 4, (byte)100);
        raster.setPixel(5, 5, (byte)120);
        raster.setPixel(4, 5, (byte)188);

        DisplayRaster view = raster.view(2, 4, 3, 5);

        JFrame frame = SwingRasterRender.showTestRenderWindow(raster);

        //  Exit on close
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
