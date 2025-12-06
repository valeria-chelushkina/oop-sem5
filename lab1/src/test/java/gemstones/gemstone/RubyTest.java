package gemstones.gemstone;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RubyTest {

    private Ruby ruby;

    @Before
    public void setUp() {
        ruby = new Ruby(1.8, 30000, 0.85, "Subadamantine", "Red", "Myanmar", 0.9);
    }

    @Test
    public void testConstructor() {
        assertEquals("Ruby", ruby.getName());
        assertEquals(1.8, ruby.getWeight(), 0.001);
        assertEquals(30000, ruby.getCost(), 0.001);
        assertEquals(0.85, ruby.getTransparency(), 0.001);
        assertEquals("Subadamantine", ruby.getLustre());
        assertEquals("Red", ruby.getColor());
        assertEquals("Myanmar", ruby.getOrigin());
        assertEquals(0.9, ruby.getClarity(), 0.001);
    }

    @Test
    public void testGetClarity() {
        assertEquals(0.9, ruby.getClarity(), 0.001);
    }

    @Test
    public void testSetClarity() {
        ruby.setClarity(0.95);
        assertEquals(0.95, ruby.getClarity(), 0.001);
        
        ruby.setClarity(0.5);
        assertEquals(0.5, ruby.getClarity(), 0.001);
    }

    @Test
    public void testToString() {
        String str = ruby.toString();
        assertTrue(str.contains("Ruby"));
        // Check for clarity format (could be 0.90 or 0,90 depending on locale)
        assertTrue(str.contains("0.9") || str.contains("0,9"));
        assertTrue(str.contains("Myanmar"));
        assertTrue(str.contains("[Precious]"));
        assertTrue(str.contains("clarity"));
    }

    @Test
    public void testInheritance() {
        assertTrue(ruby instanceof PreciousStone);
        assertTrue(ruby instanceof Gemstone);
    }

    @Test
    public void testRubyWithDifferentValues() {
        Ruby ruby2 = new Ruby(2.0, 40000, 0.9, "Adamantine", "Deep Red", "Thailand", 0.95);
        assertEquals("Deep Red", ruby2.getColor());
        assertEquals(0.95, ruby2.getClarity(), 0.001);
        assertEquals("Thailand", ruby2.getOrigin());
    }
}
