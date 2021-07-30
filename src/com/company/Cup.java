package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cup {
   // Map<Integer, Integer> dice = new HashMap<>();
   List<Die> dice = new ArrayList<>();
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
        return output.trim();
    }

    public List<Integer> parseDiceToReroll(String input){
        String[] inputArr = input.split(" ");
        List<Integer> selections = new ArrayList<>();
        for (String number : inputArr){
            selections.add(Integer.parseInt(number) - 1);
        }
        return selections.contains(-1) ? new ArrayList<Integer>() : selections;
    }


}


