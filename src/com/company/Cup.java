package com.company;


import java.util.*;

public class Cup {
    public int amountOfDice;

    public List<Die> dice = new ArrayList<>();
    //public static Map<Integer, Integer> freq = new HashMap<>();
    public int numberOfDice = 3;


    public Cup() {
        while (dice.size() < numberOfDice) {  //you need size of dice
            dice.add(new Die());      // remember .add

        }
    }

    public void diceInPlay(Player activePlayer) {
        this.amountOfDice = dice.size();
    }

    public void removeDie() {
        dice.remove(0);
    }

    public void roll() {

        for (Die die : dice) {
            die.roll();
        }
        //printNumberOfDiceLeft();
    }

    public void printNumberOfDiceLeft() {
        int count = 0;
        for (int i = 0; i < dice.size(); i++) {
            count++;
        }
        System.out.println(count + " die in play.");

    }

//    public void clearFreq() {
//        freq.clear();
//    }


    public String displayCup() {  // display used for end user
        String output = ""; // assign output a blank string
        for (Die die : dice) {
            output += die.faceUpValue + " ";
        }
        return output.trim();
    }

}


