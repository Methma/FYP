package com.Classifier;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import weka.core.Instances;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import weka.core.*;
import weka.classifiers.functions.*;
import weka.core.converters.ArffLoader;
import weka.classifiers.*;

/**
 * Created by Methma Samaranayake on 6/10/2017.
 */
public class ClassifyUnlabeled {
    public static void main(String[] args) throws Exception {
        ClassifyUnlabeled classifyUnlabeled = new ClassifyUnlabeled();
        String model="src\\main\\resources\\models\\featureDataSetNewToday";
        String dataFile = "src\\main\\resources\\Unlabled\\myInput.csv";
        String writeToFile= "src\\main\\resources\\Unlabled\\myInput.arff";

        classifyUnlabeled.classifyUnlabledData(model,dataFile,writeToFile);
    }


    public void classifyUnlabledData(String model, String dataFile, String writeFile) throws IOException {

        LoadSaveModel loadSaveModel=new LoadSaveModel();

        Classifier svm = loadSaveModel.loadModel(model); /*model to load. the model is saved previously*/

        /*load unlabeled data*/
        Instances unlabeled = null;
        try {
            unlabeled = new Instances(
                    new BufferedReader(
                            new FileReader(dataFile))); /*file which contain unlabeled data. it needs to be labeled according to the previously saved model*/
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*set class attribute*/
        unlabeled.setClassIndex(unlabeled.numAttributes() - 1);

        /*create copy*/
        Instances labeled = new Instances(unlabeled);

        /*label instances*/
        for (int i = 0; i < unlabeled.numInstances(); i++) {
            double clsLabel = 0;
            try {
                clsLabel = svm.classifyInstance(unlabeled.instance(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
            labeled.instance(i).setClassValue(clsLabel);
//            System.out.println(clsLabel);
        }

        /*save labeled data*/
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(
                    new FileWriter(writeFile));/*Save labeled data according to the previously saved model*/
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.write(labeled.toString());
        writer.newLine();
        writer.flush();
        writer.close();

    }
}
