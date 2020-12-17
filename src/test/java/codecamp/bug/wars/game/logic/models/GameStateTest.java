package codecamp.bug.wars.game.logic.models;

import codecamp.bug.wars.game.logic.service.engine.BugExecutor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GameStateTest {
    Map map;
    BugExecutor bug;
    GameState gameState;
    List<Food> food;

    @BeforeEach
    public void setup() {
        List<List<MapSpace>> rows = Arrays.asList(
                (Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN)),
                (Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN)),
                (Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN)),
                (Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.WALL, MapSpace.OPEN)),
                (Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN))
        );

        map = Map.builder().mapGrid(rows).build();
        List<Food> food = Arrays.asList(new Food(0, 0));

        bug = new BugExecutor();
        BugResponse bug = new BugResponse();
        bug.setStartingX(0);
        bug.setStartingY(1);
        bug.setEndingX(1);
        bug.setEndingY(1);

        gameState = new GameState(1, map, Arrays.asList(bug), food);
    }

    @Test
    public void constructor_instantiatesFoodForNullInput(){
        gameState = new GameState(1, map, null, null);
        assertNotNull(gameState.getFood());
    }

    @Test
    public void constructor_instantiatesBugsForNullInput(){
        gameState = new GameState(1, map, null, null);
        assertNotNull(gameState.getBugs());
    }

    @Test
    public void isEmpty_returnsFalseWithFood() {
        assertEquals(false, gameState.isEmpty(0, 0));
    }

    @Test
    public void isEmpty_shouldReturnTrueForEmptySpace() {
        assertEquals(true, gameState.isEmpty(0, 1));
        assertEquals(true, gameState.isEmpty(2, 2));
    }

    @Test
    public void isEmpty_shouldReturnFalseForWall() {
        assertEquals(false, gameState.isEmpty(3, 3));
        assertEquals(false, gameState.isEmpty(-1, 2));
        assertEquals(false, gameState.isEmpty(2, 6));
    }

    @Test
    public void isEmpty_shouldReturnFalseForBugWithEndingCoordinates() {
        assertEquals(false, gameState.isEmpty(1, 1));
        assertEquals(true, gameState.isEmpty(0, 1));
    }

}