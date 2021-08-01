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
        System.out.println("New Roll!!!!");
        for (Die die : dice) {
            die.roll();
        }
        createDiceMap();
        printNumberOfDiceLeft();
    }

    public void clearFreq() {
        freq.clear();
    }

    public void printNumberOfDiceLeft() {
        int count = 0 ;
        for(int i = 0; i < dice.size(); i++){
            count++;
        }
        System.out.println(count + " die in play.");
    }

    public String displayCup() {  // display used for end user
        String output = ""; // assign output a blank string
        for (Die die : dice) {
            output += die.faceUpValue + " ";
        }
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

    public void createDiceMap() {
        for (Die die : dice) {
            if (!freq.containsKey(die.faceUpValue)) {
                freq.put(die.faceUpValue, 1);
            } else {
                freq.put(die.faceUpValue, freq.get(die.faceUpValue) + 1);
            }
        }
    }

}


