package com.Sentiment;

import opennlp.tools.tokenize.SimpleTokenizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by Methma Samaranayake on 2/18/2017.
 */
public class StopWords {
    private String[] defaultStopWords = {",","are","is","am","s","the","a","about","they","above","after","again","all","am","an","and","any","are","aren't","as","at","be","because","been","before","being","below","between","both","but","by","can't","cannot","could","couldn't","did","didn't","do","does","doesn't","doing","don't","down","during","each","few","for","from","further","had","hadn't","has","hasn't","have","haven't","having","he","he'd","he'll","he's","her","here","here's","hers","herself","him","himself","his","how","how's","i","i'd","i'll","i'm","i've","if","in","into","is","isn't","it","it's","its","itself","let's","me","more","most","mustn't","my","myself","nor","of","off","on","once","only","or","other","ought","our","ours","ourselves","out","over","own","same","shan't","she","she'd","she'll","she's","should","shouldn't","so","some","such","than","that","that's","the","their","theirs","them","themselves","then","there","there's","these","they","they'd","they'll","they're","they've","this","those","through","to","too","under","until","up","very","was","wasn't","we","we'd","we'll","we're","we've","were","weren't","what","what's","when","when's","where","where's","which","while","who","who's","whom","why","why's","with","won't","would","wouldn't","you","you'd","you'll","you're","you've","your","yours","yourself","yourselves"};

    private static HashSet stopWords = new HashSet(); //Hold stop words for processing purpose

    /*populate hash set*/
    public StopWords() {
        stopWords.addAll(Arrays.asList(defaultStopWords));
    }

    /*Allow additional stop words to be added*/
    public void addStopWord(String word) {
        stopWords.add(word);
    }

    /*Remove StopWords*/
    public String[] removeStopWords(String[] words) {
        ArrayList<String> tokens = new ArrayList<String>(Arrays.asList(words));
        for (int i = 0; i < tokens.size(); i++) {
            if (stopWords.contains(tokens.get(i))) {
                tokens.remove(i);

            }
        }
        return (String[]) tokens.toArray(new String[tokens.size()]);
    }

    public static void main(String[] args) {
        StopWords stopWords = new StopWords();
        SimpleTokenizer simpleTokenizer = SimpleTokenizer.INSTANCE;
        String paragraph = "Analysts thought Denmark's biggest company would report a huge profit above. That didn't happen. The cat is under the tree.";
        String tokens[] = simpleTokenizer.tokenize(paragraph.toLowerCase());
        String list[] = stopWords.removeStopWords(tokens);
        //Print stop words
        for (String word : list) {
            System.out.println(word);
        }

    }

}
