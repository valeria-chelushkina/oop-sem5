package gemstones.gemstone;

// class for representing Semi Precious Stones
// inherits Gemstone class
public class SemiPreciousStone extends Gemstone {

    private String hardness; // Mohs hardness scale

    // constructor
    public SemiPreciousStone(String name, double weight, double cost, double transparency, String lustre, String color, String hardness) {
        super(name, weight, cost, transparency, lustre, color);
        this.hardness = hardness;
    }

    // getter for semiprecious stone hardness
    public String getHardness() {
        return hardness;
    }

    // setter for semiprecious stone hardness
    public void setHardness(String hardness) {
        this.hardness = hardness;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("; hardness = %s [Semiprecious]", hardness);
    }
}
