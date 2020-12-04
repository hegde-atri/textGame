package com.company;

import java.util.Random;

public class Mobs {
    Random randint = new Random();

    //This method here returns 0 for no mobs found or a number ranging from 1-5 as a mob
    public int mobFound(){
        int mobScanner = randint.nextInt(3);
        if(mobScanner>2){
            return 1;
        }

        return 0;
    }


    public int mobSelector(){
        int randomNum =  (randint.nextInt(100)+1);
        if(0<randomNum && randomNum<31){
            return 2;
        }else if(30<randomNum && randomNum<81){
            return 1;
        }else if(80<randomNum && randomNum<101){
            return 3;
        }else{
            return -1;
        }
    }

//    public void mobOne(){
//        System.out.println("mob one");
//    }
//
//    public void mobTwo(){
//        System.out.println("mob two");
//    }
//
//    public void mobThree(){
//        System.out.println("mob three");
//    }


}
