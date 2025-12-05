package gemstones.gemstone;

// class for representing Diamond - precious stone
public class Diamond extends PreciousStone {

    private String cut; // examples: Old single, Table, Point etc.

    // constructor
    public Diamond(double weight, double cost, double transparency, String lustre, String color, String origin, String cut) {
        super("Diamond", weight, cost, transparency, lustre, color, origin);
        this.cut = cut;
    }

    // getter for cut
    public String getCut() {
        return cut;
    }

    // setter for cut
    public void setCut(String cut) {
        this.cut = cut;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("; cut = %s.", cut);
    }
}
