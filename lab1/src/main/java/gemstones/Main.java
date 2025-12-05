package gemstones;

import gemstones.necklace.Necklace;
import gemstones.gemstone.*;
import gemstones.util.FileManager;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// main program
public class Main {
    private static Necklace necklace;
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        necklace = new Necklace();

        // try load data
        try {
            necklace = FileManager.loadNecklace();
            System.out.println("Data loaded from a file.");
        } catch (IOException e) {
            System.out.println("Data file not found. New necklace created.");
            // add an example of a stone for demonstration
            initializeSampleStones();
        }

        boolean running = true;
        while (running) {
            printMenu();
            int choice = getIntInput();
            switch (choice) {
                case 1:
                    showNecklace();
                    break;
                case 2:
                    addGemstone();
                    break;
                case 3:
                    sortGemstones();
                    break;
                case 4:
                    findGemstonesByTransparency();
                    break;
                case 5:
                    saveNecklace();
                    break;
                case 6:
                    loadNecklace();
                    break;
                case 0:
                    running = false;
                    System.out.println("Bye!");
                    break;
                default:
                    System.out.println("Incorrect selection. Please try again.");
            }
        }
        scanner.close();
    }

    /**
     * Вивести меню на екран.
     */
    private static void printMenu() {
        System.out.println("\n=== MENU ===");
        System.out.println("1. Show the necklace");
        System.out.println("2. Add gemstone");
        System.out.println("3. Sort gemstones");
        System.out.println("4. Find gemstone by transparency");
        System.out.println("5. Save the necklace");
        System.out.println("6. Load the necklace");
        System.out.println("0. Exit");
        System.out.print("Select an option: ");
    }

    // show necklace info
    private static void showNecklace() {
        System.out.println("\n" + necklace);
    }

    // add new gemstone
    private static void addGemstone() {
        System.out.println("\n=== ADD GEMSTONE ===");
        System.out.println("1. Diamond");
        System.out.println("2. Ruby");
        System.out.println("3. Emerald");
        System.out.println("4. Amethyst");
        System.out.println("5. Topaz");
        System.out.println("6. Aventurine");
        System.out.println("7. Create new stone");
        System.out.print("Choose the type of gemstone: ");

        int type = getIntInput();
        Gemstone stone = null;

        System.out.print("Weight (carats): ");
        double weight = getDoubleInput();
        System.out.print("Cost (UAH): ");
        double cost = getDoubleInput();
        System.out.print("Transparency (0-1): ");
        double transparency = getDoubleInput();
        System.out.print("Lustre: ");
        String lustre = scanner.nextLine().trim();
        System.out.print("Color: ");
        String color = scanner.nextLine().trim();

        switch (type) {
            case 1: // Diamond
                System.out.print("Origin: ");
                String origin = scanner.nextLine().trim();
                System.out.print("Cut: ");
                String cut = scanner.nextLine().trim();
                stone = new Diamond(weight, cost, transparency, lustre, color, origin, cut);
                break;
            case 2: // Ruby
                System.out.print("Origin: ");
                origin = scanner.nextLine().trim();
                System.out.print("Clarity: ");
                double clarity = getDoubleInput();
                stone = new Ruby(weight, cost, transparency, lustre, color, origin, clarity);
                break;
            case 3: // Emerald
                System.out.print("Origin: ");
                origin = scanner.nextLine().trim();
                System.out.print("Treated (true/false): ");
                boolean treated = getBooleanInput();
                stone = new Emerald(weight, cost, transparency, lustre, color, origin, treated);
                break;
            case 4: // Amethyst
                System.out.print("Hardness: ");
                String hardness = scanner.nextLine().trim();
                System.out.print("Pattern: ");
                String pattern = scanner.nextLine().trim();
                stone = new Amethyst(weight, cost, transparency, lustre, color, hardness, pattern);
                break;
            case 5: // Topaz
                System.out.print("Hardness: ");
                hardness = scanner.nextLine().trim();
                System.out.print("Play of colors: ");
                String playOfColors = scanner.nextLine();
                stone = new Opal(weight, cost, transparency, lustre, color, hardness, playOfColors);
                break;
            case 6: // Aventurine
                System.out.print("Hardness: ");
                hardness = scanner.nextLine().trim();
                System.out.print("Inclusions: ");
                String inclusions = scanner.nextLine().trim();
                stone = new Aventurine(weight, cost, transparency, lustre, color, hardness, inclusions);
                break;
            case 7: // Create new stone
                System.out.print("Name: ");
                String name = scanner.nextLine().trim();
                System.out.print("Type (1 - Precious, 2 - Semiprecious): ");
                int stoneType = getIntInput();
                if (stoneType == 1) {
                    System.out.print("Origin: ");
                    origin = scanner.nextLine().trim();
                    stone = new PreciousStone(name, weight, cost, transparency, lustre, color, origin);
                } else if (stoneType == 2) {
                    System.out.print("Hardness: ");
                    hardness = scanner.nextLine().trim();
                    stone = new SemiPreciousStone(name, weight, cost, transparency, lustre, color, hardness);
                } else {
                    System.out.println("Invalid stone type.");
                    return;
                }
                break;
            default:
                System.out.println("Невірний тип каміння.");
                return;
        }

        if (stone != null) {
            necklace.addGemstone(stone);
            System.out.println("Каміння додано до намиста.");
        }
    }

    // sort gemstones
    private static void sortGemstones() {
        System.out.println("\n=== SORT ===");
        System.out.println("1. By value (cost/carats)");
        System.out.println("2. By price");
        System.out.println("3. By weight");
        System.out.print("Select sort type: ");

        int choice = getIntInput();
        switch (choice) {
            case 1:
                necklace.sortByValue();
                System.out.println("The gemstones are sorted by value.");
                break;
            case 2:
                necklace.sortByCost();
                System.out.println("The gemstones are sorted by price.");
                break;
            case 3:
                necklace.sortByWeight();
                System.out.println("The gemstones are sorted by weight.");
                break;
            default:
                System.out.println("Wrong choice.");
        }
    }

    //find gemstones by transparency
    private static void findGemstonesByTransparency() {
        System.out.println("\n=== SEARCH BY TRANSPARENCY ===");
        System.out.print("Min transparency (0-1): ");
        double min = getDoubleInput();
        System.out.print("Max transparency (0-1): ");
        double max = getDoubleInput();

        // Validate and clamp values to [0-1] range
        if (min < 0) {
            min = 0;
            System.out.println("Min transparency clamped to 0.");
        }
        if (min > 1) {
            min = 1;
            System.out.println("Min transparency clamped to 1.");
        }
        if (max < 0) {
            max = 0;
            System.out.println("Max transparency clamped to 0.");
        }
        if (max > 1) {
            max = 1;
            System.out.println("Max transparency clamped to 1.");
        }

        // Swap if min > max
        if (min > max) {
            double temp = min;
            min = max;
            max = temp;
            System.out.println("Min and max transparency values were swapped.");
        }

        List<Gemstone> found = necklace.findGemstonesByTransparency(min, max);
        if (found.isEmpty()) {
            System.out.println("No gemstones with such transparency have been found.");
        } else {
            System.out.println("\nGemstones found:");
            for (int i = 0; i < found.size(); i++) {
                System.out.println((i + 1) + ". " + found.get(i));
            }
        }
    }

    // save necklace to file
    private static void saveNecklace() {
        try {
            FileManager.saveNecklace(necklace);
            System.out.println("Necklace saved to file.");
        } catch (IOException e) {
            System.out.println("Error while saving: " + e.getMessage());
        }
    }

    // load necklace from file
    private static void loadNecklace() {
        try {
            necklace = FileManager.loadNecklace();
            System.out.println("The necklace is loaded from a file.");
        } catch (IOException e) {
            System.out.println("Error while loading: " + e.getMessage());
        }
    }

     // initialize a sample stone for demonstration.
    private static void initializeSampleStones() {
        necklace.addGemstone(new Diamond(2.5, 50000, 0.95, "Adamantite", "White", "Africa", "Round brilliant"));
        necklace.addGemstone(new Ruby(1.8, 30000, 0.85, "Subadamantine", "Red", "Myanmar", 0.9));
        necklace.addGemstone(new Emerald(2.0, 25000, 0.75, "Vitreous", "Green", "Columbia", false));
        necklace.addGemstone(new Amethyst(3.0, 5000, 0.8, "Glassy", "Purple", "7", "Striped"));
        necklace.addGemstone(new Aventurine(2.2, 4000, 0.9, "Oily", "Green", "8", "Fuchsite"));
    }

    // get int number from console
    private static int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.print("Input integer: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    // get double number
    private static double getDoubleInput() {
        while (!scanner.hasNextDouble()) {
            System.out.print("Input number: ");
            scanner.next();
        }
        double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }

    // get bool value
    private static boolean getBooleanInput() {
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("true") || input.equals("1") || input.equals("yes");
    }
}