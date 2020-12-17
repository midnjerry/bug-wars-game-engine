package codecamp.bug.wars.game.logic.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MapSpaceRow {

    List<MapSpace> spaces;

}
