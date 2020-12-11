package codecamp.bug.wars.game.logic.service;


import codecamp.bug.wars.game.logic.models.*;
import codecamp.bug.wars.game.logic.service.engine.TickProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameEngineTest {

    private Game game;
    private GameResult sampleGameResult;
    private GameEngine gameEngine;
    private TickProcessor tickProcessor;

    @BeforeEach
    public void setup() {
        tickProcessor = Mockito.mock(TickProcessor.class);

        List<MapSpaceRow> rows = Arrays.asList(
                new MapSpaceRow(Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN)),
                new MapSpaceRow(Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN)),
                new MapSpaceRow(Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN)),
                new MapSpaceRow(Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN)),
                new MapSpaceRow(Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN))
        );

        List<Spawn> spawns = Arrays.asList(new Spawn(1, 0, 1, Direction.NORTH));
        List<Food> food = Arrays.asList(new Food(1, 1));
        Map map = new Map(null, rows, spawns, food);
        List<Integer> code = Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1);
        List<BugInfo> bugInfos = Arrays.asList(new BugInfo(null, 1, code.toString()));
        BugResponse bugResponse = new BugResponse(2, Direction.NORTH, 3, 4, "attack", false);
//        GameState gameStateTest = new GameState(1, bugInfos);
        List<Integer> winners = Arrays.asList(1, 2);

//        List<GameState> gameStateArray = Arrays.asList(gameStateTest);
        game = new Game(1L, map, bugInfos, 1, null);
//        sampleGameResult = new GameResult(winners, "winner", gameStateArray);
        gameEngine = new GameEngine(tickProcessor);

    }

    @Test
    public void playGame_NoBugsReturnsDrawResult() {
        // arrange
        game.setBugInfos(Collections.emptyList());
        GameResult expected = new GameResult();
        expected.setResult("DRAW");

        // act
        GameResult actual = gameEngine.playGame(game);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    public void playGame_OneBugReturnsDrawResultWithOneTeam() {
        // arrange
        GameResult expected = new GameResult();
        expected.setResult("DRAW");
        expected.setWinners(Arrays.asList(1));

        // act
        GameResult actual = gameEngine.playGame(game);

        // assert
        assertEquals(expected, actual);
    }



}