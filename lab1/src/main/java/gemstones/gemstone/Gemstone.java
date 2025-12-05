package gemstones.gemstone;

// base class for representing gemstones
public abstract class Gemstone {

    private String name;
    private double weight; // carats
    private double cost; // UAH
    private double transparency; // from 0 to 1 (0 - not transparent, 1 - transparent)
    private String lustre; //examples: Adamantine, Dull, Greasy, Metallic etc.
    private String color;

    // constructor
    public Gemstone(String name, double weight, double cost, double transparency, String lustre, String color) {
        this.name = name;
        this.weight = weight;
        this.cost = cost;
        this.transparency = transparency;
        this.lustre = lustre;
        this.color = color;
    }

    // getter for gemstone name
    public String getName() {
        return name;
    }

    // setter for gemstone name
    public void setName(String name) {
        this.name = name;
    }

    // getter for gemstone weight
    public double getWeight() {
        return weight;
    }

    // setter for gemstone weight
    public void setWeight(double weight) {
        this.weight = weight;
    }

    // getter for gemstone price
    public double getCost() {
        return cost;
    }

    // setter for gemstone price
    public void setCost(double cost) {
        this.cost = cost;
    }

    // getter for gemstone transparency
    public double getTransparency() {
        return transparency;
    }

    // setter for gemstone transparency
    public void setTransparency(double transparency) {
        if (transparency < 0 || transparency > 1) {
            throw new IllegalArgumentException("Transparency should be in the range: [0, 1].");
        }
        this.transparency = transparency;
    }

    // getter for gemstone lustre
    public String getLustre() {
        return lustre;
    }

    // setter for gemstone lustre
    public void setLustre(String lustre) {
        this.lustre = lustre;
    }

    // getter for gemstone color
    public String getColor() {
        return color;
    }

    // setter for gemstone color
    public void setColor(String color) {
        this.color = color;
    }

    // getter for gemstone Value (UAH for carat)
    public double getValue() {
        return weight > 0 ? cost / weight : 0;
    }

    @Override
    public String toString() {
        return String.format("%s: weight = %.2f carats; cost = %.2f UAH; transparency = %.2f; lustre = %s; color = %s",
                name, weight, cost, transparency, lustre, color);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Gemstone gemstone = (Gemstone) obj;
        return Double.compare(gemstone.weight, weight) == 0
                && Double.compare(gemstone.cost, cost) == 0
                && Double.compare(gemstone.transparency, transparency) == 0
                && name.equals(gemstone.name)
                && color.equals(gemstone.color);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name.hashCode();
        temp = Double.doubleToLongBits(weight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(transparency);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + color.hashCode();
        return result;
    }
}
