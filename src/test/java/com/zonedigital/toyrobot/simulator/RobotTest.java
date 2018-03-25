package com.zonedigital.toyrobot.simulator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RobotTest {

    // Test subject
    private Robot robot = new Robot(new Table(5, 5));

    @Mock
    private ReportPrinter reportPrinterMock;
    private ReportCommand reportCommand;


    @Before
    public void setUp() {
        reportCommand = new ReportCommand(reportPrinterMock);
    }


    @Test
    public void placeCommandOnOriginFacingNorthIsExecuted() {
        robot.execute(new PlaceCommand(0, 0, Facing.NORTH));

        robot.execute(reportCommand);

        verify(reportPrinterMock).print("0,0,NORTH");
    }

    @Test
    public void placeCommandOutsideNorthEdgeIsNotPossible() {
        Throwable caughtException = catchThrowable(() -> robot.execute(new PlaceCommand(0, 5, Facing.NORTH)));

        assertThat(caughtException).isExactlyInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Cannot place robot outside the table.");
    }

    @Test
    public void placeCommandOutsideSouthEdgeIsNotPossible() {
        Throwable caughtException = catchThrowable(() -> robot.execute(new PlaceCommand(0, -1, Facing.NORTH)));

        assertThat(caughtException).isExactlyInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Cannot place robot outside the table.");
    }

    @Test
    public void placeCommandOutsideEastEdgeIsNotPossible() {
        Throwable caughtException = catchThrowable(() -> robot.execute(new PlaceCommand(5, 0, Facing.NORTH)));

        assertThat(caughtException).isExactlyInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Cannot place robot outside the table.");
    }

    @Test
    public void placeCommandOutsideWestEdgeIsNotPossible() {
        Throwable caughtException = catchThrowable(() -> robot.execute(new PlaceCommand(-1, 0, Facing.NORTH)));

        assertThat(caughtException).isExactlyInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Cannot place robot outside the table.");
    }

    @Test
    public void whenRobotIsNotPlacedMoveCommandIsIgnored() {

        robot.execute(new MoveCommand());

        robot.execute(reportCommand);
        verify(reportPrinterMock).print("Not placed");
    }

    @Test
    public void whenRobotIsOnOriginFacingNorthAndMoveIsExecuted() {
        robot.execute(new PlaceCommand(0, 0, Facing.NORTH));

        robot.execute(new MoveCommand());

        robot.execute(reportCommand);

        verify(reportPrinterMock).print("0,1,NORTH");
    }

    @Test
    public void whenRobotIsInTheMiddleFacingSouthAndMoveIsExecuted() {
        robot.execute(new PlaceCommand(2, 2, Facing.SOUTH));

        robot.execute(new MoveCommand());

        robot.execute(reportCommand);

        verify(reportPrinterMock).print("2,1,SOUTH");
    }

    @Test
    public void whenRobotIsOnOriginFacingEastAndMoveIsExecuted() {
        robot.execute(new PlaceCommand(0, 0, Facing.EAST));

        robot.execute(new MoveCommand());

        robot.execute(reportCommand);

        verify(reportPrinterMock).print("1,0,EAST");
    }

    @Test
    public void whenRobotIsInTheMiddleFacingWestAndMoveIsExecuted() {
        robot.execute(new PlaceCommand(2, 2, Facing.WEST));

        robot.execute(new MoveCommand());

        robot.execute(reportCommand);

        verify(reportPrinterMock).print("1,2,WEST");
    }

    @Test
    public void whenRobotIsOnNorthEdgeFacingNorthAndMoveIsDiscardedBecauseIsTheEndOfTheTable() {
        robot.execute(new PlaceCommand(0, 4, Facing.NORTH));

        robot.execute(new MoveCommand());

        robot.execute(reportCommand);

        verify(reportPrinterMock).print("0,4,NORTH");
    }

    @Test
    public void whenRobotIsOnSouthEdgeFacingSouthAndMoveIsDiscardedBecauseIsTheEndOfTheTable() {
        robot.execute(new PlaceCommand(0, 0, Facing.SOUTH));

        robot.execute(new MoveCommand());

        robot.execute(reportCommand);

        verify(reportPrinterMock).print("0,0,SOUTH");
    }

    @Test
    public void whenRobotIsOnEastEdgeFacingEastAndMoveIsDiscardedBecauseIsTheEndOfTheTable() {
        robot.execute(new PlaceCommand(4, 0, Facing.EAST));

        robot.execute(new MoveCommand());

        robot.execute(reportCommand);

        verify(reportPrinterMock).print("4,0,EAST");
    }

    @Test
    public void whenRobotIsOnWestEdgeFacingWestAndMoveIsDiscardedBecauseIsTheEndOfTheTable() {
        robot.execute(new PlaceCommand(0, 0, Facing.WEST));

        robot.execute(new MoveCommand());

        robot.execute(reportCommand);
        verify(reportPrinterMock).print("0,0,WEST");
    }

    @Test
    public void whenRobotIsNotPlacedLeftTurnCommandIsIgnored() {
        robot.execute(new LeftTurnCommand());

        robot.execute(reportCommand);
        verify(reportPrinterMock).print("Not placed");
    }

    @Test
    public void whenRobotIsFacingNorthALeftTurnMakesItFaceWest() {
        robot.execute(new PlaceCommand(0, 0, Facing.NORTH));

        robot.execute(new LeftTurnCommand());

        robot.execute(reportCommand);
        verify(reportPrinterMock).print("0,0,WEST");
    }

    @Test
    public void whenRobotIsFacingWestALeftTurnMakesItFaceSouth() {
        robot.execute(new PlaceCommand(0, 0, Facing.WEST));

        robot.execute(new LeftTurnCommand());

        robot.execute(reportCommand);
        verify(reportPrinterMock).print("0,0,SOUTH");
    }

    @Test
    public void whenRobotIsFacingSouthALeftTurnMakesItFaceEast() {
        robot.execute(new PlaceCommand(0, 0, Facing.SOUTH));

        robot.execute(new LeftTurnCommand());

        robot.execute(reportCommand);
        verify(reportPrinterMock).print("0,0,EAST");
    }

    @Test
    public void whenRobotIsFacingEastALeftTurnMakesItFaceNorth() {
        robot.execute(new PlaceCommand(0, 0, Facing.EAST));

        robot.execute(new LeftTurnCommand());

        robot.execute(reportCommand);
        verify(reportPrinterMock).print("0,0,NORTH");
    }

    @Test
    public void whenRobotIsNotPlacedRightTurnCommandIsIgnored() {
        robot.execute(new RightTurnCommand());

        robot.execute(reportCommand);
        verify(reportPrinterMock).print("Not placed");
    }

    @Test
    public void whenRobotIsFacingNorthARightTurnMakesItFaceEast() {
        robot.execute(new PlaceCommand(0, 0, Facing.NORTH));

        robot.execute(new RightTurnCommand());

        robot.execute(reportCommand);
        verify(reportPrinterMock).print("0,0,EAST");
    }

    @Test
    public void whenRobotIsFacingEastARightTurnMakesItFaceSouth() {
        robot.execute(new PlaceCommand(0, 0, Facing.EAST));

        robot.execute(new RightTurnCommand());

        robot.execute(reportCommand);
        verify(reportPrinterMock).print("0,0,SOUTH");
    }

    @Test
    public void whenRobotIsFacingSouthARightTurnMakesItFaceWest() {
        robot.execute(new PlaceCommand(0, 0, Facing.SOUTH));

        robot.execute(new RightTurnCommand());

        robot.execute(reportCommand);
        verify(reportPrinterMock).print("0,0,WEST");
    }

    @Test
    public void whenRobotIsFacingWestARightTurnMakesItFaceNorth() {
        robot.execute(new PlaceCommand(0, 0, Facing.WEST));

        robot.execute(new RightTurnCommand());

        robot.execute(reportCommand);
        verify(reportPrinterMock).print("0,0,NORTH");
    }
}