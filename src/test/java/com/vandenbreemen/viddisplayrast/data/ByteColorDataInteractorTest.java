package com.vandenbreemen.viddisplayrast.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ByteColorDataInteractorTest {

    @Test
    public void testRedColor() {

        //  Requirement:  First 2 bytes are brightness bytes, subsequent pairings of bytes are red, green, blue.  A color
        //  value of 0 is transparent.
        byte redColor = (byte)0b11110000;

        ByteColorDataInteractor interactor = new ByteColorDataInteractor();
        int red = interactor.getRed(redColor);

        assertEquals(252, red, "Red value should be 252");

    }

    @Test
    public void testGetGreenColor() {
        //  Requirement:  First 2 bytes are brightness bytes, subsequent pairings of bytes are red, green, blue.  A color
        //  value of 0 is transparent.
        byte greenColor = (byte)0b11001100;

        ByteColorDataInteractor interactor = new ByteColorDataInteractor();
        int green = interactor.getGreen(greenColor);

        assertEquals(252, green, "Green value should be 252");
    }

    @Test
    public void testGetBlueColor() {
        //  Requirement:  First 2 bytes are brightness bytes, subsequent pairings of bytes are red, green, blue.  A color
        //  value of 0 is transparent.
        byte blueColor = (byte)0b11000011;

        ByteColorDataInteractor interactor = new ByteColorDataInteractor();
        int blue = interactor.getBlue(blueColor);

        assertEquals(252, blue, "Blue value should be 252");
    }

    @Test
    public void testGetDarkRedColor() {
        //  Requirement:  First 2 bytes are brightness bytes, subsequent pairings of bytes are red, green, blue.  A color
        //  value of 0 is transparent.
        byte redColor = (byte)0b00010000;

        //  3 combinations of darkness (first 2 bits), 3 combinations of red (next 2 bits), 2 combinations of green (next 2 bits), 2 combinations of blue (last 2 bits)


        ByteColorDataInteractor interactor = new ByteColorDataInteractor();
        int red = interactor.getRed(redColor);

        assertEquals(42, red, "Red value should be 42");

    }

    @Test
    public void testGetDarkGreenColor() {
        //  Requirement:  First 2 bytes are brightness bytes, subsequent pairings of bytes are red, green, blue.  A color
        //  value of 0 is transparent.
        byte greenColor = (byte)0b00000100;

        ByteColorDataInteractor interactor = new ByteColorDataInteractor();
        int green = interactor.getGreen(greenColor);

        assertEquals(42, green, "Green value should be 42");
    }

    @Test
    public void testGetDarkBlueColor() {
        //  Requirement:  First 2 bytes are brightness bytes, subsequent pairings of bytes are red, green, blue.  A color
        //  value of 0 is transparent.
        byte blueColor = (byte)0b00000001;

        ByteColorDataInteractor interactor = new ByteColorDataInteractor();
        int blue = interactor.getBlue(blueColor);

        assertEquals(42, blue, "Blue value should be 42");
    }

    @Test
    public void testGetColorLevelsFromNonZeroOnAllColors() {
        //  Requirement:  First 2 bytes are brightness bytes, subsequent pairings of bytes are red, green, blue.  A color
        //  value of 0 is transparent.
        byte color = (byte)0b01010101;

        ByteColorDataInteractor interactor = new ByteColorDataInteractor();
        int red = interactor.getRed(color);
        int green = interactor.getGreen(color);
        int blue = interactor.getBlue(color);

        assertEquals(84, red, "Red value should be 84");
        assertEquals(84, green, "Green value should be 84");
        assertEquals(84, blue, "Blue value should be 84");
    }

    @Test
    public void shouldGenerateColorByteFromBrightnessAndRGBValues() {
        ByteColorDataInteractor interactor = new ByteColorDataInteractor();
        byte color = interactor.getColorByte(3, 3, 3, 3);
        assertEquals((byte)0b11111111, color, "Color byte should be 0b11111111");
    }

    @Test
    public void shouldGenerateColorWithDarkerTone() {
        ByteColorDataInteractor interactor = new ByteColorDataInteractor();
        byte color = interactor.getColorByte(1, 1, 1, 1);
        assertEquals((byte)0b01010101, color, "Color byte should be 0b01010101");
    }

    @Test
    public void shouldGenerateColorWithDarkerTone2() {
        ByteColorDataInteractor interactor = new ByteColorDataInteractor();
        byte color = interactor.getColorByte(1, 1, 2, 1);
        assertEquals((byte)0b01011001, color, "Color byte should be 0b01010101");

        System.out.println("Red = " + interactor.getRed(color));
    }

}