package View;

import Model.Dice;

public class Main {
    public static void main(String[] args) {
        Dice dice = new Dice();
        DiceView diceView = new DiceView(dice);

        // Display roll results
        diceView.displayRollResults();

        // Display current doubles rolled
        diceView.displayDoublesRolled();

        // Reset doubles rolled count
        diceView.resetDoublesRolled();
    }
}