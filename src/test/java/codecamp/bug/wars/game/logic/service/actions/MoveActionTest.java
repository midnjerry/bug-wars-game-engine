package codecamp.bug.wars.game.logic.service.actions;

import codecamp.bug.wars.game.logic.service.BugRunner;
import org.junit.jupiter.api.Test;

class MoveActionTest {
    MoveAction moveAction;

    @Test
    public void movesNorth() {
        // arrange
        moveAction = new MoveAction();
        BugRunner bug = new BugRunner();

        // act
        moveAction.execute();

        // assert

    }
}