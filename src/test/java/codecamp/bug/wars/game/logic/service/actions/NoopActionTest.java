package codecamp.bug.wars.game.logic.service.actions;

import codecamp.bug.wars.game.logic.models.*;
import codecamp.bug.wars.game.logic.service.engine.BugExecutor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NoopActionTest {

    MoveAction moveAction;
    Map map;
    BugResponse bug;
    NoopAction noop;
    GameState gameState;

    @BeforeEach
    public void runBeforeEveryTest(){
        List<List<MapSpace>> rows = Arrays.asList(
                (Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN)),
                (Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN)),
                (Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN)),
                (Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.WALL, MapSpace.OPEN)),
                (Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN))
        );
        map = Map.builder().mapGrid(rows).build();
        bug = new BugResponse();
        moveAction = new MoveAction();
        noop = new NoopAction();
        gameState = new GameState(1,map, Arrays.asList(bug), Collections.emptyList());
    }

    @Test
    public void noop() {
        // arrange
        bug.setStartingX(1);
        bug.setStartingY(1);
        bug.setDirection(Direction.NORTH);

        // act
        noop.execute(bug, gameState);

        // arrange
        assertEquals(1, bug.getStartingX());
        assertEquals(1, bug.getStartingY());
        assertEquals(Direction.NORTH, bug.getDirection());
    }
}
