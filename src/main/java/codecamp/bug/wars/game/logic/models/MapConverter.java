package codecamp.bug.wars.game.logic.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.AttributeConverter;

public class MapConverter implements AttributeConverter<Map, String> {

    private final ObjectMapper mapper;

    @Autowired
    public MapConverter(ObjectMapper objectMapper) {
        mapper = objectMapper;
    }

    @Override
    public String convertToDatabaseColumn(Map map) {
        try {
            return mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Map convertToEntityAttribute(String json) {
        try {
            return mapper.readValue(json, Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}

