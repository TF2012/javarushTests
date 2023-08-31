package com.javarush.task.task12.task1227;

/* 
CanFly, CanRun, CanSwim для классов Duck, Penguin, Toad
*/

public class Solution {
    public static void main(String[] args) {

    }

    public interface CanFly {
        public void fly();
    }

    public interface CanRun {
        public void run();
    }

    public interface CanSwim {
        public void swim();
    }

    abstract public class Duck implements CanSwim, CanRun, CanFly {

    }

    abstract public class Penguin implements CanSwim, CanRun {

    }

    abstract public class Toad implements CanSwim {

    }
}
