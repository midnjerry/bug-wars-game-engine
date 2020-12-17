package codecamp.bug.wars.game.logic.models;

import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

import static codecamp.bug.wars.game.logic.models.MapSpace.*;

@NoArgsConstructor
public class GameBuilder {
    Long id;
    Map map;
    List<BugInfo> bugInfos;
    Integer ticks;
    Long seed;

    public GameBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public GameBuilder setMap(Map map) {
        this.map = map;
        return this;
    }

    public GameBuilder setBugInfos(List<BugInfo> bugInfos) {
        this.bugInfos = bugInfos;
        return this;
    }

    public GameBuilder setTicks(Integer ticks) {
        this.ticks = ticks;
        return this;
    }

    public GameBuilder setSeed(Long seed) {
        this.seed = seed;
        return this;
    }

    public GameBuilder defaultGame() {
        id = 1L;

        List<List<MapSpace>> mapGrid = Arrays.asList(
            Arrays.asList(OPEN, OPEN, OPEN, OPEN, OPEN),
            Arrays.asList(OPEN, WALL, OPEN, WALL, OPEN),
            Arrays.asList(OPEN, OPEN, OPEN, OPEN, OPEN),
            Arrays.asList(OPEN, WALL, OPEN, WALL, OPEN),
            Arrays.asList(OPEN, OPEN, OPEN, OPEN, OPEN)
        );
        List<Spawn> spawns = Arrays.asList(
            new Spawn(0, 0, 1, Direction.SOUTH),
            new Spawn(4, 0, 2, Direction.SOUTH),
            new Spawn(0, 4, 3, Direction.NORTH),
            new Spawn(4, 4, 4, Direction.NORTH)
        );
        List<Food> food = Arrays.asList(
            new Food(2, 1),
            new Food(1, 2),
            new Food(2, 3),
            new Food(3, 2)
        );
        this.map = Map.builder().mapGrid(mapGrid).foods(food).spawns(spawns).build();

        List<Integer> code = Arrays.asList(
                0, 0, 0, 0, 0, 0, 0, 0
        );
        this.bugInfos = Arrays.asList(
                new BugInfo(1, code),
                new BugInfo(2, code),
                new BugInfo(3, code),
                new BugInfo(4, code)
        );

        ticks = 1;

        seed = 123456789L;

        return this;
    }

    public Game build(){
        return new Game(id, map, bugInfos, ticks, seed);
    }

}
