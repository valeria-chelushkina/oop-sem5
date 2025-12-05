package gemstones.util;

import gemstones.gemstone.*;
import gemstones.necklace.Necklace;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// class for working with files. saves and loads the necklace initialization parameters
public class FileManager {

    private static final String DEFAULT_FILE_NAME = "lab1/src/main/java/gemstones/necklace_data.txt";

    // save the necklace into the file
    public static void saveNecklace(Necklace necklace, String fileName) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Gemstone stone : necklace.getGemstones()) {
                writer.println(gemstoneToString(stone));
            }
        }
    }

    // save the necklace to the default file
    public static void saveNecklace(Necklace necklace) throws IOException {
        saveNecklace(necklace, DEFAULT_FILE_NAME);
    }

    // load necklace from the file
    public static Necklace loadNecklace(String fileName) throws IOException {
        List<Gemstone> stones = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    Gemstone stone = stringToGemstone(line);
                    if (stone != null) {
                        stones.add(stone);
                    }
                }
            }
        }
        return new Necklace(stones);
    }

    // load the necklace from default file.
    public static Necklace loadNecklace() throws IOException {
        return loadNecklace(DEFAULT_FILE_NAME);
    }

    // convert gemstone to string for storage
    private static String gemstoneToString(Gemstone gemstone) {
        StringBuilder sb = new StringBuilder();
        sb.append(gemstone.getClass().getSimpleName()).append("|");
        sb.append(gemstone.getName()).append("|");
        sb.append(gemstone.getWeight()).append("|");
        sb.append(gemstone.getCost()).append("|");
        sb.append(gemstone.getTransparency()).append("|");
        sb.append(gemstone.getLustre()).append("|");
        sb.append(gemstone.getColor()).append("|");

        if (gemstone instanceof PreciousStone) {
            PreciousStone ps = (PreciousStone) gemstone;
            sb.append(ps.getOrigin()).append("|");
            if (gemstone instanceof Diamond) {
                Diamond d = (Diamond) gemstone;
                sb.append(d.getCut());
            } else if (gemstone instanceof Ruby) {
                Ruby r = (Ruby) gemstone;
                sb.append(r.getClarity());
            } else if (gemstone instanceof Emerald) {
                Emerald e = (Emerald) gemstone;
                sb.append(e.isTreated());
            } else {
                sb.append("");
            }
        } else if (gemstone instanceof SemiPreciousStone) {
            SemiPreciousStone sps = (SemiPreciousStone) gemstone;
            sb.append(sps.getHardness()).append("|");
            if (gemstone instanceof Amethyst) {
                Amethyst a = (Amethyst) gemstone;
                sb.append(a.getPattern());
            } else if (gemstone instanceof Opal) {
                Opal o = (Opal) gemstone;
                sb.append(o.getPlayOfColor());
            } else if (gemstone instanceof Aventurine) {
                Aventurine a = (Aventurine) gemstone;
                sb.append(a.getInclusions());
            } else {
                sb.append("");
            }
        }

        return sb.toString();
    }

    // convert string into gemstone
    private static Gemstone stringToGemstone(String line) {
        String[] parts = line.split("\\|");
        if (parts.length < 7) {
            return null;
        }

        try {
            String className = parts[0];
            String name = parts[1];
            double weight = Double.parseDouble(parts[2]);
            double cost = Double.parseDouble(parts[3]);
            double transparency = Double.parseDouble(parts[4]);
            String lustre = parts[5];
            String color = parts[6];

            switch (className) {
                case "Diamond":
                    if (parts.length >= 9) {
                        return new Diamond(weight, cost, transparency, lustre, color, parts[7], parts[8]);
                    }
                    break;
                case "Ruby":
                    if (parts.length >= 9) {
                        return new Ruby(weight, cost, transparency, lustre, color, parts[7], Double.parseDouble(parts[8]));
                    }
                    break;
                case "Emerald":
                    if (parts.length >= 9) {
                        return new Emerald(weight, cost, transparency, lustre, color, parts[7], Boolean.parseBoolean(parts[8]));
                    }
                    break;
                case "Amethyst":
                    if (parts.length >= 9) {
                        return new Amethyst(weight, cost, transparency, lustre, color, parts[7], parts[8]);
                    }
                    break;
                case "Topaz":
                    if (parts.length >= 9) {
                        return new Opal(weight, cost, transparency, lustre, color, parts[7], parts[8]);
                    }
                    break;
                case "Aventurine":
                    if (parts.length >= 9) {
                        return new Aventurine(weight, cost, transparency, lustre, color, parts[7], parts[8]);
                    }
                    break;
                case "PreciousStone":
                    if (parts.length >= 8) {
                        return new PreciousStone(name, weight, cost, transparency, lustre, color, parts[7]);
                    }
                    break;
                case "SemiPreciousStone":
                    if (parts.length >= 8) {
                        return new SemiPreciousStone(name, weight, cost, transparency, lustre, color, parts[7]);
                    }
                    break;
                default:
                    // if class unknown - try to create PreciousStone or SemiPreciousStone
                    if (parts.length >= 8) {
                        // Try PreciousStone first (with origin at parts[7])
                        return new PreciousStone(name, weight, cost, transparency, lustre, color, parts[7]);
                    } else if (parts.length == 7) {
                        // cannot create precious or semi precious stones. not enough data
                        System.err.println("Not enough data to create stones: " + name);
                    }
                    break;
            }
        } catch (Exception e) {
            System.err.println("Error parsing gemstones: " + e.getMessage());
        }

        return null;
    }
}
