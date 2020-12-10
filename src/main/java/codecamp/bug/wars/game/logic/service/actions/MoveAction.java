package codecamp.bug.wars.game.logic.service.actions;

import codecamp.bug.wars.game.logic.models.Map;
import codecamp.bug.wars.game.logic.models.MapSpace;
import codecamp.bug.wars.game.logic.service.engine.BugExecutor;
import lombok.Data;

@Data
public class MoveAction implements Action{

    public void execute(BugExecutor bugExecutor, Map map) {
        int targetX = bugExecutor.getX();
        int targetY = bugExecutor.getY();

        switch (bugExecutor.getDirection()) {
            case NORTH:
                targetY = bugExecutor.getY() - 1;
                break;
            case EAST:
                targetX = bugExecutor.getX() + 1;
                break;
            case SOUTH:
                targetY = bugExecutor.getY() + 1;
                break;
            case WEST:
                targetX = bugExecutor.getX() - 1;
                break;
        }
        if(map.getSpace(targetX , targetY) != MapSpace.WALL){
            bugExecutor.setX(targetX);
            bugExecutor.setY(targetY);
        }
    }
}
