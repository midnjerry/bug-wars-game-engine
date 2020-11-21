package codecamp.bug.wars.game.logic.controller;

import codecamp.bug.wars.game.logic.exceptions.GameNotFoundException;
import codecamp.bug.wars.game.logic.exceptions.InvalidInputException;
import codecamp.bug.wars.game.logic.models.Bug;
import codecamp.bug.wars.game.logic.models.Game;
import codecamp.bug.wars.game.logic.models.GameResult;
import codecamp.bug.wars.game.logic.models.GameState;
import codecamp.bug.wars.game.logic.service.BugRunner;
import codecamp.bug.wars.game.logic.service.GameEngineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameEngineController {

    private GameEngineService gameEngineService;
    private BugRunner gameRunner;

    public GameEngineController(GameEngineService service, BugRunner runner) {

        gameEngineService = service;
        gameRunner = runner;

    }

    @PostMapping("/games")
    public ResponseEntity<GameResult> createGame(@RequestBody Game game) {

        try{

            GameResult result = gameEngineService.saveGame(game);
            return new ResponseEntity(result, HttpStatus.OK);

        } catch (InvalidInputException e) {

            return new ResponseEntity(new GameResult(), HttpStatus.BAD_REQUEST);

        }

    }

    @GetMapping("/games/{id}/replay")
    public ResponseEntity<GameResult> getGameReplay(@PathVariable Long id) {

        try{
            GameResult retrieve = gameEngineService.getGameById(id);
            return new ResponseEntity(retrieve, HttpStatus.OK);

        } catch (GameNotFoundException e) {

            return new ResponseEntity(new GameResult(), HttpStatus.NOT_FOUND);

        }

    }

}
