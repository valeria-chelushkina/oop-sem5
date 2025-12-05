package gemstones.gemstone;

// class for representing Ruby - semi precious stone
public class Ruby extends PreciousStone {

    private double clarity;

    // constructor
    public Ruby(double weight, double cost, double transparency, String lustre, String color, String origin, double clarity) {
        super("Ruby", weight, cost, transparency, lustre, color, origin);
        this.clarity = clarity;
    }

    // getter for clarity
    public double getClarity() {
        return clarity;
    }

    // setter for clarity
    public void setClarity(double clarity) {
        this.clarity = clarity;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("; clarity = %.2f.", clarity);
    }
}
