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
    List<BugExecutor> bugExecutors;
    GameState gameState;

    @BeforeEach
    void setup(){
        bugUpdater = new BugUpdater();
        GameBuilder gameBuilder = new GameBuilder();
        Game game = gameBuilder.defaultGame().build();

        gameState = new GameState(
                1, game.getMap(), game.createBugResponses(), new ArrayList<>()
        );

        bugExecutors = game.createBugExecutors();
    }

    @Test
    void updateBugs_shouldReturnUpdatedBugResponses() {
        // arrange
        ObjectMapper objectMapper = new ObjectMapper();
        List<BugResponse> expected;

        try {
            expected = Arrays.asList(objectMapper
                    .readValue(objectMapper.writeValueAsString(gameState.getBugs()), BugResponse[].class));

        } catch (Exception e) {
            expected = null;
        }

        expected.get(0).setCommand("noop");
        expected.get(1).setEndingY(1);
        expected.get(1).setCommand("move");
        expected.get(2).setDirection(Direction.WEST);
        expected.get(2).setCommand("turnLeft");
        expected.get(3).setDirection(Direction.EAST);
        expected.get(3).setCommand("turnRight");

        // act
        bugUpdater.updateBugs(bugExecutors, gameState);

        // assert
        assertEquals(expected, gameState.getBugs());
    }
}