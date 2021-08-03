package com.company;

import java.util.*;

public class LiarsDice {
    public List<Player> players = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private final int MIN_PLAYERS = 1;
    private final int MAX_PLAYERS = 6;
    public int[] currentBid = new int[2];
    public String wasLiar;
    public int numberOfPlayers;
    public static Map<Integer, Integer> freq = new HashMap<>();
    static int activePlayerIndex = 0;

    public LiarsDice() {
        System.out.println("How many players?");
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
        while(players.size() > 1 ){
            round();
        }
    //TODO declare winner
        System.out.println(getActivePlayer().name + " Winner");
    }

    public Player getActivePlayer(){
       return players.get(activePlayerIndex);
    }

    public void endTurn(){
        if(activePlayerIndex == (players.size() - 1)){
            activePlayerIndex = 0;
        } else {
            activePlayerIndex += 1;
        }
    }

    public void roll() {
        for (Player activePlayer : players) {
            activePlayer.cup.roll();
            createDiceMap(activePlayer.cup.dice);
        }
    }

//         if (getActivePlayer().cup.dice.size() < 1 ) {
//        players.remove(getActivePlayer());
//        endTurn();
//    }

    public void round() {
        roll();
        boolean isRoundOver = false;
        while (!isRoundOver) {

                System.out.println(getActivePlayer().name + "'s turn, press enter");
                scanner.nextLine();
                isRoundOver = callLiar(getActivePlayer());
                if(isRoundOver){
                    break;
                }
                turn(getActivePlayer());
            }
        zeroOutCurrentBid();
        clearFreq();
        }

    public void turn(Player activePlayer) {
        System.out.println(activePlayer.cup.displayCup());
        getSelections(activePlayer);
        endTurn();
    }

    public void getSelections(Player activePlayer) {
        int valueBid0 = 0;
        int qtyBid1 = 0;
        System.out.println(activePlayer.name + " select a die value.");
        valueBid0 = scanner.nextInt();  // new game set at 0
        System.out.println(activePlayer.name + " please enter quantity");
        qtyBid1 = scanner.nextInt(); // new game set at 0
        if (currentBid[0] == 0) {  // first bid or new round
            currentBid[0] = valueBid0; // WHEN YOU START GAME current bid is  = 0
            currentBid[1] = qtyBid1;// WHEN YOU START GAME current bid is  = 0
        } else {
            isValidSelection(activePlayer, qtyBid1, valueBid0); // checking if valid
        }
    }

    public void zeroOutCurrentBid() {
        currentBid[0] = 0;
        currentBid[1] = 0;
    }

    public boolean callLiar(Player activePlayer) {
        System.out.println("Take a guess - Did the previous player lie (yes/no)? ");
        wasLiar = scanner.next();
        if (wasLiar.equals("yes")) {
            System.out.println(freq);
            if (freq.get(currentBid[0]) < currentBid[1])//qty check
            {
                System.out.println("Previous Player shall lose a die because they lied.");
                getPreviousPlayer().cup.removeDie();
                return true;
            } else {
                System.out.println("Guessing Player shall lose a die because they guessed wrong.");
                activePlayer.cup.removeDie();
                return true;
            }
        }
        return false;
    }

    public Player getPreviousPlayer() {
        if (activePlayerIndex == 0) {
            return players.get(players.size() - 1);
        } else {
            return players.get(activePlayerIndex - 1);
        }
    }
//    public void endTurn(){
//        if(activePlayerIndex == (players.size() - 1)){
//            activePlayerIndex = 0;
//        } else {
//            activePlayerIndex += 1;
//        }


//        activePlayer.cup.removeDie();
//        activePlayer.cup.roll();
//        zeroOutCurrentBid();
//        isGameOver(activePlayer);
//        turn(activePlayer);


    public void isValidSelection(Player activePlayer, int currentQty, int currentValue) {
        int previousValue = currentBid[0];
        int previousQty = currentBid[1];

        if (currentQty > previousQty) {
            // below; needed to set bc if bid was accepted - the next bid need to compare to
            // that bid.
            System.out.println("valid quantity");
            currentBid[0] = currentValue;
            currentBid[1] = currentQty;

        } else if (previousQty == currentQty && currentValue > previousValue) {
            System.out.println("valid value");
            currentBid[0] = currentValue;

        } else {
            System.out.println("Try again - invalid bid!");
            getSelections(activePlayer);
        }
    }

    public void isGameOver(Player activePlayer) {
        activePlayer.cup.diceInPlay(activePlayer);
        if (activePlayer.cup.amountOfDice > 0) {
            System.out.println("******* Continue game ********");
        } else {
            System.out.println("*************** Game Over ******************");
            System.exit(0);
        }
    }

    public void createDiceMap(List<Die> dice) {
        for (Die die : dice) {
            if (!freq.containsKey(die.faceUpValue)) {
                freq.put(die.faceUpValue, 1);
            } else {
                freq.put(die.faceUpValue, freq.get(die.faceUpValue) + 1);
            }
        }
    }

    public void clearFreq() {
        freq.clear();
    }
}







