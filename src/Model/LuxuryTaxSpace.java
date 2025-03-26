package Model;

public class LuxuryTaxSpace extends Space {
    public LuxuryTaxSpace() {
        super("Luxury Tax");
    }

    @Override
    public void landOn(Player player) {
        player.decreaseMoney(75); // Pay $75 for Luxury Tax
        System.out.println(player.getName() + " landed on Luxury Tax and paid $75.");
    }
}
