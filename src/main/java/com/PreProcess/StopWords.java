//package com.PreProcess;
//
//import java.util.Arrays;
//import java.util.HashSet;
//
///**
// * Created by Methma Samaranayake on 6/6/2017.
// */
//
//
//public class StopWords {
//
//    String[] stopwordsarr = {"'ll", "'s", "'m", "a", "about", "above", "after", "again", "against", "all", "am", "an", "and", "any", "are",
//            "aren't", "as", "at", "be", "because", "been", "before", "being", "below", "between", "both", "but", "can", "can't", "cannot",
//            "could", "couldn't", "did", "didn't", "do", "does", "doesn't", "doing", "don't", "during", "each", "few", "for", "from",
//            "further", "had", "hadn't", "has", "hasn't", "have", "haven't", "having", "he'd", "he'll", "he's", "her", "here", "here's", "hers",
//            "herself", "him", "himself", "his", "how", "how's", "i", "i'd", "i'll", "i'm", "i've", "if", "in", "into", "is", "isn't", "it", "it's",
//            "its", "itself", "let's", "me", "more", "most", "mustn't", "my", "myself", "no", "nor", "not", "of", "once", "only", "or", "other",
//            "ought", "our", "ours", "ourselves", "own", "same", "shan't", "she", "she'd", "she'll", "she's", "should", "shouldn't", "so", "some",
//            "such", "than", "that", "that's", "the", "their", "theirs", "them", "themselves", "then", "there", "there's", "these", "they",
//            "they'd", "they'll", "they're", "they've", "this", "those", "through", "to", "too", "until", "very", "was", "wasn't", "we", "we'd",
//            "we'll", "we're", "we've", "were", "weren't", "what", "what's", "when", "when's", "where", "where's", "which", "while", "who",
//            "who's", "whom", "why", "why's", "with", "won't", "would", "wouldn't", "you", "you'd", "you'll", "you're", "you've", "your", "yours",
//            "yourself", "yourselves", "###", "return", "arent", "cant", "couldnt", "didnt", "doesnt", "dont", "hadnt", "hasnt", "havent", "hes",
//            "heres", "hows", "im", "isnt", "its", "lets", "mustnt", "shant", "shes", "shouldnt", "thats", "theres", "theyll", "theyre", "theyve",
//            "wasnt", "were", "werent", "whats", "whens", "wheres", "whos", "whys", "wont", "wouldnt", "youd", "youll", "youre", "youve",
//            "etc.", "etc", "by", ".", ",", "?", ":", ";", "\"", "'", "\\", "|", "}", "{", "[", "]", "+", "*", "=", ")", "(", "&", "^",
//            "%", "$", "#", "@", "!", "`", "~"};
//
////    down, off, on, out, over, under, up
//
////    HashSet<String> stopwordsSet = new HashSet<String>(Arrays.asList(stopwordsarr));
//
//
//    private static HashSet stopWords = new HashSet(); //Hold stop words for processing purpose
//
//    //populate hash set
//    public StopWords() {
//        stopWords.addAll(Arrays.asList(stopwordsarr));
//    }
//
//    //Allow additional stop words to be added
//    public void addStopWord(String word) {
//        stopWords.add(word);
//    }
//
//    //Remove StopWords
//    public String[] removeStopWords(String[] sentences) {
//        Tokenization tk = new Tokenization();
//        String[] stopwordRemovedSentences = new String[sentences.length];
//        System.out.println("\nstopwords removed\n");
//
//        for (int a = 0; a < sentences.length; a++) {
//            String[] words = tk.wordTokenization(sentences[a]);
//            String stopwordRemovedContent = new String();
//            for (int i = 0; i < words.length; i++) {
//                if (stopWords.contains(words[i])) {
//                    words[i] = "<REMOVED>";
//                }
//                stopwordRemovedContent = stopwordRemovedContent.concat(words[i]);
//                if (i != words.length - 1) {
//                    stopwordRemovedContent = stopwordRemovedContent.concat(" ");
//                }
//            }
//            System.out.println(stopwordRemovedContent);
//            stopwordRemovedSentences[a] = stopwordRemovedContent;
//        }
//        return stopwordRemovedSentences;
//    }
//
//}
//
