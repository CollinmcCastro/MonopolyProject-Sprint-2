package Model;

public class TaxSpace extends Space {
    private final int taxAmount;
    private Bank bank;

    public void setBank(Bank bank) { this.bank = bank; }
    public TaxSpace(String name, int location, int taxAmount) {
        super(name);
        this.taxAmount = taxAmount;
    }

    @Override
    public void landOn(Player player) {
        bank.collectFromPlayer(player, taxAmount);
        System.out.println(player.getName() + " landed on " + name + " and paid $" + taxAmount + ".");
    }

    public int getTaxAmount() {
        return taxAmount;
    }
}
