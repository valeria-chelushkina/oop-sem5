package gemstones.gemstone;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class EmeraldTest {

    private Emerald emerald;
    private Emerald treatedEmerald;

    @Before
    public void setUp() {
        emerald = new Emerald(2.0, 25000, 0.75, "Vitreous", "Green", "Columbia", false);
        treatedEmerald = new Emerald(1.5, 20000, 0.8, "Vitreous", "Green", "Zambia", true);
    }

    @Test
    public void testConstructor() {
        assertEquals("Emerald", emerald.getName());
        assertEquals(2.0, emerald.getWeight(), 0.001);
        assertEquals(25000, emerald.getCost(), 0.001);
        assertEquals(0.75, emerald.getTransparency(), 0.001);
        assertEquals("Vitreous", emerald.getLustre());
        assertEquals("Green", emerald.getColor());
        assertEquals("Columbia", emerald.getOrigin());
        assertFalse(emerald.isTreated());
    }

    @Test
    public void testIsTreated() {
        assertFalse(emerald.isTreated());
        assertTrue(treatedEmerald.isTreated());
    }

    @Test
    public void testSetTreated() {
        emerald.setTreated(true);
        assertTrue(emerald.isTreated());
        
        treatedEmerald.setTreated(false);
        assertFalse(treatedEmerald.isTreated());
    }

    @Test
    public void testToString() {
        String str = emerald.toString();
        assertTrue(str.contains("Emerald"));
        assertTrue(str.contains("treatment = no"));
        assertTrue(str.contains("Columbia"));
        assertTrue(str.contains("[Precious]"));
        
        String str2 = treatedEmerald.toString();
        assertTrue(str2.contains("treatment = yes"));
    }

    @Test
    public void testInheritance() {
        assertTrue(emerald instanceof PreciousStone);
        assertTrue(emerald instanceof Gemstone);
    }

    @Test
    public void testEmeraldWithDifferentValues() {
        Emerald emerald2 = new Emerald(3.0, 35000, 0.7, "Glassy", "Deep Green", "Brazil", true);
        assertEquals("Deep Green", emerald2.getColor());
        assertTrue(emerald2.isTreated());
        assertEquals("Brazil", emerald2.getOrigin());
    }
}
