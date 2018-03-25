package com.zonedigital.toyrobot.simulator;

public class Robot {
    private Table table;
    private Position currentPosition;
    private Facing facing;

    public Robot(Table table) {
        this.table = table;
    }

    public void execute(Command command) {
        command.execute(this);
    }

    Facing getFacing() {
        return facing;
    }

    void setFacing(Facing newFacing) {
        this.facing = newFacing;
    }

    Position getCurrentPosition() {
        return currentPosition;
    }

    void setCurrentPosition(Position newPosition) {
        if (!table.isWithinBounds(newPosition)) {
            throw new IllegalArgumentException("newPosition cannot be outside the table.");
        }

        this.currentPosition = newPosition;
    }

    boolean isNotPlaced() {
        return currentPosition == null || facing == null;
    }
}
