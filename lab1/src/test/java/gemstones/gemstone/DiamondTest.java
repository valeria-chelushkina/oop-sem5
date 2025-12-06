package gemstones.gemstone;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DiamondTest {

    private Diamond diamond;

    @Before
    public void setUp() {
        diamond = new Diamond(2.5, 50000, 0.95, "Adamantite", "White", "Africa", "Round brilliant");
    }

    @Test
    public void testConstructor() {
        assertEquals("Diamond", diamond.getName());
        assertEquals(2.5, diamond.getWeight(), 0.001);
        assertEquals(50000, diamond.getCost(), 0.001);
        assertEquals(0.95, diamond.getTransparency(), 0.001);
        assertEquals("Adamantite", diamond.getLustre());
        assertEquals("White", diamond.getColor());
        assertEquals("Africa", diamond.getOrigin());
        assertEquals("Round brilliant", diamond.getCut());
    }

    @Test
    public void testGetCut() {
        assertEquals("Round brilliant", diamond.getCut());
    }

    @Test
    public void testSetCut() {
        diamond.setCut("Princess");
        assertEquals("Princess", diamond.getCut());
        
        diamond.setCut("Emerald cut");
        assertEquals("Emerald cut", diamond.getCut());
    }

    @Test
    public void testToString() {
        String str = diamond.toString();
        assertTrue(str.contains("Diamond"));
        assertTrue(str.contains("Round brilliant"));
        assertTrue(str.contains("Africa"));
        assertTrue(str.contains("[Precious]"));
    }

    @Test
    public void testInheritance() {
        assertTrue(diamond instanceof PreciousStone);
        assertTrue(diamond instanceof Gemstone);
    }

    @Test
    public void testDiamondWithDifferentValues() {
        Diamond diamond2 = new Diamond(1.0, 10000, 0.9, "Vitreous", "Blue", "Russia", "Oval");
        assertEquals("Blue", diamond2.getColor());
        assertEquals("Oval", diamond2.getCut());
        assertEquals(1.0, diamond2.getWeight(), 0.001);
    }
}
