package codecamp.bug.wars.game.logic.models;

import codecamp.bug.wars.game.logic.service.engine.BugExecutor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Game {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = MapConverter.class)
    @Column(columnDefinition="text")
    private Map map;

    @Convert(converter = BugInfosConverter.class)
    @Column(columnDefinition="text")
    private List<BugInfo> bugInfos;

    private Integer ticks;
    private Long seed;

    public List<BugExecutor> createBugExecutors(){
        List<BugExecutor> res = new ArrayList<>();

        if(bugInfos == null || bugInfos.isEmpty()){
            return res;
        }

        for(BugInfo bug : bugInfos){
            res.add(new BugExecutor(bug.getTeam(), bug.getCode(), 0));
        }

        return res;
    }

    public List<BugResponse> createBugResponses(){
        List<BugResponse> res = new ArrayList<>();
        Spawn bugSpawn;

        for(BugInfo bug : bugInfos){
            bugSpawn = map.getSpawns().get(map.getSpawnIndex(bug.getTeam()));

            res.add(new BugResponse(
                    bug.getTeam(),
                    bugSpawn.getX(),
                    bugSpawn.getY(),
                    bugSpawn.getX(),
                    bugSpawn.getY(),
                    bugSpawn.getDirection(),
                    null,
                    false
            ));
        }

        return res;
    }
}
