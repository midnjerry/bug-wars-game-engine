package codecamp.bug.wars.game.logic.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BugResponse {

    Integer team;
    Direction direction;
    Integer x;
    Integer y;
    String command;
    Boolean dying;

}
