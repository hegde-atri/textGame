package com.company;

import java.io.*;
import java.util.*;

public class reservoirSampling {


    public reservoirSampling(){}

    public List<String> sampler (int reservoirSize) throws IOException
    {
        String currentLine;
        List <String> reservoirList= new ArrayList<>(reservoirSize);
        int count=0;
        Random ra = new Random();
        int randomNumber;
        Scanner sc = new Scanner(new File(setSceneryDirectory())).useDelimiter("\n");
        while (sc.hasNext())
        {
            currentLine = sc.next();
            count ++;
            if (count<=reservoirSize)
            {
                reservoirList.add(currentLine);
            }
            else if ((randomNumber = ra.nextInt(count))<reservoirSize)
            {
                reservoirList.set(randomNumber, currentLine);
            }
        }
        return reservoirList;
    }

    public static String setSceneryDirectory(){
        return System.getProperty("user.dir") + "\\scenery.txt";
    }
}