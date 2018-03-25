package com.zonedigital.toyrobot.simulator;

import java.util.Scanner;

public class Main {

    public static void main(String... args) {
        Robot robot = new Robot(new Table(5, 5));
        SystemOutReportPrinter reportPrinter = new SystemOutReportPrinter();

        MoveCommand moveCommand = new MoveCommand();
        LeftTurnCommand leftTurnCommand = new LeftTurnCommand();
        RightTurnCommand rightTurnCommand = new RightTurnCommand();
        ReportCommand reportCommand = new ReportCommand(reportPrinter);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Start of Toy Robot Simulator");
        System.out.print("> ");

        while (scanner.hasNext()) {
            String next = scanner.nextLine();
            if (next.startsWith("PLACE")) {
                String positionAndFacingPart = next.split(" ")[1];
                int x = Integer.valueOf(positionAndFacingPart.split(",")[0]);
                int y = Integer.valueOf(positionAndFacingPart.split(",")[1]);
                Facing facing = Facing.valueOf(positionAndFacingPart.split(",")[2]);

                try {
                    robot.execute(new PlaceCommand(x, y, facing));
                } catch (UnsupportedOperationException e) {
                    System.out.println(e.getMessage());
                }
            } else if (next.equals("MOVE")) {
                robot.execute(moveCommand);
            } else if (next.equals("LEFT")) {
                robot.execute(leftTurnCommand);
            } else if (next.equals("RIGHT")) {
                robot.execute(rightTurnCommand);
            } else if (next.equals("REPORT")) {
                robot.execute(reportCommand);
            } else if (next.equals("EXIT")) {
                break;
            } else {
                System.out.println("Invalid command. Please type one of: PLACE, MOVE, LEFT, RIGHT, REPORT, EXIT");
            }

            System.out.print("> ");
        }
    }
}
