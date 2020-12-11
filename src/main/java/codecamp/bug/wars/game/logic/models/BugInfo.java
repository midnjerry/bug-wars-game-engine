package codecamp.bug.wars.game.logic.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BugInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Integer team;

    String code;

    public List<Integer> parseCode(){
        List<String> stringList = Arrays.asList(code.split(" "));
        List<Integer> resList = new ArrayList<>();
        for(String code : stringList){
            resList.add(Integer.parseInt(code));
        }
        return resList;
    }
}
