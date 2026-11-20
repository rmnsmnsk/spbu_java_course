package src.MyTreeSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MyTreeSetImplTest {

    private MyTreeSet<Integer> set;

    @BeforeEach
    void setUp() {
        set = new MyTreeSetImpl<>();
    }

    @Test
    void newSet_isEmpty() {
        assertThat(set.size(), is(0));
        assertThat(set.isEmpty(), is(true));
    }

    @Test
    void add_whenElementDoesNotExist_addsElement() {
        boolean added = set.add(5);

        assertThat(added, is(true));
        assertThat(set.contains(5), is(true));
        assertThat(set.size(), is(1));
    }

    @Test
    void add_whenElementAlreadyExists_doesNotAddElementAgain() {
        set.add(5);

        boolean added = set.add(5);

        assertThat(added, is(false));
        assertThat(set.size(), is(1));
    }

    @Test
    void remove_whenRemovingLeaf_removesElement() {
        set.add(5);
        set.add(3);
        set.add(7);

        boolean removed = set.remove(3);

        assertThat(removed, is(true));
        assertThat(set.contains(3), is(false));
        assertThat(set.size(), is(2));
    }

    @Test
    void remove_whenNodeHasOneChild_removesElement() {
        set.add(5);
        set.add(3);
        set.add(2);

        boolean removed = set.remove(3);

        assertThat(removed, is(true));
        assertThat(set.contains(2), is(true));
        assertThat(set.contains(3), is(false));
        assertThat(set.size(), is(2));
    }

    @Test
    void remove_whenNodeHasTwoChildren_removesElement() {
        set.add(5);
        set.add(3);
        set.add(7);
        set.add(6);
        set.add(8);

        boolean removed = set.remove(7);

        assertThat(removed, is(true));
        assertThat(set.contains(6), is(true));
        assertThat(set.contains(7), is(false));
        assertThat(set.contains(8), is(true));
        assertThat(set.size(), is(4));
    }

    @Test
    void remove_whenElementDoesNotExist_returnsFalse() {
        set.add(5);

        boolean removed = set.remove(10);

        assertThat(removed, is(false));
        assertThat(set.size(), is(1));
    }

    @Test
    void iterator_returnsElementsInAscendingOrder() {
        set.add(5);
        set.add(2);
        set.add(8);

        Iterator<Integer> iterator = set.iterator();

        assertThat(iterator.next(), is(2));
        assertThat(iterator.next(), is(5));
        assertThat(iterator.next(), is(8));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test
    void descendingIterator_returnsElementsInDescendingOrder() {
        set.add(5);
        set.add(2);
        set.add(8);

        Iterator<Integer> iterator = set.descendingIterator();

        assertThat(iterator.next(), is(8));
        assertThat(iterator.next(), is(5));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test
    void iterator_whenThereAreNoMoreElements_throwsException() {
        Iterator<Integer> iterator = set.iterator();

        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    void firstAndLast_whenSetIsNotEmpty_returnFirstAndLastElements() {
        set.add(5);
        set.add(2);
        set.add(8);

        assertThat(set.first(), is(2));
        assertThat(set.last(), is(8));
    }

    @Test
    void firstAndLast_whenSetIsEmpty_throwException() {
        assertThrows(NoSuchElementException.class, set::first);
        assertThrows(NoSuchElementException.class, set::last);
    }

    @Test
    void navigationMethods_returnNearestElements() {
        set.add(2);
        set.add(5);
        set.add(8);

        assertThat(set.lower(5), is(2));
        assertThat(set.floor(5), is(5));
        assertThat(set.ceiling(6), is(8));
        assertThat(set.higher(5), is(8));
        assertThat(set.lower(2), is((Integer) null));
        assertThat(set.higher(8), is((Integer) null));
    }

    @Test
    void constructorWithComparator_usesComparatorOrder() {
        set = new MyTreeSetImpl<>(Comparator.reverseOrder());
        set.add(1);
        set.add(3);
        set.add(2);

        Iterator<Integer> iterator = set.iterator();

        assertThat(iterator.next(), is(3));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.next(), is(1));
    }

    @Test
    void descendingSet_usesTheSameTree() {
        set.add(5);
        set.add(2);
        set.add(8);
        MyTreeSet<Integer> descendingSet = set.descendingSet();

        descendingSet.add(10);
        set.remove(2);

        assertThat(set.contains(10), is(true));
        assertThat(descendingSet.contains(2), is(false));
        assertThat(descendingSet.first(), is(10));
        assertThat(descendingSet.last(), is(5));
    }

    @Test
    void clear_whenSetIsNotEmpty_makesSetEmpty() {
        set.add(5);
        set.add(2);
        set.add(8);

        set.clear();

        assertThat(set.size(), is(0));
        assertThat(set.isEmpty(), is(true));
        assertThat(set.contains(5), is(false));
    }
}
