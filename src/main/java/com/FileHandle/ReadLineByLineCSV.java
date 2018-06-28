package com.FileHandle;

import com.opencsv.CSVReader;
import com.sun.xml.internal.fastinfoset.util.StringArray;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Methma Samaranayake on 6/5/2017.
 */
public class ReadLineByLineCSV {
    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception {

        String fileName = "src\\main\\resources\\tweetSanders\\trainingDataSample.csv";
        ReadLineByLineCSV readLineByLineCSV = new ReadLineByLineCSV();
        readLineByLineCSV.readToArrayList(fileName);

    }

    /*Build reader instance, Read data.csv, Default seperator is comma, Default quote character is double quote, Start reading from line number 2 (line numbers start from zero)*/
public ArrayList<String[]> readToArrayList (String fileName) throws IOException {

        CSVReader reader = new CSVReader(new FileReader(fileName), ',' , '"' , 1);

        //Read CSV line by line and use the string array as you want
        String[] nextLine;
        ArrayList<String[]> tweetsList = new ArrayList<String[]>();
        while ((nextLine = reader.readNext()) != null) {
            if (nextLine != null) {
                //Verifying the read data here
//                System.out.println(Arrays.toString(nextLine)); //To read the whole line -->(Arrays.toString(nextLine[1]) , Get certain row --> System.out.println(nextLine[1])
                //Write in to an ArrayList
                tweetsList.add(new String[]{Arrays.toString(nextLine)});
            }
        }
    return tweetsList;
    }


}
