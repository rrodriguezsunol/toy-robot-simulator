package com.zonedigital.toyrobot.simulator;

public enum Facing {

    NORTH {
        @Override
        Position nextPosition(Position currentPosition) {
            return new Position(currentPosition.getX(), currentPosition.getY() + 1);
        }

        @Override
        public Facing leftTurn() {
            return WEST;
        }

        @Override
        public Facing rightTurn() {
            return EAST;
        }
    },
    SOUTH {
        @Override
        Position nextPosition(Position currentPosition) {
            return new Position(currentPosition.getX(), currentPosition.getY() - 1);
        }

        @Override
        public Facing leftTurn() {
            return EAST;
        }

        @Override
        public Facing rightTurn() {
            return WEST;
        }
    },
    EAST {
        @Override
        Position nextPosition(Position currentPosition) {
            return new Position(currentPosition.getX() + 1, currentPosition.getY());
        }

        @Override
        public Facing leftTurn() {
            return NORTH;
        }

        @Override
        public Facing rightTurn() {
            return SOUTH;
        }
    },
    WEST {
        @Override
        Position nextPosition(Position currentPosition) {
            return new Position(currentPosition.getX() - 1, currentPosition.getY());
        }

        @Override
        public Facing leftTurn() {
            return SOUTH;
        }

        @Override
        public Facing rightTurn() {
            return NORTH;
        }
    };

    abstract Position nextPosition(Position currentPosition);

    public abstract Facing leftTurn();

    public abstract Facing rightTurn();
}
