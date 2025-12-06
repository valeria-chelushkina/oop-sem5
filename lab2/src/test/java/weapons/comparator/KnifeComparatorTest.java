package weapons.comparator;

import weapons.model.Knife;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class KnifeComparatorTest {

    private KnifeComparator comparator;
    private Knife knife1;
    private Knife knife2;
    private Knife knife3;

    @Before
    public void setUp() {
        comparator = new KnifeComparator();
        knife1 = new Knife("knife-001", "Dagger", "One-handed", "Germany", true);
        knife2 = new Knife("knife-002", "Dagger", "One-handed", "Germany", false);
        knife3 = new Knife("knife-003", "Sword", "Two-handed", "Japan", true);
    }

    @Test
    public void testCompareDifferentTypes() {
        int result = comparator.compare(knife1, knife3);
        assertTrue("Dagger should come before Sword", result < 0);
        
        int result2 = comparator.compare(knife3, knife1);
        assertTrue("Sword should come after Dagger", result2 > 0);
    }

    @Test
    public void testCompareSameTypeDifferentOrigin() {
        Knife knife4 = new Knife("knife-004", "Dagger", "One-handed", "Japan", true);
        int result = comparator.compare(knife1, knife4);
        assertTrue("Germany should come before Japan", result < 0);
        
        int result2 = comparator.compare(knife4, knife1);
        assertTrue("Japan should come after Germany", result2 > 0);
    }

    @Test
    public void testCompareSameTypeSameOriginDifferentId() {
        int result = comparator.compare(knife1, knife2);
        assertTrue("knife-001 should come before knife-002", result < 0);
        
        int result2 = comparator.compare(knife2, knife1);
        assertTrue("knife-002 should come after knife-001", result2 > 0);
    }

    @Test
    public void testCompareEqualKnives() {
        Knife knife1Copy = new Knife("knife-001", "Dagger", "One-handed", "Germany", true);
        int result = comparator.compare(knife1, knife1Copy);
        assertEquals("Equal knives should return 0", 0, result);
    }

    @Test
    public void testCompareNullType() {
        Knife knifeNull = new Knife("knife-005", null, "One-handed", "Germany", true);
        try {
            comparator.compare(knifeNull, knife1);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
    }

    @Test
    public void testCompareNullOrigin() {
        Knife knifeNull = new Knife("knife-006", "Dagger", "One-handed", null, true);
        try {
            comparator.compare(knife1, knifeNull);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
    }

    @Test
    public void testCompareNullId() {
        Knife knifeNull = new Knife(null, "Dagger", "One-handed", "Germany", true);
        try {
            comparator.compare(knife1, knifeNull);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
    }
}

