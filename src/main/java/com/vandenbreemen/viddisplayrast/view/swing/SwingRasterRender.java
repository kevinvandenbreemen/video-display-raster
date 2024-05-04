package com.vandenbreemen.viddisplayrast.view.swing;

import com.vandenbreemen.viddisplayrast.data.DisplayRaster;
import com.vandenbreemen.viddisplayrast.view.RasterRender;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Renders a raster as a Swing component
 */
public class SwingRasterRender implements RasterRender<JPanel> {

    private int pixelSizeX;
    private int pixelSizeY;

    public SwingRasterRender(int pixelSizeX, int pixelSizeY){
        this.pixelSizeX = pixelSizeX;
        this.pixelSizeY = pixelSizeY;
    }

    public void setPixelSizeX(int pixelSizeX) {
        this.pixelSizeX = pixelSizeX;
    }

    public void setPixelSizeY(int pixelSizeY) {
        this.pixelSizeY = pixelSizeY;
    }

    @Override
    public JPanel renderRaster(DisplayRaster raster) {
        JPanel panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                //  Calculate size of each square based on pixelsizex and y
                int squareWidth = pixelSizeX / raster.getXDim();
                int squareHeight = pixelSizeY / raster.getYDim();


                for(int y=0; y<raster.getYDim(); y++){
                    for(int x=0; x<raster.getXDim(); x++){
                        g.setColor(raster.getPixel(x, y) > 0 ? Color.WHITE : Color.BLACK);
                        g.fillRect(x*squareWidth, y*squareHeight, squareWidth, squareHeight);
                    }
                }
            }
        };

        panel.setPreferredSize(new Dimension(pixelSizeX, pixelSizeY));
        return panel;
    }

    public static void showTestRenderWindow(DisplayRaster raster){
        JFrame frame = new JFrame("Raster Render Test");
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
