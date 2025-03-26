package Model;

public class GoToJailSpace extends Space {
    public GoToJailSpace() {
        super("Go To Jail");
    }

    @Override
    public void landOn(Player player) {
        player.goToJail(); // Send player to Jail
        System.out.println(player.getName() + " landed on Go To Jail and is sent to Jail.");
    }
}
