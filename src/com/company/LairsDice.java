package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LairsDice {
    public List<Player> players = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private final int MIN_PLAYERS = 1;
    private final int MAX_PLAYERS = 6;
    public int[] previousPlayerDieValue = new int[1];
    public int[] guessingPlayerDieValue = new int[1];
    public int[] previousPlayerDieQty = new int[1];
    public int[] guessingPlayerDieQty = new int[1];
    //public int[] quantity = new int[MAX_PLAYERS];
    public String wasLiar;

    public LairsDice() {
        System.out.println("How many players?");
        int numberOfPlayers;

        do {
            numberOfPlayers = scanner.nextInt();
            scanner.nextLine();
        } while (numberOfPlayers < MIN_PLAYERS || numberOfPlayers > MAX_PLAYERS);
        // ADD PLAYER TO LIST WITH NAME
        while (players.size() < numberOfPlayers) {
            System.out.println("What is your name?");
            players.add(new Player((scanner.nextLine().trim())));
        }
    }

    public void play() {
        for (Player player : players) {
            turn(player);

        }
    }

    public void display(Player player) {
        System.out.println(player.cup.displayCup());
    }


    public void round() {
    }

    public void turn(Player player) {
        System.out.println(player.name + "'s turn, press enter");
        scanner.nextLine();
        player.cup.roll();
        System.out.println(player.cup.displayCup());
        getSelections(player);
    }


    public void getSelections(Player player) {
        System.out.println("Select die for your bid.");
        previousPlayerDieValue[0] = scanner.nextInt();
        System.out.println("Please enter quantity");
        previousPlayerDieQty[0] = scanner.nextInt();
        System.out.println(player.cup.displayCup());
        callLair(player);
        System.out.println(player.cup.displayCup());
        System.out.println("Please enter die number for your bid");
        guessingPlayerDieValue[0] = scanner.nextInt();
        System.out.println("Please enter quantity");
        guessingPlayerDieQty[0] = scanner.nextInt();
        checkBid();
    }


    public void callLair(Player player) {
        System.out.println("Take a guess - Did the previous player lie (yes/no)? ");
        wasLiar = scanner.next();
        player.cup.clearFreq();
        player.cup.createDiceMap();
        //System.out.println("Only " + player.cup.freq.get(previousPlayerDie[0]) + " die/dice in play");
        if (player.cup.freq.get(previousPlayerDieValue[0]) < previousPlayerDieQty[0]) {
            System.out.println("Previous Player shall lose a die because they lied.");
        } else {
            System.out.println("Guessing Player shall lose a die because they guessed wrong.");
        }

        if (player.cup.dice.size() > 0) {
            System.out.println("******* Continue game ********");
        } else {
            System.out.println("game over");
        }
        player.cup.removeDie();
        player.cup.roll();


    }

    public void checkBid(){
        if (guessingPlayerDieQty[0] > previousPlayerDieQty[0]) {
            System.out.println("true1");
        } else if (previousPlayerDieQty[0] == guessingPlayerDieQty[0] && guessingPlayerDieValue[0] > previousPlayerDieValue[0]) {
            System.out.println("true2");
        } else {
            System.out.println("false");
        }


    }
}


//
//        public void round(){
//            for (Player activePlayer : players) {
//                turn(activePlayer);
//        }
//
//        public void turn(Player activePlayer){
//
//            }
//
//    public void getSelections (Player activePlayer){
//        System.out.println("Select dice you want to re-roll (1-5) ?");
//        String input = scanner.nextLine(); // "1 2 5"
//        if (input.equals("")) return;
//        activePlayer.cup.roll(activePlayer.cup.parseDiceToReroll(input));
//
//    }
//}





