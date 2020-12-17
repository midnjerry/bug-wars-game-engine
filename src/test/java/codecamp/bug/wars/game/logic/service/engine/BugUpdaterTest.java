package codecamp.bug.wars.game.logic.service.engine;

import codecamp.bug.wars.game.logic.models.*;
import codecamp.bug.wars.game.logic.service.actions.NoopAction;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class BugUpdaterTest {
    BugUpdater bugUpdater;
    List<BugResponse> bugResponses;
    List<BugExecutor> bugExecutors;
    GameState gameState;

    @BeforeEach
    void setup(){
        bugUpdater = new BugUpdater();
        GameBuilder gameBuilder = new GameBuilder();
        Game game = gameBuilder.defaultGame().build();

        gameState = new GameState(
                1, game.getMap(), new ArrayList<>(), new ArrayList<>()
        );

        bugResponses = game.createBugResponses();
        bugExecutors = game.createBugExecutors();
    }

    @Test
    void updateBugs_shouldReturnUpdatedBugResponses() {
        // arrange
        ObjectMapper objectMapper = new ObjectMapper();
        List<BugResponse> expected = new ArrayList<>();

        try {
            expected = Arrays.asList(objectMapper
                    .readValue(objectMapper.writeValueAsString(bugResponses), BugResponse[].class));

        } catch (Exception e) {
            expected = null;
        }

        expected.get(0).setCommand("NoopAction()");
        expected.get(1).setEndingY(1);
        expected.get(1).setCommand("MoveAction()");
        expected.get(2).setDirection(Direction.WEST);
        expected.get(2).setCommand("TurnLeftAction()");
        expected.get(3).setDirection(Direction.EAST);
        expected.get(3).setCommand("TurnRightAction()");

        // act
        bugUpdater.updateBugs(bugResponses, bugExecutors, gameState);

        // assert
        assertEquals(expected, bugResponses);
    }
}