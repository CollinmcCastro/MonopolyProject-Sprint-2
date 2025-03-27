/**
 * Class Created by Kristian Wright
 */
package View;

import Model.Dice;

public class DiceMain {
    public static void main(String[] args) {
        Dice dice = new Dice();
        DiceView diceView = new DiceView(dice);

        // Roll the dice until 3 doubles are rolled
        while (dice.getDoublesRolled() < 3) {
            System.out.println(diceView.displayRollResults());
            System.out.println(diceView.displayDoublesRolled());
        }

        // Reset doubles rolled count
        System.out.println(diceView.resetDoublesRolled());
    }
}