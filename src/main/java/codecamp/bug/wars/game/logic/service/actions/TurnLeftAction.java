package codecamp.bug.wars.game.logic.service.actions;

import codecamp.bug.wars.game.logic.models.BugResponse;
import codecamp.bug.wars.game.logic.models.Direction;
import codecamp.bug.wars.game.logic.models.GameState;
import lombok.Data;

@Data
public class TurnLeftAction implements Action{
    @Override
    public void execute(BugResponse bugExecutor, GameState gameState) {

        switch(bugExecutor.getDirection()) {

            case NORTH:
                bugExecutor.setDirection(Direction.WEST);
                break;
            case EAST:
                bugExecutor.setDirection(Direction.NORTH);
                break;
            case SOUTH:
                bugExecutor.setDirection(Direction.EAST);
                break;
            case WEST:
                bugExecutor.setDirection(Direction.SOUTH);
                break;
        }
    }

    public String toString(){
        return "turnLeft";
    }
}
