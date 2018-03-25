package com.zonedigital.toyrobot.simulator;

public final class ReportCommand implements Command {
    private final ReportPrinter reportPrinter;

    public ReportCommand(ReportPrinter reportPrinter) {
        this.reportPrinter = reportPrinter;
    }

    @Override
    public void execute(Robot robot) {
        if (robot.isNotPlaced()) {
            reportPrinter.print("Not placed");
        } else {
            reportPrinter.print(String.format("%d,%d,%s",
                    robot.getCurrentPosition().getX(),
                    robot.getCurrentPosition().getY(),
                    robot.getFacing().name()));
        }
    }
}
