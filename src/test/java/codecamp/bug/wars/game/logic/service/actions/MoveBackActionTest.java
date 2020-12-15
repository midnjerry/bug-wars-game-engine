package codecamp.bug.wars.game.logic.service.actions;

import codecamp.bug.wars.game.logic.models.*;
import codecamp.bug.wars.game.logic.service.engine.BugExecutor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoveBackActionTest {
    MoveBackAction moveBackAction;
    Map map;
    BugExecutor bug;

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
        moveBackAction = new MoveBackAction();
    }

    @Test
    public void movesBackSouth_incrementsY(){
        // arrange
        bug.setStartingX(1);
        bug.setStartingY(2);
        bug.setDirection(Direction.NORTH);

        // act
        moveBackAction.execute(bug, map);

        // assert
        assertEquals(1,bug.getEndingX());
        assertEquals(3,bug.getEndingY());

        // arrange
        bug.setStartingX(bug.getEndingX());
        bug.setStartingY(bug.getEndingY());

        // act
        moveBackAction.execute(bug, map);

        // assert
        assertEquals(1,bug.getEndingX());
        assertEquals(4,bug.getEndingY());
        assertEquals(Direction.NORTH, bug.getDirection());
    }

    @Test
    public void moveBackEast_incrementX(){
        // arrange
        bug.setStartingX(2);
        bug.setStartingY(1);
        bug.setDirection(Direction.WEST);

        // act
        moveBackAction.execute(bug, map);

        // assert
        assertEquals(3,bug.getEndingX());
        assertEquals(1,bug.getEndingY());
        assertEquals(Direction.WEST, bug.getDirection());
    }

    @Test
    public void movesBackNorth_decrementsY(){
        // arrange
        bug.setStartingX(3);
        bug.setStartingY(3);
        bug.setDirection(Direction.SOUTH);

        // act
        moveBackAction.execute(bug, map);

        // assert
        assertEquals(3,bug.getEndingX());
        assertEquals(2,bug.getEndingY());

        // arrange
        bug.setStartingX(bug.getEndingX());
        bug.setStartingY(bug.getEndingY());

        // act
        moveBackAction.execute(bug, map);

        // assert
        assertEquals(3,bug.getEndingX());
        assertEquals(1,bug.getEndingY());
        assertEquals(Direction.SOUTH, bug.getDirection());
    }

    @Test
    public void moveBackWest_decrementX(){
        // arrange
        bug.setStartingX(2);
        bug.setStartingY(1);
        bug.setDirection(Direction.EAST);

        // act
        moveBackAction.execute(bug, map);

        // assert
        assertEquals(1,bug.getEndingX());
        assertEquals(1,bug.getEndingY());
        assertEquals(Direction.EAST, bug.getDirection());
    }

    @Test
    public void canNotMoveBackIntoWall_facingNorth() {
        // arrange
        bug.setStartingX(3);
        bug.setStartingY(4);
        bug.setEndingX(3);
        bug.setEndingY(4);
        bug.setDirection(Direction.NORTH);

        //act
        moveBackAction.execute(bug, map);

        //assert
        assertEquals(3,bug.getEndingX());
        assertEquals(4,bug.getEndingY());
        assertEquals(Direction.NORTH, bug.getDirection());
    }

    @Test
    public void canNotMoveBackIntoWall_facingSouth() {
        // arrange
        bug.setStartingX(3);
        bug.setStartingY(4);
        bug.setEndingX(3);
        bug.setEndingY(4);
        bug.setDirection(Direction.SOUTH);

        //act
        moveBackAction.execute(bug, map);

        //assert
        assertEquals(3,bug.getEndingX());
        assertEquals(4,bug.getEndingY());
        assertEquals(Direction.SOUTH, bug.getDirection());
    }

    @Test
    public void canNotMoveBackIntoWall_facingWest() {
        // arrange
        bug.setStartingX(4);
        bug.setStartingY(3);
        bug.setEndingX(4);
        bug.setEndingY(3);
        bug.setDirection(Direction.WEST);

        //act
        moveBackAction.execute(bug, map);

        //assert
        assertEquals(4,bug.getEndingX());
        assertEquals(3,bug.getEndingY());
        assertEquals(Direction.WEST, bug.getDirection());
    }

    @Test
    public void canNotMoveBackIntoWall_facingEast() {
        // arrange
        bug.setStartingX(4);
        bug.setStartingY(3);
        bug.setEndingX(4);
        bug.setEndingY(3);
        bug.setDirection(Direction.EAST);

        //act
        moveBackAction.execute(bug, map);

        //assert
        assertEquals(4,bug.getEndingX());
        assertEquals(3,bug.getEndingY());
        assertEquals(Direction.EAST, bug.getDirection());
    }
}
