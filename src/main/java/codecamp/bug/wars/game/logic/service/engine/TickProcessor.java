package codecamp.bug.wars.game.logic.service.engine;

import codecamp.bug.wars.game.logic.models.GameState;
import codecamp.bug.wars.game.logic.service.actions.Action;
import org.springframework.stereotype.Service;

@Service
public class TickProcessor {
    public GameState processTick(GameState gameState) {
        for(BugExecutor bug : gameState.getBugs()){
            bug.setStartingX(bug.getEndingX());
            bug.setStartingY(bug.getEndingY());
            Action action = bug.getNextCommand();
            action.execute(bug, gameState.getMap());
        }
        return gameState;
    }
}
