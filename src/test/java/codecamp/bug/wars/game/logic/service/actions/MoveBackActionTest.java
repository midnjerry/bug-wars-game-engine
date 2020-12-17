package codecamp.bug.wars.game.logic.service.actions;

import codecamp.bug.wars.game.logic.models.*;
import codecamp.bug.wars.game.logic.service.engine.BugExecutor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MoveBackActionTest {

    Map map;
    BugResponse bug;
    MoveBackAction moveBackAction;
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
        moveBackAction = new MoveBackAction();
        gameState = new GameState(1,map, Arrays.asList(bug), null);
    }


    @Test
    public void movesBackSouth_incrementsY(){
        // arrange
        bug.setStartingX(1);
        bug.setStartingY(2);
        bug.setDirection(Direction.NORTH);

        // act
        moveBackAction.execute(bug, gameState);

        // assert
        assertEquals(1,bug.getEndingX());
        assertEquals(3,bug.getEndingY());

        // arrange
        bug.setStartingX(bug.getEndingX());
        bug.setStartingY(bug.getEndingY());

        // act
        moveBackAction.execute(bug, gameState);

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
        moveBackAction.execute(bug, gameState);

        // assert
        assertEquals(3,bug.getEndingX());
        assertEquals(1,bug.getEndingY());
        assertEquals(Direction.WEST, bug.getDirection());

        // arrange
        bug.setStartingX(bug.getEndingX());
        bug.setStartingY(bug.getEndingY());

        // act
        moveBackAction.execute(bug, gameState);

        // assert
        assertEquals(4, bug.getEndingX());
        assertEquals(1, bug.getEndingY());
        assertEquals(Direction.WEST, bug.getDirection());

    }

    @Test
    public void movesBackNorth_decrementsY(){
        // arrange
        bug.setStartingX(3);
        bug.setStartingY(3);
        bug.setDirection(Direction.SOUTH);

        // act
        moveBackAction.execute(bug, gameState);

        // assert
        assertEquals(3,bug.getEndingX());
        assertEquals(2,bug.getEndingY());

        // arrange
        bug.setStartingX(bug.getEndingX());
        bug.setStartingY(bug.getEndingY());

        // act
        moveBackAction.execute(bug, gameState);

        // assert
        assertEquals(3,bug.getEndingX());
        assertEquals(1,bug.getEndingY());
        assertEquals(Direction.SOUTH, bug.getDirection());

    }

    @Test
    public void moveBackWest_decrementX(){
        // arrange
        bug.setStartingX(3);
        bug.setStartingY(1);
        bug.setDirection(Direction.EAST);

        // act
        moveBackAction.execute(bug, gameState);

        // assert
        assertEquals(2,bug.getEndingX());
        assertEquals(1,bug.getEndingY());
        assertEquals(Direction.EAST, bug.getDirection());

        // arrange
        bug.setStartingX(bug.getEndingX());
        bug.setStartingY(bug.getEndingY());

        // act
        moveBackAction.execute(bug, gameState);

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
        moveBackAction.execute(bug, gameState);

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
        moveBackAction.execute(bug, gameState);

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
        moveBackAction.execute(bug, gameState);
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
        moveBackAction.execute(bug, gameState);
        //assert
        assertEquals(4,bug.getEndingX());
        assertEquals(3,bug.getEndingY());
        assertEquals(Direction.EAST, bug.getDirection());
    }

    @Test
    public void cantMoveBackIntoOtherBug(){
        //arrange
        bug.setStartingX(1);
        bug.setStartingY(2);
        bug.setEndingX(1);
        bug.setEndingY(2);
        bug.setTeam(1);
        bug.setDirection(Direction.SOUTH);

        BugResponse bug2 = new BugResponse();
        bug2.setTeam(2);
        bug2.setStartingX(1);
        bug2.setStartingY(1);
        bug2.setEndingX(1);
        bug2.setEndingY(1);

        gameState.setBugs(Arrays.asList(bug, bug2));

        //act
        moveBackAction.execute(bug, gameState);

        //assert
        assertNotEquals(bug.getTeam(), bug2.getTeam());
        assertEquals(1, bug.getEndingX());
        assertEquals(2, bug.getEndingY());
        assertEquals(Direction.SOUTH, bug.getDirection());
    }



}
