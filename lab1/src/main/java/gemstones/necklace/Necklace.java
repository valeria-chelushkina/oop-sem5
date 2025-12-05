package gemstones.necklace;

import gemstones.gemstone.Gemstone;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// class for representing necklace created with gemstones
// contains methods for working with gemstones: adding, renoving, sorting, finding etc.
public class Necklace {
    private List<Gemstone> gemstones;

    // constructor of empty necklace
    public Necklace() {
        this.gemstones = new ArrayList<>();
    }

    // constructor for necklace with existing list of gemstones
    public Necklace(List<Gemstone> gemstones) {
        this.gemstones = new ArrayList<>(gemstones);
    }

    // add gemstone to the necklace
    public void addGemstone(Gemstone gemstone) {
        if (gemstone != null) {
            gemstones.add(gemstone);
        }
    }

    // remove gemstone from the necklace
    public boolean removeGemstone(Gemstone gemstone) {
        return gemstones.remove(gemstone);
    }

    // getter for the list of the gemstones in the necklace
    public List<Gemstone> getGemstones() {
        return new ArrayList<>(gemstones);
    }

    // getter for the total weight of gemstones in the necklace
    public double getTotalWeight() {
        return gemstones.stream()
                .mapToDouble(Gemstone::getWeight)
                .sum();
    }

    // getter for the total price of the necklace
    public double getTotalCost() {
        return gemstones.stream()
                .mapToDouble(Gemstone::getCost)
                .sum();
    }

    // sort gemstones in the necklace - from the most valueable to the least
    public void sortByValue() {
        gemstones.sort(Comparator.comparingDouble(Gemstone::getValue).reversed());
    }

    // sort gemstones in the necklace - from the most expensive to the least 
    public void sortByCost() {
        gemstones.sort(Comparator.comparingDouble(Gemstone::getCost).reversed());
    }

    // sort gemstones in the necklace - from heaviest to lightest
    public void sortByWeight() {
        gemstones.sort(Comparator.comparingDouble(Gemstone::getWeight).reversed());
    }

    // find gemstones that are in transapency range
    public List<Gemstone> findGemstonesByTransparency(double minTransparency, double maxTransparency) {
        List<Gemstone> result = new ArrayList<>();
        for (Gemstone gemstone : gemstones) {
            double transparency = gemstone.getTransparency();
            if (transparency >= minTransparency && transparency <= maxTransparency) {
                result.add(gemstone);
            }
        }
        return result;
    }

    // getter for the number of gemstones in the necklace
    public int getGemstoneCount() {
        return gemstones.size();
    }

    // clear the entire necklace
    public void clear() {
        gemstones.clear();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Necklace:\n");
        sb.append(String.format("Number of gemstones: %d\n", gemstones.size()));
        sb.append(String.format("Total weight: %.2f carats\n", getTotalWeight()));
        sb.append(String.format("Total price: %.2f UAH\n\n", getTotalCost()));
        sb.append("Gemstones:\n");
        for (int i = 0; i < gemstones.size(); i++) {
            sb.append(String.format("%d. %s\n", i + 1, gemstones.get(i)));
        }
        return sb.toString();
    }
}