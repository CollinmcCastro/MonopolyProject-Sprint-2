package Model;

public class JailSpace extends Space {
    public JailSpace() {
        super("Jail");
    }

    @Override
    public void landOn(Player player) {
        // Logic for landing on Jail space
        System.out.println(player.getName() + " landed on Jail.");
    }
}
