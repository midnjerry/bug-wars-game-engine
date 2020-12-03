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

    public MapSpace getNextSpace(int x, int y, Direction direction){

        switch (direction){
            case EAST: return MapSpace.WALL;
            case WEST: return MapSpace.OPEN;
            case SOUTH: return MapSpace.OPEN;
            case NORTH: return MapSpace.OPEN;
        }
        return null;
    };


}
