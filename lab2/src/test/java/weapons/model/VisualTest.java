package weapons.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.math.BigDecimal;

public class VisualTest {

    private Visual visual;
    private Blade blade;
    private Handle handle;

    @Before
    public void setUp() {
        visual = new Visual();
        blade = new Blade(new BigDecimal("15.5"), new BigDecimal("3.2"));
        handle = new Handle("Wood", "Oak");
    }

    @Test
    public void testDefaultConstructor() {
        assertNotNull(visual);
        assertNull(visual.getBlade());
        assertNull(visual.getMaterial());
        assertNull(visual.getHandle());
        assertFalse(visual.isBloodGroove());
    }

    @Test
    public void testParameterizedConstructor() {
        Visual visual2 = new Visual(blade, "Steel", handle, true);
        assertEquals(blade, visual2.getBlade());
        assertEquals("Steel", visual2.getMaterial());
        assertEquals(handle, visual2.getHandle());
        assertTrue(visual2.isBloodGroove());
    }

    @Test
    public void testGetBlade() {
        assertNull(visual.getBlade());
        visual.setBlade(blade);
        assertEquals(blade, visual.getBlade());
    }

    @Test
    public void testSetBlade() {
        visual.setBlade(blade);
        assertEquals(blade, visual.getBlade());
        
        Blade blade2 = new Blade(new BigDecimal("20.0"), new BigDecimal("4.0"));
        visual.setBlade(blade2);
        assertEquals(blade2, visual.getBlade());
    }

    @Test
    public void testGetMaterial() {
        assertNull(visual.getMaterial());
        visual.setMaterial("Titanium");
        assertEquals("Titanium", visual.getMaterial());
    }

    @Test
    public void testSetMaterial() {
        visual.setMaterial("Carbon");
        assertEquals("Carbon", visual.getMaterial());
        
        visual.setMaterial("Aluminum");
        assertEquals("Aluminum", visual.getMaterial());
    }

    @Test
    public void testGetHandle() {
        assertNull(visual.getHandle());
        visual.setHandle(handle);
        assertEquals(handle, visual.getHandle());
    }

    @Test
    public void testSetHandle() {
        visual.setHandle(handle);
        assertEquals(handle, visual.getHandle());
        
        Handle handle2 = new Handle("Plastic", "ABS");
        visual.setHandle(handle2);
        assertEquals(handle2, visual.getHandle());
    }

    @Test
    public void testIsBloodGroove() {
        assertFalse(visual.isBloodGroove());
        visual.setBloodGroove(true);
        assertTrue(visual.isBloodGroove());
    }

    @Test
    public void testSetBloodGroove() {
        visual.setBloodGroove(true);
        assertTrue(visual.isBloodGroove());
        
        visual.setBloodGroove(false);
        assertFalse(visual.isBloodGroove());
    }

    @Test
    public void testToString() {
        visual.setBlade(blade);
        visual.setMaterial("Steel");
        visual.setHandle(handle);
        visual.setBloodGroove(true);
        
        String str = visual.toString();
        assertTrue(str.contains("Visual"));
        assertTrue(str.contains("Steel"));
        assertTrue(str.contains("bloodGroove=true"));
    }
}

