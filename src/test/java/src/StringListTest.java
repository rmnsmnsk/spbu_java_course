package src;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.task1.StringList;

public class StringListTest {
    private StringList stringList;

    @BeforeEach
    public void init() {
        stringList = new StringList();
    }

    @Test
    public void iteratorTest() {
        Assertions.assertFalse(stringList.hasNext());

        String value = "value";
        for (int i = 0; i <= 4; i++) {
            stringList.addFirst(value + i);
        }

        for (int i = 4; i >= 0; i--) {
            Assertions.assertTrue(stringList.hasNext());
            Assertions.assertEquals(value + i, stringList.next());
        }

        Assertions.assertFalse(stringList.hasNext());
    }

    @Test
    public void addFirstTest() {
        Assertions.assertEquals(0, stringList.size());

        String value = "value";
        stringList.addFirst(value);

        Assertions.assertEquals(1, stringList.size());
    }

    @Test
    public void removeEmptyTest() {
        Assertions.assertEquals(0, stringList.size());

        String value = "value";

        Assertions.assertFalse(stringList.remove(value));

        Assertions.assertEquals(0, stringList.size());
    }

    @Test
    public void removeContainedTest() {
        Assertions.assertEquals(0, stringList.size());

        String value = "value";
        stringList.addFirst(value);

        Assertions.assertTrue(stringList.remove(value));
        Assertions.assertFalse(stringList.remove(value));

        Assertions.assertEquals(0, stringList.size());
    }

    @Test
    public void removeNotContainedTest() {
        Assertions.assertEquals(0, stringList.size());

        String value = "value";
        stringList.addFirst(value);

        Assertions.assertFalse(stringList.remove(value + "NOT"));

        Assertions.assertEquals(1, stringList.size());
    }

    @Test
    public void containedTest() {
        String value = "value";
        stringList.addFirst(value);

        Assertions.assertTrue(stringList.contains(value));
    }

    @Test
    public void notContainedTest() {
        String value = "value";
        stringList.addFirst(value);

        Assertions.assertFalse(stringList.contains(value + "NOT"));
    }
}
