package codecamp.bug.wars.game.logic.models;

import codecamp.bug.wars.game.logic.service.engine.BugExecutor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class GameState {
    Integer tick;
    Map map;
    List<BugExecutor> bugs;
    List<Food> food;

    public GameState(Integer tick, Map map, List<BugExecutor> bugs, List<Food> food) {
        this.tick = tick;
        this.map = map;
        this.bugs = bugs;
        if (this.bugs == null){
            this.bugs = new ArrayList<>();
        }
        this.food = food;
        if (this.food == null) {
            this.food = new ArrayList<>();
        }
    }


    public boolean isEmpty(int targetX, int targetY) {
        for (Food foodItem : food) {
            if (foodItem.getX() == targetX && foodItem.getY() == targetY) {
                return false;
            }
        }

        if (map.getSpace(targetX, targetY) == MapSpace.WALL) {
            return false;
        }

        for (BugExecutor bug : bugs) {
            if (bug.getEndingX() == targetX && bug.getEndingY() == targetY) {
                return false;
            }
        }

        return true;
    }
}
