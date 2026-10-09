package src.task1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class StringSetTest {
    private StringList list;
    private StringSet set;

    @BeforeEach
    void setUp() {
        list = mock(StringList.class);
        set = new StringSet(() -> list);
    }

    @Test
    void put_whenElementDoesNotExist_addsElement() {
        when(list.contains("1")).thenReturn(false);

        set.put("1");

        verify(list).addFirst("1");
        assertThat(set.getSize(), is(1));
    }

    @Test
    void put_whenElementAlreadyExists_doesNotAddElement() {
        when(list.contains("1")).thenReturn(true);

        set.put("1");

        verify(list, never()).addFirst("1");
        assertThat(set.getSize(), is(0));
    }

    @Test
    void contains_whenElementExists_returnsTrue() {
        when(list.contains("1")).thenReturn(true);

        boolean contains = set.contains("1");

        assertThat(contains, is(true));
    }

    @Test
    void remove_whenElementExists_removesElement() {
        when(list.contains("1")).thenReturn(false);
        when(list.remove("1")).thenReturn(true);
        set.put("1");

        boolean removed = set.remove("1");

        assertThat(removed, is(true));
        assertThat(set.getSize(), is(0));
    }

    @Test
    void remove_whenElementDoesNotExist_returnsFalse() {
        when(list.remove("1")).thenReturn(false);

        boolean removed = set.remove("1");

        assertThat(removed, is(false));
        assertThat(set.getSize(), is(0));
    }

    @Test
    void clear_whenSetIsNotEmpty_makesSetEmpty() {
        when(list.contains("1")).thenReturn(false);
        set.put("1");

        set.clear();

        assertThat(set.getSize(), is(0));
    }
}
