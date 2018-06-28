package com.FileHandle;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
//import java.util.Objects;

//import java.util.stream.Collectors;

/**
 * Created by Methma Samaranayake on 2/17/2017.
 */
public class HandleCSV {

    //Write in to a .CSV file
    public void writeToCSV(String user, String tweet, String fileName) throws IOException {
        String twt = user + tweet;
        CSVWriter writer = new CSVWriter(new FileWriter(fileName, true), '\t');
        //Convert data to an array
        String[] entries = twt.split("-");
        writer.writeNext(entries);
        writer.close();
    }

    //write from duplicate array of courses to a CSV file
    public static void intArrayWriteCSV(int[] intArray,String fileName) throws Exception {

        //create a File class object and give the file the name employees.csv
        java.io.File intCSV = new java.io.File(fileName);

        //Create a Printwriter text output stream and link it to the CSV File
        java.io.PrintWriter outfile = new java.io.PrintWriter(intCSV);

        //Iterate the elements actually being used
        for (int i=0; i < intArray.length ; i++) {
            outfile.write(intArray[i]/*.toCSVString()*/);

        }//end for

        outfile.close();
    } //end writeCSV()


    //Return String Array List as the twitter document
    public ArrayList<String> readFromCSVOpenCSV(String fileName) throws IOException {
        CSVReader reader = new CSVReader(new FileReader(fileName));
        List content = reader.readAll();

        ArrayList<String> tweets = new ArrayList<String>(content.size());
        for (Object object : content) {
            tweets.add(object != null ? object.toString() : null);
//            System.out.println("tweet :"+tweets);
        }
        reader.close();
    return tweets;
    }

    //Return String Array as the Twitter Document
    public String[] readToString(String fileName) throws IOException {
//        String fileName = "src\\main\\resources\\TwitterData\\NYSC.csv";
        CSVReader reader = new CSVReader(new FileReader(fileName));
            List content = reader.readAll();
//            Object[] objectArray = content.toArray();
        // to get to array
        String[] csvFile = (String[]) content.toArray(new String[content.size()]);
        System.out.println("csv file : "+csvFile[2]);

//            String[] csvFile = Arrays.copyOf(objectArray, objectArray.length, String[].class);
            reader.close();
        System.out.println("csv file:"+csvFile[2]);
        return csvFile;
    }


    /*Read line by line from a CSV file into a String Array List*/
    public List<String > readByLine (String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        List<String> lines = new ArrayList<>();
        String line = null;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
    }

    return lines;
    }


    public static void main(String[] args) throws IOException {
        HandleCSV handleCSV = new HandleCSV();
        String fileName = "C:\\Users\\Methma Samaranayake\\IdeaProjects\\SentimentTwitter\\src\\main\\resources\\tweetSanders\\trainingDataSample.csv";
        handleCSV.readByLine(fileName);

    }
}



