package com.SentimentRun;

import com.FeatureExtraction.WordVector;
import com.FeatureExtraction.WordVectorStreamed;
import com.FileHandle.Converter;
import com.FileHandle.HandleCSV;
import com.FileHandle.ReadLineByLineCSV;
import com.PreProcess.EmojiHandle;
import com.PreProcess.Lemmatizer;
import com.PreProcess.PreProcess;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Methma Samaranayake on 2/19/2017.
 */
public class PreProcessUnlabled {

    public static void main(String[] args) throws IOException {

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

        String writeCSV = "src\\main\\resources\\features\\featureTrainingMyInput8.csv";
        String writeARFF= "src\\main\\resources\\svm\\featureTrainingMyInput8.arff";
        String[] headers = {"pos_count","neg_count","pos_emo","neg_emo","len_score","sentiment"};


        /*Give testing or training data accordingly*/
        ArrayList<String[]> testTweets = readLineByLineCSV.readToArrayList(streamedDataSet);

        CSVWriter writer = new CSVWriter(new FileWriter(writeCSV, true),',','\0'/*,'\n'*/);
        writer.writeNext(headers);
        System.out.println("tweet size :"+testTweets.size());
        for (int j =0; j<testTweets.size(); j++){

            int[] featureVector = new int[6];

            /*Pre Process*/
            String RTnUserRemoved = preProcess.removeRTnUser(Arrays.toString(testTweets.get(j)));
            String urlRemoved = preProcess.removeUrl(RTnUserRemoved);
            int[] emojiFilledFV = emojiHandle.captureEmoji(urlRemoved,featureVector);

            String nonLetterRemoved = preProcess.removeNumbers(urlRemoved);

            List<String> lemmatizedText = lemmatizer.lemmatize(nonLetterRemoved);

            String lemmatizedString = Arrays.toString(lemmatizedText.toArray()).replaceAll(",","").replace("[","").replaceAll("]","");

            String[] stopwordRemoved = preProcess.removeStopWord(lemmatizedString);

            int[] unigramCounted = wordVectorStreamed.unigramCount(stopwordRemoved,positiveCorpus,negativeCorpus,emojiFilledFV); /*get feature vector*/
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
