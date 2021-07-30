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
    public int qty1;
    public int qty2;

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
        qty1 = scanner.nextInt();
        System.out.println(player.cup.displayCup());
        System.out.println("Please enter die number");
        guessingPlayerDie[0] = scanner.nextInt();
        System.out.println("Please enter quantity");
        qty2 = scanner.nextInt();
        if (qty2 > qty1) {
            System.out.println("true");
        } else if (qty1 == qty2 && guessingPlayerDie[0] > previousPlayerDie[0]) {
            System.out.println("true");
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





