package codecamp.bug.wars.game.logic.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import static codecamp.bug.wars.game.logic.models.MapSpace.OPEN;
import static codecamp.bug.wars.game.logic.models.MapSpace.WALL;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MapConverterTest {

    Map map;
    MapConverter mapConverter;


    @BeforeEach
    public void setup() {
        map = new Map(
                Arrays.asList(
                        (Arrays.asList(OPEN, OPEN, OPEN, OPEN, OPEN)),
                        (Arrays.asList(OPEN, WALL, OPEN, WALL, OPEN)),
                        (Arrays.asList(OPEN, OPEN, OPEN, OPEN, OPEN)),
                        (Arrays.asList(OPEN, WALL, OPEN, WALL, OPEN)),
                        (Arrays.asList(OPEN, OPEN, OPEN, OPEN, OPEN))
                ), Arrays.asList(
                new Spawn(0, 0, 1, Direction.NORTH),
                new Spawn(4, 4, 2, Direction.NORTH)),
                Arrays.asList(new Food(2, 2)));
        mapConverter = new MapConverter(new ObjectMapper());
    }


    private String openResourceFile(String filename) {
        try {
            URL resource = MapConverterTest.class.getClassLoader().getResource(filename);
            System.out.println(resource);
            byte[] bytes = Files.readAllBytes(Paths.get(resource.toURI()));
            return new String(bytes);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Test
    public void convertToDatabaseColumn_TakesMapAndConvertsToJson() {
        // arrange
        String json = openResourceFile("map.json");

        // act
        String actual = mapConverter.convertToDatabaseColumn(map);

        // assert
        assertEquals(json.replaceAll("\\s+",""), actual);
    }

    @Test
    public void convertToEntityAttribute_ShouldConvertJsonToObject() {
        // arrange
        String json = openResourceFile("map.json");

        // act
        Map actual = mapConverter.convertToEntityAttribute(json);

        // assert
        assertEquals(map, actual);
    }


}