package codecamp.bug.wars.game.logic.service.actions;

import codecamp.bug.wars.game.logic.models.Direction;
import codecamp.bug.wars.game.logic.models.Map;
import codecamp.bug.wars.game.logic.models.MapSpace;
import codecamp.bug.wars.game.logic.service.BugRunner;

public class MoveAction implements Action{

    public void execute(BugRunner bugRunner, Map map) {
        int targetX = bugRunner.getStartX();
        int targetY = bugRunner.getStartY();

        switch (bugRunner.getDirection()) {
            case NORTH:
                targetY = bugRunner.getStartY() - 1;
                break;
            case EAST:
                targetX = bugRunner.getStartX() + 1;
                break;
            case SOUTH:
                targetY = bugRunner.getStartY() + 1;
                break;
            case WEST:
                targetX = bugRunner.getStartX() - 1;
                break;
        }
        if(map.getSpace(targetX , targetY) != MapSpace.WALL){
            bugRunner.setEndX(targetX);
            bugRunner.setEndY(targetY);
        }
        else {
            bugRunner.setEndX(bugRunner.getStartX());
            bugRunner.setEndY(bugRunner.getStartY());
        }
    }
}
