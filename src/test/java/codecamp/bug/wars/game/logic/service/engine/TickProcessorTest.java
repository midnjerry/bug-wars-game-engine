package codecamp.bug.wars.game.logic.service.engine;

import codecamp.bug.wars.game.logic.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TickProcessorTest {
    private TickProcessor tickProcessor;
    private GameState gameState;
    private Map map;
    private BugResponse bug1;
    private BugResponse bug2;
    private List<Food> food;

    @BeforeEach
    public void setup() {
        List<List<MapSpace>> rows = Arrays.asList(
                (Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN)),
                (Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN)),
                (Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN)),
                (Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN)),
                (Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN))
        );

        List<Spawn> spawns = Arrays.asList(new Spawn(3, 3, 1, Direction.NORTH));
        food = Arrays.asList(new Food(1, 1));
        List<Integer> code = Arrays.asList(2, 2, 2, 2, 2, 2, 2, 2);
        bug1 = new BugResponse();
        bug2 = new BugResponse();
        map = Map.builder().mapGrid(rows).foods(food).spawns(spawns).build();

        gameState = new GameState(1, map, Arrays.asList(bug1, bug2), food);

        tickProcessor = new TickProcessor();
    }

    @Test
    public void processTick_shouldReturnNextTick() {
        // arrange
        GameState expected = new GameState(2, map, Arrays.asList(bug1, bug2), food);

        // act
        tickProcessor.processTick(gameState);

        // assert
        assertEquals(expected, gameState);
    }
}
