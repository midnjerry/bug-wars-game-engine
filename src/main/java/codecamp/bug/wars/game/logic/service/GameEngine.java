package codecamp.bug.wars.game.logic.service;

import codecamp.bug.wars.game.logic.models.*;
import codecamp.bug.wars.game.logic.repository.GameRepository;
import codecamp.bug.wars.game.logic.service.actions.Action;
import codecamp.bug.wars.game.logic.service.engine.BugExecutor;
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
        List<BugExecutor> currBugs = new ArrayList<>();
        res.setResult("WINNER");
        Spawn spawn;
        List<BugResponse> resBugs = new ArrayList<>();
        BugResponse resBug;
        GameState gameState = new GameState(0, game.getMap(), null, null);
        List<Integer> winningTeams = new ArrayList<>();
        Action tempAction;


        // TODO: add functionality to test if bugs are at same coords.

        // automatically setting result and winner if 0 or 1 bug
        if(game.getBugInfos().size() < 1){
            res.setResult("DRAW");
            return res;
        } else if(game.getBugInfos().size() < 2) {
            res.setResult("DRAW");
            res.setWinners(Arrays.asList(game.getBugInfos().get(0).getTeam()));
            return res;
        }

        res.setGameStates(new ArrayList<>());

        // creating bugExecutor for each bugInfo
        // creating bugResponse for each bugInfo
        for(BugInfo bug : game.getBugInfos()){
            spawn = game.getMap().getSpawns().get(game.getMap().getSpawnIndex(bug.getTeam()));
            currBugs.add(new BugExecutor(bug.getTeam(), bug.getCode(), 0));
            resBugs.add(new BugResponse(bug.getTeam(), spawn.getX(), spawn.getY(), spawn.getX(), spawn.getY(), spawn.getDirection(), "NOOP", false));
        }

        int counter = 0;

        // getting the gameStates
        while(gameState.getTick() <= game.getTicks()){
            for(BugResponse bug : resBugs){
                BugExecutor bugEx = currBugs.get(counter);
                tempAction = bugEx.getNextCommand();
                tempAction.execute(bug, gameState);
                counter++;
            }
            counter = 0;
            gameState.setBugs(resBugs);
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
