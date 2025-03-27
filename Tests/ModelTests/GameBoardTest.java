/**
 * Class Created by Kristian Wright
 */
package ModelTests;

import Model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameBoardTest {
    private GameBoard gameBoard;
    private Player player1;

    @BeforeEach
    public void setUp() {
        List<Player> players = new ArrayList<>();
        gameBoard = new GameBoard(players);
        player1 = new Player("Player 1", "Token1", gameBoard);
        Player player2 = new Player("Player 2", "Token2", gameBoard);
        players.add(player1);
        players.add(player2);
    }

    @Test
    public void testInitializeBoard() {
        assertEquals(40, gameBoard.getSpaces().size(), "The board should have 40 spaces.");
    }

    @Test
    public void testMovePlayer() {
        gameBoard.movePlayer(player1, 5);
        assertEquals(5, player1.getPosition(), "Player 1 should be at position 5.");
        gameBoard.movePlayer(player1, 35);
        assertEquals(0, player1.getPosition(), "Player 1 should be at position 0 after passing Go.");
        assertEquals(1700, player1.getMoney(), "Player 1 should have $1700 after passing Go.");
    }

    @Test
    public void testDistributeStartingMoney() {
        gameBoard.distributeStartingMoney();
        for (Player player : gameBoard.getPlayers()) {
            assertEquals(1500, player.getMoney(), player.getName() + " should have $1500 at the start.");
        }
    }

    @Test
    public void testShuffleChanceCards() {
        List<String> originalOrder = new ArrayList<>();
        gameBoard.getChanceDeck().forEach(card -> originalOrder.add(card.getDescription()));
        gameBoard.shuffleChanceCards();
        List<String> shuffledOrder = new ArrayList<>();
        gameBoard.getChanceDeck().forEach(card -> shuffledOrder.add(card.getDescription()));
        assertNotEquals(originalOrder, shuffledOrder, "Chance cards should be shuffled.");
    }

    @Test
    public void testShuffleCommunityChestCards() {
        List<String> originalOrder = new ArrayList<>();
        gameBoard.getCommunityDeck().forEach(card -> originalOrder.add(card.getDescription()));
        gameBoard.shuffleCommunityChestCards();
        List<String> shuffledOrder = new ArrayList<>();
        gameBoard.getCommunityDeck().forEach(card -> shuffledOrder.add(card.getDescription()));
        assertNotEquals(originalOrder, shuffledOrder, "Community Chest cards should be shuffled.");
    }

    @Test
    public void testLandOnDifferentSpaces() {
        gameBoard.movePlayer(player1, 1); // Move to Mediterranean Avenue
        assertEquals("Mediterranean Avenue", gameBoard.getSpace(player1.getPosition()).getName(), "Player 1 should be on Mediterranean Avenue.");
        assertEquals(1, player1.getPosition(), "Player 1 should be at position 1.");

        gameBoard.movePlayer(player1, 2); // Move to Baltic Avenue
        assertEquals("Baltic Avenue", gameBoard.getSpace(player1.getPosition()).getName(), "Player 1 should be on Baltic Avenue.");
        assertEquals(3, player1.getPosition(), "Player 1 should be at position 3.");

        gameBoard.movePlayer(player1, 1); // Move to Income Tax
        assertEquals("Income Tax", gameBoard.getSpace(player1.getPosition()).getName(), "Player 1 should be on Income Tax.");
        assertEquals(4, player1.getPosition(), "Player 1 should be at position 4.");

        gameBoard.movePlayer(player1, 1); // Move to Reading Railroad
        assertEquals("Reading Railroad", gameBoard.getSpace(player1.getPosition()).getName(), "Player 1 should be on Reading Railroad.");
        assertEquals(5, player1.getPosition(), "Player 1 should be at position 5.");
    }

    @Test
    public void testPlayerPassesGo() {
        player1.setPosition(38);
        gameBoard.movePlayer(player1, 3);
        assertEquals(1, player1.getPosition(), "Player 1 should be at position 1 after passing Go.");
        assertEquals(1700, player1.getMoney(), "Player 1 should have $1700 after passing Go.");
    }

    @Test
    public void testPlayerLandsOnGoToJail() {
        player1.setPosition(29);
        gameBoard.movePlayer(player1, 1); // Move to Go To Jail
        assertEquals(10, player1.getPosition(), "Player 1 should be at position 10 (Jail).");
        assertTrue(player1.isInJail(), "Player 1 should be in jail.");
    }

    @Test
    public void testDrawGoToJailCard() {
        // Mock drawing a "Go to Jail" card
        ChanceCard goToJailCard = new ChanceCard("Go to Jail", Player::goToJail);

        player1.setPosition(7); // Assume player is on Chance space
        goToJailCard.apply(player1);

        assertEquals(10, player1.getPosition(), "Player 1 should be in Jail.");
        assertTrue(player1.isInJail(), "Player 1 should be in jail.");
    }

    @Test
    public void testDrawAdvanceToRailroadCard() {
        // Mock drawing an "Advance to Nearest Railroad" card
        ChanceCard advanceToRailroadCard = new ChanceCard("Advance to Nearest Railroad", (player) -> {
            int newPosition = gameBoard.getNearestRailroad(player.getPosition());
            player.setPosition(newPosition);
        });

        player1.setPosition(7); // Assume player is on Chance space
        advanceToRailroadCard.apply(player1);

        int expectedRailroadPosition = gameBoard.getNearestRailroad(7);
        assertEquals(expectedRailroadPosition, player1.getPosition(), "Player 1 should be on the nearest Railroad.");
        assertTrue(gameBoard.getSpace(player1.getPosition()).getName().contains("Railroad"), "Player 1 should be on a Railroad space.");
    }

    @Test
    public void testDrawAdvanceToGoCard() {
        // Mock drawing an "Advance to Go" card
        CommunityChestCard advanceToGoCard = new CommunityChestCard("Advance to Go", (player) -> {
            player.setPosition(0);
            player.increaseMoney(200);
        });

        player1.setPosition(7); // Assume player is on Community Chest space
        advanceToGoCard.apply(player1);

        assertEquals(0, player1.getPosition(), "Player 1 should be on Go.");
        assertEquals(1700, player1.getMoney(), "Player 1 should have $1700 after advancing to Go.");
    }

    @Test
    public void testDrawBankErrorInYourFavorCard() {
        // Mock drawing a "Bank error in your favor" card
        CommunityChestCard bankErrorCard = new CommunityChestCard("Bank error in your favor", (player) -> player.increaseMoney(200));

        player1.setPosition(2); // Assume player is on Community Chest space
        bankErrorCard.apply(player1);

        assertEquals(1700, player1.getMoney(), "Player 1 should have $1700 after bank error in their favor.");
    }
}