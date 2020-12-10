package codecamp.bug.wars.game.logic.service.engine;

import codecamp.bug.wars.game.logic.models.GameState;

public class TickProcessor {
    public GameState processTick(GameState gameState) {
        Integer tick = gameState.getTick();
        gameState.setTick(++tick);
        return gameState;
    }
}
