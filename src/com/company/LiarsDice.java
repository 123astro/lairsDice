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
        while (players.size() > 1) {  // game doesn't end until the players list is no longer greater than 1
            round();
        }
        System.out.println(getActivePlayer().name + " Winner");  // end of game
    }
    public void round() {
        rollAll();
        boolean isRoundOver = false; // callLair turns isRoundOver to turn if user believes previous player lied.
        boolean isTurnOne = true;  // safeguard => no prompt if liar prior to bidding. First turn of round only!
        while (!isRoundOver) { // while loop continues until lair method returns True.
           // clearScreen();
            System.out.println(getActivePlayer().name + "'s turn"); // inform who's turn it is and invoke the
            // getActivePlayer method which is the index of the player out of players list.
            if (!isTurnOne) {    // turn one is true and set to false bc the else is executed.
                isRoundOver = callLiar(getActivePlayer());
            } else {
                isTurnOne = false; // sets isTurn to false => next loop => step into   if (!isTurnOne).
            }
            if (isRoundOver) {
                break;
            }
            turn(getActivePlayer());
        }
        zeroOutCurrentBid();
        clearFreq();
        removePlayer();
    }

    public void turn(Player activePlayer) {
        System.out.println(activePlayer.cup.displayCup());
        getSelections(activePlayer);
        setNextPlayersTurn();
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

    public Player getActivePlayer() {  // returns active players index out of players list. Player is the data type.
        //System.out.println(players.get(activePlayerIndex) + " is active player from getActivePlayer method");
        return players.get(activePlayerIndex); // get players Name from index.
    }

    public void setNextPlayersTurn() {
        if (activePlayerIndex == (players.size() - 1)) { // testing if activeplayer index is equal to the size of the
            // list and - 1 for that index. This will set the new activeplayer to zero => start at the beginning again.
            activePlayerIndex = 0;
        } else {
            activePlayerIndex += 1; //activePlayer is increaent to the next player in the list.
        }
    }

    public void rollAll() {
        for (Player activePlayer : players) {
            activePlayer.cup.roll();
            diceFreqMap(activePlayer.cup.dice);
        }
        System.out.println("New Roll!!!!");
        System.out.println("There are " + numberOfDiceInPlay() + " dice in play now.");
    }

    public int numberOfDiceInPlay(){
        int num2 =0;
        for(Integer num : freq.values()){
            num2 = num + num2 ;
        }
        return num2;
    }


    public void removePlayer() {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).cup.dice.size() < 1) {
                if (i == players.size() - 1) {
                    setNextPlayersTurn();
                }
                players.remove(i);
                break;
            }
        }
    }

    public void clearScreen(){
        for(int i = 0; i < 35; i++ ){
            System.out.println("\n");
        }
    }



    public void zeroOutCurrentBid() {
        currentBid[0] = 0;
        currentBid[1] = 0;
    }

    public boolean callLiar(Player activePlayer) {
        System.out.println("Take a guess - Did the previous player lie (y/n)? ");
        wasLiar = scanner.next();
        if (wasLiar.equals("y") || wasLiar.equals("yes")) {
            System.out.println(activePlayer.name + " guessed yes that the previous player lied!");
            if (freq.get(currentBid[0]) < currentBid[1])//qty check
            {
                System.out.println("Previous Player LIED! They shall lose a die.\nBelow has the list of dice values " +
                        "and " +
                        "quantities for that round.");
                System.out.println(freq);
                getPreviousPlayer().cup.removeDie();
                return true;
            } else {
                System.out.println("Guessing Player shall lose a die because they guessed wrong. \nBelow has the " +
                        "list " +
                        "of dice values and quantities for that round.");
                System.out.println(freq);
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


    public void diceFreqMap(List<Die> dice) {
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







