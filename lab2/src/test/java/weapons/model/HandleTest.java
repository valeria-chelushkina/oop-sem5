package weapons.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class HandleTest {

    private Handle handle;

    @Before
    public void setUp() {
        handle = new Handle();
    }

    @Test
    public void testDefaultConstructor() {
        assertNotNull(handle);
        assertNull(handle.getMaterial());
        assertNull(handle.getWoodType());
    }

    @Test
    public void testParameterizedConstructor() {
        Handle handle2 = new Handle("Wood", "Oak");
        assertEquals("Wood", handle2.getMaterial());
        assertEquals("Oak", handle2.getWoodType());
    }

    @Test
    public void testGetMaterial() {
        assertNull(handle.getMaterial());
        handle.setMaterial("Metal");
        assertEquals("Metal", handle.getMaterial());
    }

    @Test
    public void testSetMaterial() {
        handle.setMaterial("Plastic");
        assertEquals("Plastic", handle.getMaterial());
        
        handle.setMaterial("Wood");
        assertEquals("Wood", handle.getMaterial());
    }

    @Test
    public void testGetWoodType() {
        assertNull(handle.getWoodType());
        handle.setWoodType("Oak");
        assertEquals("Oak", handle.getWoodType());
    }

    @Test
    public void testSetWoodType() {
        handle.setWoodType("Pine");
        assertEquals("Pine", handle.getWoodType());
        
        handle.setWoodType("Maple");
        assertEquals("Maple", handle.getWoodType());
    }

    @Test
    public void testToStringWithWoodType() {
        handle.setMaterial("Wood");
        handle.setWoodType("Oak");
        String str = handle.toString();
        
        assertTrue(str.contains("Handle"));
        assertTrue(str.contains("Wood"));
        assertTrue(str.contains("Oak"));
        assertTrue(str.contains("material"));
        assertTrue(str.contains("woodType"));
    }

    @Test
    public void testToStringWithoutWoodType() {
        handle.setMaterial("Metal");
        String str = handle.toString();
        
        assertTrue(str.contains("Handle"));
        assertTrue(str.contains("Metal"));
        assertTrue(str.contains("material"));
    }

    @Test
    public void testToStringWithNullWoodType() {
        handle.setMaterial("Plastic");
        handle.setWoodType(null);
        String str = handle.toString();
        
        assertTrue(str.contains("Handle"));
        assertTrue(str.contains("Plastic"));
    }

    @Test
    public void testToStringWithEmptyWoodType() {
        handle.setMaterial("Steel");
        handle.setWoodType("");
        String str = handle.toString();
        
        assertTrue(str.contains("Handle"));
        assertTrue(str.contains("Steel"));
    }
}

