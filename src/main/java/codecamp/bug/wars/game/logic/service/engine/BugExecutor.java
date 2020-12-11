package codecamp.bug.wars.game.logic.service.engine;

import codecamp.bug.wars.game.logic.models.Direction;
import codecamp.bug.wars.game.logic.service.actions.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BugExecutor {
    int startingX;
    int startingY;
    int EndingX;
    int EndingY;
    Direction direction;
    boolean dying;
    int team;
    List<Integer> program;
    int programCounter;
    private static Map<Integer,Action> actionMap;

    static {
        actionMap = new HashMap<>();
        actionMap.put(0, new NoopAction());
        actionMap.put(2, new MoveAction());
        actionMap.put(3, new TurnLeftAction());
        actionMap.put(4, new TurnRightAction());
    }

    public Action getNextCommand() {
        if(programCounter >= program.size()){
            return new NoopAction();
        }
        int objCode = program.get(programCounter);
        int counter = 0;
        while(objCode == 20 && counter<6){
            int targetIndex = program.get(programCounter+1);
            objCode = program.get(targetIndex);
            programCounter = targetIndex;
            counter ++;
        }
        Action action = actionMap.get(objCode);
        if(action == null){
            if(objCode != 20) {
                programCounter++;
            }
            return new NoopAction();
        }
        programCounter++;
        return action;
    }
}
