package com.javarush.task.task21.task2113;

import java.util.*;

public class Hippodrome {
    private List<Horse> horses;
    static  Hippodrome game;

    public static void main(String[] args) {
        List<Horse> horses1 = new ArrayList<>();
        horses1.add(new Horse("Baiden", 3,0));
        horses1.add(new Horse("Obama", 3,0));
        horses1.add(new Horse("Bush", 3,0));
        game = new Hippodrome(horses1);
        game.run();
        game.printWinner();
    }

    Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }
    
    public List<Horse> getHorses() {
        return this.horses;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            move();
            print();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void move() {
        for (Horse hors : horses) {
            hors.move();
        }
    }

    public void print() {
        for (Horse hors : horses) {
            hors.print();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }

    public Horse getWinner() {
        int biggestDistance = 0;
        Horse winner = null;
        for (Horse hors : horses) {
            if (hors.getDistance() > biggestDistance) {
                biggestDistance = (int) hors.getDistance();
                winner = hors;
            }

        }
        return winner;
    }

    public void printWinner() {
        String win = getWinner().getName();
        System.out.println("Winner is " + win + "!");
    }
}