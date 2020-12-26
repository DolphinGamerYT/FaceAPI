package com.dolphln.headapi.utils;

public enum Size {

    NORMAL(8),
    LARGE(16),
    EXTRA_LARGE(32),
    ULTRA_LARGE(64);

    private int headSize;

    Size(int headSize) {
        this.headSize = headSize;
    }

    /**
     * Get the integer for the size of the head.
     *
     * @return Size of the head in pixels.
     */
    public int getHeadSize() {
        return headSize;
    }

    /**
     * Get the amount of chars that should have one single color
     *
     * @return Amount of chars per color
     */
    public int getCharSize() {
        return getHeadSize()/8;
    }

    /**
     * Function to get the size of the head from an integer number.
     *
     * @param size Integer to be checked.
     * @return Size object
     */
    public static Size getSize(int size) {
        switch (size) {
            case 2:
            case 16:
                return LARGE;
            case 3:
            case 32:
                return EXTRA_LARGE;
            case 4:
            case 64:
                return ULTRA_LARGE;
            default:
                return NORMAL;
        }
    }

}
