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
                new MapSpaceRow(Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.WALL, MapSpace.OPEN)),
                new MapSpaceRow(Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN))
        );
        map = new Map(null, rows, null, null);
        bug = new BugRunner();
        moveAction = new MoveAction();
    }

    @Test
    public void movesNorth_decrementsY() {
        // arrange
        bug.setStartX(1);
        bug.setStartY(2);
        bug.setDirection(Direction.NORTH);

        // act
        moveAction.execute(bug, map);

        // assert
        assertEquals(1,bug.getEndX());
        assertEquals(1,bug.getEndY());

        //arrange
        bug.setStartX(bug.getEndX());
        bug.setStartY(bug.getEndY());

        // act
        moveAction.execute(bug, map);

        // assert
        assertEquals(1,bug.getEndX());
        assertEquals(0,bug.getEndY());
        assertEquals(Direction.NORTH, bug.getDirection());

    }

    @Test
    public void movesWest_decrementsX() {
        // arrange
        bug.setStartX(2);
        bug.setStartY(1);
        bug.setDirection(Direction.WEST);

        // act
        moveAction.execute(bug, map);

        // assert
        assertEquals(1,bug.getEndX());
        assertEquals(1,bug.getEndY());


        // arrange
        bug.setStartX(bug.getEndX());
        bug.setStartY(bug.getEndY());

        // act
        moveAction.execute(bug, map);

        // assert
        assertEquals(0,bug.getEndX());
        assertEquals(1,bug.getEndY());
        assertEquals(Direction.WEST, bug.getDirection());
    }

    @Test
    public void movesEast_incrementsX() {
        // arrange
        bug.setStartX(1);
        bug.setStartY(1);
        bug.setDirection(Direction.EAST);

        // act
        moveAction.execute(bug, map);

        // assert
        assertEquals(2,bug.getEndX());
        assertEquals(1,bug.getEndY());

        // arrange
        bug.setStartX(bug.getEndX());
        bug.setStartY(bug.getEndY());

        // act
        moveAction.execute(bug, map);

        // assert
        assertEquals(3,bug.getEndX());
        assertEquals(1,bug.getEndY());
        assertEquals(Direction.EAST, bug.getDirection());
    }

    @Test
    public void movesSouth_incrementsY() {
        // arrange
        bug.setStartX(1);
        bug.setStartY(1);
        bug.setDirection(Direction.SOUTH);

        // act
        moveAction.execute(bug, map);

        // assert
        assertEquals(1,bug.getEndX());
        assertEquals(2,bug.getEndY());

        // arrange
        bug.setStartX(bug.getEndX());
        bug.setStartY(bug.getEndY());

        // act
        moveAction.execute(bug, map);

        // assert
        assertEquals(1,bug.getEndX());
        assertEquals(3,bug.getEndY());
        assertEquals(Direction.SOUTH, bug.getDirection());
    }

    @Test
    public void cantMoveIntoWall_NORTH(){
        //arrange
        bug.setStartX(3);
        bug.setStartY(4);
        bug.setDirection(Direction.NORTH);

        //act
        moveAction.execute(bug, map);

        //assert
        assertEquals(3,bug.getEndX());
        assertEquals(4,bug.getEndY());
        assertEquals(Direction.NORTH, bug.getDirection());
    }

    @Test
    public void cantMoveIntoWall_EAST(){
        //arrange
        bug.setStartX(2);
        bug.setStartY(3);
        bug.setDirection(Direction.EAST);

        //act
        moveAction.execute(bug, map);

        //assert
        assertEquals(2,bug.getEndX());
        assertEquals(3,bug.getEndY());
        assertEquals(Direction.EAST, bug.getDirection());
    }

    @Test
    public void cantMoveIntoWall_SOUTH(){
        //arrange
        bug.setStartX(3);
        bug.setStartY(2);
        bug.setDirection(Direction.SOUTH);

        //act
        moveAction.execute(bug, map);

        //assert
        assertEquals(3,bug.getEndX());
        assertEquals(2,bug.getEndY());
        assertEquals(Direction.SOUTH, bug.getDirection());
    }

    @Test
    public void cantMoveIntoWall_WEST(){
        //arrange
        bug.setStartX(4);
        bug.setStartY(3);
        bug.setDirection(Direction.WEST);

        //act
        moveAction.execute(bug, map);

        //assert
        assertEquals(4,bug.getEndX());
        assertEquals(3,bug.getEndY());
        assertEquals(Direction.WEST, bug.getDirection());
    }



}