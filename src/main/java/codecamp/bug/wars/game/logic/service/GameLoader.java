package codecamp.bug.wars.game.logic.service;

import codecamp.bug.wars.game.logic.exceptions.GameNotFoundException;
import codecamp.bug.wars.game.logic.exceptions.InvalidInputException;
import codecamp.bug.wars.game.logic.models.Game;
import codecamp.bug.wars.game.logic.models.GameResult;
import codecamp.bug.wars.game.logic.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameLoader {

    private final GameRepository gameRepository;
    private final GameEngine gameEngine;

    public GameLoader(GameRepository gameRepository, GameEngine gameEngine) {
        this.gameRepository = gameRepository;
        this.gameEngine = gameEngine;
    }

    public GameResult createGame(Game game) {
        if (game == null){
            throw new InvalidInputException("Game cannot be null");
        }
        if (game.getId() != null){
            throw new InvalidInputException("Override Unsuccessful: Game ID should be null");
        }

        Game savedGame = gameRepository.save(game);
        return gameEngine.playGame(savedGame);
    }

    public GameResult getReplay(Long id) {
        Optional<Game> optional = gameRepository.findById(id);
        if (optional.isEmpty()) {
            throw new GameNotFoundException("There is no Game with that ID.");
        }
        Game game = optional.get();
        GameResult gameResult = gameEngine.playGame(game);
        return gameResult;
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }


}
