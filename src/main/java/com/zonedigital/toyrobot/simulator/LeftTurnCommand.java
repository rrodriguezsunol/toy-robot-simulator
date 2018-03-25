package com.zonedigital.toyrobot.simulator;

public class LeftTurnCommand implements Command {

    @Override
    public void execute(Robot robot) {
        if (robot.isNotPlaced()) {
            return;
        }

        Facing newFacing = robot.getFacing().leftTurn();
        robot.setFacing(newFacing);
    }
}
