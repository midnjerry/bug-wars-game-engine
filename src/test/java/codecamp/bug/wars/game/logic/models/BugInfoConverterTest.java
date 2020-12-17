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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BugInfoConverterTest {
    List<BugInfo> bugs;
    BugInfosConverter bugInfosConverter;

    @BeforeEach
    void setup(){
        bugInfosConverter = new BugInfosConverter(new ObjectMapper());

        List<Integer> code1 = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> code2 = Arrays.asList(6, 5, 4, 3, 2, 1);
        bugs = Arrays.asList(
                new BugInfo(1, code1),
                new BugInfo(2, code2)
        );
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
    void convertToDatabaseColumn() {
        // arrange
        String json = openResourceFile("bugInfo.json");

        // act
        String actual = bugInfosConverter.convertToDatabaseColumn(bugs);

        // assert
        assertEquals(json.replaceAll("\\s+",""), actual);
    }

    @Test
    void convertToEntityAttribute() {
        // arrange
        String json = openResourceFile("bugInfo.json");

        // act
        List<BugInfo> actual = bugInfosConverter.convertToEntityAttribute(json);

        // assert
        assertEquals(bugs, actual);
    }
}