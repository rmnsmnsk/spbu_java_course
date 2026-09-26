package src.task1;


public class StringList {
    int size;
    Node head;

    StringList(){
        size = 0;
        head = null;
    }

    public void addFirst(String value){
        return;
    }

    public boolean remove(String value){
        return true;
    }

    public boolean contains(String value){
        return true;
    }

}

class Node{
    Node next;
    String value;
    Node (Node next, String value){
        this.next = next;
        this.value = value;

    }
}
