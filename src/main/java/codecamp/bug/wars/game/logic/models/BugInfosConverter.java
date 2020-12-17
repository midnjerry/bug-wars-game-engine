package codecamp.bug.wars.game.logic.models;

import aj.org.objectweb.asm.TypeReference;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.List;

public class BugInfosConverter implements AttributeConverter<List<BugInfo>, String> {

    private final ObjectMapper mapper;

    @Autowired
    public BugInfosConverter(ObjectMapper objectMapper) {
        mapper = objectMapper;
//        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    @Override
    public String convertToDatabaseColumn(List<BugInfo> bugs) {
        try {
            return mapper.writeValueAsString(bugs);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<BugInfo> convertToEntityAttribute(String json) {
        try {
            return Arrays.asList(mapper.readValue(json, BugInfo[].class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}

