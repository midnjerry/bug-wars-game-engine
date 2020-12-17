package codecamp.bug.wars.game.logic.models;

import codecamp.bug.wars.game.logic.service.engine.BugExecutor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BugInfo {
    Integer team;
    List<Integer> code;
}
