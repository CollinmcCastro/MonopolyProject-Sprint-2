package View;

import Model.Dice;

public class DiceView {

    private Dice dice;

    public DiceView(Dice dice) {
        this.dice = dice;
    }

    public void displayRollResults() {
        var results = dice.rollDice();
        for (int i = 0; i < results.size(); i += 2) {
            System.out.println("Rolled: " + results.get(i) + " and " + results.get(i + 1));
            if (results.get(i).equals(results.get(i + 1))) {
                System.out.println("Rolled doubles! Total doubles rolled: " + dice.getDoublesRolled());
            }
        }
    }

    public void displayDoublesRolled() {
        System.out.println("Current doubles rolled: " + dice.getDoublesRolled());
    }

    public void resetDoublesRolled() {
        dice.resetDoublesRolled();
        System.out.println("Doubles rolled count reset.");
    }
}