package codecamp.bug.wars.game.logic.service;

import codecamp.bug.wars.game.logic.repository.GameEngineRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

public class GameEngineServiceTest {

    private GameEngineRepository gameEngineMockRepository;
    private GameEngineService gameEngineService;

    @BeforeEach
    public void setup(){
        gameEngineMockRepository = Mockito.mock(GameEngineRepository.class);
        gameEngineService = new GameEngineService(gameEngineMockRepository);
    }
}
