package codecamp.bug.wars.game.logic.controller;

import codecamp.bug.wars.game.logic.exceptions.InvalidInputException;
import codecamp.bug.wars.game.logic.models.Game;
import codecamp.bug.wars.game.logic.models.GameResult;
import codecamp.bug.wars.game.logic.service.GameEngineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class GameEngineControllerTest {

    private GameEngineService mockGameEngineService;
    private GameEngineController gameEngineController;

    @BeforeEach
    public void setup() {
        mockGameEngineService = Mockito.mock(GameEngineService.class);

        gameEngineController = new GameEngineController(mockGameEngineService);
    }

    @Test
    public void createGame_shouldReturn400IfGameRejectedByService(){

        Game input = new Game(null, null, null, null);

        ResponseEntity<GameResult> expected = new ResponseEntity(new GameResult(), HttpStatus.BAD_REQUEST);

        Mockito.when(mockGameEngineService.saveGame(Mockito.any()))
                .thenThrow(new InvalidInputException("Invalid Game"));

        ResponseEntity<GameResult> response = gameEngineController.createGame(input);

        assertEquals(expected, response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

//    @Test
//    public void createGame_shouldReturnGameResultAndOkHttpStatus() {
//        // arrange
//        Game input = new Game(
//
//        );
//    }

}