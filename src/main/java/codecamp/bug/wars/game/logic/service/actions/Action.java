package codecamp.bug.wars.game.logic.service.actions;

import codecamp.bug.wars.game.logic.models.Map;
import codecamp.bug.wars.game.logic.models.BugRunner;

public interface Action {
    void execute(BugRunner bugRunner, Map map);
}
