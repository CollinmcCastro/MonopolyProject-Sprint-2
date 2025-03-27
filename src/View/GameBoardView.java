package View;

import Model.GameBoard;
import Model.Player;
import Model.Space;
import Model.PropertySpace;

public class GameBoardView {
    private final GameBoard gameBoard;

    public GameBoardView(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void displayBoard() {
        System.out.println("Game Board:");
        for (int i = 0; i < gameBoard.getSpaces().size(); i++) {
            Space space = gameBoard.getSpace(i);
            StringBuilder spaceInfo = new StringBuilder(i + ": " + space.getName());

            // Check if the space is a property and display ownership information
            if (space instanceof PropertySpace property) {
                if (property.getOwner() != null) {
                    spaceInfo.append(" (Owned by ").append(property.getOwner().getName()).append(")");
                } else {
                    spaceInfo.append(" (Unowned)");
                }
            }

            // Check if any players are on this space
            for (Player player : gameBoard.getPlayers()) {
                if (player.getPosition() == i) {
                    spaceInfo.append(" [").append(player.getName()).append("]");
                }
            }

            System.out.println(spaceInfo);
        }
    }

    public void displayPlayerStatus() {
        System.out.println("Player Status:");
        for (Player player : gameBoard.getPlayers()) {
            System.out.println(player.getName() + " - Position: " + player.getPosition() + ", Money: $" + player.getMoney());
        }
    }
}