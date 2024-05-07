package com.vandenbreemen.viddisplayrast.data;

/**
 * Given a byte determine integer color values from 0 to 255
 */
public class ByteColorDataInteractor {

    private static final int colorStep = (int) (double) (256 / 6);

    private int getBrightness(byte colorByte) {
        //  Get the first 2 bits of the byte as a brightness value
        return (colorByte & 0b11000000) >> 6;
    }

    public int getRed(byte colorByte) {

        int brightness = getBrightness(colorByte);
        int red = (colorByte & 0b00110000) >> 4;

        return (red * colorStep) + (brightness*colorStep);
    }

    public int getGreen(byte colorByte) {
        int brightness = getBrightness(colorByte);
        int green = (colorByte & 0b00001100) >> 2;

        return (green * colorStep) + (brightness*colorStep);
    }

    public int getBlue(byte colorByte) {
        int brightness = getBrightness(colorByte);
        int blue = colorByte & 0b00000011;

        return (blue * colorStep) + (brightness*colorStep);
    }
}
