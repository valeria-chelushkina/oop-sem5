package gemstones.gemstone;

// class for representing Aventurine - semi precious stone
public class Aventurine extends SemiPreciousStone {

    private String inclusions; // examples: Fuchsite, Hematite, Dumortierite etc.

    // constructor
    public Aventurine(double weight, double cost, double transparency, String lustre, String color, String hardness, String inclusions) {
        super("Aventurine", weight, cost, transparency, lustre, color, hardness);
        this.inclusions = inclusions;
    }

    // getter for inclusions
    public String getInclusions() {
        return inclusions;
    }

    // setter for inclusions
    public void setInclusions(String inclusions) {
        this.inclusions = inclusions;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("; inclusions = %s.", inclusions);
    }
}
