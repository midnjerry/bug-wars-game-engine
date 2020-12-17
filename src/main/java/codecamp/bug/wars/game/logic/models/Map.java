package codecamp.bug.wars.game.logic.models;

import codecamp.bug.wars.game.logic.exceptions.InvalidInputException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Map {
    List<List<MapSpace>> mapGrid;
    List<Spawn> spawns;
    List<Food> foods;

    public MapSpace getSpace(int x, int y){
        if (y < 0 || x < 0 || y >= mapGrid.size()) {
            return MapSpace.WALL;
        }

       List<MapSpace> row = mapGrid.get(y);

        if (x >= row.size()){
            return MapSpace.WALL;
        }

        return row.get(x);
    }

    public int getSpawnIndex(int team) {
        int counter = 0;
        for(Spawn s : spawns){
            if(s.getTeam() == team){
                return counter;
            }
            counter++;
        }
        throw new InvalidInputException("Team does not exist in spawns");
    }
}