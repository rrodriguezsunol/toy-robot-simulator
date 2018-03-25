package com.zonedigital.toyrobot.simulator;

public final class Table {
    private static final int MIN_WIDTH = 0;
    private static final int MIN_HEIGHT = 0;

    private final int maxWidth;
    private final int maxHeight;

    public Table(int width, int height) {
        this.maxWidth = width;
        this.maxHeight = height;
    }

    @Override
    public String toString() {
        return maxWidth + " x "  + maxHeight;
    }

    public boolean isWithinBounds(Position position) {
        return position.getY() >= MIN_HEIGHT && position.getY() < maxHeight
                && position.getX() >= MIN_WIDTH && position.getX() < maxWidth;
    }
}
