package weapons.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class KnifeTest {

    private Knife knife;
    private Visual visual1;
    private Visual visual2;

    @Before
    public void setUp() {
        knife = new Knife();
        visual1 = new Visual();
        visual1.setMaterial("Steel");
        visual2 = new Visual();
        visual2.setMaterial("Titanium");
    }

    @Test
    public void testDefaultConstructor() {
        assertNotNull(knife);
        assertNull(knife.getId());
        assertNull(knife.getType());
        assertNull(knife.getHandy());
        assertNull(knife.getOrigin());
        assertNotNull(knife.getVisuals());
        assertEquals(0, knife.getVisuals().size());
        assertFalse(knife.isValue());
    }

    @Test
    public void testParameterizedConstructor() {
        Knife knife2 = new Knife("knife-001", "Dagger", "One-handed", "Germany", true);
        assertEquals("knife-001", knife2.getId());
        assertEquals("Dagger", knife2.getType());
        assertEquals("One-handed", knife2.getHandy());
        assertEquals("Germany", knife2.getOrigin());
        assertTrue(knife2.isValue());
        assertNotNull(knife2.getVisuals());
        assertEquals(0, knife2.getVisuals().size());
    }

    @Test
    public void testGetId() {
        assertNull(knife.getId());
        knife.setId("knife-001");
        assertEquals("knife-001", knife.getId());
    }

    @Test
    public void testSetId() {
        knife.setId("knife-002");
        assertEquals("knife-002", knife.getId());
        
        knife.setId("knife-003");
        assertEquals("knife-003", knife.getId());
    }

    @Test
    public void testGetType() {
        assertNull(knife.getType());
        knife.setType("Sword");
        assertEquals("Sword", knife.getType());
    }

    @Test
    public void testSetType() {
        knife.setType("Dagger");
        assertEquals("Dagger", knife.getType());
        
        knife.setType("Knife");
        assertEquals("Knife", knife.getType());
    }

    @Test
    public void testGetHandy() {
        assertNull(knife.getHandy());
        knife.setHandy("One-handed");
        assertEquals("One-handed", knife.getHandy());
    }

    @Test
    public void testSetHandy() {
        knife.setHandy("Two-handed");
        assertEquals("Two-handed", knife.getHandy());
        
        knife.setHandy("One-handed");
        assertEquals("One-handed", knife.getHandy());
    }

    @Test
    public void testGetOrigin() {
        assertNull(knife.getOrigin());
        knife.setOrigin("Japan");
        assertEquals("Japan", knife.getOrigin());
    }

    @Test
    public void testSetOrigin() {
        knife.setOrigin("Germany");
        assertEquals("Germany", knife.getOrigin());
        
        knife.setOrigin("USA");
        assertEquals("USA", knife.getOrigin());
    }

    @Test
    public void testGetVisuals() {
        List<Visual> visuals = knife.getVisuals();
        assertNotNull(visuals);
        assertEquals(0, visuals.size());
    }

    @Test
    public void testSetVisuals() {
        List<Visual> visuals = new java.util.ArrayList<>();
        visuals.add(visual1);
        visuals.add(visual2);
        knife.setVisuals(visuals);
        
        assertEquals(2, knife.getVisuals().size());
        assertTrue(knife.getVisuals().contains(visual1));
        assertTrue(knife.getVisuals().contains(visual2));
    }

    @Test
    public void testAddVisual() {
        assertEquals(0, knife.getVisuals().size());
        knife.addVisual(visual1);
        assertEquals(1, knife.getVisuals().size());
        assertTrue(knife.getVisuals().contains(visual1));
        
        knife.addVisual(visual2);
        assertEquals(2, knife.getVisuals().size());
        assertTrue(knife.getVisuals().contains(visual2));
    }

    @Test
    public void testIsValue() {
        assertFalse(knife.isValue());
        knife.setValue(true);
        assertTrue(knife.isValue());
    }

    @Test
    public void testSetValue() {
        knife.setValue(true);
        assertTrue(knife.isValue());
        
        knife.setValue(false);
        assertFalse(knife.isValue());
    }

    @Test
    public void testToString() {
        knife.setId("knife-001");
        knife.setType("Dagger");
        knife.setHandy("One-handed");
        knife.setOrigin("Germany");
        knife.setValue(true);
        knife.addVisual(visual1);
        
        String str = knife.toString();
        assertTrue(str.contains("Knife"));
        assertTrue(str.contains("knife-001"));
        assertTrue(str.contains("Dagger"));
        assertTrue(str.contains("One-handed"));
        assertTrue(str.contains("Germany"));
        assertTrue(str.contains("value=true"));
    }
}

