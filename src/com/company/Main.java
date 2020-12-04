package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


/*
This project is highly undeveloped and not finished, many little details like, exploration is totally random and you cannot end in the same place through retracing steps.
Surroundings have no impact on the actual game, surroundings aren't real, you aren't gonna find a river in front of you nor is it gonna affect the game
*/

public class Main {


    public static String sceneryDirectory;
    int[] stats = new int[5];
    int[] inventory = new int[6];
    public static int hp = 100;
    public static int mobHp;
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
        System.out.println("  w = forward            ");
        System.out.println("  s = backward           ");
        System.out.println("  a = left               ");
        System.out.println("  d = right              ");
        System.out.println("  l = light attack       ");
        System.out.println("  h = heavy attack       ");
        System.out.println("  j = dodge              ");
        System.out.println("  k = shield             ");
        System.out.println("  z = search Location    ");
        System.out.println("  q = quit game          ");

    }


    //will perform actions for inputs like fight, sword, block, dodge, heavy attack
    public static void userInput() throws IOException {
        char key = input.nextLine().charAt(0);
        switch(key){
            case('r'):
                rules();
            case('i'):
                displayInventory();
            case('w'):
                sceneryDescription();
                break;
            case('a'):
                sceneryDescription();
                break;
            case('s'):
                sceneryDescription();
                break;
            case('d'):
                sceneryDescription();
                break;
            default:
                System.out.println("Invalid Input!!");
                break;
        }



    }

    //This method sets the directory of the scenery.txt that's used in the next method.
    public static void setSceneryDirectory(){
        sceneryDirectory = System.getProperty("user.dir") + "\\scenery.txt";
    }


    public static void sceneryDescription() throws IOException {
        // uses the getRandomLine method fromthe RandomLine Class
        RandomLine line = new RandomLine();
        boolean found = false;
        while(found == false){
            String sceneryStr = line.getRandomLine();
            if(sceneryStr==(null)){
                found = false;
                //System.out.println("repeating if statement"); This control statement allows me to see if this method throws a NullPointerException, because i cannot use .equals(nulls) as the parameter for the if statement
            }else{
                System.out.println(sceneryStr);
                found = true;
            }
        }
    }

    //Displays the items in the Users Inventory
    public static void displayInventory(){

    }


    //This searches the 'direction' the user has chosen to travel in, rolls a dice and has a 33% chance of finding a mob
    public static void searchLocation(){
        Mobs mobs = new Mobs();
        if(mobs.mobFound()==1){
            int mob = mobs.mobSelector();
            fight(mob);
        }
    }

    public static void setMobHp(int mob){
        if(mob==1){
            mobHp = 100;
        }else if(mob==2){
            mobHp = 150;
        }else if(mob==3){
            mobHp = 200;
        }else{
            mobHp = 100;
        }

    }

    //Engages battle mode where hp of you and the monster will display every now and then
    public static void fight(int mob){
        if(mob==1){
            mobHp = 100;
            boolean mobLife = true;
            while(mobLife==true){

            }

        }else if(mob==2){
            mobHp = 150;
        }else if(mob==3){
            mobHp = 200;
        }

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


    public static void main(String[] args) throws IOException {
        setSceneryDirectory();
//        System.out.println(mobs.mobSelector());
       for(int i =0; i<50; i++){
           sceneryDescription();
       }
//        while(true){
//            userInput();
//        }





    }
}
