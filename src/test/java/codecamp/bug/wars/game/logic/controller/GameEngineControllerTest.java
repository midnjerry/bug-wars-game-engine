package codecamp.bug.wars.game.logic.controller;

import codecamp.bug.wars.game.logic.exceptions.GameNotFoundException;
import codecamp.bug.wars.game.logic.exceptions.InvalidInputException;
import codecamp.bug.wars.game.logic.models.*;
import codecamp.bug.wars.game.logic.service.GameEngineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameEngineControllerTest {

    private GameEngineService mockGameEngineService;
    private GameEngineController gameEngineController;
    private Game sampleGame;
    private GameResult sampleGameResult;

    @BeforeEach
    public void setup() {
        mockGameEngineService = Mockito.mock(GameEngineService.class);
        gameEngineController = new GameEngineController(mockGameEngineService);

        List<MapSpaceRow> rows = Arrays.asList(
                new MapSpaceRow(Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN)),
                new MapSpaceRow(Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN)),
                new MapSpaceRow(Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN)),
                new MapSpaceRow(Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN)),
                new MapSpaceRow(Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN))
        );

        List<Spawn> spawns = Arrays.asList(new Spawn(1, 0, 1));
        List<Food> food = Arrays.asList(new Food(1, 1));
        Map map = new Map(null, rows, spawns, food);
        List<Integer> code = Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1);
        List<BugInfo> bugInfos = Arrays.asList(new BugInfo(null,1, code));
        BugResponse bugResponse = new BugResponse(2, Direction.NORTH, 3, 4, "attack", false);
        GameState gameStateTest = new GameState(1, bugResponse);
        List<Integer> winners = Arrays.asList(1, 2);

        List<GameState> gameStateArray = Arrays.asList(gameStateTest);
        sampleGame = new Game(1L, map, bugInfos, 1, null);
        sampleGameResult = new GameResult(winners, "winner", gameStateArray);


    }

    @Test
    public void createGame_shouldReturn400IfGameRejectedByService() {

        Game input = new Game(1L, null, null, null, null);

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
        ResponseEntity<GameResult> expectedResponse = new ResponseEntity(sampleGameResult, HttpStatus.OK);
        Mockito.when(mockGameEngineService.saveGame(Mockito.any())).thenReturn(sampleGameResult);

        // act
        ResponseEntity<GameResult> response = gameEngineController.createGame(sampleGame);

        // assert
        assertEquals(expectedResponse, response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getGameReplay_shouldGetGameReplayAndOkHttpStatus() {
        //arrange
        ResponseEntity<GameResult> expectedResponse = new ResponseEntity(sampleGameResult, HttpStatus.OK);
        Mockito.when(mockGameEngineService.getReplay(1L)).thenReturn(sampleGameResult);

        // act
        ResponseEntity<GameResult> response = gameEngineController.getGameReplay(1L);

        // assert
        assertEquals(expectedResponse, response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void getGameReplay_shouldReturn404IfGameResultDoesNotExist() {
        // arrange
        Mockito.when(mockGameEngineService.getReplay(1l))
                .thenThrow(new GameNotFoundException("Game ID does not exist"));

        //act
        ResponseEntity<GameResult> response = gameEngineController.getGameReplay(1l);

        //assert
        assertEquals(new GameResult(), response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void getGameReplay_shouldReturn404IfInputIsNull() {
        // arrange
        Mockito.when(mockGameEngineService.getReplay(null))
                .thenThrow(new GameNotFoundException("Game ID does not exist"));

        //act
        ResponseEntity<GameResult> response = gameEngineController.getGameReplay(null);

        //assert
        assertEquals(new GameResult(), response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void getAllGames_shouldReturnEmptyListIfEmpty(){
        // arrange
        List<Game> expected = new ArrayList<>();
        Mockito.when(mockGameEngineService.getAllGames()).thenReturn(expected);

        // act
        ResponseEntity<List<Game>> response = gameEngineController.getAllGames();

        // assert
        assertEquals(expected, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getAllGames_shouldReturnListOfGames(){
        // arrange
        List<Game> expected = new ArrayList<>();
        expected.add(sampleGame);
        expected.add(sampleGame);
        Mockito.when(mockGameEngineService.getAllGames()).thenReturn(expected);

        // act
        ResponseEntity<List<Game>> response = gameEngineController.getAllGames();

        // assert
        assertEquals(expected, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}