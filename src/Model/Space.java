package Model;

public abstract class Space {
    protected String name;

    public Space(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void landOn(Player player);
}