package Model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a pair of dice used in the game.
 */
public class Dice {
    private int doublesRolled;

    public Dice(){
        doublesRolled = 0;
    }

    /**
     * Rolls the dice.
     * @return the results of the dice roll.
     */
    public ArrayList<Integer> rollDice() {
        Random r = new Random();
        ArrayList<Integer> results = new ArrayList<>();
        int result1 = r.nextInt(1, 7);
        int result2 = r.nextInt(1, 7);
        System.out.println("Rolled: " + result1 + " and " + result2); // Print dice roll
        if (result1 == result2){
            doublesRolled += 1;
        }
        results.add(result1);
        results.add(result2);
        return results;
    }

    public int getDoublesRolled() {
        return doublesRolled;
    }

    public void resetDoublesRolled() {
        doublesRolled = 0;
    }
}