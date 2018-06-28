package com.Classifier;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import weka.core.*;
import weka.classifiers.functions.*;
import weka.core.converters.ArffLoader;
import weka.classifiers.*;
/**
 * Created by Methma Samaranayake on 3/31/2017.
 */
public class SvmTest {
    public static void main(String[] args){

        try{
            ArffLoader loader = new ArffLoader();
            loader.setFile(new File("src\\main\\resources\\features\\featureVector.arff"));
            Instances structure = loader.getDataSet();
            structure.setClassIndex(structure.numAttributes() - 1);

            LibSVM svm = new LibSVM();
            svm.buildClassifier(structure);
            System.out.println("svm :"+svm);

            Instances unlabeled = new Instances(new BufferedReader(
                    new FileReader("src\\main\\resources\\features\\featureVectorTest.arff")));

            // set class attribute
            unlabeled.setClassIndex(unlabeled.numAttributes() - 1);
            System.out.println("unlabled :"+unlabeled);

            // create copy
            Instances labeled = new Instances(unlabeled);

            // label
            for (int i = 0; i < unlabeled.numInstances(); i++) {
                double clsLabel = svm.classifyInstance(unlabeled.instance(i));
//                System.out.println("class label: " + clsLabel);
                labeled.instance(i).setClassValue(clsLabel);
//                System.out.println("labled :"+labeled.instance(i));
            }
            // save labeled data

            BufferedWriter writer = new BufferedWriter(new
                    FileWriter("src\\main\\resources\\classifierOutput\\arffOutputSVM.arff"));
            writer.write(labeled.toString());
            writer.newLine();
            writer.flush();
            writer.close();

            Evaluation eval = new Evaluation(labeled);
            System.out.println(eval.evaluateModel(svm, unlabeled));
            System.out.println(eval.toSummaryString("\nResults\n======\n", false));
            //System.out.println(eval.correct());
            //System.out.println(eval.pctCorrect());
            //System.out.println(eval.unclassified());
            System.out.println(eval.toMatrixString());

        }
        catch(Exception e){e.printStackTrace();
        }

    }
}

