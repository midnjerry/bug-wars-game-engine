package codecamp.bug.wars.game.logic.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {

    private Map map;
    private List<Bug> bugs;
    private Integer ticks;
    private Long seed;

}
