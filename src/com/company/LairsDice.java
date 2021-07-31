package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LairsDice {
    public List<Player> players = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private final int MIN_PLAYERS = 1;
    private final int MAX_PLAYERS = 6;
    public int[] previousPlayerDie = new int[1];
    public int[] guessingPlayerDie = new int[1];
    public int[] quantity = new int[MAX_PLAYERS];
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
        previousPlayerDie[0] = scanner.nextInt();
        System.out.println("Please enter quantity");
        quantity[0] = scanner.nextInt();
        System.out.println(player.cup.displayCup());
        System.out.println("Take a guess - Did the previous player lie (yes/no)? ");
        wasLiar = scanner.next();
        if (wasLiar.equals("yes")) {
            callLair1(player);
        }
        System.out.println(player.cup.displayCup());
        System.out.println("Please enter die number for your bid");
        guessingPlayerDie[0] = scanner.nextInt();
        System.out.println("Please enter quantity");
        quantity[1] = scanner.nextInt();
        checkBid();


    }

    public void callLair1(Player player) {
        player.cup.clearFreq();
        player.cup.createDiceMap();
        //System.out.println("Only " + player.cup.freq.get(previousPlayerDie[0]) + " die/dice in play");
        if (player.cup.freq.get(previousPlayerDie[0]) < this.quantity[0]) {
            System.out.println("Previous Player shall lose a die.");
        } else {
            System.out.println("Guessing Player shall lose a die.");
        }

        if (player.cup.dice.size() > 0) {
            System.out.println("******* Continue game ********");
        } else {
            System.out.println("game over");
        }
        player.cup.removeDie();
        player.cup.clearFreq();
        player.cup.roll();
        //turn(player);

    }

    public void checkBid(){
        if (quantity[1] > quantity[0]) {
            System.out.println("true1");
        } else if (quantity[0] == quantity[1] && guessingPlayerDie[0] > previousPlayerDie[0]) {
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





