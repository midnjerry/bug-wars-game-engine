package codecamp.bug.wars.game.logic.controller;

import codecamp.bug.wars.game.logic.exceptions.InvalidInputException;
import codecamp.bug.wars.game.logic.models.Game;
import codecamp.bug.wars.game.logic.models.GameResult;
import codecamp.bug.wars.game.logic.models.GameState;
import codecamp.bug.wars.game.logic.service.GameEngineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GameEngineController {

    private GameEngineService gameEngineService;

    public GameEngineController(GameEngineService service) {

        gameEngineService = service;

    }

    @PostMapping("/games")
    public ResponseEntity<GameResult> createGame(@RequestBody Game game) {

        try{

            GameResult result = gameEngineService.saveGame(game);
            return new ResponseEntity(new GameResult(), HttpStatus.OK);

        } catch (InvalidInputException e) {

            return new ResponseEntity(new GameResult(), HttpStatus.BAD_REQUEST);

        }

    }

}
