package codecamp.bug.wars.game.logic.service.actions;

import codecamp.bug.wars.game.logic.models.Direction;
import codecamp.bug.wars.game.logic.models.Map;
import codecamp.bug.wars.game.logic.service.engine.BugExecutor;
import lombok.Data;

@Data
public class TurnRightAction implements Action{
    @Override
    public void execute(BugExecutor bugExecutor, Map map) {

        switch(bugExecutor.getDirection()) {

            case NORTH:
                bugExecutor.setDirection(Direction.EAST);
                break;
            case EAST:
                bugExecutor.setDirection(Direction.SOUTH);
                break;
            case SOUTH:
                bugExecutor.setDirection(Direction.WEST);
                break;
            case WEST:
                bugExecutor.setDirection(Direction.NORTH);
                break;
        }
    }
}
