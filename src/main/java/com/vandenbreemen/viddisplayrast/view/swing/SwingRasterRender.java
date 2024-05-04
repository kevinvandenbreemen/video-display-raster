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

                        //  Compute the color as grayscale based on the pixel value
                        //  Make a Grayscale color:
                        int pixelValue = Byte.toUnsignedInt(raster.getPixel(x, y));
                        Color pixelColor = new Color(pixelValue, pixelValue, pixelValue);

                        g.setColor(pixelColor);
                        g.fillRect(x*squareWidth, y*squareHeight, squareWidth, squareHeight);
                    }
                }

                //  Add a tooltip that shows the pixel value when user hovers their mouse over that pixel
                addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
                    @Override
                    public void mouseMoved(java.awt.event.MouseEvent evt) {
                        int x = evt.getX() / squareWidth;
                        int y = evt.getY() / squareHeight;

                        //  Don't continue if the mouse is outside the bounds of the raster
                        if(x < 0 || y < 0 || x >= raster.getXDim() || y >= raster.getYDim()){
                            return;
                        }

                        int pixelValue = Byte.toUnsignedInt(raster.getPixel(x, y));
                        setToolTipText("Pixel value: " + pixelValue);
                    }
                });
            }
        };

        panel.setPreferredSize(new Dimension(pixelSizeX, pixelSizeY));
        return panel;
    }

    /**
     * Generates a simple JFrame that displays the current contents of your raster
     * @param raster
     * @return
     */
    public static JFrame showTestRenderWindow(DisplayRaster raster){

        int width = 400;
        int height = 400;

        JFrame frame = new JFrame("Raster Render Test");

        frame.setBounds(100, 100,  width, height);
        frame.setResizable(false);

        frame.setLayout(new FlowLayout());

        SwingRasterRender render = new SwingRasterRender(width, height);

        frame.add(render.renderRaster(raster));
        frame.pack();

        frame.setVisible(true);
        return  frame;
    }

}
