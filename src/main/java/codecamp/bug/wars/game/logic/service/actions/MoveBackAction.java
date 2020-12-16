package codecamp.bug.wars.game.logic.service.actions;
import codecamp.bug.wars.game.logic.models.Direction;
import codecamp.bug.wars.game.logic.models.GameState;
import codecamp.bug.wars.game.logic.models.Map;
import codecamp.bug.wars.game.logic.models.MapSpace;
import codecamp.bug.wars.game.logic.service.engine.BugExecutor;
import lombok.Data;

@Data
public class MoveBackAction implements Action{

    public void execute(BugExecutor bugExecutor, GameState gameState){
        int targetX =  bugExecutor.getStartingX();
        int targetY = bugExecutor.getStartingY();

        switch (bugExecutor.getDirection()){
            case NORTH:
                targetY = bugExecutor.getStartingY() + 1;
                break;
            case WEST:
                targetX = bugExecutor.getStartingX() + 1;
                break;
            case SOUTH:
                targetY = bugExecutor.getStartingY() - 1;
                break;
            case EAST:
                targetX = bugExecutor.getStartingX() - 1;
                break;
        }
        if(gameState.isEmpty(targetX, targetY)){
            bugExecutor.setEndingX(targetX);
            bugExecutor.setEndingY(targetY);
        }
    }
}