package src.task1;

import java.util.ArrayList;

public class StringSet {
    private int capacity = 8;
    private ArrayList<StringList> array = new ArrayList<>(capacity);
    private int size = 0;


    public StringSet() {
        init(this.array);
    }


    private void init(ArrayList<StringList> array) {
        for (int i = 0; i < capacity; i++) {
            array.add(new StringList());
        }
        size = 0;
    }

    public void put(String element) {
        if (this.contains(element)) {
            return;
        }

        double load = ((double) size) / capacity;
        
        if (load > 0.75) {
            resize();
        }
        
        int index = getIndex(element);

        array.get(index).addFirst(element);
        size++;
    }

    //    возвращает true, если операция завершилась успешно
    public boolean remove(String element) {
        int index = getIndex(element);

        if (array.get(index).remove(element)) {
            size--;
            return true;
        }
        
        return false;
    }

    
    public boolean contains(String element) {
        int index = getIndex(element);
        
        return array.get(index).contains(element);
    }
    
    public int getSize() {
        return size;
    }
    
    
    public void clear() {
        capacity = 8;
        array = new ArrayList<>(capacity);
        init(array);
    }
    
    private int getIndex(String element) {
        return Math.abs(element.hashCode()) % capacity;
    }
    
    
    private void resize() {
        System.out.println("resize");
        capacity *= 2;
        ArrayList<StringList> newArray = new ArrayList<StringList>(capacity);
        init(newArray);

        for (int i = 0; i < array.size(); i++) {
            StringList stringList = array.get(i);

            while (stringList.hasNext()) {
                String value = stringList.next();
                int newIndex = getIndex(value);

                newArray.get(newIndex).addFirst(value);
                size++;
            }
        }

        array = newArray;
    }
}
