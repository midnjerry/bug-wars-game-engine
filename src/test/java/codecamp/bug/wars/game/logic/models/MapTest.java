package codecamp.bug.wars.game.logic.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {
    Map map;



    @BeforeEach
    public void setup(){
        List<MapSpaceRow> rows = Arrays.asList(
                new MapSpaceRow(Arrays.asList(MapSpace.WALL, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN)),
                new MapSpaceRow(Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.WALL, MapSpace.OPEN, MapSpace.WALL)),
                new MapSpaceRow(Arrays.asList(MapSpace.WALL, MapSpace.OPEN, MapSpace.OPEN, MapSpace.WALL, MapSpace.OPEN)),
                new MapSpaceRow(Arrays.asList(MapSpace.OPEN, MapSpace.WALL, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN)),
                new MapSpaceRow(Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.WALL, MapSpace.WALL))
        );
        map = new Map(null, rows, null, null);
    };

    @Test
    public void getNextSpace_shouldReturnRightSpaceFacingEast(){
//        Arrange
//        Act
        MapSpace result = map.getNextSpace(1,1, Direction.EAST);
//        Assert
        assertEquals(MapSpace.WALL, result);
    };

    @Test
    public void getNextSpace_shouldReturnLeftSpaceFacingWest(){
        //        Arrange
//        Act
        MapSpace result = map.getNextSpace(1,1, Direction.WEST);
//        Assert
        assertEquals(MapSpace.OPEN, result);
    };

    @Test
    public void getNextSpace_shouldReturnTopSpaceFacingNorth(){
        //        Arrange
//        Act
        MapSpace result = map.getNextSpace(1,1, Direction.NORTH);
//        Assert
        assertEquals(MapSpace.OPEN, result);
    };

    @Test
    public void getNextSpace_shouldReturnTopSpaceFacingSouth(){
        //        Arrange
//        Act
        MapSpace result = map.getNextSpace(1,1, Direction.SOUTH);
//        Assert
        assertEquals(MapSpace.OPEN, result);
    }


    }

