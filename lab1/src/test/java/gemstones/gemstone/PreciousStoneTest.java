package gemstones.gemstone;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PreciousStoneTest {

    private PreciousStone preciousStone;
    private Diamond diamond;
    private Ruby ruby;
    private Emerald emerald;

    @Before
    public void setUp() {
        preciousStone = new PreciousStone("Sapphire", 1.5, 20000, 0.88, "Adamantine", "Blue", "Sri Lanka");
        diamond = new Diamond(2.5, 50000, 0.95, "Adamantite", "White", "Africa", "Round brilliant");
        ruby = new Ruby(1.8, 30000, 0.85, "Subadamantine", "Red", "Myanmar", 0.9);
        emerald = new Emerald(2.0, 25000, 0.75, "Vitreous", "Green", "Columbia", false);
    }

    @Test
    public void testGetOrigin() {
        assertEquals("Sri Lanka", preciousStone.getOrigin());
        assertEquals("Africa", diamond.getOrigin());
        assertEquals("Myanmar", ruby.getOrigin());
        assertEquals("Columbia", emerald.getOrigin());
    }

    @Test
    public void testSetOrigin() {
        preciousStone.setOrigin("India");
        assertEquals("India", preciousStone.getOrigin());
        
        diamond.setOrigin("Russia");
        assertEquals("Russia", diamond.getOrigin());
    }

    @Test
    public void testToString() {
        String str = preciousStone.toString();
        assertTrue(str.contains("Sapphire"));
        assertTrue(str.contains("Sri Lanka"));
        assertTrue(str.contains("[Precious]"));
    }

    @Test
    public void testInheritance() {
        assertTrue(preciousStone instanceof Gemstone);
        assertTrue(diamond instanceof PreciousStone);
        assertTrue(diamond instanceof Gemstone);
        assertTrue(ruby instanceof PreciousStone);
        assertTrue(emerald instanceof PreciousStone);
    }

    @Test
    public void testPreciousStoneProperties() {
        assertEquals("Sapphire", preciousStone.getName());
        assertEquals(1.5, preciousStone.getWeight(), 0.001);
        assertEquals(20000, preciousStone.getCost(), 0.001);
        assertEquals(0.88, preciousStone.getTransparency(), 0.001);
        assertEquals("Adamantine", preciousStone.getLustre());
        assertEquals("Blue", preciousStone.getColor());
    }
}
