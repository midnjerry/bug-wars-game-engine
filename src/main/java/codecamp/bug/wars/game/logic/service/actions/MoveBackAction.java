package codecamp.bug.wars.game.logic.service.actions;
import codecamp.bug.wars.game.logic.models.BugResponse;
import codecamp.bug.wars.game.logic.models.GameState;
import lombok.Data;

@Data
public class MoveBackAction implements Action{

    public void execute(BugResponse bugResponse, GameState gameState){
        int targetX =  bugResponse.getStartingX();
        int targetY = bugResponse.getStartingY();

        switch (bugResponse.getDirection()){
            case NORTH:
                targetY = bugResponse.getStartingY() + 1;
                break;
            case WEST:
                targetX = bugResponse.getStartingX() + 1;
                break;
            case SOUTH:
                targetY = bugResponse.getStartingY() - 1;
                break;
            case EAST:
                targetX = bugResponse.getStartingX() - 1;
                break;
        }
        if(gameState.isEmpty(targetX, targetY)){
            bugResponse.setEndingX(targetX);
            bugResponse.setEndingY(targetY);
        }
    }

    public String toString(){
        return "moveBack";
    }
}