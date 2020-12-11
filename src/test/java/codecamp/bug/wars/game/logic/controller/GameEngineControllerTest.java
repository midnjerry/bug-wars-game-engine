package codecamp.bug.wars.game.logic.controller;

import codecamp.bug.wars.game.logic.exceptions.GameNotFoundException;
import codecamp.bug.wars.game.logic.exceptions.InvalidInputException;
import codecamp.bug.wars.game.logic.models.*;
import codecamp.bug.wars.game.logic.service.engine.GameLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameEngineControllerTest {

    private GameLoader mockGameEngineService;
    private GameEngineController gameEngineController;
    private Game sampleGame;
    private GameResult sampleGameResult;

    @BeforeEach
    public void setup() {
        mockGameEngineService = Mockito.mock(GameLoader.class);
        gameEngineController = new GameEngineController(mockGameEngineService);

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
        List<BugInfo> bugInfos = Arrays.asList(new BugInfo(null,1, code.toString()));
        BugResponse bugResponse = new BugResponse(2, Direction.NORTH, 3, 4, "attack", false);
        GameState gameStateTest = new GameState(map, 1, null, food);
        List<Integer> winners = Arrays.asList(1, 2);

        List<GameState> gameStateArray = Arrays.asList(gameStateTest);
        sampleGame = new Game(1L, map, bugInfos, 1, null);
        sampleGameResult = new GameResult(winners, "winner", gameStateArray);


    }

    @Test
    public void createGame_shouldReturn400IfGameRejectedByService() {

        Game input = new Game(1L, null, null, null, null);

        ResponseEntity<GameResponse> expected = new ResponseEntity(new GameResponse(null, "Invalid Game"), HttpStatus.BAD_REQUEST);

        Mockito.when(mockGameEngineService.createGame(Mockito.any()))
                .thenThrow(new InvalidInputException("Invalid Game"));

        ResponseEntity<GameResponse> response = gameEngineController.createGame(input);

        assertEquals(expected, response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }


    @Test
    public void createGame_shouldReturnGameInputAndOkHttpStatus() {
        // arrange
        ResponseEntity<GameResponse> expectedResponse = new ResponseEntity(new GameResponse(sampleGameResult, null), HttpStatus.OK);
        Mockito.when(mockGameEngineService.createGame(Mockito.any())).thenReturn(sampleGameResult);

        // act
        ResponseEntity<GameResponse> response = gameEngineController.createGame(sampleGame);

        // assert
        assertEquals(expectedResponse, response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getGameReplay_shouldGetGameReplayAndOkHttpStatus() {
        //arrange
        ResponseEntity<GameResponse> expectedResponse = new ResponseEntity(new GameResponse(sampleGameResult, null), HttpStatus.OK);
        Mockito.when(mockGameEngineService.getReplay(1L)).thenReturn(sampleGameResult);

        // act
        ResponseEntity<GameResponse> response = gameEngineController.getGameReplay(1L);

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
        ResponseEntity<GameResponse> response = gameEngineController.getGameReplay(1l);

        //assert
        assertEquals(new GameResponse(null, "Game ID does not exist"), response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void getGameReplay_shouldReturn404IfInputIsNull() {
        // arrange
        Mockito.when(mockGameEngineService.getReplay(null))
                .thenThrow(new GameNotFoundException("Game ID does not exist"));

        //act
        ResponseEntity<GameResponse> response = gameEngineController.getGameReplay(null);

        //assert
        assertEquals(new GameResponse(null, "Game ID does not exist"), response.getBody());
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

    @Test
    public void replayGame_shouldReturnGetReplayWithOkHTTPStatus(){
        //arrange
        Mockito.when(mockGameEngineService.getReplay(1l)).thenReturn(sampleGameResult);
        ResponseEntity<GameResponse> expected = new ResponseEntity<GameResponse>(new GameResponse(sampleGameResult, null), HttpStatus.OK);

        //act
        ResponseEntity<GameResponse> response = gameEngineController.getGameReplay(1l);

        //assert
        assertEquals(expected, response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void replayGame_shouldReturn404GameNotFoundWhenIdIsInvalid(){
        //arrange
        Mockito.when(mockGameEngineService.getReplay(null)).thenThrow(new GameNotFoundException("A game result with that id does not exist"));

        //act
        ResponseEntity<GameResponse> response = gameEngineController.getGameReplay(null);

        //assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("A game result with that id does not exist", response.getBody().getError());

    }



}