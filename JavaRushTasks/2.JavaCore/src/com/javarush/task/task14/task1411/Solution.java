package com.javarush.task.task14.task1411;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

/* 
User, Loser, Coder and Proger
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Person person = null;
        String key = null;
        //тут цикл по чтению ключей, пункт 1
        while (reader.ready()){
            key = reader.readLine();
            switch (key) {
                case "user":
                    person = new Person.User();
                    doWork(person);
                    break;
                case  "loser":
                    person = new Person.Loser();
                    doWork(person);
                    break;
                case "coder":
                    person = new Person.Coder();
                    doWork(person);
                    break;
                case "proger":
                    person = new Person.Proger();
                    doWork(person);
                    break;
                default: return;
            }
        }
        reader.close();
    }

    public static void doWork(Person person) {
        // пункт 3
        if (person instanceof Person.User) ((Person.User) person).live();
        else if (person instanceof Person.Loser) ((Person.Loser) person).doNothing();
        else if (person instanceof Person.Coder) ((Person.Coder) person).writeCode();
        else if (person instanceof Person.Proger) ((Person.Proger) person).enjoy();
        else return;
        }
    }