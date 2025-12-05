package gemstones.gemstone;

// class for representing Precious Stones
// inherits Gemstone class
public class PreciousStone extends Gemstone {

    private String origin;

    // constructor
    public PreciousStone(String name, double weight, double cost, double transparency, String lustre, String color, String origin) {
        super(name, weight, cost, transparency, lustre, color);
        this.origin = origin;
    }

    // getter for precious stone origin
    public String getOrigin() {
        return origin;
    }

    // setter for precious stone origin
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("; origin = %s [Precious]", origin);
    }
}
