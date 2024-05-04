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

        raster.setPixel(5, 5, (byte)1);
        raster.setPixel(6, 5, (byte)1);
        raster.setPixel(5, 6, (byte)1);
        raster.setPixel(6, 6, (byte)1);


        JFrame frame = new JFrame("Raster Render Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new FlowLayout());

        SwingRasterRender render = new SwingRasterRender(400, 400);

        frame.add(render.renderRaster(raster));
        frame.pack();

        //  Provide for detecting resize:
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {

                render.setPixelSizeX(frame.getWidth());
                render.setPixelSizeY(frame.getHeight());

                frame.getContentPane().getComponent(0).repaint();
            }
        });

        frame.setVisible(true);
    }

}
