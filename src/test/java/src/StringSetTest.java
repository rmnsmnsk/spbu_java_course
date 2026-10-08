package src;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.task1.StringSet;

public class StringSetTest {
    private StringSet stringSet = null;
    private static final int PRE_RESIZE_VALUE = 7;

    @BeforeEach
    void createStringSet() {
        stringSet = new StringSet();
    }

    @Test
    void putElementNotContainedAddElementToSet() {
        Assertions.assertEquals(0, stringSet.getSize());

        String value = "some value";

        stringSet.put(value);

        Assertions.assertEquals(1, stringSet.getSize());
    }

    @Test
    void putElementDuplicateTest() {
        Assertions.assertEquals(0, stringSet.getSize());

        String value = "some value";

        stringSet.put(value);
        stringSet.put(value);

        Assertions.assertEquals(1, stringSet.getSize());
    }

    @Test
    public void elementNotContainedReturnFalse() {
        String value = "some value";

        boolean result = stringSet.contains(value);

        Assertions.assertFalse(result);
    }

    @Test
    public void elementContainedReturnTrue() {
        String value = "some value";

        stringSet.put(value);

        boolean result = stringSet.contains(value);

        Assertions.assertTrue(result);
    }

    @Test
    public void removeElementNotContainedReturnFalse() {
        Assertions.assertEquals(0, stringSet.getSize());

        String value = "some value";

        boolean result = stringSet.remove(value);

        Assertions.assertFalse(result);
        Assertions.assertEquals(0, stringSet.getSize());
    }

    @Test
    public void removeElementContainedReturnTrue() {
        Assertions.assertEquals(0, stringSet.getSize());

        String value = "some value";

        stringSet.put(value);

        boolean result = stringSet.remove(value);

        Assertions.assertTrue(result);
        Assertions.assertEquals(0, stringSet.getSize());
    }


    @Test
    public void clearStringSetTest() {
        Assertions.assertEquals(0, stringSet.getSize());

        String value = "some value";

        for (int i = 0; i < PRE_RESIZE_VALUE; i++) {
            stringSet.put(value + i);
        }

        Assertions.assertEquals(PRE_RESIZE_VALUE, stringSet.getSize());

        stringSet.clear();

        Assertions.assertEquals(0, stringSet.getSize());
        for (int i = 0; i < PRE_RESIZE_VALUE; i++) {
            Assertions.assertFalse(stringSet.contains(value + i));
        }
    }

    @Test
    public void onResizeAllOldElementInSetTest() {
        Assertions.assertEquals(0, stringSet.getSize());

        String value = "some value";

        for (int i = 0; i < PRE_RESIZE_VALUE; i++) {
            stringSet.put(value + i);
        }
        Assertions.assertEquals(PRE_RESIZE_VALUE, stringSet.getSize());


        stringSet.put(value + "resize");

        Assertions.assertEquals(PRE_RESIZE_VALUE + 1, stringSet.getSize());

        for (int i = 0; i < PRE_RESIZE_VALUE; i++) {
            Assertions.assertTrue(stringSet.contains(value + i));
        }

        Assertions.assertTrue(stringSet.contains(value + "resize"));
    }
}
