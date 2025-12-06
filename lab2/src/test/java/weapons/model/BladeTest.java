package weapons.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.math.BigDecimal;

public class BladeTest {

    private Blade blade;

    @Before
    public void setUp() {
        blade = new Blade();
    }

    @Test
    public void testDefaultConstructor() {
        assertNotNull(blade);
        assertNull(blade.getLength());
        assertNull(blade.getWidth());
    }

    @Test
    public void testParameterizedConstructor() {
        BigDecimal length = new BigDecimal("15.5");
        BigDecimal width = new BigDecimal("3.2");
        Blade blade2 = new Blade(length, width);
        
        assertEquals(length, blade2.getLength());
        assertEquals(width, blade2.getWidth());
    }

    @Test
    public void testGetLength() {
        assertNull(blade.getLength());
        BigDecimal length = new BigDecimal("20.0");
        blade.setLength(length);
        assertEquals(length, blade.getLength());
    }

    @Test
    public void testSetLength() {
        BigDecimal length = new BigDecimal("18.5");
        blade.setLength(length);
        assertEquals(length, blade.getLength());
        
        BigDecimal length2 = new BigDecimal("25.0");
        blade.setLength(length2);
        assertEquals(length2, blade.getLength());
    }

    @Test
    public void testGetWidth() {
        assertNull(blade.getWidth());
        BigDecimal width = new BigDecimal("2.5");
        blade.setWidth(width);
        assertEquals(width, blade.getWidth());
    }

    @Test
    public void testSetWidth() {
        BigDecimal width = new BigDecimal("3.0");
        blade.setWidth(width);
        assertEquals(width, blade.getWidth());
        
        BigDecimal width2 = new BigDecimal("4.5");
        blade.setWidth(width2);
        assertEquals(width2, blade.getWidth());
    }

    @Test
    public void testToString() {
        blade.setLength(new BigDecimal("15.5"));
        blade.setWidth(new BigDecimal("3.2"));
        String str = blade.toString();
        
        assertTrue(str.contains("Blade"));
        assertTrue(str.contains("15.5"));
        assertTrue(str.contains("3.2"));
        assertTrue(str.contains("cm"));
        assertTrue(str.contains("mm"));
    }

    @Test
    public void testToStringWithNullValues() {
        String str = blade.toString();
        assertTrue(str.contains("Blade"));
    }
}

