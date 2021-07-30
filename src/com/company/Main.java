package com.company;

import com.sun.source.tree.NewArrayTree;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
//       Player player = new Player("Keith");
//       player.cup.roll();
//       System.out.println(player.cup.displayCup());
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Please enter die number");
//        int die1 = scanner.nextInt();
//        System.out.println("Please enter quantity");
//        int qty1 = scanner.nextInt();
//        System.out.println(player.cup.displayCup());
//        System.out.println("Please enter die number");
//        int die2 = scanner.nextInt();
//        System.out.println("Please enter quantity");
//        int qty2 = scanner.nextInt();
//        if(qty2 > qty1){
//            System.out.println("true");
//        } else if( qty1 == qty2  && die2 > die1){
//            System.out.println("true");
//        } else {
//            System.out.println("false");
//        }
//
//    }
//    public static void callLair(){
//
//    }
        LairsDice game = new LairsDice();
        game.play();

    }
}