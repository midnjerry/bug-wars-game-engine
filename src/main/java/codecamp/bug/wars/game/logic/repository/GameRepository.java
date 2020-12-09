package codecamp.bug.wars.game.logic.repository;

import codecamp.bug.wars.game.logic.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {

}
