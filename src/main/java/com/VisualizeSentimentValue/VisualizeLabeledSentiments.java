package com.VisualizeSentimentValue;

import com.Classifier.LoadSaveModel;
import com.FileHandle.HandleCSV;
import com.FileHandle.UpdateCSV;
import com.opencsv.CSVWriter;
import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Methma Samaranayake on 6/11/2017.
 */
public class VisualizeLabeledSentiments {
    public static void main(String[] args) throws IOException {
        UpdateCSV updateCSV = new UpdateCSV();
        String fileToUpdate = "src\\main\\resources\\Unlabled\\myInput.csv"; /*File which need to check sentiment*/

            /*Read all instances of the file*/
            ArffLoader loader = new ArffLoader();
            loader.setFile(new File("src\\main\\resources\\svm\\featureTrainingMyInput7.arff")); /*Testing arrf file*/
            Instances labeled = loader.getDataSet();

            String[] autoLabled = new String[labeled.numInstances()];

            for (int i=0;i<labeled.numInstances();i++) {
                String label= labeled.instance(i).stringValue(labeled.numAttributes()-1);
                System.out.println("label "+label);
                autoLabled[i]=label;
            }
        System.out.println("AutoLabeled :"+ Arrays.toString(autoLabled));
        updateCSV.updateCsvFile(fileToUpdate,autoLabled,1);

    }
}
