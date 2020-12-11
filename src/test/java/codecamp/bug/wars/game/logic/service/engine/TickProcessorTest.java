package codecamp.bug.wars.game.logic.service.engine;

import codecamp.bug.wars.game.logic.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TickProcessorTest {
    private TickProcessor tickProcessor;
    private GameState gameState;
    private Map map;
    private BugExecutor bug1;
    private BugExecutor bug2;

    @BeforeEach
    public void setup() {
        List<MapSpaceRow> rows = Arrays.asList(
                new MapSpaceRow(Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN)),
                new MapSpaceRow(Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN)),
                new MapSpaceRow(Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN)),
                new MapSpaceRow(Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN)),
                new MapSpaceRow(Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN))
        );

        List<Spawn> spawns = Arrays.asList(new Spawn(3, 3, 1, Direction.NORTH));
        List<Food> food = Arrays.asList(new Food(1, 1));
        List<Integer> code = Arrays.asList(2, 2, 2, 2, 2, 2, 2, 2);
        bug1 = new BugExecutor(4, 4, 4, 4, Direction.NORTH, false, 1, code, 0);
        bug2 = new BugExecutor(3, 4, 3, 4, Direction.NORTH, false, 1, code, 0);
        map = new Map(null, rows, spawns, food);
        gameState = new GameState(map, 1, Arrays.asList(bug1, bug2), food);

        tickProcessor = new TickProcessor();
    }

    @Test
    public void processTick_shouldReturnNextTick(){
        // arrange
        bug1.setStartingX(3);
        bug2.setStartingY(3);
        bug1.setProgramCounter(1);
        bug2.setProgramCounter(1);

        GameState expected = gameState = new GameState(map, 2, Arrays.asList(bug1, bug2), Collections.emptyList());

        // act
        GameState result = tickProcessor.processTick(gameState);

        // assert
        assertEquals(expected, result);
    }
}
