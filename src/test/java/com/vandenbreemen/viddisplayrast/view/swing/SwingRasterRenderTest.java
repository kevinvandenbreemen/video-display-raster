package com.vandenbreemen.viddisplayrast.view.swing;

import com.vandenbreemen.viddisplayrast.data.DisplayRaster;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class SwingRasterRenderTest {

    public static void main(String[] args) {
        //  Given
        DisplayRaster raster = new DisplayRaster(10, 10);

        raster.setPixel(4, 4, (byte)100);
        raster.setPixel(5, 4, (byte)100);
        raster.setPixel(5, 5, (byte)120);
        raster.setPixel(4, 5, (byte)188);


        JFrame frame = SwingRasterRender.showTestRenderWindow(raster);

        //  Exit on close
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
