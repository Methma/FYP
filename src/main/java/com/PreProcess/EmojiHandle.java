package com.PreProcess;

import com.FileHandle.ReadLineByLineCSV;
import opennlp.tools.tokenize.SimpleTokenizer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Methma Samaranayake on 6/8/2017.
 */
public class EmojiHandle {
    public static void main(String[] args) throws IOException {

        ReadLineByLineCSV readLineByLineCSV = new ReadLineByLineCSV();
        EmojiHandle emojiHandle= new EmojiHandle();
        int[] fv= new int[6];

//        String[] positiveEmoji = {"\uD83D\uDE01", "\uD83D\uDE02", "\uD83D\uDE03", "\uD83D\uDE04", "\uD83D\uDE05", "\uD83D\uDE06", "\uD83D\uDE09", "\uD83D\uDE0A", "\uD83D\uDE0B", "\uD83D\uDE0C", "\uD83D\uDE0D", "\uD83D\uDE18", "\uD83D\uDE1A", "❤", "\uD83D\uDC4D"};
//        String[] negativeEmoji = {"\uD83D\uDE0F", "\uD83D\uDE12", "\uD83D\uDE13", "\uD83D\uDE14", "\uD83D\uDE16", "\uD83D\uDE1E", "\uD83D\uDE20", "\uD83D\uDE21", "\uD83D\uDE22", "\uD83D\uDE23", "\uD83D\uDE24", "\uD83D\uDE25", "\uD83D\uDE28", "\uD83D\uDE29", "\uD83D\uDE2A", "\uD83D\uDE2B", "\uD83D\uDE2D", "\uD83D\uDE30", "\uD83D\uDE31", "\uD83D\uDC4E"};

        ArrayList<String[]> testTweets= readLineByLineCSV.readToArrayList("src\\main\\resources\\corpus\\xxx.csv");
//        ArrayList<String[]> testTweets= readLineByLineCSV.readToArrayList("C:\\Users\\Methma Samaranayake\\IdeaProjects\\twitterdata1\\src\\main\\resources\\TwitterData\\tweets.csv");

        for(int i=0; i<testTweets.size();i++){
            String[] fff= testTweets.get(i);
            emojiHandle.captureEmoji(Arrays.toString(fff),fv);
//            System.out.println(Arrays.toString(fff));
        }
    }

    public int[] captureEmoji(String text, int[] featureVector){

        String[] positiveEmoji = {"\uD83D\uDE01", "\uD83D\uDE02", "\uD83D\uDE03", "\uD83D\uDE04", "\uD83D\uDE05", "\uD83D\uDE06", "\uD83D\uDE09", "\uD83D\uDE0A", "\uD83D\uDE0B", "\uD83D\uDE0C", "\uD83D\uDE0D", "\uD83D\uDE18", "\uD83D\uDE1A", "❤", "\uD83D\uDC4D"};
        String[] negativeEmoji = {"\uD83D\uDE0F", "\uD83D\uDE12", "\uD83D\uDE13", "\uD83D\uDE14", "\uD83D\uDE16", "\uD83D\uDE1E", "\uD83D\uDE20", "\uD83D\uDE21", "\uD83D\uDE22", "\uD83D\uDE23", "\uD83D\uDE24", "\uD83D\uDE25", "\uD83D\uDE28", "\uD83D\uDE29", "\uD83D\uDE2A", "\uD83D\uDE2B", "\uD83D\uDE2D", "\uD83D\uDE30", "\uD83D\uDE31", "\uD83D\uDC4E"};

        int positiveCount = 0;
        int negativeCount = 0;

        System.out.println("received feature vector :"+Arrays.toString(featureVector));

        final String emo_regex = "([\\u20a0-\\u32ff\\ud83c\\udc00-\\ud83d\\udeff\\udbb9\\udce5-\\udbb9\\udcee])";

//        String test = "josh stevens is the best 😂 . I ❤ you. \uD83D\uDE0D . \uD83D\uDE1A \uD83D\uDC4D ";

        String[] splittedTweet =text.split(" ");
//        System.out.println("splitted tweet[] "+Arrays.toString(splittedTweet));

        for (String word : splittedTweet ) {
//            System.out.println("splitted :"+word);
            if (word.matches(emo_regex)) {
//                System.out.println("word :"+word);
                for(int i =0; i<positiveEmoji.length;i++){
                    if(positiveEmoji[i].equals(word)){
                        positiveCount++;
                        System.out.println("+ "+positiveCount);
                    }
                }
                for (int j=0;j<negativeEmoji.length;j++){
                    if (negativeEmoji[j].equals(word)){
                        negativeCount++;
                        System.out.println("- "+negativeCount);
                    }
                }
//                if (word.equals("❤")) {
//                    System.out.println("heart");
//                }
//                if (word.equals("\uD83D\uDE1A")){
//                    System.out.println("heart face");
//                }
//                if(word.equals("\uD83D\uDC4D")){
//                    System.out.println("thumbs up");
//                }
//                if (word.equals("\uD83D\uDE0D")){
//                    System.out.println("heart eyes");
//                }
            }
            if(word.matches("\\:-?\\)")){
                positiveCount++;
            }
            if(word.matches("\\:-?\\(")){
                negativeCount++;
            }
            if(word.matches("\\:-?\\D")){
                positiveCount++;
            }
            if(word.matches("\\:-?\\/")){
                negativeCount++;
            }
            if(word.matches("\\;-?\\)")){
                positiveCount++;
            }
        }
        System.out.println("++++++ :"+positiveCount);
        System.out.println("------ :"+negativeCount);
        featureVector[2]=positiveCount;
        featureVector[3]=negativeCount;

//        System.out.println("emoji filled :"+Arrays.toString(featureVector));
        return featureVector;
    }
}


