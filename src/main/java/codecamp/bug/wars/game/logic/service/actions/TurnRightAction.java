package codecamp.bug.wars.game.logic.service.actions;

import codecamp.bug.wars.game.logic.models.Direction;
import codecamp.bug.wars.game.logic.models.Map;
import codecamp.bug.wars.game.logic.models.BugRunner;

public class TurnRightAction implements Action{
    @Override
    public void execute(BugRunner bugRunner, Map map) {

        switch(bugRunner.getDirection()) {

            case NORTH:
                bugRunner.setDirection(Direction.EAST);
                break;
            case EAST:
                bugRunner.setDirection(Direction.SOUTH);
                break;
            case SOUTH:
                bugRunner.setDirection(Direction.WEST);
                break;
            case WEST:
                bugRunner.setDirection(Direction.NORTH);
                break;
        }
    }
}
