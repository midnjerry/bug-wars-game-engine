package codecamp.bug.wars.game.logic.models;

import codecamp.bug.wars.game.logic.service.actions.Action;
import codecamp.bug.wars.game.logic.service.engine.BugExecutor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BugResponse {

    Integer team;
    Integer startingX;
    Integer startingY;
    Integer EndingX;
    Integer EndingY;
    Direction direction;
    String command;
    Boolean dying;

//    public BugResponse(BugExecutor bugEx, Action action){
//        this.team = bugEx.getTeam();
//        this.startingX = bugEx.getStartingX();
//        this.startingY = bugEx.getStartingY();
//        this.EndingX = bugEx.getEndingX();
//        this.EndingY = bugEx.getEndingY();
//        this.dying = bugEx.getDying();
//        this.command = action.toString();
//    }

}
