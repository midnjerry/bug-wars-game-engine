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
    private GameEngine gameEngine;
    private GameBuilder gameBuilder;
    private TickProcessor tickProcessor;

    @BeforeEach
    public void setup() {
        tickProcessor = Mockito.mock(TickProcessor.class);
        gameEngine = new GameEngine(tickProcessor);
        gameBuilder = new GameBuilder();
        game = gameBuilder.defaultGame().build();
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
        game.setBugInfos(Arrays.asList(game.getBugInfos().get(0)));

        // act
        GameResult actual = gameEngine.playGame(game);

        // assert
        assertEquals(expected, actual);
    }



}