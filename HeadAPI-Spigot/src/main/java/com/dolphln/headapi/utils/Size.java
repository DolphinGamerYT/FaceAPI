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

    public int getHeadSize() {
        return headSize;
    }

    public int getCharSize() {
        return getHeadSize()/8;
    }

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
