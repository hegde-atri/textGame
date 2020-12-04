package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;


/*
This project is highly undeveloped and not finished, many little details like, exploration is totally random and you cannot end in the same place through retracing steps.
Surroundings have no impact on the actual game, surroundings aren't real, you aren't gonna find a river in front of you nor is it gonna affect the game
I have referred the enemies in the game as mobs which you will see frequently in variable names and comments.
*/

public class Main {


    public static String sceneryDirectory;
    int[] stats = new int[5];
    public static String[] inventory = new String[6];
    public static int hp = 100;
    public static int mob;
    public static int mobHp;
    public static final Scanner input = new Scanner(System.in);
    static Random randint = new Random();
    public static boolean isPlayeralive = true;


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

    //Intro to the game where basic rules are told and what to do in the game!
    public static void intro(){
        System.out.println("Welcome to this game where you use inputs to move around and search the location. \nIf there is a mob in the location you fight it and it can drop loot, loot varies with the difficulty of the mob \nType in r for rules and y for inputs (case sensitive)");
    }

    //This displays the list of rules of the game
    public static void rules(){
        System.out.println("_______________________________________________________________________________________________________________________");
        System.out.println("To start you will be given a sword and shield");
        System.out.println("Fighting mobs will earn XP");
        System.out.println("Fighting mob Bosses that appear randomly can give weapon drops at the end of the battle");
        System.out.println("Your shield can never break but cannot be used against heavy attacks");
        System.out.println("You have an inventory that has 5 weapon slots and a shield slot");
        System.out.println("When not in combat you can find people to fight by moving around the map by typing in forward, backward, right and left\nif" +
                " there is a mob present, you will be told its name and will be given an option to engage or ignore");
        System.out.println("Continue using using movement commands to ignore and type in f to engage");
        System.out.println("A heavy attack can be used only when it has not been used for 2 turns, and does 10 extra damage");
        System.out.println("Type in i to display inventory");
        System.out.println("Type in r to display this page again");
        System.out.println("`````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````");


    }

    //This displays the letters they need to enter to perform actions in the game
    public static void keyBinds(){
        System.out.println("--------------------------");
        System.out.println("       Inputs page       ");
        System.out.println("  r = rules              ");
        System.out.println("  i = inventory          ");
        System.out.println("  w = forward            ");
        System.out.println("  s = backward           ");
        System.out.println("  a = left               ");
        System.out.println("  d = right              ");
        System.out.println("  f = fight enemy        ");
        System.out.println("  l = light attack       ");
        System.out.println("  h = heavy attack       ");
//        System.out.println("  j = dodge              ");
//        System.out.println("  k = shield             ");
        System.out.println("  z = search Location    ");
        System.out.println("  r = store item         ");
        System.out.println("1-6 = drop item from     ");
        System.out.println("      inventory          ");
        System.out.println("  y = input page (current)");
        System.out.println("  q = quit game          ");
        System.out.println("--------------------------");


    }

    //will perform actions for inputs like fight, sword, block, dodge, heavy attack
    public static void userInput(){
        char key = input.nextLine().charAt(0);
        switch(key){
            case('r'):
                rules();
                break;
            case('i'):
                displayInventory();
                break;
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
            case('f'):
                engageEnemy();
                break;
            case('z'):
                searchLocation();
                break;
            case('y'):
                keyBinds();
                break;
            case('q'):
                isPlayeralive = false;
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

    //Prints out scenery description as a randomline from scenery.txt
    public static void sceneryDescription() {
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
        for(int i=0; i<6; i++){
            if(inventory[i]==null){
                System.out.print("(empty) ");
            }else {
                System.out.print(inventory[i] + " ");
            }
        }
    }

    //This searches the 'direction' the user has chosen to travel in, rolls a dice and has a 33% chance of finding a mob
    public static void searchLocation(){
        Mobs mobs = new Mobs();
        if(mobs.mobFound()==1){
            mob = mobs.mobSelector();
            setMobHp();
            System.out.println("Mob found in the area, type f to fight it");

        }else{
            System.out.println("No mobs were found!");
        }
    }

    //Set the mobHp based on the level of the mob
    public static void setMobHp(){
        if(mob==1){
            mobHp = 100;
        }else if(mob==2){
            mobHp = 150;
        }else if(mob==3){
            mobHp = 200;
        }
    }

    //returns the value of damage dealt which is dealt towards the player
    public static int mobDamage(){

        int multiplier = randint.nextInt(4)+1;   //This is multiplied with the baseDamage for total damage dealt
        int baseDamage;                             //This changes according to the total health of the mob

        if(150<mobHp && mobHp<201){
            baseDamage = 5;
        }else if(100<mobHp && mobHp<151){
            baseDamage = 4;
        }else if(50<mobHp && mobHp<101){
            baseDamage = 3;
        }else{
            baseDamage = 0;
        }

        int damageDealt = multiplier*baseDamage;
        return damageDealt;
    }

    //returns the value of damage dealt to the mob
    public static int playerDamage(){

        int multiplier = randint.nextInt(5)+1;  //This is multiplied with the baseDamage for total damage dealt
        int baseDamage;                            //This changes according to the total health of the mob

        if(150<hp && hp<201){
            baseDamage = 8;
        }else if(100<hp && hp<151){
            baseDamage = 7;
        }else if(50<hp && hp<101){
            baseDamage = 6;
        }else{
            baseDamage = 0;
        }

        int damageDealt = multiplier*baseDamage;
        return damageDealt;

    }

    //This method checks if the mobs health is more less than 1, if it is less than one, it returns false, else it returns true
    public static boolean isMobAlive(){
        if(mobHp<1){
            return false;
        }else{
            return true;
        }
    }

    //This method checks if the players health is less than 1, if it is less than 1 it returns false, else it returns true
    public static boolean isPlayerAlive(){
        if(hp<1){
            return false;
        }else{
            return true;
        }
    }

    //Engages battle mode where hp of you and the monster will display every now and then
    public static void engageEnemy(){
        try {
            boolean enemyAlive = true;
            while(enemyAlive) {
                int a;
                System.out.print("Your turn to attack- ");
                char attackKey = input.nextLine().charAt(0);
                if(attackKey == 'l'){
                    a = playerDamage();

                    TimeUnit.SECONDS.sleep(1);
                }else if(attackKey == 'h'){
                    playerDamage();
                    mobHp -=10;
                    TimeUnit.SECONDS.sleep(1);
                }else if(attackKey == 'j'){
                    System.out.println("dodging next attack...");
                    TimeUnit.SECONDS.sleep(1);
                }else{
                    System.out.println("Invalid input!");
                }
                System.out.println("Enemy is now attacking!");

            }




        }catch(Exception e){
            System.out.println("Error: " + e);
        }
    }

    //Issues I'm aware and working to find a solution for
    public static void printBugs(){
        System.out.println("Known bug: fight progress resets upon Invalid Input");
        System.out.println("Release 0.1");
        System.out.println("Atri Hegde ©");
    }

    //Provides the loop where all my methods will be called in
    public static void runGame(){
        intro();
        while(isPlayeralive == true){
            userInput();
            if(hp<1) isPlayeralive = false;
        }

        System.out.println("GGWP");


    }

    public static void main(String[] args) throws IOException {
//        setSceneryDirectory();
//        for(int i =0; i<50; i++){
//            searchLocation();
//            setMobHp();
//            System.out.println(mobDamage());
//        }
        runGame();






    }
}
