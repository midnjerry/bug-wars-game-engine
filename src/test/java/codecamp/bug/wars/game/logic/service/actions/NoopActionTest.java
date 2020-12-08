package codecamp.bug.wars.game.logic.service.actions;

import codecamp.bug.wars.game.logic.models.*;
import codecamp.bug.wars.game.logic.service.BugRunner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NoopActionTest {

    MoveAction moveAction;
    Map map;
    BugRunner bug;
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
        bug = new BugRunner();
        moveAction = new MoveAction();
        noop = new NoopAction();
        gameState = new GameState();
    }

    @Test
    public void noop() {
        // arrange
        bug.setX(1);
        bug.setY(1);
        bug.setDirection(Direction.NORTH);

        // act
        noop.execute(bug, map);

        // arrange
        assertEquals(1, bug.getX());
        assertEquals(1, bug.getY());
        assertEquals(Direction.NORTH, bug.getDirection());
    }
}
