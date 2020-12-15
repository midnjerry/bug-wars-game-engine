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
    BugExecutor bug;
    NoopAction noop;
    GameState gameState;

    @BeforeEach
    public void runBeforeEveryTest(){
        List<MapSpaceRow> rows = Arrays.asList(
                new MapSpaceRow(Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN)),
                new MapSpaceRow(Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN)),
                new MapSpaceRow(Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN)),
                new MapSpaceRow(Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.WALL, MapSpace.OPEN)),
                new MapSpaceRow(Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN))
        );
        map = new Map(null, rows, null, null);
        bug = new BugExecutor();
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
