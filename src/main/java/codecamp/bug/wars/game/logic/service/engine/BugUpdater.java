package codecamp.bug.wars.game.logic.service.engine;

import codecamp.bug.wars.game.logic.models.BugResponse;
import codecamp.bug.wars.game.logic.models.GameState;
import codecamp.bug.wars.game.logic.service.actions.Action;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BugUpdater {
    public void updateBugs(List<BugExecutor> bugExecutors, GameState gameState){
        int counter = 0;
        Action action;

        for(BugResponse bug : gameState.getBugs()){
            bug.setStartingX(bug.getEndingX());
            bug.setStartingY(bug.getEndingY());
            action = bugExecutors.get(counter).getNextCommand();
            bug.setCommand(action.toString());
            action.execute(bug, gameState);
            counter++;
        }
    }
}
