package com.vandenbreemen.viddisplayrast.data;

/**
 * Given a byte determine integer color values from 0 to 255
 */
public class ByteColorDataInteractor {

    private static final int CHANNEL_STEP_COUNT = 3;

    /**
     * Each color consists of one of 3 channel values along with a value corresponding to the first 2
     * brightness bits
     */
    private static final int NUM_COLOR_STEPS = CHANNEL_STEP_COUNT * 2;
    private static final int COLOR_STEP = (int) (double) (256 / NUM_COLOR_STEPS);

    public int getBrightness(byte colorByte) {
        //  Get the first 2 bits of the byte as a brightness value
        return (colorByte & 0b11000000) >> 6;
    }

    public int getRed(byte colorByte) {

        int red = getRedChannel(colorByte);
        if(red == 0) {
            return 0;
        }

        int brightness = getBrightness(colorByte);

        return (red * COLOR_STEP) + (brightness* COLOR_STEP);
    }

    public int getGreen(byte colorByte) {

        int green = getGreenChannel(colorByte);
        if(green == 0) {
            return 0;
        }
        int brightness = getBrightness(colorByte);

        return (green * COLOR_STEP) + (brightness* COLOR_STEP);
    }

    public int getBlue(byte colorByte) {

        int blue = getBlueChannel(colorByte);
        if(blue == 0) {
            return 0;
        }
        int brightness = getBrightness(colorByte);

        return (blue * COLOR_STEP) + (brightness* COLOR_STEP);
    }

    /**
     * Get the raw red channel number (not the actual color value)
     * @param colorByte
     * @return
     */
    public int getRedChannel(byte colorByte) {
        return (colorByte & 0b00110000) >> 4;
    }

    /**
     * Get the raw green channel number (not the actual color value)
     * @param colorByte
     * @return
     */
    public int getGreenChannel(byte colorByte) {
        return (colorByte & 0b00001100) >> 2;
    }

    /**
     * Get the raw blue channel number (not the actual color value)
     * @param colorByte
     * @return
     */
    public int getBlueChannel(byte colorByte) {
        return colorByte & 0b00000011;
    }

    /**
     * Get the number of allowed colors for each channel
     * @return
     */
    public int getChannelStepCount() {
        return CHANNEL_STEP_COUNT;
    }


    public byte getColorByte(int brightness, int red, int green, int blue) {
        //  First make sure that the values are within the color steps allowed
        //  All values must be within the CHANNEL_STEP_COUNT

        if(brightness < 0 || brightness > CHANNEL_STEP_COUNT){
            throw new IllegalArgumentException("Brightness value must be between 0 and " + (CHANNEL_STEP_COUNT));
        }

        if(red < 0 || red > CHANNEL_STEP_COUNT){
            throw new IllegalArgumentException("Red value must be between 0 and " + (CHANNEL_STEP_COUNT));
        }

        if(green < 0 || green > CHANNEL_STEP_COUNT){
            throw new IllegalArgumentException("Green value must be between 0 and " + (CHANNEL_STEP_COUNT));
        }

        if(blue < 0 || blue > CHANNEL_STEP_COUNT){
            throw new IllegalArgumentException("Blue value must be between 0 and " + (CHANNEL_STEP_COUNT));
        }

        return (byte) ((brightness << 6) | (red << 4) | (green << 2) | blue);
    }
}
