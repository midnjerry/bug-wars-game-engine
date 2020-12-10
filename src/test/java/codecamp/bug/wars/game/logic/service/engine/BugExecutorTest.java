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

    // return NOOP if objCode doesn't map to Action
    // write a test for '20' (GOTO)  (NEED TO SET PC)
    // STOP INFINITE LOOP.  6 GOTOS SHOULD RETURN NOOP
    //

}