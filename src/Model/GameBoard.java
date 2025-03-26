package Model;
import Model.GameState;

import java.util.*;

/**
 * The GameBoard class represents the game board in a Monopoly game.
 * It manages the spaces, Chance and Community Chest decks, and the players.
 */
public class GameBoard {
    private final List<Space> spaces; // Board spaces
    private final Stack<ChanceCard> chanceDeck; // Chance card deck
    private final Stack<CommunityChestCard> communityDeck; // Community Chest card deck
    private final List<Player> players; // Players in the game
    private final GameState gameState;


    /**
     * Constructs a GameBoard with the given list of players.
     * Initializes the board spaces and card decks.
     *
     * @param players The list of players in the game.
     */
    public GameBoard(List<Player> players, GameState gameState) {
        this.players = players;
        this.spaces = new ArrayList<>();
        this.chanceDeck = new Stack<>();
        this.communityDeck = new Stack<>();
        this.gameState = gameState;
        initializeBoard();
    }

    /**
     * Initializes the game board with properties and spaces.
     */
    private void initializeBoard() {
        gameState.initializeGameSpaces();
        gameState.initializeChanceCards();
        gameState.initializeCommunityChestCards();
        shuffleChanceCards();
        shuffleCommunityChestCards();
        distributeStartingMoney();
    }


    /**
     * Shuffles the Chance cards.
     */
    public static void shuffleChanceCards() {
        Collections.shuffle(ChanceCard);
    }

    /**
     * Shuffles the Community Chest cards.
     */
    public static void shuffleCommunityChestCards() {
        Collections.shuffle(CommunityChestCard);
    }

    /**
     * Distributes the starting money to each player.
     */
    public static void distributeStartingMoney() {
        for (Player player : players) {
            player.setMoney(1500);
        }
    }

    /**
     * Moves a player by a specified number of steps.
     * If the player passes Go, they collect $200.
     *
     * @param player The player to move.
     * @param steps The number of steps to move.
     */
    public void movePlayer(Player player, int steps) {
        int oldPosition = player.getPosition();
        int newPosition = (oldPosition + steps) % spaces.size();
        if (oldPosition > newPosition) {
            player.increaseMoney(200);
            System.out.println(player.getName() + " passed Go and collected $200!");
        }
        player.setPosition(newPosition);
        System.out.println(player.getName() + " moved to " + spaces.get(newPosition).getName());
        spaces.get(newPosition).landOn(player);
    }

    /**
     * Gets the space at a specified position.
     *
     * @param position The position of the space.
     * @return The space at the specified position.
     */
    public Space getSpace(int position) {
        return spaces.get(position);
    }

    /**
     * Gets the Chance card deck.
     *
     * @return The Chance card deck.
     */
    public Stack<ChanceCard> getChanceDeck() {
        return chanceDeck;
    }

    /**
     * Gets the Community Chest card deck.
     *
     * @return The Community Chest card deck.
     */
    public Stack<CommunityChestCard> getCommunityDeck() {
        return communityDeck;
    }

    /**
     * Gets the list of spaces on the board.
     *
     * @return The list of spaces.
     */
    public List<Space> getSpaces() {
        return spaces;
    }
}