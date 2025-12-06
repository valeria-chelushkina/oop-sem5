package gemstones.gemstone;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for Gemstone abstract class.
 * Tests are performed using concrete implementations (Diamond, Amethyst).
 */
public class GemstoneTest {

    private Diamond diamond;
    private Amethyst amethyst;

    @Before
    public void setUp() {
        diamond = new Diamond(2.5, 50000, 0.95, "Adamantite", "White", "Africa", "Round brilliant");
        amethyst = new Amethyst(3.0, 5000, 0.8, "Glassy", "Purple", "7", "Striped");
    }

    @Test
    public void testGetName() {
        assertEquals("Diamond", diamond.getName());
        assertEquals("Amethyst", amethyst.getName());
    }

    @Test
    public void testSetName() {
        diamond.setName("TestDiamond");
        assertEquals("TestDiamond", diamond.getName());
        
        amethyst.setName("TestAmethyst");
        assertEquals("TestAmethyst", amethyst.getName());
    }

    @Test
    public void testGetWeight() {
        assertEquals(2.5, diamond.getWeight(), 0.001);
        assertEquals(3.0, amethyst.getWeight(), 0.001);
    }

    @Test
    public void testSetWeight() {
        diamond.setWeight(3.0);
        assertEquals(3.0, diamond.getWeight(), 0.001);
        
        amethyst.setWeight(4.0);
        assertEquals(4.0, amethyst.getWeight(), 0.001);
    }

    @Test
    public void testGetCost() {
        assertEquals(50000, diamond.getCost(), 0.001);
        assertEquals(5000, amethyst.getCost(), 0.001);
    }

    @Test
    public void testSetCost() {
        diamond.setCost(60000);
        assertEquals(60000, diamond.getCost(), 0.001);
        
        amethyst.setCost(6000);
        assertEquals(6000, amethyst.getCost(), 0.001);
    }

    @Test
    public void testGetTransparency() {
        assertEquals(0.95, diamond.getTransparency(), 0.001);
        assertEquals(0.8, amethyst.getTransparency(), 0.001);
    }

    @Test
    public void testSetTransparencyValid() {
        diamond.setTransparency(0.5);
        assertEquals(0.5, diamond.getTransparency(), 0.001);
        
        diamond.setTransparency(0.0);
        assertEquals(0.0, diamond.getTransparency(), 0.001);
        
        diamond.setTransparency(1.0);
        assertEquals(1.0, diamond.getTransparency(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetTransparencyInvalidNegative() {
        diamond.setTransparency(-0.1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetTransparencyInvalidGreaterThanOne() {
        diamond.setTransparency(1.1);
    }

    @Test
    public void testGetLustre() {
        assertEquals("Adamantite", diamond.getLustre());
        assertEquals("Glassy", amethyst.getLustre());
    }

    @Test
    public void testSetLustre() {
        diamond.setLustre("Vitreous");
        assertEquals("Vitreous", diamond.getLustre());
        
        amethyst.setLustre("Dull");
        assertEquals("Dull", amethyst.getLustre());
    }

    @Test
    public void testGetColor() {
        assertEquals("White", diamond.getColor());
        assertEquals("Purple", amethyst.getColor());
    }

    @Test
    public void testSetColor() {
        diamond.setColor("Blue");
        assertEquals("Blue", diamond.getColor());
        
        amethyst.setColor("Red");
        assertEquals("Red", amethyst.getColor());
    }

    @Test
    public void testGetValue() {
        double expectedValue = 50000 / 2.5;
        assertEquals(expectedValue, diamond.getValue(), 0.001);
        
        double expectedValue2 = 5000 / 3.0;
        assertEquals(expectedValue2, amethyst.getValue(), 0.001);
    }

    @Test
    public void testGetValueZeroWeight() {
        diamond.setWeight(0);
        assertEquals(0, diamond.getValue(), 0.001);
    }

    @Test
    public void testToString() {
        String diamondStr = diamond.toString();
        assertTrue(diamondStr.contains("Diamond"));
        // Check for weight format (could be 2.50 or 2,50 depending on locale)
        assertTrue(diamondStr.contains("2.5") || diamondStr.contains("2,5"));
        // Check for cost format
        assertTrue(diamondStr.contains("50000") || diamondStr.contains("50000"));
        assertTrue(diamondStr.contains("0.95") || diamondStr.contains("0,95"));
        assertTrue(diamondStr.contains("Adamantite"));
        assertTrue(diamondStr.contains("White"));
        assertTrue(diamondStr.contains("carats"));
        assertTrue(diamondStr.contains("UAH"));
    }

    @Test
    public void testEqualsSameObject() {
        assertEquals(diamond, diamond);
    }

    @Test
    public void testEqualsEqualObjects() {
        Diamond diamond2 = new Diamond(2.5, 50000, 0.95, "Adamantite", "White", "Africa", "Round brilliant");
        assertEquals(diamond, diamond2);
    }

    @Test
    public void testEqualsDifferentWeight() {
        Diamond diamond2 = new Diamond(3.0, 50000, 0.95, "Adamantite", "White", "Africa", "Round brilliant");
        assertNotEquals(diamond, diamond2);
    }

    @Test
    public void testEqualsDifferentCost() {
        Diamond diamond2 = new Diamond(2.5, 60000, 0.95, "Adamantite", "White", "Africa", "Round brilliant");
        assertNotEquals(diamond, diamond2);
    }

    @Test
    public void testEqualsDifferentTransparency() {
        Diamond diamond2 = new Diamond(2.5, 50000, 0.9, "Adamantite", "White", "Africa", "Round brilliant");
        assertNotEquals(diamond, diamond2);
    }

    @Test
    public void testEqualsDifferentName() {
        Diamond diamond2 = new Diamond(2.5, 50000, 0.95, "Adamantite", "White", "Africa", "Round brilliant");
        diamond2.setName("Different");
        assertNotEquals(diamond, diamond2);
    }

    @Test
    public void testEqualsDifferentColor() {
        Diamond diamond2 = new Diamond(2.5, 50000, 0.95, "Adamantite", "Blue", "Africa", "Round brilliant");
        assertNotEquals(diamond, diamond2);
    }

    @Test
    public void testEqualsNull() {
        assertNotEquals(diamond, null);
    }

    @Test
    public void testEqualsDifferentClass() {
        assertNotEquals(diamond, amethyst);
    }

    @Test
    public void testHashCode() {
        Diamond diamond2 = new Diamond(2.5, 50000, 0.95, "Adamantite", "White", "Africa", "Round brilliant");
        assertEquals(diamond.hashCode(), diamond2.hashCode());
    }

    @Test
    public void testHashCodeDifferentObjects() {
        Diamond diamond2 = new Diamond(3.0, 50000, 0.95, "Adamantite", "White", "Africa", "Round brilliant");
        assertNotEquals(diamond.hashCode(), diamond2.hashCode());
    }
}
