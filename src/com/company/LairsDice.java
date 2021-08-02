package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LairsDice {
    public List<Player> players = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private final int MIN_PLAYERS = 1;
    private final int MAX_PLAYERS = 6;
    public int[] currentBid = new int[2];
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
            System.out.println(player.name + "'s turn, press enter");
            scanner.nextLine();
            player.cup.roll();
            getSelections(player);
        }
    }

    public void turn(Player player) {
        getSelections(player);
    }


    public void getSelections(Player player) {
        int valueBid0 = 0;
        int qtyBid1 = 0;
        System.out.println(player.name + " select a die value.");
        valueBid0 = scanner.nextInt();
        System.out.println(player.name + " please enter quantity");
        qtyBid1 = scanner.nextInt();
        if (currentBid[0] == 0) {  // first bid or new round
            currentBid[0] = valueBid0; // WHEN YOU START GAME = 0
            currentBid[1] = qtyBid1; // WHEN YOU START GAME = 0
            callLair(player);
        } else {
            isValidSelection(player, qtyBid1, valueBid0); // checking if valid
            callLair(player);
        }
        System.out.println(player.cup.displayCup());
    }


    public void callLair(Player player) {
        System.out.println("Take a guess - Did the previous player lie (yes/no)? ");
        wasLiar = scanner.next();
        if (wasLiar.equals("yes")) {
            player.cup.clearFreq();
            player.cup.createDiceMap();
            System.out.println(player.cup.freq);
            if (player.cup.freq.get(currentBid[0]) < currentBid[1]) //qty check
            {
                System.out.println("Previous Player shall lose a die because they lied.");
                player.cup.removeDie();
                player.cup.roll();
                currentBid[0] = 0;
                currentBid[1] = 0;
                isGameOver(player);
                turn(player);
            } else {
                System.out.println("Guessing Player shall lose a die because they guessed wrong.");
                player.cup.removeDie();
                player.cup.roll();
                currentBid[0] = 0;
                currentBid[1] = 0;
                isGameOver(player);
                turn(player);
            }


        } else if (wasLiar.equals("no")) {
            System.out.println(player.cup.displayCup());
            getSelections(player);

        } else {
            System.out.println("Please enter a valid case (yes/no)");
            callLair(player);
        }
        play();
    }

    public void isValidSelection(Player player, int currentQty, int currentValue) {
        int previousValue = currentBid[0];
        int previousQty = currentBid[1];
        if (currentQty > previousQty) {
            System.out.println("valid q");
            callLair(player);

        } else if (previousQty == currentQty && currentValue > previousValue) {
            System.out.println("valid V");
            callLair(player);

        } else {
            System.out.println("Try a valid bid");
            getSelections(player);
        }
    }

    public boolean isGameOver(Player player){
        player.cup.diceInPlay(player);
        if (player.cup.amountOfDice > 0) {
            System.out.println("******* Continue game ********");
        } else {
            System.out.println("game over");
            return true;
        }
        return false;
    }
}







