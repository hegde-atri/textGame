package com.company;

import java.io.*;
import java.util.concurrent.ThreadLocalRandom;

public class RandomLine{

    public void getRandomLine() throws IOException {
            RandomAccessFile file = new RandomAccessFile(getSceneryDirectory(), "r"); //Construct a RandomAccessFile, file
            long fileLength = file.length();                                              //Get the length of that file, fileLength, by calling file.length()
            long randomPos = ThreadLocalRandom.current().nextLong(fileLength);            //Generate a random number (randomPos) between 0 and fileLength
            file.seek(randomPos);                                                         //Use .seek() to go that position in the text file
            file.readLine();                                                              // Use file.readLine() to read whatever remains of that line in the file
            String str = file.readLine();                                                 // Read the nextLine and assign it to str
            System.out.println(str);                                                      //Prints the random sentence/line out

    }


    public static String getSceneryDirectory(){
        return System.getProperty("user.dir") + "\\scenery.txt";
    }
}








