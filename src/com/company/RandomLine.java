package com.company;

import java.io.*;
import java.util.concurrent.ThreadLocalRandom;

public class RandomLine{

/*
Use RandomAccessFile:
Construct a RandomAccessFile, file
Get the length of that file, filelen, by calling file.length()
Generate a random number, pos, between 0 and filelen
Call file.seek(pos) to seek to the random position
Call file.readLine() to get to the end of the current line
Read the next line by calling file.readLine() again
*/

    public void getRandomLine() throws IOException {
            RandomAccessFile file = new RandomAccessFile(getSceneryDirectory(), "r");
            long fileLength = file.length();
            long randomPos = ThreadLocalRandom.current().nextLong(fileLength);
            file.seek(randomPos);
            file.readLine();
            String str = file.readLine();
            System.out.println(str);

    }



    public static String getSceneryDirectory(){
        return System.getProperty("user.dir") + "\\scenery.txt";
    }
}








