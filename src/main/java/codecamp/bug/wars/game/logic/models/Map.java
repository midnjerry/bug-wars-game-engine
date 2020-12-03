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

    @ManyToMany
    List<MapSpaceRow> mapGrid;

    @ElementCollection
    List<Spawn> spawns;

    @ElementCollection
    List<Food> foods;

    public MapSpace getSpace(int x, int y){
        if (y < 0 || x < 0 || y >= mapGrid.size()) {
            return MapSpace.WALL;
        }

       MapSpaceRow row = mapGrid.get(y);

        if (x >= row.getSpaces().size()){
            return MapSpace.WALL;
        }

        return row.getSpaces().get(x);
    }
}