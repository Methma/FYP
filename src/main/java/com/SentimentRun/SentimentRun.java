package com.SentimentRun;

import com.FeatureExtraction.WordVector;
import com.FeatureExtraction.WordVectorStreamed;
import com.FileHandle.Converter;
import com.FileHandle.ReadLineByLineCSV;
import com.PreProcess.EmojiHandle;
import com.PreProcess.Lemmatizer;
import com.PreProcess.PreProcess;
import com.FileHandle.HandleCSV;
import com.PreProcess.StanfordLemmatizer;
import com.Sentiment.TwitterSentimentStanford;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Methma Samaranayake on 2/19/2017.
 */
public class SentimentRun {

    public static void main(String[] args) throws IOException {

        HandleCSV handleCSV = new HandleCSV();
        ReadLineByLineCSV readLineByLineCSV = new ReadLineByLineCSV();
        PreProcess preProcess = new PreProcess();
        WordVector wordVector = new WordVector();
        Lemmatizer lemmatizer = new Lemmatizer();
        EmojiHandle emojiHandle = new EmojiHandle();
        WordVectorStreamed wordVectorStreamed = new WordVectorStreamed();
        Converter converter = new Converter();

        String trainingDataSet = "src\\main\\resources\\dataSets\\NewDataSetTraining.csv";
        String testingDataSet= "src\\main\\resources\\dataSets\\NewDataSetTesting.csv";

        String positiveCorpus = "src\\main\\resources\\corpus\\positive.csv";
        String negativeCorpus = "src\\main\\resources\\corpus\\negative.csv";

        String writeCSV = "src\\main\\resources\\features\\featureTrainingNewDataSet7.csv";
        String writeARFF= "src\\main\\resources\\svm\\featureTrainingNewDataSet7.arff";
        String[] headers = {"pos_count","neg_count","pos_emo","neg_emo","len_score","sentiment"};


        /*Give testing or training data accordingly*/
        ArrayList<String[]> testTweets = readLineByLineCSV.readToArrayList(trainingDataSet);

        CSVWriter writer = new CSVWriter(new FileWriter(writeCSV, true),',','\0'/*,'\n'*/);
        writer.writeNext(headers);

        for (int j =0; j<testTweets.size(); j++){

            int[] featureVector = new int[6];

            /*Pre Process*/
            String RTnUserRemoved = preProcess.removeRTnUser(Arrays.toString(testTweets.get(j)));
            String urlRemoved = preProcess.removeUrl(RTnUserRemoved);
            int[] emojiFilledFV = emojiHandle.captureEmoji(urlRemoved,featureVector);
//            System.out.println("emoji filled :"+Arrays.toString(emojiFilledFV));

//            System.out.println("\nRTnUser removed :"+RTnUserRemoved);
            String nonLetterRemoved = preProcess.removeNumbers(urlRemoved);

//            System.out.println("\n number removed:"+ nonLetterRemoved);
            List<String> lemmatizedText = lemmatizer.lemmatize(nonLetterRemoved);
//            System.out.println("Lemmatized :"+lemmatizedText);
            String lemmatizedString = Arrays.toString(lemmatizedText.toArray()).replaceAll(",","").replace("[","").replaceAll("]","");
//            System.out.println("lem string "+lemmatizedString);
            String[] stopwordRemoved = preProcess.removeStopWord(lemmatizedString);

            int[] unigramCounted = wordVector.unigramCount(stopwordRemoved,positiveCorpus,negativeCorpus,emojiFilledFV); /*get feature vector*/
            System.out.println("unigram counted :"+Arrays.toString(unigramCounted));


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
}
