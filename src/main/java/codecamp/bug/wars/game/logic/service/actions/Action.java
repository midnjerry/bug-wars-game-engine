package codecamp.bug.wars.game.logic.service.actions;

import codecamp.bug.wars.game.logic.models.GameState;
import codecamp.bug.wars.game.logic.service.engine.BugExecutor;

public interface Action {
    void execute(BugExecutor bugExecutor, GameState gameState);
}
