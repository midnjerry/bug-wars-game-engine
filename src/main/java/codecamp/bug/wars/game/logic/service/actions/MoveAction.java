package codecamp.bug.wars.game.logic.service.actions;

import codecamp.bug.wars.game.logic.models.Direction;
import codecamp.bug.wars.game.logic.models.Map;
import codecamp.bug.wars.game.logic.models.MapSpace;
import codecamp.bug.wars.game.logic.service.BugRunner;

public class MoveAction implements Action{

    public void execute(BugRunner bugRunner, Map map) {
        int targetX = bugRunner.getX();
        int targetY = bugRunner.getY();

        switch (bugRunner.getDirection()) {
            case NORTH:
                targetY = bugRunner.getY() - 1;
                break;
            case EAST:
                targetX = bugRunner.getX() + 1;
                break;
            case SOUTH:
                targetY = bugRunner.getY() + 1;
                break;
            case WEST:
                targetX = bugRunner.getX() - 1;
                break;
        }
        if(map.getSpace(targetX , targetY) != MapSpace.WALL){
            bugRunner.setX(targetX);
            bugRunner.setY(targetY);
        }
    }
}
