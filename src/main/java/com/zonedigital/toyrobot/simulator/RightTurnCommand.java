package com.zonedigital.toyrobot.simulator;

public class RightTurnCommand implements Command {

    @Override
    public void execute(Robot robot) {
        if (robot.isNotPlaced()) {
            return;
        }

        Facing newFacing = robot.getFacing().rightTurn();
        robot.setFacing(newFacing);
    }
}
