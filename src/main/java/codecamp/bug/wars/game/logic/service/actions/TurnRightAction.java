package codecamp.bug.wars.game.logic.service.actions;

import codecamp.bug.wars.game.logic.models.BugResponse;
import codecamp.bug.wars.game.logic.models.Direction;
import codecamp.bug.wars.game.logic.models.GameState;
import lombok.Data;

@Data
public class TurnRightAction implements Action{
    @Override
    public void execute(BugResponse bugResponse, GameState gameState) {

        switch(bugResponse.getDirection()) {

            case NORTH:
                bugResponse.setDirection(Direction.EAST);
                break;
            case EAST:
                bugResponse.setDirection(Direction.SOUTH);
                break;
            case SOUTH:
                bugResponse.setDirection(Direction.WEST);
                break;
            case WEST:
                bugResponse.setDirection(Direction.NORTH);
                break;
        }
    }
}
