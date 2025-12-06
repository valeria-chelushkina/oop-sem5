package gemstones.gemstone;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AventurineTest {

    private Aventurine aventurine;

    @Before
    public void setUp() {
        aventurine = new Aventurine(2.2, 4000, 0.9, "Oily", "Green", "8", "Fuchsite");
    }

    @Test
    public void testConstructor() {
        assertEquals("Aventurine", aventurine.getName());
        assertEquals(2.2, aventurine.getWeight(), 0.001);
        assertEquals(4000, aventurine.getCost(), 0.001);
        assertEquals(0.9, aventurine.getTransparency(), 0.001);
        assertEquals("Oily", aventurine.getLustre());
        assertEquals("Green", aventurine.getColor());
        assertEquals("8", aventurine.getHardness());
        assertEquals("Fuchsite", aventurine.getInclusions());
    }

    @Test
    public void testGetInclusions() {
        assertEquals("Fuchsite", aventurine.getInclusions());
    }

    @Test
    public void testSetInclusions() {
        aventurine.setInclusions("Hematite");
        assertEquals("Hematite", aventurine.getInclusions());
        
        aventurine.setInclusions("Dumortierite");
        assertEquals("Dumortierite", aventurine.getInclusions());
    }

    @Test
    public void testToString() {
        String str = aventurine.toString();
        assertTrue(str.contains("Aventurine"));
        assertTrue(str.contains("Fuchsite"));
        assertTrue(str.contains("8"));
        assertTrue(str.contains("[Semiprecious]"));
    }

    @Test
    public void testInheritance() {
        assertTrue(aventurine instanceof SemiPreciousStone);
        assertTrue(aventurine instanceof Gemstone);
    }

    @Test
    public void testAventurineWithDifferentValues() {
        Aventurine aventurine2 = new Aventurine(3.0, 5000, 0.85, "Greasy", "Blue", "6.5", "Hematite");
        assertEquals("Blue", aventurine2.getColor());
        assertEquals("Hematite", aventurine2.getInclusions());
        assertEquals("6.5", aventurine2.getHardness());
    }
}
