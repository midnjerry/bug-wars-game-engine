package codecamp.bug.wars.game.logic.service.actions;

import codecamp.bug.wars.game.logic.models.*;
import codecamp.bug.wars.game.logic.service.BugRunner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TurnLeftActionTest {
    TurnLeftAction turnLeftAction;
    Map map;
    BugRunner bug;

    @BeforeEach
    public void setup() {
        bug = new BugRunner();
        bug.setX(1);
        bug.setY(1);

        turnLeftAction = new TurnLeftAction();

        List<MapSpaceRow> rows = Arrays.asList(
                new MapSpaceRow(Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN)),
                new MapSpaceRow(Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN)),
                new MapSpaceRow(Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN))
        );

        List<Spawn> spawns = Arrays.asList(new Spawn(1, 0, 1));
        List<Food> food = Arrays.asList(new Food(1, 1));
        map = new Map(null, rows, spawns, food);
    }

    @Test
    public void execute_shouldMakeTheBugFaceEast() {
        bug.setDirection(Direction.SOUTH);
        turnLeftAction.execute(bug, map);
        assertEquals(Direction.EAST, bug.getDirection());
    }

    @Test
    public void execute_shouldMakeTheBugFaceSouth() {
        bug.setDirection(Direction.WEST);
        turnLeftAction.execute(bug, map);
        assertEquals(Direction.SOUTH, bug.getDirection());
    }

    @Test
    public void execute_shouldMakeTheBugFaceWest() {
        bug.setDirection(Direction.NORTH);
        turnLeftAction.execute(bug, map);
        assertEquals(Direction.WEST, bug.getDirection());
    }

    @Test
    public void execute_shouldMakeTheBugFaceNorth() {
        bug.setDirection(Direction.EAST);
        turnLeftAction.execute(bug, map);
        assertEquals(Direction.NORTH, bug.getDirection());
    }

    @Test
    public void execute_shouldNotChangeCoordinates(){
        bug.setDirection(Direction.EAST);
        turnLeftAction.execute(bug, map);
        assertEquals(1, bug.getX());
        assertEquals(1, bug.getY());
    }
}