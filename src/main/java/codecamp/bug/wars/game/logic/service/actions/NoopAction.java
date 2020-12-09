package codecamp.bug.wars.game.logic.service.actions;

import codecamp.bug.wars.game.logic.models.GameState;
import codecamp.bug.wars.game.logic.models.Map;
import codecamp.bug.wars.game.logic.service.BugRunner;

public class NoopAction implements Action {

    @Override
    public void execute(BugRunner bugRunner, Map map) {
        bugRunner.setEndX(bugRunner.getStartX());
        bugRunner.setEndY(bugRunner.getStartY());
    }
}
