package codecamp.bug.wars.game.logic.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bug {
    Integer team;
    List<Integer> code;
}
