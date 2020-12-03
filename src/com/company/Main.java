package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Main {

    int[] stats = new int[5];
    public static final Scanner input = new Scanner(System.in);


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
        System.out.println("Type in rules to display this page again");


    }



    public static void main(String[] args) {
        rules();


    }
}
