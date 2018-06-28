package com.FileHandle;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Methma Samaranayake on 6/11/2017.
 */
public class UpdateCSV {


    public static void updateCsvFile(String fileToUpdate, String[] replaceContent, int col) throws IOException {

        CSVReader reader = new CSVReader(new FileReader(fileToUpdate));//
        String[] temp = new String[replaceContent.length+1];
            temp[0]="sentiment";
            for(int i=1;i<temp.length;i++){
                temp[i]=replaceContent[i-1];
            }
        String[] entries = null;
        int i=0;
        ArrayList finalList = new ArrayList<ArrayList<String>>();
        while ((entries = reader.readNext()) != null) {
            ArrayList<String> list = new ArrayList<String>(Arrays.asList(entries));
            list.add(temp[i]);
//            System.out.println(list);
            finalList.add(list);
            i++;
        }
        reader.close();
        CSVWriter writer = new CSVWriter(new FileWriter(fileToUpdate),',');
        for(Object d: finalList){
            ArrayList<String> t =(ArrayList<String>) d;
            String[] o = t.toArray(new String[t.size()]);
            writer.writeNext(o);
        }
        writer.close();
    }

////        File inputFile = new File("src\\main\\resources\\Unlabled\\myInput.csv");
//        System.out.println("size replace content :"+replaceContent.length);
//String[] temp = new String[replaceContent.length+1];
//temp[0]="sentiment";
//for(int i=1;i<temp.length;i++){
//    temp[i]=replaceContent[i-1];
//}
//        System.out.println("size temp content :"+temp.length);
//        // Read existing file
//        CSVReader reader = new CSVReader(new FileReader(fileToUpdate) /*','*/);
//        List<String[]> csvBody = reader.readAll();
//        System.out.println("csv body :"+csvBody.size());
//
//        // get CSV row column  and replace with by using row and column
//        for(int i=0; i<=temp.length+1;i++){
//            System.out.println(i);
//            System.out.println(csvBody.get(i)[1].toString());
//            csvBody.get(i)[col] = temp[i];
//        }
//
//        reader.close();
//
//        // Write to CSV file which is open
//        CSVWriter writer = new CSVWriter(new FileWriter(fileToUpdate) /*','*/);
//        writer.writeAll(csvBody);
//        writer.flush();
//        writer.close();
//    }
}
