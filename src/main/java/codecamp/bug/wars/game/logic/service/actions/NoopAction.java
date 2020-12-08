package codecamp.bug.wars.game.logic.service.actions;

import codecamp.bug.wars.game.logic.models.GameState;
import codecamp.bug.wars.game.logic.models.Map;
import codecamp.bug.wars.game.logic.service.BugRunner;

public class Noop implements Action {

    @Override
    public void execute(BugRunner bugRunner, Map map) {
        GameState gameState = new GameState();

        int targetX = bugRunner.getX();
        int targetY = bugRunner.getY();
        int tick = gameState.getTick();
        tick++;

    }
}
