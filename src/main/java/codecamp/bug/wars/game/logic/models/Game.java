package codecamp.bug.wars.game.logic.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
}
