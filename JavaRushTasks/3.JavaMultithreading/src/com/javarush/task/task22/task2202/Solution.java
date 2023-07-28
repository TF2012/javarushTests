package com.javarush.task.task22.task2202;

/* 
Найти подстроку
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
    }

    public static String getPartOfString(String string) throws TooShortStringException{
        String[] str = string.split(" ");
        int count = 0;
        if (string.contains(" ")) {
            count++;
        }
        if ((string != null) || (count > 4)) {
            return (str[1] + " " + str[2] + " " + str[3] + " " + str[4]);
        } else {
            throw new TooShortStringException();
        }
    }

    public static class TooShortStringException extends RuntimeException {
    }
}
