package codecamp.bug.wars.game.logic.service;

import codecamp.bug.wars.game.logic.models.*;
import codecamp.bug.wars.game.logic.repository.GameEngineRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class GameEngineServiceTest {

    private GameEngineRepository gameEngineMockRepository;
    private GameEngineService gameEngineService;
    private Game sampleGame;
    private GameResult sampleGameResult;

    @BeforeEach
    public void setup(){
        gameEngineMockRepository = Mockito.mock(GameEngineRepository.class);
        gameEngineService = new GameEngineService(gameEngineMockRepository);

        MapSpace[][] rows = {
                {MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN},
                {MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN},
                {MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN},
                {MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN},
                {MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN}
        };

        List<Spawn> spawns = Arrays.asList(new Spawn(1, 0, 1));
        List<Food> food = Arrays.asList(new Food(1, 1));
        Map map = new Map(rows, spawns, food);
        List<Integer> code = Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1);
        List<Bug> bugs = Arrays.asList(new Bug(1, code));
        BugResponse bugResponse = new BugResponse(2, Direction.NORTH, 3, 4, "attack", false);
        GameState gameStateTest = new GameState(1, bugResponse);
        List<Integer> winners = Arrays.asList(1, 2);

        List<GameState> gameStateArray = Arrays.asList(gameStateTest);
        sampleGame = new Game(map, bugs, 1, null);
        sampleGameResult = new GameResult(winners, "winner", gameStateArray);
    }

    @Test
    public void getAllGames_callsRepositoryAndReturnsList(){
        //    arrange
        List<Game> expected = Arrays.asList(
                sampleGame,
                sampleGame,
                sampleGame
        );
    Mockito.when(GameEngineRepository.findAllGames()).thenReturn(expected);
    }
}
