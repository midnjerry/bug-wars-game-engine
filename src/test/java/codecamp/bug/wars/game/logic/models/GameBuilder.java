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

        List<Integer> code1 = Arrays.asList(
                1, 1, 1, 1, 1, 1, 1, 1
        );
        List<Integer> code2 = Arrays.asList(
                2, 2, 2, 2, 2, 2, 2, 2
        );
        List<Integer> code3 = Arrays.asList(
                3, 3, 3, 3, 3, 3, 3, 3
        );
        List<Integer> code4 = Arrays.asList(
                4, 4, 4, 4, 4, 4, 4, 4
        );

        this.bugInfos = Arrays.asList(
                new BugInfo(1, code1),
                new BugInfo(2, code2),
                new BugInfo(3, code3),
                new BugInfo(4, code4)
        );

        ticks = 1;

        seed = 123456789L;

        return this;
    }

    public Game build(){
        return new Game(id, map, bugInfos, ticks, seed);
    }

}
