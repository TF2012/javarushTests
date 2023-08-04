package com.javarush.task.pro.task13.task1306;

import java.util.ArrayList;
import java.util.Collections;

/* 
Изучаем методы класса Collections, часть 1
*/

public class Solution {
    public static void main(String[] args) {
        ArrayList<String> arrai1 = new ArrayList<>();
        ArrayList<String> arrai2 = new ArrayList<>();
        String[] array3 = new String[4];
        String st1 = "8 9 10 11";
        String st2 = "10 11 12 8";
        arrai1.add("1");
        arrai1.add("2");
        arrai1.add("3");
        arrai1.add("4");
        arrai2.add("5");
        arrai2.add("6");
        arrai2.add("7");
        arrai2.add("8");

        copy(arrai1,arrai2);
        addAll(arrai1, array3);
        replaceAll(arrai1, st1, st2);

    }

    public static void copy(ArrayList<String> destination, ArrayList<String> source) {
        Collections.copy(destination, source);
        System.out.println(destination);
    }

    public static void addAll(ArrayList<String> list, String... strings) {
        Collections.addAll(list,strings);
        System.out.println(list);
    }

    public static void replaceAll(ArrayList<String> list, String oldValue, String newValue) {
        Collections.replaceAll(list, oldValue, newValue);
        System.out.println(list);
    }
}
