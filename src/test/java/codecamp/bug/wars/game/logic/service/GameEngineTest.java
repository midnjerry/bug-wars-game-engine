package codecamp.bug.wars.game.logic.service;


import codecamp.bug.wars.game.logic.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class GameEngineTest {

//    private Game game;
//    private GameResult sampleGameResult;
//    private GameEngine gameEngine;
//
//    @BeforeEach
//    public void setup() {
//        List<MapSpaceRow> rows = Arrays.asList(
//                new MapSpaceRow(Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN)),
//                new MapSpaceRow(Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN)),
//                new MapSpaceRow(Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN)),
//                new MapSpaceRow(Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN)),
//                new MapSpaceRow(Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN))
//        );
//
//        List<Spawn> spawns = Arrays.asList(new Spawn(1, 0, 1));
//        List<Food> food = Arrays.asList(new Food(1, 1));
//        Map map = new Map(null, rows, spawns, food);
//        List<Integer> code = Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1);
//        List<BugInfo> bugInfos = Arrays.asList(new BugInfo(null, 1, code.toString()));
//        BugResponse bugResponse = new BugResponse(2, Direction.NORTH, 3, 4, "attack", false);
//        GameState gameStateTest = new GameState(1, bugResponse);
//        List<Integer> winners = Arrays.asList(1, 2);
//
//        List<GameState> gameStateArray = Arrays.asList(gameStateTest);
//        game = new Game(1L, map, bugInfos, 1, null);
//        sampleGameResult = new GameResult(winners, "winner", gameStateArray);
//        gameEngine = new GameEngine();
//
//    }
//
//    @Test
//    public void playGame_NoBugsReturnsDrawResult() {
//        // arrange
//        game.setBugInfos(Collections.emptyList());
//
//        // act
//        GameResult actual = gameEngine.playGame(game);
//
//        // assert
//    }
}