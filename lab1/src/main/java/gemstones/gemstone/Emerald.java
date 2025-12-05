package gemstones.gemstone;

// class for representing Emerald - precious stone
public class Emerald extends PreciousStone {

    private boolean treated; // treated/untreated

    // constructor
    public Emerald(double weight, double cost, double transparency, String lustre, String color, String origin, boolean treated) {
        super("Emerald", weight, cost, transparency, lustre, color, origin);
        this.treated = treated;
    }

    // getter for treated; if Emerald is treated - True, otherwise - False
    public boolean isTreated() {
        return treated;
    }

    // setter for treated
    public void setTreated(boolean treated) {
        this.treated = treated;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("; treatment = %s.", treated ? "yes" : "no");
    }
}
