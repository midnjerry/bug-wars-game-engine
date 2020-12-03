package codecamp.bug.wars.game.logic.service;

import codecamp.bug.wars.game.logic.models.Direction;
import codecamp.bug.wars.game.logic.models.Game;
import codecamp.bug.wars.game.logic.models.GameResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BugRunner {
    int x;
    int y;
    Direction direction;
    boolean dying;
    int team;
    List<Integer> program;
    int programCounter;

}
