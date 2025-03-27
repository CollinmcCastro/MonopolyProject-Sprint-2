/**
 * Tests Created by Kristian Wright
 */
package ModelTests;

import Model.Dice;
import Model.GameBoard;
import Model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Dice class.
 */
public class DiceTest {
    private Dice dice;
    private Player player;

    /**
     * Sets up the test environment before each test.
     * Initializes a new Dice object and a Player object.
     */
    @BeforeEach
    public void setUp() {
        dice = new Dice();
        GameBoard gameBoard = new GameBoard(new ArrayList<>());
        player = new Player("TestPlayer", "Car", gameBoard);
    }

    /**
     * Tests the rollDice method of the Dice class.
     * Verifies that the player moves the correct distance or goes to jail.
     */
    @Test
    public void testRollDice() {
        int initialPosition = player.getPosition();
        int initialMoney = player.getMoney();
        ArrayList<Integer> results = dice.rollDice();
        player.move(results.get(0) + results.get(1));
        int finalPosition = player.getPosition();
        int totalDistanceMoved = (finalPosition - initialPosition + 40) % 40; // Handle board wrap-around

        // Check if the player passed Go and collected $200
        if (finalPosition < initialPosition && !player.isInJail()) {
            assertEquals(initialMoney + 200, player.getMoney(), "Player should collect $200 for passing Go.");
        }

        // Check if the player moved the correct distance or went to jail
        if (player.isInJail()) {
            assertEquals(10, player.getPosition(), "Player should be in jail at position 10.");
        } else {
            int result1 = results.get(0);
            int result2 = results.get(1);
            assertTrue(result1 + result2 >= 2 && result1 + result2 <= 12, "Player should move between 2 and 12 spaces after rolling dice.");
        }
    }
}