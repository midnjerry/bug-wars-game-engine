package codecamp.bug.wars.game.logic.service.engine;

import codecamp.bug.wars.game.logic.models.GameState;
import codecamp.bug.wars.game.logic.service.actions.Action;
import org.springframework.stereotype.Service;

@Service
public class TickProcessor {
    public void processTick(GameState gameState) {
        gameState.setTick(gameState.getTick() + 1);
    }
}
