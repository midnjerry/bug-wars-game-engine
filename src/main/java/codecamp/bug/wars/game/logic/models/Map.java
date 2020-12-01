package codecamp.bug.wars.game.logic.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Map {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    List<MapSpaceRow> mapGrid;

    @ElementCollection
    List<Spawn> spawns;

    @ElementCollection
    List<Food> foods;


}
