package gemstones.gemstone;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AmethystTest {

    private Amethyst amethyst;

    @Before
    public void setUp() {
        amethyst = new Amethyst(3.0, 5000, 0.8, "Glassy", "Purple", "7", "Striped");
    }

    @Test
    public void testConstructor() {
        assertEquals("Amethyst", amethyst.getName());
        assertEquals(3.0, amethyst.getWeight(), 0.001);
        assertEquals(5000, amethyst.getCost(), 0.001);
        assertEquals(0.8, amethyst.getTransparency(), 0.001);
        assertEquals("Glassy", amethyst.getLustre());
        assertEquals("Purple", amethyst.getColor());
        assertEquals("7", amethyst.getHardness());
        assertEquals("Striped", amethyst.getPattern());
    }

    @Test
    public void testGetPattern() {
        assertEquals("Striped", amethyst.getPattern());
    }

    @Test
    public void testSetPattern() {
        amethyst.setPattern("Solid");
        assertEquals("Solid", amethyst.getPattern());
        
        amethyst.setPattern("Zoned");
        assertEquals("Zoned", amethyst.getPattern());
    }

    @Test
    public void testToString() {
        String str = amethyst.toString();
        assertTrue(str.contains("Amethyst"));
        assertTrue(str.contains("Striped"));
        assertTrue(str.contains("7"));
        assertTrue(str.contains("[Semiprecious]"));
    }

    @Test
    public void testInheritance() {
        assertTrue(amethyst instanceof SemiPreciousStone);
        assertTrue(amethyst instanceof Gemstone);
    }

    @Test
    public void testAmethystWithDifferentValues() {
        Amethyst amethyst2 = new Amethyst(4.0, 6000, 0.75, "Vitreous", "Deep Purple", "7.5", "Zoned");
        assertEquals("Deep Purple", amethyst2.getColor());
        assertEquals("Zoned", amethyst2.getPattern());
        assertEquals("7.5", amethyst2.getHardness());
    }
}
