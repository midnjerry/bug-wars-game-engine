package codecamp.bug.wars.game.logic.service.engine;


import codecamp.bug.wars.game.logic.exceptions.GameNotFoundException;
import codecamp.bug.wars.game.logic.exceptions.InvalidInputException;
import codecamp.bug.wars.game.logic.models.*;
import codecamp.bug.wars.game.logic.repository.GameRepository;
import codecamp.bug.wars.game.logic.service.GameEngine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GameLoaderTest {

    private GameRepository gameEngineMockRepository;
    private GameLoader mockGameLoader;
    private Game sampleGameWithId;
    private Game sampleGameWithNoId;
    private GameResult sampleGameResult;
    private GameEngine gameEngine;

    @BeforeEach
    public void setup() {
        gameEngineMockRepository = Mockito.mock(GameRepository.class);
        gameEngine = Mockito.mock(GameEngine.class);
        mockGameLoader = new GameLoader(gameEngineMockRepository, gameEngine);

        List<MapSpaceRow> rows = Arrays.asList(
                new MapSpaceRow(Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN)),
                new MapSpaceRow(Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN)),
                new MapSpaceRow(Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN)),
                new MapSpaceRow(Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN)),
                new MapSpaceRow(Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN))
        );

        List<Spawn> spawns = Arrays.asList(new Spawn(1, 0, 1, Direction.NORTH));
        List<Food> food = Arrays.asList(new Food(1, 1));
        Map map = Map.builder().mapGrid(rows).foods(food).spawns(spawns).build();
        List<Integer> code = Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1);
        List<BugInfo> bugInfos = Arrays.asList(new BugInfo(null, 1, code.toString()));
        BugResponse bugResponse = new BugResponse(2, Direction.NORTH, 3, 4, "attack", false);
        GameState gameStateTest = new GameState(1, map, null, food);
        List<Integer> winners = Arrays.asList(1, 2);

        List<GameState> gameStateArray = Arrays.asList(gameStateTest);
        sampleGameWithId = new Game(1L, map, bugInfos, 1, null);
        sampleGameWithNoId = new Game(null, map, bugInfos, 1, null);
        sampleGameResult = new GameResult(winners, "winner", gameStateArray);
    }

    @Test
    public void getAllGames_callsRepositoryAndReturnsList() {
        //    arrange
        List<Game> expected = Arrays.asList(
                sampleGameWithId,
                sampleGameWithId,
                sampleGameWithId
        );
        when(gameEngineMockRepository.findAll()).thenReturn(expected);

//    Act
        List<Game> result = mockGameLoader.getAllGames();

//        Assert
        assertEquals(expected, result);
        verify(gameEngineMockRepository).findAll();
    }

    @Test
    public void getReplay_callsRepository() {
        // arrange
        when(gameEngineMockRepository.findById(1L)).thenReturn(Optional.of(sampleGameWithId));

        // act
        mockGameLoader.getReplay(1L);

        // assert
        verify(gameEngineMockRepository).findById(1L);
    }

    @Test
    public void getReplay_AfterObtainingGame_CallGameEngineToExecute() {
        // arrange
        when(gameEngineMockRepository.findById(1L)).thenReturn(Optional.of(sampleGameWithId));
        when(gameEngine.playGame(sampleGameWithId)).thenReturn(sampleGameResult);

        // act
        GameResult actual = mockGameLoader.getReplay(1L);

        // assert
        verify(gameEngine).playGame(sampleGameWithId);
        assertEquals(sampleGameResult, actual);
    }

    @Test
    public void getReplay_IfGameDoesNotExistThrowError() {
        GameNotFoundException exception = assertThrows(GameNotFoundException.class, () -> {
            // act
            GameResult actual = mockGameLoader.getReplay(1L);
        });
        // assert
        assertEquals("There is no Game with that ID.", exception.getMessage());
    }

    @Test
    public void createGame_ifGameIsNullThrowAnError() {
        InvalidInputException exception = assertThrows(InvalidInputException.class, () -> {
            // act
            GameResult actual = mockGameLoader.createGame(null);
        });
        // assert
        assertEquals("Game cannot be null", exception.getMessage());
    }

    @Test
    public void createGame_ifGameHasIdThrowAnError() {
        InvalidInputException exception = assertThrows(InvalidInputException.class, () -> {
            // act
            mockGameLoader.createGame(sampleGameWithId);
        });
        // assert
        assertEquals("Override Unsuccessful: Game ID should be null", exception.getMessage());
    }

    @Test
    public void createGame_savesGameInRepository() {
        mockGameLoader.createGame(sampleGameWithNoId);
        Mockito.verify(gameEngineMockRepository).save(sampleGameWithNoId);
    }

    @Test
    public void createGame_callsEngineToPlayAndReturnsResult() {
        // arrange
        when(gameEngineMockRepository.save(sampleGameWithNoId)).thenReturn(sampleGameWithId);
        when(gameEngine.playGame(sampleGameWithId)).thenReturn(sampleGameResult);

        // act
        GameResult actual = mockGameLoader.createGame(sampleGameWithNoId);

        // assert
        verify(gameEngine).playGame(sampleGameWithId);
        assertEquals(sampleGameResult, actual);
    }
}
