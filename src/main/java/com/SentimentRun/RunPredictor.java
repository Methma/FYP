package com.SentimentRun;

import com.Classifier.LoadSaveModel;
import com.Classifier.SVM;
import com.FeatureExtraction.WordVector;
import com.FeatureExtraction.WordVectorStreamed;
import com.FileHandle.Converter;
import com.FileHandle.HandleCSV;
import com.FileHandle.ReadLineByLineCSV;
import com.FileHandle.UpdateCSV;
import com.PreProcess.EmojiHandle;
import com.PreProcess.Lemmatizer;
import com.PreProcess.PreProcess;
import com.opencsv.CSVWriter;
import weka.classifiers.bayes.NaiveBayesUpdateable;
import weka.classifiers.functions.LibSVM;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Methma Samaranayake on 6/11/2017.
 */
public class RunPredictor {
    public static void main1(String[] args) throws IOException {
        HandleCSV handleCSV = new HandleCSV();
        ReadLineByLineCSV readLineByLineCSV = new ReadLineByLineCSV();
        PreProcess preProcess = new PreProcess();
        WordVector wordVector = new WordVector();
        Lemmatizer lemmatizer = new Lemmatizer();
        EmojiHandle emojiHandle = new EmojiHandle();
        WordVectorStreamed wordVectorStreamed = new WordVectorStreamed();
        Converter converter = new Converter();

        String streamedDataSet= "src\\main\\resources\\Unlabled\\myInput.csv";

        String positiveCorpus = "src\\main\\resources\\corpus\\positive.csv";
        String negativeCorpus = "src\\main\\resources\\corpus\\negative.csv";

        String writeCSV = "src\\main\\resources\\features\\featureTrainingMyInput6.csv";
        String writeARFF= "src\\main\\resources\\svm\\featureTrainingMyInput6.arff";
        String[] headers = {"pos_count","neg_count","pos_emo","neg_emo","len_score","sentiment"};


        int[] featureVector = new int[6];

        /*Give testing or training data accordingly*/
        ArrayList<String[]> testTweets = readLineByLineCSV.readToArrayList(streamedDataSet);

        CSVWriter writer = new CSVWriter(new FileWriter(writeCSV, true),',','\0'/*,'\n'*/);
        writer.writeNext(headers);

        for (int j =0; j<testTweets.size(); j++){

            /*Pre Process*/
            String RTnUserRemoved = preProcess.removeRTnUser(Arrays.toString(testTweets.get(j)));

            String urlRemoved = preProcess.removeUrl(RTnUserRemoved);

            int[] emojiFilledFV = emojiHandle.captureEmoji(urlRemoved,featureVector);

            String nonLetterRemoved = preProcess.removeNumbers(urlRemoved);

            List<String> lemmatizedText = lemmatizer.lemmatize(nonLetterRemoved);

            String lemmatizedString = Arrays.toString(lemmatizedText.toArray()).replaceAll(",","").replace("[","").replaceAll("]","");

            String[] stopwordRemoved = preProcess.removeStopWord(lemmatizedString);

            int[] unigramCounted = wordVectorStreamed.unigramCount(stopwordRemoved,positiveCorpus,negativeCorpus,emojiFilledFV); /*get feature vector*/

            String [] featureVectorString = new String [unigramCounted.length];
            for (int x=0;x<unigramCounted.length;x++) {
                String ss= Integer.toString(unigramCounted[x]);
                featureVectorString[x]= ss;
                if(x==unigramCounted.length-1 && featureVectorString[x].equals("2")){
                    featureVectorString[x] = "Negative";
                }else if(x==unigramCounted.length-1 && featureVectorString[x].equals("4")){
                    featureVectorString[x] = "Positive";
                }
            }
            writer.writeNext(featureVectorString);
        }
        writer.close();

        /*Convert csv to arff*/
        try {
            converter.Convert(writeCSV,writeARFF);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String testArffPath = "src\\main\\resources\\sm\\featureTrainingMyInput8.arff";
        String csvToUpdate = "src\\main\\resources\\Unlabled\\myInput.csv";
        String [] pVals = SVMPredictionfortest(testArffPath);;
        UpdateCSV updateCSV = new UpdateCSV();
        try {
            updateCSV.updateCsvFile(csvToUpdate,pVals,1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String[] SVMPredictionfortest(String traningSetPath){
        try {
            ArffLoader loader = new ArffLoader();
            loader.setFile(new File(traningSetPath));
            Instances structure = loader.getDataSet();
            structure.setClassIndex(structure.numAttributes()-1);
            LibSVM svm = (LibSVM) LoadSaveModel.loadModel("src\\main\\resources\\models\\featureDataSet5");
            String[] predictedVals = new String[structure.numInstances()];
            Instance i;
            System.out.println(svm == null);
            System.out.println(structure.numInstances());
            System.out.println(structure.numAttributes());
            for(int j=0; j<structure.numInstances();j++){
                i= structure.instance(j);
                double clsLabel = svm.classifyInstance(i);
                i.setClassValue(clsLabel);
//                System.out.println(clsLabel);
                String predictedVal = i.stringValue(structure.numAttributes()-1);
                predictedVals[j] = predictedVal;
            }
//            Instance i =structure.instance(structure.numInstances()-1);

            return predictedVals;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
