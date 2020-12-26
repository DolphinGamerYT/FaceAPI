package com.dolphln.headapi.utils;

public enum BlockChar {

    BLOCK('\u2588'),
    DARK_SHADE('\u2593'),
    MEDIUM_SHADE('\u2592'),
    LIGHT_SHADE('\u2591'),
    TRANSPARENT_CHAR(' ');

    private char c;

    BlockChar(char c) {
        this.c = c;
    }

    /**
     * Get the character for the head.
     *
     * @return Character for the head.
     */
    public char getChar() {
        return c;
    }

}
