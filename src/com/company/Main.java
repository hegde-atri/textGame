package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;


/*
This project is highly undeveloped and not finished, many little details like, exploration is totally random and you cannot end in the same place through retracing steps.
Surroundings have no impact on the actual game, surroundings aren't real, you aren't gonna find a river in front of you nor is it gonna affect the game
I have referred the enemies in the game as mobs which you will see frequently in variable names and comments.
*/

public class Main {


    public static String sceneryDirectory, droppedWeapon;
    public static String[][] inventory = new String[2][6];
    public static int hp = 100;
    public static int mob, mobHp;
    public static final Scanner input = new Scanner(System.in);
    static Random randint = new Random();
    public static boolean playerLife = true;


    public static String[][] weapons =
            {
                    {"| Wooden Sword |", "| Stone Sword |", "| Steel Sword |", "| Forged Sword |", "| Titanium Sword |", "| Excalibur |"},
                    {"|       3      |", "|      6      |", "|     10      |", "|      12      |", "|       18       |", "|    24     |"}
            };





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
        System.out.println("To start you will be given a wooden sword");
        System.out.println("Fighting mobs will earn XP");
        System.out.println("Fighting mob Bosses that appear randomly can give weapon drops at the end of the battle");
        System.out.println("Your shield can never break but cannot be used against heavy attacks");
        System.out.println("You have an inventory that has 5 weapon slots and a shield slot");
        System.out.println("When not in combat you can find people to fight by moving around the map by typing in forward, backward, right and left\nif" +
                " there is a mob present, you will given an option to engage or ignore");
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
        System.out.println("  u = store item         ");
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
            case('u'):
                pickDroppedItem();
                break;
            case('y'):
                keyBinds();
                break;
            case('q'):
                playerLife = false;
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
        for(int x = 0; x<2; x++){
            for(int i=0; i<6; i++) {
                if (inventory[x][i] == null) {
                    System.out.print("(empty) ");
                } else {
                    System.out.print(inventory[x][i] + " ");
                }
            }
            System.out.println("");
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
            baseDamage = 2;
        }

        int damageDealt = multiplier*baseDamage;
        hp -= damageDealt;
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
        mobHp -= damageDealt;
        return damageDealt;

    }

    //Engages battle mode where hp of you and the monster will display every now and then
    public static void engageEnemy(){
        try {
            boolean enemyAlive = true;
//            int count = 0;
            while(enemyAlive==true) {

//                checkCount(count);
                int a, x;
                System.out.print("Your turn to attack- ");
                char attackKey = input.nextLine().charAt(0);
                TimeUnit.MILLISECONDS.sleep(500);

                if(attackKey == 'l'){
                    a = playerDamage();
                    System.out.println("\n______________");
                    System.out.println("Damage " + a);
                    System.out.println("mob HP " + mobHp);
                    System.out.println("--------------");

                    TimeUnit.SECONDS.sleep(1);
                }else if(attackKey == 'h'){
                    a = playerDamage() + 10;
                    mobHp -=10;
                    System.out.println("\n______________");
                    System.out.println("Damage " + a);
                    System.out.println("mob HP " + mobHp);
                    System.out.println("--------------");
                    TimeUnit.SECONDS.sleep(1);
                }else{
                    System.out.println("Invalid input!");
                }


                //Checks if Mob is still alive after player attacks
                if(mobHp<1){
                    enemyAlive = false;
                    break;
                }

                System.out.println("Enemy is now attacking!");
                TimeUnit.MILLISECONDS.sleep(500);
                x = mobDamage();
                System.out.println("\n______________");
                System.out.println("Damage " + x);
                System.out.println("Player HP " + hp);
                System.out.println("--------------");
                TimeUnit.SECONDS.sleep(1);

                //Checks if player is still alive after mob attacks
                 if(hp<1){
                    System.out.println("lmao you died !");
                    break;
                 }

            }
            if(playerLife == true) System.out.println("You beat the mob, Well Done!");
            itemDrop();





        }catch(Exception e){
            System.out.println("Error: " + e);
        }
    }

//    public static void printFightDetails(String entity, int damage, int currentHealth){
//        currentHealth -= damage;
//        System.out.println("______________");
//        System.out.println("Damage " + damage);
//        System.out.println(entity + " HP " + currentHealth);
//        System.out.println("--------------");
//   }

    public static void itemDrop(){
        int a, x;
        x = randint.nextInt(7);
        if( x < 6){
            a = randint.nextInt(4);
        }else{
            a =randint.nextInt(2)+4;
        }

        droppedWeapon = weapons[0][a];
        System.out.println("Type u to discover the weapon drop");
    }

    //This will ask the user where he wants to store is weapon in the inventory
    public static void pickDroppedItem(){
        System.out.println("Where would you like to store this item in your inventory (type in 1-6)");
        displayInventory();
        int inventorySlot = Integer.parseInt(input.nextLine());
        inventory[0][inventorySlot-1] = droppedWeapon;

        for( int i = 0; i < 2; ++i ) {
            for(int j = 0; j < 6; ++j ) {
                if( weapons[i][j] == droppedWeapon ) {
                    inventory[1][inventorySlot-1] = weapons[1][j];
                }
            }
        }

        try {
            TimeUnit.MICROSECONDS.sleep(500);
        }catch(Exception e){
            System.out.println("Error: " + e);
        }
        System.out.println("Weapon Stored!");


    }


    //makes count = 0 if count is less than one so that the cooldown for the heavy attack works
    //feature currently diasble
    public static int checkCount(int count){
        if(count < 0){
            return 0;
        }else{
            return count;
        }
    }

    //Issues I'm aware and working to find a solution for
    public static void printBugs(){
        System.out.println("lots of bugs atm!");
        System.out.println("Release 0.2");
        System.out.println("Atri Hegde©");
    }

    //Provides the loop where all my methods will be called in
    public static void runGame(){

        printBugs();
        intro();
        rules();
        inventory[0][0] = "Wooden Sword";
        inventory[1][0] = weapons[1][0];
//        inventory[0][5] = "Shield";
        while(playerLife == true){
            userInput();
            if(hp<1) playerLife = false;
        }

        System.out.println("GGWP");


    }

    public static void main(String[] args) throws IOException {
//        for(int x =0; x<50; x++){
//            itemDrop();
//            pickDroppedItem();
//            displayInventory();
//        }
        runGame();










    }
}
