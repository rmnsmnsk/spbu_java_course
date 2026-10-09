package src.task1;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class StringListTest {

    @Test
    void addFirst_whenListIsEmpty_addsElement() {
        var list = new StringList();

        list.addFirst("1");

        assertThat(list.contains("1"), is(true));
        assertThat(list.size(), is(1));
    }

    @Test
    void remove_whenListIsNotEmpty_removesElement() {
        var list = new StringList();
        list.addFirst("1");

        boolean removed = list.remove("1");

        assertThat(removed, is(true));
        assertThat(list.size(), is(0));
        assertThat(list.contains("1"), is(false));
    }

    @Test
    void remove_whenElementDoesNotExist_doesNotChangeSize() {
        var list = new StringList();

        boolean removed = list.remove("1");

        assertThat(removed, is(false));
        assertThat(list.size(), is(0));
    }

    @Test
    void remove_whenListContainsSeveralElements_removesSpecifiedElement() {
        var list = new StringList();
        list.addFirst("1");
        list.addFirst("2");
        list.addFirst("3");
        list.addFirst("4");

        boolean removed = list.remove("2");
        assertThat(removed, is(true));
        assertThat(list.contains("1"), is(true));
        assertThat(list.contains("3"), is(true));
        assertThat(list.contains("4"), is(true));
        assertThat(list.contains("2"), is(false));
        assertThat(list.size(), is(3));


    }


}