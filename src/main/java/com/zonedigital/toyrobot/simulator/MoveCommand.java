package com.zonedigital.toyrobot.simulator;

public class MoveCommand implements Command {

    @Override
    public void execute(Robot robot) {
        if (robot.isNotPlaced()) {
            return;
        }

        Position newPosition = robot.getFacing().nextPosition(robot.getCurrentPosition());

        try {
            robot.setCurrentPosition(newPosition);
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal move. Command ignored");
        }
    }
}
