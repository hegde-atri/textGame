package com.company;

import java.util.Random;

public class Mobs {
    public int mobSelector(){
        Random randint = new Random();
        return randint.nextInt(6);
    }

}
