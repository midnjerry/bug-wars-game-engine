package codecamp.bug.wars.game.logic.service.actions;

import codecamp.bug.wars.game.logic.models.BugResponse;
import codecamp.bug.wars.game.logic.models.GameState;

public interface Action {
    void execute(BugResponse bugResponse, GameState gameState);
}
