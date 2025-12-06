package gemstones.gemstone;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SemiPreciousStoneTest {

    private SemiPreciousStone semiPreciousStone;
    private Amethyst amethyst;
    private Opal opal;
    private Aventurine aventurine;

    @Before
    public void setUp() {
        semiPreciousStone = new SemiPreciousStone("Quartz", 2.8, 3000, 0.7, "Vitreous", "Clear", "7");
        amethyst = new Amethyst(3.0, 5000, 0.8, "Glassy", "Purple", "7", "Striped");
        opal = new Opal(2.2, 4000, 0.9, "Oily", "Green", "8", "Iridescent");
        aventurine = new Aventurine(2.2, 4000, 0.9, "Oily", "Green", "8", "Fuchsite");
    }

    @Test
    public void testGetHardness() {
        assertEquals("7", semiPreciousStone.getHardness());
        assertEquals("7", amethyst.getHardness());
        assertEquals("8", opal.getHardness());
        assertEquals("8", aventurine.getHardness());
    }

    @Test
    public void testSetHardness() {
        semiPreciousStone.setHardness("6.5");
        assertEquals("6.5", semiPreciousStone.getHardness());
        
        amethyst.setHardness("8");
        assertEquals("8", amethyst.getHardness());
    }

    @Test
    public void testToString() {
        String str = semiPreciousStone.toString();
        assertTrue(str.contains("Quartz"));
        assertTrue(str.contains("7"));
        assertTrue(str.contains("[Semiprecious]"));
    }

    @Test
    public void testInheritance() {
        assertTrue(semiPreciousStone instanceof Gemstone);
        assertTrue(amethyst instanceof SemiPreciousStone);
        assertTrue(amethyst instanceof Gemstone);
        assertTrue(opal instanceof SemiPreciousStone);
        assertTrue(aventurine instanceof SemiPreciousStone);
    }

    @Test
    public void testSemiPreciousStoneProperties() {
        assertEquals("Quartz", semiPreciousStone.getName());
        assertEquals(2.8, semiPreciousStone.getWeight(), 0.001);
        assertEquals(3000, semiPreciousStone.getCost(), 0.001);
        assertEquals(0.7, semiPreciousStone.getTransparency(), 0.001);
        assertEquals("Vitreous", semiPreciousStone.getLustre());
        assertEquals("Clear", semiPreciousStone.getColor());
    }
}
