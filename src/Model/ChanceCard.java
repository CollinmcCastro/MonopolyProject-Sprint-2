/**
 * Class Created by Kristian Wright
 */
package Model;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * Represents a Chance card in the game.
 * Each Chance card has a description and an effect that is applied to a player.
 */
public class ChanceCard {
    final private String description;
    final private Consumer<Player> effect;

    /**
     * Constructs a ChanceCard with the given description and effect.
     *
     * @param description The description of the Chance card.
     * @param effect The effect of the Chance card, represented as a Consumer of Player.
     */
    public ChanceCard(String description, Consumer<Player> effect) {
        this.description = description;
        this.effect = effect;
    }

    /**
     * Applies the effect of the Chance card to the given player.
     *
     * @param player The player to apply the effect to.
     */
    public void apply(Player player) {
        effect.accept(player);
    }

    /**
     * Gets the description of the Chance card.
     *
     * @return The description of the Chance card.
     */
    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChanceCard that = (ChanceCard) o;
        return Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }

    // Helper methods for card actions

    /**
     * Moves the player to the nearest utility.
     */
    public static void moveToNearestUtility(Player player) {
        GameBoard gameBoard = player.getGameBoard();
        int currentPosition = player.getPosition();
        int[] utilityPositions = {12, 28}; // Example positions for utilities
        int nearestUtility = utilityPositions[0];

        for (int pos : utilityPositions) {
            if (pos > currentPosition) {
                nearestUtility = pos;
                break;
            }
        }

        if (currentPosition > utilityPositions[1]) {
            nearestUtility = utilityPositions[0];
        }

        player.setPosition(nearestUtility);
        System.out.println(player.getName() + " moved to the nearest utility at position " + nearestUtility);
    }

    /**
     * Moves the player to the nearest railroad.
     */
    public static void moveToNearestRailroad(Player player) {
        GameBoard gameBoard = player.getGameBoard();
        int nearestRailroad = gameBoard.getNearestRailroad(player.getPosition());
        player.setPosition(nearestRailroad);
        System.out.println(player.getName() + " moved to the nearest railroad at position " + nearestRailroad);
    }

    /**
     * Makes general repairs on the player's properties.
     */
    public static void makeGeneralRepairs(Player player) {
        int houseRepairCost = 25;
        int hotelRepairCost = 100;
        int totalRepairCost = 0;

        for (Property property : player.getProperties()) {
            if (property.hasHotel()) {
                totalRepairCost += hotelRepairCost;
            } else {
                totalRepairCost += property.getHouseCount() * houseRepairCost;
            }
        }

        player.decreaseMoney(totalRepairCost);
        System.out.println(player.getName() + " paid $" + totalRepairCost + " for general repairs on their properties.");
    }

    /**
     * Pays each player a specified amount.
     */
    public static void payEachPlayer(Player payer, int amount) {
        GameBoard gameBoard = payer.getGameBoard();
        for (Player player : gameBoard.getPlayers()) {
            if (!player.equals(payer)) {
                payer.decreaseMoney(amount);
                player.increaseMoney(amount);
                System.out.println(payer.getName() + " paid $" + amount + " to " + player.getName());
            }
        }
    }
}