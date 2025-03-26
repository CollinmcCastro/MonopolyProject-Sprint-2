package Model;

public class FreeParkingSpace extends Space {
    public FreeParkingSpace() {
        super("Free Parking");
    }

    @Override
    public void landOn(Player player) {
        // Logic for landing on Free Parking space
        System.out.println(player.getName() + " landed on Free Parking.");
    }
}
