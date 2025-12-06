package gemstones.gemstone;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class OpalTest {

    private Opal opal;

    @Before
    public void setUp() {
        opal = new Opal(2.2, 4000, 0.9, "Oily", "Green", "8", "Iridescent");
    }

    @Test
    public void testConstructor() {
        assertEquals("Opal", opal.getName());
        assertEquals(2.2, opal.getWeight(), 0.001);
        assertEquals(4000, opal.getCost(), 0.001);
        assertEquals(0.9, opal.getTransparency(), 0.001);
        assertEquals("Oily", opal.getLustre());
        assertEquals("Green", opal.getColor());
        assertEquals("8", opal.getHardness());
        assertEquals("Iridescent", opal.getPlayOfColor());
    }

    @Test
    public void testGetPlayOfColor() {
        assertEquals("Iridescent", opal.getPlayOfColor());
    }

    @Test
    public void testSetPlayOfColor() {
        opal.setPlayOfColor("Fire");
        assertEquals("Fire", opal.getPlayOfColor());
        
        opal.setPlayOfColor("Harlequin");
        assertEquals("Harlequin", opal.getPlayOfColor());
    }

    @Test
    public void testToString() {
        String str = opal.toString();
        assertTrue(str.contains("Opal"));
        assertTrue(str.contains("Iridescent"));
        assertTrue(str.contains("8"));
        assertTrue(str.contains("[Semiprecious]"));
    }

    @Test
    public void testInheritance() {
        assertTrue(opal instanceof SemiPreciousStone);
        assertTrue(opal instanceof Gemstone);
    }

    @Test
    public void testOpalWithDifferentValues() {
        Opal opal2 = new Opal(1.5, 3000, 0.85, "Vitreous", "Blue", "5.5", "Pinfire");
        assertEquals("Blue", opal2.getColor());
        assertEquals("Pinfire", opal2.getPlayOfColor());
        assertEquals("5.5", opal2.getHardness());
    }
}
