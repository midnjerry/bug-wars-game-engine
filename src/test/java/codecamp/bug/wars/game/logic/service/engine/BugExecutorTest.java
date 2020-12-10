package codecamp.bug.wars.game.logic.service.engine;

import codecamp.bug.wars.game.logic.service.actions.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BugExecutorTest {

    private BugExecutor bugExecutor = new BugExecutor();

    @Test
    public void getNextCommand_ShouldReturnNoop() {
        // arrange
        bugExecutor.setProgramCounter(0);
        bugExecutor.setProgram(Arrays.asList(0, 1, 2));
        Action expected = new NoopAction();

        // act
        assertEquals(expected, bugExecutor.getNextCommand());
    }

    @Test
    public void getNextCommand_ShouldReturnMove() {
        // arrange
        bugExecutor.setProgramCounter(0);
        bugExecutor.setProgram(Arrays.asList(2, 1, 2));
        Action expected = new MoveAction();

        // act
        assertEquals(expected, bugExecutor.getNextCommand());
    }

    @Test
    public void getNextCommand_ShouldReturnTurnLeft() {
        // arrange
        bugExecutor.setProgramCounter(0);
        bugExecutor.setProgram(Arrays.asList(3, 1, 2));
        Action expected = new TurnLeftAction();

        // act
        assertEquals(expected, bugExecutor.getNextCommand());
    }

    @Test
    public void getNextCommand_ShouldReturnTurnRight() {
        // arrange
        bugExecutor.setProgramCounter(0);
        bugExecutor.setProgram(Arrays.asList(4, 1, 2));
        Action expected = new TurnRightAction();

        // act
        assertEquals(expected, bugExecutor.getNextCommand());
    }

    @Test
    public void getNextCommand_ProgramCounterShouldIncrementByOne(){
        // arrange
        bugExecutor.setProgramCounter(0);
        bugExecutor.setProgram(Arrays.asList(0, 0, 0));

        bugExecutor.getNextCommand();
        assertEquals(1, bugExecutor.getProgramCounter());

        bugExecutor.getNextCommand();
        assertEquals(2, bugExecutor.getProgramCounter());
    }

    @Test
    public void getNextCommand_shouldReturnNoopIfCommandNotMappedAndIterateCounter(){
        // arrange
        bugExecutor.setProgramCounter(0);
        bugExecutor.setProgram(Arrays.asList(1, 0, 0));
        Action expected = new NoopAction();

        // act
        Action actual = bugExecutor.getNextCommand();

        // assert
        assertEquals(expected, actual);
        assertEquals(1, bugExecutor.getProgramCounter());
    }

    @Test
    public void getNextCommand_gotoShouldSetProgramCounter(){
        // arrange
        bugExecutor.setProgramCounter(0);
        bugExecutor.setProgram(Arrays.asList(20, 2, 0, 1, 1));

        // act
        bugExecutor.getNextCommand();

        // assert
        assertEquals(3, bugExecutor.getProgramCounter());
    }

    @Test
    public void getNextCommand_gotoShouldReturnActionAtTargetIndex(){
        // arrange
        bugExecutor.setProgramCounter(0);
        bugExecutor.setProgram(Arrays.asList(20, 2, 0, 1, 1));
        Action expected = new NoopAction();

        // act
        Action actual = bugExecutor.getNextCommand();

        // assert
        assertEquals(expected, actual);
    }

    @Test
    public void getNextCommand_gotoShouldRunMoreThanOnce(){
        // arrange
        bugExecutor.setProgramCounter(0);
        bugExecutor.setProgram(Arrays.asList(20, 2, 20, 5, 0, 2, 0, 0, 0));
        Action expected = new MoveAction();

        // act
        Action actual = bugExecutor.getNextCommand();

        // assert
        assertEquals(expected, actual);
        assertEquals(6, bugExecutor.getProgramCounter());
    }

    @Test
    public void getNextCommand_gotoShouldNotLoopMoreThanSixTimes(){
        // arrange
        bugExecutor.setProgramCounter(0);
        bugExecutor.setProgram(Arrays.asList(20, 2, 20, 4, 20, 0));
        Action expected = new NoopAction();

        // act
        Action actual = bugExecutor.getNextCommand();

        // assert
        assertEquals(0, bugExecutor.getProgramCounter());
        assertEquals(expected, actual);
    }
}