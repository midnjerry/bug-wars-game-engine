package codecamp.bug.wars.game.logic.service.actions;

import codecamp.bug.wars.game.logic.models.Direction;
import codecamp.bug.wars.game.logic.models.Map;
import codecamp.bug.wars.game.logic.service.BugRunner;

public class MoveAction implements Action{

    public void execute(BugRunner bugRunner, Map map) {
       switch (bugRunner.getDirection()){
           case NORTH: bugRunner.setY(0);
               break;
           case EAST: bugRunner.setX(2);
               break;
           case SOUTH: bugRunner.setY(2);
               break;
           case WEST: bugRunner.setX(0);
               break;
       }
    }
}
