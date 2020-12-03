package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/*
This project is highly undeveloped and not finished, many little details like, exploration is totally random and you cannot end in the same place through retracing steps.
Surroundings have no impact on the actual game, surroundings aren't real, you aren't gonna find a river in front of you nor is it gonna affect the game
*/

public class Main {

    public static String scenaryDirectory;
    int[] stats = new int[5];
    int[] inventory = new int[6];
    int hp = 100;
    public static final Scanner input = new Scanner(System.in);

    //Experimental and not in use at the moment. Creates a static window with basic attributes
    private static void createWindow() {
        JFrame frame = new JFrame("Atri's Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel textLabel = new JLabel("Still Testing!", SwingConstants.CENTER);
        textLabel.setPreferredSize(new Dimension(500, 250));
        frame.getContentPane().add(textLabel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

    //This displays the list of rules of the game
    public static void rules(){
        System.out.println("To start you will be given a sword and shield");
        System.out.println("Fighting mobs will earn XP");
        System.out.println("Fighting mob Bosses that appear randomly can give weapon drops at the end of the battle");
        System.out.println("Your shield can never break but cannot be used against heavy attacks");
        System.out.println("You have an inventory that has 5 weapon slots and a shield slot");
        System.out.println("When not in combat you can find people to fight by moving around the map by typing in forward, backward, right and left\nif" +
                " there is a mob present, you will be told its name and will be given an option to engage or ignore");
        System.out.println("Continue using using movement commands to ignore and type in fight to engage");
        System.out.println("Type in i to display inventory");
        System.out.println("Type in r to display this page again");


    }


    //This displays the letters they need to enter to perform actions in the game
    public static void keyBinds(){
        System.out.println("-------------------------");
        System.out.println("       Keybinds page     ");
        System.out.println("  r = rules              ");
        System.out.println("  i = inventory          ");
        System.out.println("  l = light attack       ");
        System.out.println("  h = heavy attack       ");
        System.out.println("  d = dodge              ");
        System.out.println("  s = shield             ");
        System.out.println();

    }


    //will perform actions for inputs like fight, sword, block, dodge, heavy attack
    public static void fightMode(){
        char key = input.nextLine().charAt(0);
        switch(key){
            case('r'):
                rules();
            case('i'):
                displayInventory();
            case('l'):
            case('h'):
            case('d'):
            case('s'):
            default:
                System.out.println("Invalid Input!!");
                break;
        }



    }

    //This method sets the directory of the scenary.txt thats used in the next method.
    public static void setScenaryDirectory(){
        scenaryDirectory = System.getProperty("user.dir") + "\\scenary.txt";
    }



    //Moves the user to different locations and provides a brief description of his surroundings
    public static void move(String direction){
        //The string array has random description of surrounding that are chosen at random to show to the user
//        String[] surroundings = {"You stand in a flat region dotted with huge multicolored stones. It's also spotted with trees, grass, and foul-smelling wildflowers.\nYou can see a huge river close by and a citadel to the south.\nThe temperature is quite cool and the sky is overcast."
//                , "You stand in a mountainous region strewn with small shrubs.\nYou can see a lake nearby.\nThe temperature is very warm and the sky is mostly clear."
//                , "You stand in a hilly region sprinkled with smooth multicolored stones.\nYou can see a bog nearby.\nThe temperature is hot and the sky is overcast."
//                , "You stand in a broken terrain smattered with unusual grass and various wildflowers.\nYou can see a stream in the distance.\nThe temperature is a little cool and the sky is partially cloudy."
//                , "You stand in a hilly terrain covered with huge purplish stones.\nYou can see a village nearby.\nThe temperature is hot and the sky is overcast."
//                , "You stand in a mountainous region sprinkled with foul-smelling trees, dead shrubs, and grass.\nYou can see a small body of water to the south and a cave to the north.\nThe temperature is quite cool and the sky is overcast."
//                , "You stand in a mountainous terrain strewn with gigantic bluish rocks.  You can see a bog miles away and a monastery nearby.  The temperature is very warm and the sky is overcast."
//                , "You stand in a mountainous region spotted with grass and wildflowers.  You can see a large lake miles away.  The temperature is somewhat warm and the sky is partially cloudy."
//                , "You stand in a flat area sprinkled with huge brown stones.  You can see a tower to the north.  The temperature is warm and the sky is partially cloudy."
//                , "You stand in a mountainous terrain replete with small shrubs.  You can see a small body of water close by and a citadel to the east.  The temperature is quite cool and the sky is partially cloudy."};

    }

    //Displays the items in the Users Inventory
    public static void displayInventory(){

    }


    //This searches the 'direction' the user has chosen to travel in, rolls a dice and has a 50% chance of spotting a mob
    public static void searchLocation(){

    }


    //Provides the loop where all my methods will be called in
    public static void runGame(){

    }

    //Issues I'm aware and working to find a solution for
    public static void printBugs(){
        System.out.println("Known bug: fight progress resets upon Invalid Input");
        System.out.println("Release 0.1");
        System.out.println("Atri Hegde ©");
    }


    public static void main(String[] args) {

        Mobs b = new Mobs();
        keyBinds();



    }
}
