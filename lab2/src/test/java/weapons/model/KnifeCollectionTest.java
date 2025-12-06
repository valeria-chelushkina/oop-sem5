package weapons.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class KnifeCollectionTest {

    private KnifeCollection collection;
    private Knife knife1;
    private Knife knife2;

    @Before
    public void setUp() {
        collection = new KnifeCollection();
        knife1 = new Knife("knife-001", "Dagger", "One-handed", "Germany", true);
        knife2 = new Knife("knife-002", "Sword", "Two-handed", "Japan", false);
    }

    @Test
    public void testDefaultConstructor() {
        assertNotNull(collection);
        assertNotNull(collection.getKnives());
        assertEquals(0, collection.getKnives().size());
    }

    @Test
    public void testGetKnives() {
        List<Knife> knives = collection.getKnives();
        assertNotNull(knives);
        assertEquals(0, knives.size());
    }

    @Test
    public void testSetKnives() {
        List<Knife> knives = new java.util.ArrayList<>();
        knives.add(knife1);
        knives.add(knife2);
        collection.setKnives(knives);
        
        assertEquals(2, collection.getKnives().size());
        assertTrue(collection.getKnives().contains(knife1));
        assertTrue(collection.getKnives().contains(knife2));
    }

    @Test
    public void testAddKnife() {
        assertEquals(0, collection.getKnives().size());
        collection.addKnife(knife1);
        assertEquals(1, collection.getKnives().size());
        assertTrue(collection.getKnives().contains(knife1));
        
        collection.addKnife(knife2);
        assertEquals(2, collection.getKnives().size());
        assertTrue(collection.getKnives().contains(knife2));
    }

    @Test
    public void testAddMultipleKnives() {
        collection.addKnife(knife1);
        collection.addKnife(knife2);
        collection.addKnife(new Knife("knife-003", "Knife", "One-handed", "USA", true));
        
        assertEquals(3, collection.getKnives().size());
    }

    @Test
    public void testGetKnivesReturnsSameList() {
        collection.addKnife(knife1);
        List<Knife> knives1 = collection.getKnives();
        List<Knife> knives2 = collection.getKnives();
        
        assertEquals(knives1, knives2);
        assertSame(knives1, knives2);
    }
}

