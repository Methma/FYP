package com.Classifier;
import java.io.*;

import weka.core.*;
import weka.classifiers.functions.*;
import weka.core.converters.ArffLoader;
import weka.classifiers.*;

/**
 * Created by Methma Samaranayake on 6/9/2017.
 */
public class SVM {

    public void svmClassifierTrainModel(String training, String testing, String writeTo, String modelName){

        LoadSaveModel loadSaveModel=new LoadSaveModel();

        /*Instances class handle an ordered set of weighted instances.*/
        ClassifyUnlabeled classifyUnlabeled = new ClassifyUnlabeled();

        try{

            /*Read all instances of the file*/
            ArffLoader loader = new ArffLoader();
            loader.setFile(new File(training)); /*Training arrf file*/
            Instances structure = loader.getDataSet();

            /*Make last attribute as the class*/
            structure.setClassIndex(structure.numAttributes() - 1);

            LibSVM svm = new LibSVM();
            svm.buildClassifier(structure);

            /*Save trained model*/
            loadSaveModel.saveModel(svm,modelName);/*pass trained model and name to save it as*/

            Instances unlabeled = new Instances(new BufferedReader(
                    new FileReader(testing))); /*testing arrf file*/

            /*set class attribute*/
            unlabeled.setClassIndex(unlabeled.numAttributes() - 1);

            /*create copy*/
            Instances labeled = new Instances(unlabeled);
//            System.out.println("labeled : "+labeled); //streamed
//            System.out.println("structure : "+structure); //featureVectorTraining90
//            System.out.println("unlabeled :"+unlabeled); //streamed

            /*label instances*/
            for (int i = 0; i < unlabeled.numInstances(); i++) {
                double clsLabel = svm.classifyInstance(unlabeled.instance(i));
//                System.out.println("class label: " + clsLabel);
                labeled.instance(i).setClassValue(clsLabel);
//                System.out.println(labeled.instance(i));
            }

            /*save labeled data*/
            BufferedWriter writer = new BufferedWriter(new
                    FileWriter(writeTo)); /*write to arrf file*/
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
