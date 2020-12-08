package codecamp.bug.wars.game.logic.controller;

import codecamp.bug.wars.game.logic.exceptions.GameNotFoundException;
import codecamp.bug.wars.game.logic.exceptions.InvalidInputException;
import codecamp.bug.wars.game.logic.models.Game;
import codecamp.bug.wars.game.logic.models.GameResponse;
import codecamp.bug.wars.game.logic.models.GameResult;
import codecamp.bug.wars.game.logic.service.GameEngineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameEngineController {

    private GameEngineService gameEngineService;

    public GameEngineController(GameEngineService service) {
        gameEngineService = service;
    }

    @GetMapping("/games")
    public ResponseEntity<List<Game>> getAllGames() {
        return ResponseEntity.ok(gameEngineService.getAllGames());
    }

    @PostMapping("/games")
    public ResponseEntity<GameResponse> createGame(@RequestBody Game game) {

        try {
            GameResult result = gameEngineService.saveGame(game);
            return new ResponseEntity(new GameResponse(result, null), HttpStatus.OK);

        } catch (InvalidInputException e) {

            return new ResponseEntity(new GameResponse(null, e.getMessage()), HttpStatus.BAD_REQUEST);

        }

    }

    @GetMapping("/games/{id}/replay")
    public ResponseEntity<GameResponse> getGameReplay(@PathVariable Long id) {
        try {
            GameResult result = gameEngineService.getReplay(id);
            return new ResponseEntity(new GameResponse(result, null), HttpStatus.OK);
        } catch (GameNotFoundException e) {
            return new ResponseEntity(new GameResponse(null, e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

}
