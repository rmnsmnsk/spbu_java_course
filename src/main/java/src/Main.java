package src;


import src.task1.StringSet;

import java.awt.*;

public class Main {
    static void main() {
        StringSet stringSet = new StringSet();

        stringSet.put("1");
        stringSet.put("2");
        System.out.println("size: " + stringSet.getSize());
        stringSet.put("3");
        stringSet.put("4");
        stringSet.put("5");
        stringSet.put("6");
        stringSet.put("10");
        System.out.println("size: " + stringSet.getSize());


        System.out.println("7: " + stringSet.contains("7"));
        System.out.println("1: " + stringSet.contains("1"));

        System.out.println("remove 1: " + stringSet.remove("1"));
        System.out.println("remove 7: " + stringSet.remove("7"));
        System.out.println("1: " + stringSet.contains("1"));
        System.out.println("size: " + stringSet.getSize());

    }
}
