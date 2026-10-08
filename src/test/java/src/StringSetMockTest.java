package src;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import src.task1.StringList;
import src.task1.StringSet;

import static org.mockito.Mockito.*;

public class StringSetMockTest {
    private StringSet stringSet = null;
    private StringList mockList = null;

    @BeforeEach
    void createStringSet() {
        mockList = Mockito.mock(StringList.class);

        stringSet = new StringSet(() -> mockList);
    }


    @Test
    public void removeElementNotContainedReturnFalse() {
        Assertions.assertEquals(0, stringSet.getSize());

        String value = "some value";
        when(mockList.remove(value)).thenReturn(false);

        boolean result = stringSet.remove(value);

        verify(mockList, times(1)).remove(value);
        Assertions.assertFalse(result);
    }

    @Test
    public void removeElementContainedReturnTrue() {
        Assertions.assertEquals(0, stringSet.getSize());

        String value = "some value";
        when(mockList.remove(value)).thenReturn(true);

        boolean result = stringSet.remove(value);

        verify(mockList, times(1)).remove(value);
        Assertions.assertTrue(result);
        Assertions.assertEquals(-1, stringSet.getSize());
    }

    @Test
    public void elementNotContainedReturnFalse() {
        String value = "some value";
        when(mockList.contains(value)).thenReturn(false);

        boolean result = stringSet.contains(value);

        verify(mockList, times(1)).contains(value);
        Assertions.assertFalse(result);
    }

    @Test
    public void elementContainedReturnTrue() {
        String value = "some value";
        when(mockList.contains(value)).thenReturn(true);

        boolean result = stringSet.contains(value);

        verify(mockList, times(1)).contains(value);
        Assertions.assertTrue(result);
    }

    @Test
    void putElementContainedDoNothing() {
        String value = "some value";
        when(mockList.contains(value)).thenReturn(true);

        stringSet.put(value);

        verify(mockList).contains(value);
        verify(mockList, never()).addFirst(anyString());
    }


    @Test
    void putElementNotContainedAddElementToList() {
        Assertions.assertEquals(0, stringSet.getSize());

        String value = "some value";
        when(mockList.contains(value)).thenReturn(false).thenReturn(true);

        stringSet.put(value);

        verify(mockList, times(1)).addFirst(value);
        verify(mockList).contains(value);

        Assertions.assertEquals(1, stringSet.getSize());
    }
}
