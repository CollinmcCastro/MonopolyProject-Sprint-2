package Model;

import java.util.*;

public class Player {
    final private String name;
    private String token;
    private int money;
    private int position;
    final private List<Property> properties;
    private boolean inJail;
    private int jailTurns;
    private boolean hasGetOutOfJailFreeCard;
    private Dice dice;
    private GameBoard gameBoard;

    private final List<Player> players; // Players in the game

    public Player(String name, String token, GameBoard gameBoard, List<Player> players) {
        this.name = name;
        this.token = token;
        this.players = players;
        this.money = 1500;
        this.position = 0;
        this.properties = new ArrayList<>();
        this.inJail = false;
        this.jailTurns = 0;
        this.hasGetOutOfJailFreeCard = false;
        this.gameBoard = gameBoard;
        this.dice = new Dice();
    }

    public void move(int steps) {
        int oldPosition = position;
        position = (position + steps) % 40;
        Space newSpace = gameBoard.getSpace(position);
        System.out.println(token + " moved to " + newSpace.getName());
    }

    public void goToJail() {
        this.inJail = true;
        this.position = 10;
        System.out.println(token + " is sent to Jail.");
    }

    public String assignTokensToPlayers() {
        List<String> availableTokens = new ArrayList<>(Arrays.asList(
                "Top Hat", "Battleship", "Thimble", "Cannon", "Cat", "Iron",
                "Scottie dog", "The Shoe", "Boot", "Ducky", "Horse & Rider",
                "Penguin", "Race car", "Train", "Wheelbarrow"
        ));
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        String chosenToken = availableTokens.remove(choice - 1);

        for (Player player : players) {
            System.out.println(this.name + ", choose your token from the following list:");
            for (int j = 0; j < availableTokens.size(); j++) {
                System.out.println((j + 1) + ": " + availableTokens.get(j));
            }

            while (choice < 1 || choice > availableTokens.size()) {
                System.out.print("Enter the number of your choice: ");
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                }
            }

            player.token = chosenToken;
            System.out.println(this.name + " has chosen the " + chosenToken + " token.");
        }
        return chosenToken;
    }

    public void rollDice() {
        dice.rollDice(this);
    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void increaseMoney(int amount) {
        money += amount;
    }

    public void decreaseMoney(int amount) {
        money -= amount;
    }

    public boolean isInJail() {
        return inJail;
    }

    public boolean hasGetOutOfJailFreeCard() {
        return hasGetOutOfJailFreeCard;
    }

    public void receiveGetOutOfJailFreeCard() {
        this.hasGetOutOfJailFreeCard = true;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void addProperty(Property property) {
        properties.add(property);
    }

    public Dice getDice() {
        return dice;
    }
}