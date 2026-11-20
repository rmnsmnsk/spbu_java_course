package src.MyTreeSet;

import java.util.AbstractSet;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyTreeSetImpl<E> extends AbstractSet<E> implements MyTreeSet<E> {

    private static class Node<E> {
        private E value;
        private Node<E> right;
        private Node<E> left;

        private Node(E value) {
            this.value = value;
        }
    }

    private static class StackNode<E> {
        private final Node<E> value;
        private final StackNode<E> next;

        private StackNode(Node<E> value, StackNode<E> next) {
            this.value = value;
            this.next = next;
        }
    }

    @SuppressWarnings("unchecked")
    private int compare(E o1, E o2) {
        if (comparator != null) {
            return comparator.compare(o1, o2);
        } else {
            return ((Comparable<? super E>) o1).compareTo(o2);
        }
    }

    private Node<E> root;
    private int size;

    private final Comparator<? super E> comparator;

    public MyTreeSetImpl(Comparator<? super E> comparator) {
        this.comparator = comparator;
        this.size = 0;
        this.root = null;
    }

    public MyTreeSetImpl() {
        this(null);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(E element) {
        if (root == null) {
            compare(element, element);
            root = new Node<>(element);
            size++;
            return true;
        }

        Node<E> current = root;

        while (true) {
            int result = compare(element, current.value);

            if (result == 0) {
                return false;
            }

            if (result < 0) {
                if (current.left == null) {
                    current.left = new Node<>(element);
                    size++;
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new Node<>(element);
                    size++;
                    return true;
                }
                current = current.right;
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean contains(Object element) {
        Node<E> current = root;
        E value = (E) element;

        while (current != null) {
            int result = compare(value, current.value);

            if (result == 0) {
                return true;
            }

            if (result < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean remove(Object element) {
        Node<E> parent = null;
        Node<E> current = root;
        E value = (E) element;

        while (current != null) {
            int result = compare(value, current.value);

            if (result == 0) {
                break;
            }

            parent = current;
            if (result < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        if (current == null) {
            return false;
        }

        if (current.left != null && current.right != null) {
            Node<E> nextParent = current;
            Node<E> next = current.right;

            while (next.left != null) {
                nextParent = next;
                next = next.left;
            }

            current.value = next.value;
            parent = nextParent;
            current = next;
        }

        Node<E> child;
        if (current.left != null) {
            child = current.left;
        } else {
            child = current.right;
        }

        if (parent == null) {
            root = child;
        } else if (parent.left == current) {
            parent.left = child;
        } else {
            parent.right = child;
        }

        size--;
        return true;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    private class AscendingIterator implements Iterator<E> {
        private StackNode<E> stack;

        private AscendingIterator() {
            Node<E> node = root;
            while (node != null) {
                stack = new StackNode<>(node, stack);
                node = node.left;
            }
        }

        @Override
        public boolean hasNext() {
            return stack != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Node<E> node = stack.value;
            stack = stack.next;
            Node<E> current = node.right;

            while (current != null) {
                stack = new StackNode<>(current, stack);
                current = current.left;
            }

            return node.value;
        }
    }

    private class DescendingIterator implements Iterator<E> {
        private StackNode<E> stack;

        private DescendingIterator() {
            Node<E> node = root;
            while (node != null) {
                stack = new StackNode<>(node, stack);
                node = node.right;
            }
        }

        @Override
        public boolean hasNext() {
            return stack != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Node<E> node = stack.value;
            stack = stack.next;
            Node<E> current = node.left;

            while (current != null) {
                stack = new StackNode<>(current, stack);
                current = current.right;
            }

            return node.value;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new AscendingIterator();
    }

    @Override
    public Iterator<E> descendingIterator() {
        return new DescendingIterator();
    }

    @Override
    public E first() {
        if (root == null) {
            throw new NoSuchElementException();
        }

        Node<E> current = root;
        while (current.left != null) {
            current = current.left;
        }

        return current.value;
    }

    @Override
    public E last() {
        if (root == null) {
            throw new NoSuchElementException();
        }

        Node<E> current = root;
        while (current.right != null) {
            current = current.right;
        }

        return current.value;
    }

    @Override
    public E lower(E e) {
        if (root == null) return null;
        Node<E> node = root;
        E found = null;
        while (node != null) {
            int result = compare(node.value, e);
            if (result < 0) {
                found = node.value;
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return found;
    }

    @Override
    public E floor(E e) {
        if (root == null) return null;
        Node<E> node = root;
        E found = null;
        while (node != null) {
            int result = compare(node.value, e);
            if (result == 0) return node.value;
            else if (result < 0) {
                found = node.value;
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return found;
    }

    @Override
    public E higher(E e) {
        if (root == null) return null;
        Node<E> node = root;
        E found = null;
        while (node != null) {
            int result = compare(node.value, e);
            if (result > 0) {
                found = node.value;
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return found;
    }

    @Override
    public E ceiling(E e) {
        if (root == null) return null;
        Node<E> node = root;
        E found = null;
        while (node != null) {
            int result = compare(node.value, e);
            if (result == 0) return node.value;
            else if (result > 0) {
                found = node.value;
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return found;
    }

    @Override
    public MyTreeSet<E> descendingSet() {
        return new DescendingSet();
    }

    private class DescendingSet extends AbstractSet<E> implements MyTreeSet<E> {

        @Override
        public int size() {
            return MyTreeSetImpl.this.size();
        }

        @Override
        public boolean add(E element) {
            return MyTreeSetImpl.this.add(element);
        }

        @Override
        public boolean contains(Object element) {
            return MyTreeSetImpl.this.contains(element);
        }

        @Override
        public boolean remove(Object element) {
            return MyTreeSetImpl.this.remove(element);
        }

        @Override
        public void clear() {
            MyTreeSetImpl.this.clear();
        }

        @Override
        public Iterator<E> iterator() {
            return MyTreeSetImpl.this.descendingIterator();
        }

        @Override
        public Iterator<E> descendingIterator() {
            return MyTreeSetImpl.this.iterator();
        }

        @Override
        public MyTreeSet<E> descendingSet() {
            return MyTreeSetImpl.this;
        }

        @Override
        public E first() {
            return MyTreeSetImpl.this.last();
        }

        @Override
        public E last() {
            return MyTreeSetImpl.this.first();
        }

        @Override
        public E lower(E element) {
            return MyTreeSetImpl.this.higher(element);
        }

        @Override
        public E floor(E element) {
            return MyTreeSetImpl.this.ceiling(element);
        }

        @Override
        public E ceiling(E element) {
            return MyTreeSetImpl.this.floor(element);
        }

        @Override
        public E higher(E element) {
            return MyTreeSetImpl.this.lower(element);
        }
    }

}
