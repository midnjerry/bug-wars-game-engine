package codecamp.bug.wars.game.logic.service.actions;

import codecamp.bug.wars.game.logic.models.Direction;
import codecamp.bug.wars.game.logic.models.Map;
import codecamp.bug.wars.game.logic.models.BugRunner;

public class TurnLeftAction implements Action{
    @Override
    public void execute(BugRunner bugRunner, Map map) {

        switch(bugRunner.getDirection()) {

            case NORTH:
                bugRunner.setDirection(Direction.WEST);
                break;
            case EAST:
                bugRunner.setDirection(Direction.NORTH);
                break;
            case SOUTH:
                bugRunner.setDirection(Direction.EAST);
                break;
            case WEST:
                bugRunner.setDirection(Direction.SOUTH);
                break;
        }
    }
}
