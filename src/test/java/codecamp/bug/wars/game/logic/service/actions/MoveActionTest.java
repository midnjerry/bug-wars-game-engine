package codecamp.bug.wars.game.logic.service.actions;

import codecamp.bug.wars.game.logic.models.*;
import codecamp.bug.wars.game.logic.service.BugRunner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MoveActionTest {
    MoveAction moveAction;
    Map map;
    BugRunner bug;

    @BeforeEach
    public void runBeforeEveryTest(){
        List<MapSpaceRow> rows = Arrays.asList(
                new MapSpaceRow(Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN)),
                new MapSpaceRow(Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN)),
                new MapSpaceRow(Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN)),
                new MapSpaceRow(Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN)),
                new MapSpaceRow(Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN))
        );
        map = new Map(null, rows, null, null);
        bug = new BugRunner();
        moveAction = new MoveAction();
    }

    @Test
    public void movesNorth_decrementsY() {
        // arrange
        bug.setX(1);
        bug.setY(1);
        bug.setDirection(Direction.NORTH);

        // act
        moveAction.execute(bug, map);

        // assert
        assertEquals(1,bug.getX());
        assertEquals(0,bug.getY());
        assertEquals(Direction.NORTH, bug.getDirection());
    }

    @Test
    public void movesWest_decrementsX() {
        // arrange
        bug.setX(1);
        bug.setY(1);
        bug.setDirection(Direction.WEST);

        // act
        moveAction.execute(bug, map);

        // assert
        assertEquals(0,bug.getX());
        assertEquals(1,bug.getY());
        assertEquals(Direction.WEST, bug.getDirection());
    }

    @Test
    public void movesEast_incrementsX() {
        // arrange
        bug.setX(1);
        bug.setY(1);
        bug.setDirection(Direction.EAST);

        // act
        moveAction.execute(bug, map);

        // assert
        assertEquals(2,bug.getX());
        assertEquals(1,bug.getY());
        assertEquals(Direction.EAST, bug.getDirection());
    }

    @Test
    public void movesSouth_incrementsY() {
        // arrange
        bug.setX(1);
        bug.setY(1);
        bug.setDirection(Direction.SOUTH);

        // act
        moveAction.execute(bug, map);

        // assert
        assertEquals(1,bug.getX());
        assertEquals(2,bug.getY());
        assertEquals(Direction.SOUTH, bug.getDirection());
    }


}