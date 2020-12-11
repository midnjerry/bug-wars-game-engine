package codecamp.bug.wars.game.logic.models;

import codecamp.bug.wars.game.logic.service.engine.BugExecutor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameState {

    Map map;
    Integer tick;
    List<BugExecutor> bugs;
    List<Food> food;
}
