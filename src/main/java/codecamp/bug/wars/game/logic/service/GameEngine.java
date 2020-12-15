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
        GameState gameState;
        int i = 1;
        List<Integer> winningTeams = new ArrayList<>();

        // TODO: add functionality to test if bugs are at same coords.
//        String coordString;
//        Set coordSet = new HashSet<>();

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
        for(BugInfo bug : game.getBugInfos()){
            spawn = game.getMap().getSpawns().get(game.getMap().getSpawnIndex(bug.getTeam()));
            currBugs.add(new BugExecutor(
                    spawn.getX(), spawn.getY(), spawn.getX(), spawn.getY(), spawn.getDirection(),false,bug.getTeam(),bug.parseCode(),0
            ));
        }

        // getting the gameStates
        while(i <= game.getTicks()){
            gameState = tickProcessor.processTick(new GameState(i, game.getMap(), currBugs, new ArrayList<>()));
            currBugs = gameState.getBugs();
            res.getGameStates().add(gameState);
        }

        // checking if draw or winner
        for(BugExecutor bug : currBugs){
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
