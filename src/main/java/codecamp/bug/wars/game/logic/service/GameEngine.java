package codecamp.bug.wars.game.logic.service;

import codecamp.bug.wars.game.logic.models.*;
import codecamp.bug.wars.game.logic.repository.GameRepository;
import codecamp.bug.wars.game.logic.service.actions.Action;
import codecamp.bug.wars.game.logic.service.engine.BugExecutor;
import codecamp.bug.wars.game.logic.service.engine.BugUpdater;
import codecamp.bug.wars.game.logic.service.engine.TickProcessor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;
import java.util.Map;

@Service
public class GameEngine {
    private final TickProcessor tickProcessor;

    public GameEngine(TickProcessor tickProcessor) {
        this.tickProcessor = new TickProcessor();
    }

    public GameResult playGame(Game game){
        GameResult res = new GameResult();
        List<BugExecutor> bugExecutors = new ArrayList<>();
        res.setResult("WINNER");
        List<BugResponse> bugResponses = new ArrayList<>();
        List<Integer> winningTeams = new ArrayList<>();
        Action tempAction;
        res.setGameStates(new ArrayList<>());
        BugUpdater bugUpdater = new BugUpdater();

        GameState gameState = new GameState(0, game.getMap(), null, null);

        // determines winner if 1 or 0 bugs
        if(game.getBugInfos().size() < 1){
            res.setResult("DRAW");
            return res;
        } else if(game.getBugInfos().size() < 2) {
            res.setResult("DRAW");
            res.setWinners(Arrays.asList(game.getBugInfos().get(0).getTeam()));
            return res;
        }

        // creating bugResponse and bugExecutor for each bugInfo
        game.createBugResponses();
        game.createBugExecutors();

        int counter = 0;

        // getting the gameStates
        while(gameState.getTick() <= game.getTicks()){
            bugUpdater.updateBugs(bugResponses, bugExecutors, gameState);
            gameState.setTick(gameState.getTick()+1);
            res.getGameStates().add(gameState);
        }

        // checking if draw or winner
        for(BugResponse bug : gameState.getBugs()){
            if(winningTeams.contains(bug.getTeam())){
                res.setResult("DRAW");
            }
            else {
                winningTeams.add(bug.getTeam());
            }
        }

        res.setWinners(winningTeams);
        return res;
    }
}
