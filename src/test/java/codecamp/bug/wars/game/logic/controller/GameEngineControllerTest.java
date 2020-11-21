package codecamp.bug.wars.game.logic.controller;

import codecamp.bug.wars.game.logic.exceptions.InvalidInputException;
import codecamp.bug.wars.game.logic.models.*;
import codecamp.bug.wars.game.logic.service.BugRunner;
import codecamp.bug.wars.game.logic.service.GameEngineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameEngineControllerTest {

    private GameEngineService mockGameEngineService;
    private GameEngineController gameEngineController;
    private BugRunner gameRunner;

    @BeforeEach
    public void setup() {

        gameRunner = Mockito.mock(BugRunner.class);

        mockGameEngineService = Mockito.mock(GameEngineService.class);

        gameEngineController = new GameEngineController(mockGameEngineService, gameRunner);
    }

    @Test
    public void createGame_shouldReturn400IfGameRejectedByService() {

        Game input = new Game(null, null, null, null);

        ResponseEntity<GameResult> expected = new ResponseEntity(new GameResult(), HttpStatus.BAD_REQUEST);

        Mockito.when(mockGameEngineService.saveGame(Mockito.any()))
                .thenThrow(new InvalidInputException("Invalid Game"));

        ResponseEntity<GameResult> response = gameEngineController.createGame(input);

        assertEquals(expected, response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }


    @Test
    public void createGame_shouldReturnGameInputAndOkHttpStatus() {
        // arrange
        MapSpace[][] rows =  {
            { MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN,  MapSpace.OPEN},
            { MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN,  MapSpace.OPEN},
            { MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN,  MapSpace.OPEN},
            { MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN,  MapSpace.OPEN},
            { MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN,  MapSpace.OPEN}
        };

        List<Spawn> spawns = new ArrayList<>();
        spawns.add(new Spawn(1, 0, 1));
        List<Food> food = new ArrayList<>();
        food.add(new Food(1, 1));
        Map map = new Map(rows, spawns, food);

        List<Bug> bugs = new ArrayList<>();
        Integer[] code = {1, 1, 1, 1, 1, 1, 1, 1};
        bugs.add(new Bug(1, code));

        BugResponse bugResponse = new BugResponse(2, Direction.NORTH, 3, 4, "attack", false);
        GameState gameStateTest = new GameState(1, bugResponse);
        Game postBodyInput = new Game(map, bugs, 1, null);
        Integer[] winners = {1, 2};
        GameState [] gameStateArray = {gameStateTest};
        GameResult expected = new GameResult(winners , "winner", gameStateArray);
        ResponseEntity<GameResult> expectedResponse = new ResponseEntity(expected, HttpStatus.OK);
        Mockito.when(mockGameEngineService.saveGame(Mockito.any())).thenReturn(expected);

//        Act
        ResponseEntity<GameResult> response = gameEngineController.createGame(postBodyInput);

//        assert
        assertEquals(expectedResponse, response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getGameReplay_shouldGetGameReplayAndOkHttpStatus() {

        //arrange
        MapSpace[][] rows =  {
                { MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN,  MapSpace.OPEN},
                { MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN,  MapSpace.OPEN},
                { MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN,  MapSpace.OPEN},
                { MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN,  MapSpace.OPEN},
                { MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN,  MapSpace.OPEN}
        };

        List<Spawn> spawns = new ArrayList<>();
        spawns.add(new Spawn(1, 0, 1));
        List<Food> food = new ArrayList<>();
        food.add(new Food(1, 1));
        Map map = new Map(rows, spawns, food);

        List<Bug> bugs = new ArrayList<>();
        Integer[] code = {1, 1, 1, 1, 1, 1, 1, 1};
        bugs.add(new Bug(1, code));

        BugResponse bugResponse = new BugResponse(2, Direction.NORTH, 3, 4, "attack", false);
        GameState gameStateTest = new GameState(1, bugResponse);
        Game postBodyInput = new Game(map, bugs, 1, null);
        Integer[] winners = {1, 2};

        GameState [] gameStateArray = {gameStateTest};
        GameResult expected = new GameResult(winners , "winner", gameStateArray);
        ResponseEntity<GameResult> expectedResponse = new ResponseEntity(expected, HttpStatus.OK);
        Mockito.when(mockGameEngineService.getGameById(Mockito.any())).thenReturn(expected);

//        act
        ResponseEntity<GameResult> response = gameEngineController.createGame(postBodyInput);


    }

}