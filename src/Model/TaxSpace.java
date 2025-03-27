package Model;

public class TaxSpace extends Space {
    private final int taxAmount;

    public TaxSpace(String name, int location, int taxAmount) {
        super(name);
        this.taxAmount = taxAmount;
    }

    @Override
    public void landOn(Player player) {
        player.decreaseMoney(taxAmount);
        System.out.println(player.getName() + " landed on " + name + " and paid $" + taxAmount + ".");
    }

    public int getTaxAmount() {
        return taxAmount;
    }
}
