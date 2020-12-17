package codecamp.bug.wars.game.logic.service.actions;

import codecamp.bug.wars.game.logic.models.BugResponse;
import codecamp.bug.wars.game.logic.models.GameState;
import lombok.Data;

@Data
public class NoopAction implements Action {

    @Override
    public void execute(BugResponse bugResponse, GameState gameState) {

    }

    public String toString(){
        return "noop";
    }
}
