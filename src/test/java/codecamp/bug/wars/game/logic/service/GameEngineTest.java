package codecamp.bug.wars.game.logic.service;


import codecamp.bug.wars.game.logic.models.*;
import codecamp.bug.wars.game.logic.service.engine.BugUpdater;
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
    private BugUpdater bugUpdater;

    @BeforeEach
    public void setup() {
        bugUpdater = Mockito.mock(BugUpdater.class);
        gameEngine = new GameEngine(bugUpdater);
        gameBuilder = new GameBuilder();
        game = gameBuilder.defaultGame().build();
    }

    @Test
    public void playGame_NoBugsReturnsDrawResult() {
        // arrange
        game.setBugInfos(Collections.emptyList());
        GameResult expected = new GameResult(Collections.emptyList(), "DRAW", Collections.emptyList());

        // act
        GameResult actual = gameEngine.playGame(game);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    public void playGame_OneBugReturnsDrawResultWithOneTeam() {
        // arrange
        GameResult expected = new GameResult(Arrays.asList(1), "DRAW", Collections.emptyList());
        game.setBugInfos(Arrays.asList(game.getBugInfos().get(0)));

        // act
        GameResult actual = gameEngine.playGame(game);

        // assert
        assertEquals(expected, actual);
    }



}