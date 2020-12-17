package codecamp.bug.wars.game.logic.service;

import codecamp.bug.wars.game.logic.models.*;
import codecamp.bug.wars.game.logic.service.engine.BugExecutor;
import codecamp.bug.wars.game.logic.service.engine.BugUpdater;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class GameEngine {
    BugUpdater bugUpdater;

    GameEngine(BugUpdater bugUpdater){
        this.bugUpdater = bugUpdater;
    }

    public GameResult playGame(Game game){
        GameResult res = new GameResult(new ArrayList<>(), "DRAW", new ArrayList<>());

        // determines winner if 1 or 0 bugs
        if(game.getBugInfos().size() < 1){
            return res;
        } else if(game.getBugInfos().size() < 2) {
            res.setWinners(Arrays.asList(game.getBugInfos().get(0).getTeam()));
            return res;
        }

        // creating bugResponse and bugExecutor for each bugInfo
        List<BugExecutor> bugExecutors  = game.createBugExecutors();
        GameState gameState = new GameState(1, game.getMap(), null, null);
        gameState.setBugs(game.createBugResponses());
        res.getGameStates().add(gameState);

        // getting the gameStates
        while(gameState.getTick() <= game.getTicks()){
            gameState = gameState.clone();
            bugUpdater.updateBugs(bugExecutors, gameState);
            gameState.setTick(gameState.getTick()+1);
            res.getGameStates().add(gameState);
        }

        // adding winners to list
        for(BugResponse bug : gameState.getBugs()){
            res.getWinners().add(bug.getTeam());
        }

        // determine result
        if(res.getWinners().size() == 1){
            res.setResult("WINNER");
        }

        return res;
    }
}
