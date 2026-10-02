package src.task1;

import java.util.Iterator;
import java.util.Objects;

public class StringList implements Iterator {
    private int size;
    private Node head;

    private Node position = null;

    @Override
    public boolean hasNext() {
        return position != null;
    }

    @Override
    public String next() {
        String element = position.value;

        position = position.next;

        return element;
    }

    private static class Node {
        private final String value;
        private Node next;

        private Node(String value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    public StringList() {
        size = 0;
        head = null;
    }

    public void addFirst(String value) {
        head = new Node(value, head);
        position = head;
        size++;
    }

    public boolean remove(String value) {
        if (head == null) {
            return false;
        }

        if (Objects.equals(head.value, value)) {
            head = head.next;
            size--;
            return true;
        }

        Node current = head;

        while (current.next != null) {
            if (Objects.equals(current.next.value, value)) {
                current.next = current.next.next;
                size--;
                return true;
            }

            current = current.next;
        }

        return false;
    }

    public boolean contains(String value) {
        Node current = head;

        while (current != null) {
            if (Objects.equals(current.value, value)) {
                return true;
            }

            current = current.next;
        }

        return false;
    }

    public int size() {
        return size;
    }

}