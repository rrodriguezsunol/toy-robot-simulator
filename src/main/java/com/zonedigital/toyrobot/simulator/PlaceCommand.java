package com.zonedigital.toyrobot.simulator;

public final class PlaceCommand implements Command {
    private final Position position;
    private final Facing facing;

    public PlaceCommand(int x, int y, Facing facing) {
        this.position = new Position(x, y);
        this.facing = facing;
    }

    @Override
    public void execute(Robot robot) {
        try {
            robot.setCurrentPosition(position);
            robot.setFacing(facing);
        } catch (IllegalArgumentException e) {
            throw new UnsupportedOperationException("Cannot place robot outside the table.");
        }
    }
}
