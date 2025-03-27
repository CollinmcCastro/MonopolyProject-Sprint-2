package Model;

public class GoSpace extends Space {
    public GoSpace() {
        super("Go");
    }

    private Bank bank;

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    @Override
    public void landOn(Player player) {
        bank.payPlayer(player,200); // Collect $200 for passing Go
        System.out.println(player.getName() + " landed on Go and collected $200.");
    }
}