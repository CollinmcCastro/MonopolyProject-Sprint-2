package Model;

import java.util.List;

public class GameState {
    private final GameBoard board;
    private final Dice dice;
    private final List<Player> players;
    private int currentPlayerIndex;
    private boolean gameOver;

    public GameState(GameBoard board) {
        this.board = board;
        this.dice = new Dice();
        this.players = board.getPlayers();
        this.currentPlayerIndex = 0;
        this.gameOver = false;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public void nextTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        dice.resetDoublesRolled();
    }

    public void handleTurn() {
        Player current = getCurrentPlayer();
        if (current.isInJail()) {
            handleJailTurn(current);
            return;
        }

        List<Integer> roll = dice.rollDice();
        int total = roll.get(0) + roll.get(1);
        board.movePlayer(current, total);

        if (dice.getDoublesRolled() > 0) {
            System.out.println(current.getName() + " rolled doubles and gets another turn!");
        } else {
            nextTurn();
        }
    }

    private void handleJailTurn(Player player) {
        if (player.hasGetOutOfJailFreeCard()) {
            System.out.println(player.getName() + " used a Get Out of Jail Free card.");
            player.receiveGetOutOfJailFreeCard(); // resets flag
            player.setPosition(10);
            player.getDice().resetDoublesRolled();
            player.move(1); // move out of jail (or use logic to determine roll next turn)
            player.goToJail(); // this should be a method that releases them
        } else {
            List<Integer> roll = player.getDice().rollDice();
            if (roll.get(0).equals(roll.get(1))) {
                System.out.println(player.getName() + " rolled doubles and is released from jail!");
                player.setPosition((player.getPosition() + roll.get(0) + roll.get(1)) % 40);
                player.goToJail(); // should be a release method or flag reset
            } else {
                System.out.println(player.getName() + " did not roll doubles and stays in jail.");
                nextTurn();
            }
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void endGame() {
        this.gameOver = true;
        System.out.println("Game Over!");
    }
}
