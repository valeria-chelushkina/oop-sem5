package gemstones.necklace;

import gemstones.gemstone.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class NecklaceTest {

    private Necklace necklace;
    private Diamond diamond;
    private Ruby ruby;
    private Emerald emerald;
    private Amethyst amethyst;
    private Opal opal;
    private Aventurine aventurine;

    @Before
    public void setUp() {
        necklace = new Necklace();
        diamond = new Diamond(2.5, 50000, 0.95, "Adamantite", "White", "Africa", "Round brilliant");
        ruby = new Ruby(1.8, 30000, 0.85, "Subadamantine", "Red", "Myanmar", 0.9);
        emerald = new Emerald(2.0, 25000, 0.75, "Vitreous", "Green", "Columbia", false);
        amethyst = new Amethyst(3.0, 5000, 0.8, "Glassy", "Purple", "7", "Striped");
        opal = new Opal(2.2, 4000, 0.9, "Oily", "Green", "8", "Iridescent");
        aventurine = new Aventurine(2.2, 4000, 0.9, "Oily", "Green", "8", "Fuchsite");
    }

    @Test
    public void testConstructorEmpty() {
        Necklace emptyNecklace = new Necklace();
        assertEquals(0, emptyNecklace.getGemstoneCount());
        assertEquals(0, emptyNecklace.getTotalWeight(), 0.001);
        assertEquals(0, emptyNecklace.getTotalCost(), 0.001);
    }

    @Test
    public void testConstructorWithList() {
        List<Gemstone> stones = new ArrayList<>();
        stones.add(diamond);
        stones.add(ruby);
        Necklace necklaceWithStones = new Necklace(stones);
        assertEquals(2, necklaceWithStones.getGemstoneCount());
    }

    @Test
    public void testAddGemstone() {
        necklace.addGemstone(diamond);
        assertEquals(1, necklace.getGemstoneCount());
        assertTrue(necklace.getGemstones().contains(diamond));
    }

    @Test
    public void testAddMultipleGemstones() {
        necklace.addGemstone(diamond);
        necklace.addGemstone(ruby);
        necklace.addGemstone(emerald);
        assertEquals(3, necklace.getGemstoneCount());
    }

    @Test
    public void testAddNullGemstone() {
        necklace.addGemstone(diamond);
        necklace.addGemstone(null);
        assertEquals(1, necklace.getGemstoneCount());
    }

    @Test
    public void testRemoveGemstone() {
        necklace.addGemstone(diamond);
        necklace.addGemstone(ruby);
        assertTrue(necklace.removeGemstone(diamond));
        assertEquals(1, necklace.getGemstoneCount());
        assertFalse(necklace.getGemstones().contains(diamond));
    }

    @Test
    public void testRemoveNonExistentGemstone() {
        necklace.addGemstone(diamond);
        assertFalse(necklace.removeGemstone(ruby));
        assertEquals(1, necklace.getGemstoneCount());
    }

    @Test
    public void testGetGemstones() {
        necklace.addGemstone(diamond);
        necklace.addGemstone(ruby);
        List<Gemstone> stones = necklace.getGemstones();
        assertEquals(2, stones.size());
        assertTrue(stones.contains(diamond));
        assertTrue(stones.contains(ruby));
    }

    @Test
    public void testGetGemstonesReturnsCopy() {
        necklace.addGemstone(diamond);
        List<Gemstone> stones = necklace.getGemstones();
        stones.clear();
        assertEquals(1, necklace.getGemstoneCount());
    }

    @Test
    public void testGetTotalWeight() {
        necklace.addGemstone(diamond);
        necklace.addGemstone(ruby);
        double expectedWeight = 2.5 + 1.8;
        assertEquals(expectedWeight, necklace.getTotalWeight(), 0.001);
    }

    @Test
    public void testGetTotalWeightEmpty() {
        assertEquals(0, necklace.getTotalWeight(), 0.001);
    }

    @Test
    public void testGetTotalCost() {
        necklace.addGemstone(diamond);
        necklace.addGemstone(ruby);
        double expectedCost = 50000 + 30000;
        assertEquals(expectedCost, necklace.getTotalCost(), 0.001);
    }

    @Test
    public void testGetTotalCostEmpty() {
        assertEquals(0, necklace.getTotalCost(), 0.001);
    }

    @Test
    public void testSortByValue() {
        necklace.addGemstone(amethyst); // value = 5000/3.0 = 1666.67
        necklace.addGemstone(diamond);  // value = 50000/2.5 = 20000
        necklace.addGemstone(ruby);     // value = 30000/1.8 = 16666.67
        
        necklace.sortByValue();
        List<Gemstone> stones = necklace.getGemstones();
        
        assertEquals(diamond, stones.get(0));
        assertEquals(ruby, stones.get(1));
        assertEquals(amethyst, stones.get(2));
    }

    @Test
    public void testSortByCost() {
        necklace.addGemstone(amethyst); // cost = 5000
        necklace.addGemstone(diamond);  // cost = 50000
        necklace.addGemstone(ruby);     // cost = 30000
        
        necklace.sortByCost();
        List<Gemstone> stones = necklace.getGemstones();
        
        assertEquals(diamond, stones.get(0));
        assertEquals(ruby, stones.get(1));
        assertEquals(amethyst, stones.get(2));
    }

    @Test
    public void testSortByWeight() {
        necklace.addGemstone(ruby);     // weight = 1.8
        necklace.addGemstone(diamond);  // weight = 2.5
        necklace.addGemstone(amethyst); // weight = 3.0
        
        necklace.sortByWeight();
        List<Gemstone> stones = necklace.getGemstones();
        
        assertEquals(amethyst, stones.get(0));
        assertEquals(diamond, stones.get(1));
        assertEquals(ruby, stones.get(2));
    }

    @Test
    public void testFindGemstonesByTransparency() {
        necklace.addGemstone(diamond);  // transparency = 0.95
        necklace.addGemstone(ruby);     // transparency = 0.85
        necklace.addGemstone(emerald);   // transparency = 0.75
        necklace.addGemstone(amethyst);  // transparency = 0.8
        
        List<Gemstone> found = necklace.findGemstonesByTransparency(0.75, 0.85);
        assertEquals(3, found.size());
        assertTrue(found.contains(ruby));
        assertTrue(found.contains(emerald));
        assertTrue(found.contains(amethyst));
        assertFalse(found.contains(diamond));
    }

    @Test
    public void testFindGemstonesByTransparencyExactMatch() {
        necklace.addGemstone(diamond);
        necklace.addGemstone(ruby);
        
        List<Gemstone> found = necklace.findGemstonesByTransparency(0.95, 0.95);
        assertEquals(1, found.size());
        assertTrue(found.contains(diamond));
    }

    @Test
    public void testFindGemstonesByTransparencyNoMatch() {
        necklace.addGemstone(diamond);
        necklace.addGemstone(ruby);
        
        List<Gemstone> found = necklace.findGemstonesByTransparency(0.5, 0.6);
        assertEquals(0, found.size());
    }

    @Test
    public void testFindGemstonesByTransparencyEmpty() {
        List<Gemstone> found = necklace.findGemstonesByTransparency(0.0, 1.0);
        assertEquals(0, found.size());
    }

    @Test
    public void testGetGemstoneCount() {
        assertEquals(0, necklace.getGemstoneCount());
        necklace.addGemstone(diamond);
        assertEquals(1, necklace.getGemstoneCount());
        necklace.addGemstone(ruby);
        assertEquals(2, necklace.getGemstoneCount());
    }

    @Test
    public void testClear() {
        necklace.addGemstone(diamond);
        necklace.addGemstone(ruby);
        necklace.clear();
        assertEquals(0, necklace.getGemstoneCount());
        assertEquals(0, necklace.getTotalWeight(), 0.001);
        assertEquals(0, necklace.getTotalCost(), 0.001);
    }

    @Test
    public void testToString() {
        necklace.addGemstone(diamond);
        necklace.addGemstone(ruby);
        String str = necklace.toString();
        
        assertTrue(str.contains("Necklace"));
        assertTrue(str.contains("Number of gemstones: 2"));
        assertTrue(str.contains("Total weight"));
        assertTrue(str.contains("Total price"));
        assertTrue(str.contains("Gemstones"));
        assertTrue(str.contains("Diamond"));
        assertTrue(str.contains("Ruby"));
    }

    @Test
    public void testToStringEmpty() {
        String str = necklace.toString();
        assertTrue(str.contains("Number of gemstones: 0"));
        // Check for weight format (could be 0.00 or 0,00 depending on locale)
        assertTrue(str.contains("Total weight: 0") || str.contains("Total weight: 0"));
        assertTrue(str.contains("Total price: 0") || str.contains("Total price: 0"));
        assertTrue(str.contains("carats"));
        assertTrue(str.contains("UAH"));
    }
}
