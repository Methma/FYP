package com.SentimentRun;

import com.Classifier.SVM;

/**
 * Created by Methma Samaranayake on 6/11/2017.
 */
public class RunClassifier {
    public static void main(String[] args) {
        SVM svm = new SVM();

        String training = "src\\main\\resources\\svm\\featureTrainingNewDataSet.arff";
        String testing = "src\\main\\resources\\svm\\featureTestingNewDataSet.arff";
        String writeTo = "src\\main\\resources\\classifierOutput\\featureNewDataSet.arff";
        String modelName = "src\\main\\resources\\models\\featureDataSetNewToday";

        svm.svmClassifierTrainModel(training,testing,writeTo,modelName);

    }
}
