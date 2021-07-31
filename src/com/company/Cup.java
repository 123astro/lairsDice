package com.company;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class Cup {

    List<Die> dice = new ArrayList<>();
    public Map<Integer, Integer> freq = new HashMap<>();

    public int numberOfDice = 5;


    public Cup() {
        while (dice.size() < numberOfDice) {  //you need size of dice
            dice.add(new Die());      // remember .add

        }
    }

    public void removeDie() {
        dice.remove(0);
    }

    public void roll() {
        for (Die die : dice) {
            die.roll();
        }
    }

    public String displayCup() {  // display used for end user
        String output = ""; // assign output a blank string
        for (Die die : dice) {
            output += die.faceUpValue + " ";
        }
       // checkDiceArray();
        return output.trim();

    }

    public List<Integer> parseDiceToReroll(String input) {
        String[] inputArr = input.split(" ");
        List<Integer> selections = new ArrayList<>();
        for (String number : inputArr) {
            selections.add(Integer.parseInt(number) - 1);
        }
        return selections.contains(-1) ? new ArrayList<Integer>() : selections;
    }

    public void checkDiceArray() {
       // Map<Integer, Integer> freq = new HashMap<>();
        for (Die die : dice) {
            if (!freq.containsKey(die.faceUpValue)) {
                freq.put(die.faceUpValue, 1);
            } else {
                freq.put(die.faceUpValue, freq.get(die.faceUpValue) + 1);
            }
        }
       System.out.println("worked");
    }

}


