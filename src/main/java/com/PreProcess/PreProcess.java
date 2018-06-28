package com.PreProcess;

import com.Sentiment.StopWords;
import opennlp.tools.tokenize.SimpleTokenizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Methma Samaranayake on 2/20/2017.
 */

/*Remove RT, @Un, Numbers
* Identify Emoji, lengthy words*/

public class PreProcess {
    StopWords stopWords = new StopWords();
    SimpleTokenizer simpleTokenizer = SimpleTokenizer.INSTANCE;

    public String[] removeStopWord(String tweet){
        String stopWordRemovedList[] = null;
            String tokens[] = simpleTokenizer.tokenize(tweet.toLowerCase());
        System.out.println("tokens"+Arrays.toString(tokens));
            stopWordRemovedList = stopWords.removeStopWords(tokens);

        return stopWordRemovedList;
    }

    public String removeUrl(String tweet){
        String urlPattern = "((https?|ftp|gopher|telnet|file|Unsure|http):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
        Pattern p = Pattern.compile(urlPattern,Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(tweet);
        int i = 0;
        while (m.find()) {
            tweet = tweet.replaceAll(m.group(i),"").trim();
            i++;
        }
        return tweet;
    }

    public  List<String> removeEmpty(String[] arr){
        List<String> list = new ArrayList<String>(Arrays.asList(arr));
        System.out.println(list);
        list.removeAll(Arrays.asList("", null));
        System.out.println(list);
        return list;
    }


    /*Remove all non letter characters(numbers and punctuations) from the tweet*/
    public String removeNumbers(String tweets){
//            String numberRemoved = tweets.replaceAll("\\d+", "").trim();
            String nonLetterRemoved = tweets.replaceAll("[^a-zA-Z ]", "").toLowerCase().trim();
//            String[] words = instring.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
        return nonLetterRemoved;
    }

    /*remove RT and user names from tweets*/
    public String removeRTnUser(String tweet){
        String RtRemoved = tweet.replaceAll("RT","");
        String UserRemoved = RtRemoved.replaceAll("@[a-z0-9_]+", "");
        return UserRemoved;
    }

    public String[] removeDuplicate(String[] words){
        // remember which word is a duplicate
        boolean[] isDuplicate = new boolean[words.length];
        // and count them
        int countDuplicate = 0;
        for (int i = 0; i < words.length ; i++){
            // only check "forward" because "backwards checked" duplicates have been marked yet
            for(int j = i + 1; j < words.length ; j++){
                if (words[i].equals(words[j])) {
                    isDuplicate[j] = true;
                    countDuplicate++;
                }
            }
        }
        // collect non-duplicate strings
        String[] nonDuplicate = new String[words.length - countDuplicate];
        int j = 0;
        for (int i = 0; i < isDuplicate.length; i++) {
            if (isDuplicate[i] == false) {
                nonDuplicate[j] = words[i];
                j++;
            }
        }
//        System.out.println("Duplicate removed:"+ Arrays.toString(nonDuplicate));
        // and return them
        return nonDuplicate;
    }


    //TODO remove you, your etc.
}
