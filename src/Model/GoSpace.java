package Model;

public class GoSpace extends Space {
    public GoSpace() {
        super("Go");
    }

    @Override
    public void landOn(Player player) {
        player.increaseMoney(200); // Collect $200 for passing Go
        System.out.println(player.getName() + " landed on Go and collected $200.");
    }
}
