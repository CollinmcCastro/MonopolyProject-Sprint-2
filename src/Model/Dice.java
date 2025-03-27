package Model;

import java.util.ArrayList;
import java.util.Random;

public class Dice {
    private int doublesRolled;
    private final Random random;

    public Dice() {
        this.doublesRolled = 0;
        this.random = new Random();
    }

    public ArrayList<Integer> rollDice() {
        ArrayList<Integer> results = new ArrayList<>();
        int die1 = random.nextInt(6) + 1;
        int die2 = random.nextInt(6) + 1;
        results.add(die1);
        results.add(die2);

        if (die1 == die2) {
            doublesRolled++;
        }

        return results;
    }

    public int getDoublesRolled() {
        return doublesRolled;
    }

    public void resetDoublesRolled() {
        doublesRolled = 0;
    }
}