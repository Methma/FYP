package com.FeatureExtraction;

import com.FileHandle.ReadLineByLineCSV;
import com.PreProcess.RemoveRepChar;

import java.io.IOException;
import java.util.*;

/**
 * Created by Methma Samaranayake on 3/31/2017.
 */
public class WordVectorStreamed {

    ReadLineByLineCSV readLineByLineCSV = new ReadLineByLineCSV();
    RemoveRepChar removeRepChar = new RemoveRepChar();



    /*Fill positive and negative hash sets using positive and negative corpus*/
    public Set<String> createHashSet(String corpus) throws IOException {
        List<String> corpusList = new ArrayList<String>();
        ArrayList<String[]> fromCorpus = readLineByLineCSV.readToArrayList(corpus);

        for(int i= 0; i<fromCorpus.size();i++){
            String xxx = Arrays.toString(fromCorpus.get(i));
            String xxxNoBrackets = xxx.replaceAll("[^A-Za-z]","");
            corpusList.add(xxxNoBrackets);
        }

        Set<String> corpusSet = new HashSet<String>(corpusList);

    return corpusSet;
    }

    public int[] unigramCount(String[] processedTweet,String positive, String negative, int[] featureVector) throws IOException {
        System.out.println("received emoji filled :"+Arrays.toString(featureVector));
        Set<String> positiveCorpus = this.createHashSet(positive);
        Set<String> negativeCorpus = this.createHashSet(negative);
        int[] score = new int[processedTweet.length];

        int positiveCount = 0;
        int negativeCount = 0;

        ArrayList<String > neutralWords = new ArrayList<String >() ;

        for(int i =0 ; i<processedTweet.length-1;i++){ /*to not consider label field*/

            if(positiveCorpus.contains(processedTweet[i])){
                score[i] = 4;
                positiveCount++;
            }else if(negativeCorpus.contains(processedTweet[i])){
                score[i] = 2;
                negativeCount ++;
            }else {
                score[i]= 3;
                neutralWords.add(processedTweet[i]);
            }
                    }

        featureVector[0]= positiveCount;
        featureVector[1]= negativeCount;

//        if(processedTweet[sentimentIndex].contentEquals("positive")){
//            featureVector[5] = 4;
//        }
//        if(processedTweet[sentimentIndex].contentEquals("negative")){
//            featureVector[5] = 5;
//        }
//        System.out.println("ne li :"+neutralWords);
        int lengthyCount = handleLengthyWords(neutralWords);
        featureVector[4]= lengthyCount;

        if(positiveCount>negativeCount){
            featureVector[5] = 4;
        }
        if(negativeCount>positiveCount){
            featureVector[5]= 2;
        }
        else {
            featureVector[5]=4;
        }
        System.out.println("feature vector :"+Arrays.toString(featureVector));


//        return score;
        return featureVector;

    }

    public int handleLengthyWords(ArrayList<String> canBeLengthy){
        String[] positiveLengthy = {"like","hi","you","thanks","yay","wow","great","fine","lol","helo", "hah","haha","col","bah","yes","rip"};
        String [] negativeLengthy = {"oh","no","sory","but","fuck","what","ops","sh","shit","mis","wtf","hate", "ugh","as","argh","rip"};
        int positiveLengthyCount = 0;
        int negativeLengthyCount = 0;
        int lengthyTotal = 0;

        for (int i=0; i<canBeLengthy.size();i++){
            String baseForm = removeRepChar.removeRep(canBeLengthy.get(i));
//            System.out.println("removed :"+baseForm);
            for(int j=0; j<positiveLengthy.length;j++){
                if(baseForm.contains(positiveLengthy[j])){
                    positiveLengthyCount = positiveLengthyCount+5;
            }
            for (int k=0; k<negativeLengthyCount;k++){
                    if (baseForm.contains(negativeLengthy[k])){
                        negativeLengthyCount++;
                    }
            }

            lengthyTotal = negativeLengthyCount+positiveLengthyCount;

            }
        }
        return lengthyTotal;


    }


}
