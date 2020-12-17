package codecamp.bug.wars.game.logic.models;

import codecamp.bug.wars.game.logic.service.engine.BugExecutor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    Game game;
    GameBuilder gameBuilder;

    @BeforeEach
    void setup(){
        gameBuilder = new GameBuilder();
        game = gameBuilder.defaultGame().build();
    }

    @Test
    void createBugExecutors_shouldReturnEmptyListForNullBugInfos() {
        // arrange
        game.setBugInfos(null);
        List<BugExecutor> expected = Collections.emptyList();

        // act
        List<BugExecutor> actual = game.createBugExecutors();

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void createBugExecutors_shouldReturnEmptyListForEmptyBugInfos() {
        // arrange
        game.setBugInfos(Collections.emptyList());
        List<BugExecutor> expected = Collections.emptyList();

        // act
        List<BugExecutor> actual = game.createBugExecutors();

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void createBugExecutors_shouldReturnBugExecutorForEachBugInfo() {
        // arrange
        List<BugExecutor> expected = Arrays.asList(
                new BugExecutor(1, game.getBugInfos().get(0).getCode(), 0),
                new BugExecutor(2, game.getBugInfos().get(1).getCode(), 0),
                new BugExecutor(3, game.getBugInfos().get(2).getCode(), 0),
                new BugExecutor(4, game.getBugInfos().get(3).getCode(), 0)
        );

        // act
        List<BugExecutor> actual = game.createBugExecutors();

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void createBugResponses_shouldReturnEmptyListForNullBugInfos() {
        // arrange
        game.setBugInfos(null);
        List<BugResponse> expected = Collections.emptyList();

        // act
        List<BugResponse> actual = game.createBugResponses();

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void createBugResponses_shouldReturnEmptyListForEmptyBugInfos() {
        // arrange
        game.setBugInfos(Collections.emptyList());
        List<BugResponse> expected = Collections.emptyList();

        // act
        List<BugResponse> actual = game.createBugResponses();

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void createBugResponses_shouldReturnBugResponseForEachBugInfo() {
        // arrange
        List<BugResponse> expected = Arrays.asList(
                new BugResponse(
                        1, game.getMap().getSpawns().get(0).getX(),
                        game.getMap().getSpawns().get(0).getY(),
                        game.getMap().getSpawns().get(0).getX(),
                        game.getMap().getSpawns().get(0).getY(),
                        game.getMap().getSpawns().get(0).getDirection(),
                        "noop",
                        false
                ),
                new BugResponse(
                        2, game.getMap().getSpawns().get(1).getX(),
                        game.getMap().getSpawns().get(1).getY(),
                        game.getMap().getSpawns().get(1).getX(),
                        game.getMap().getSpawns().get(1).getY(),
                        game.getMap().getSpawns().get(1).getDirection(),
                        "noop",
                        false
                ),
                new BugResponse(
                        3, game.getMap().getSpawns().get(2).getX(),
                        game.getMap().getSpawns().get(2).getY(),
                        game.getMap().getSpawns().get(2).getX(),
                        game.getMap().getSpawns().get(2).getY(),
                        game.getMap().getSpawns().get(2).getDirection(),
                        "noop",
                        false
                ),
                new BugResponse(
                        4, game.getMap().getSpawns().get(3).getX(),
                        game.getMap().getSpawns().get(3).getY(),
                        game.getMap().getSpawns().get(3).getX(),
                        game.getMap().getSpawns().get(3).getY(),
                        game.getMap().getSpawns().get(3).getDirection(),
                        "noop",
                        false
                )
        );

        // act
        List<BugResponse> actual = game.createBugResponses();

        // assert
        assertEquals(expected, actual);
    }
}