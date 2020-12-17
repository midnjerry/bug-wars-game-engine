package codecamp.bug.wars.game.logic.models;

import codecamp.bug.wars.game.logic.service.engine.BugExecutor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class GameState {
    Integer tick;
    Map map;
    List<BugResponse> bugs;
    List<Food> food;

    public GameState(Integer tick, Map map, List<BugResponse> bugs, List<Food> food) {
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

        for (BugResponse bug : bugs) {
            if (bug.getEndingX() == targetX && bug.getEndingY() == targetY) {
                return false;
            }
        }

        return true;
    }

    public GameState clone() {
        ObjectMapper mapper = new ObjectMapper();
        GameState copy;

        try {
            copy = mapper.readValue(mapper.writeValueAsString(this), GameState.class);
        } catch (Exception e) {
            copy = new GameState();
        }

        return copy;
    }
}
