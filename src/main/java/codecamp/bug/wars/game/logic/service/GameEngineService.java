package codecamp.bug.wars.game.logic.service;

import codecamp.bug.wars.game.logic.models.Game;
import codecamp.bug.wars.game.logic.models.GameResult;
import codecamp.bug.wars.game.logic.repository.GameEngineRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameEngineService {

    private final GameEngineRepository gameEngineRepository;

    public GameEngineService(GameEngineRepository gameEngineRepository){
        this.gameEngineRepository = gameEngineRepository;
    }

    public GameResult saveGame(Game game) {

        return null;

    }

    public Game getGameById(Long id) {

        return null;

    }

    public GameResult getReplay(Long id){
        return null;
    }


    public List<Game> getAllGames() {
        return null;
    }


}
