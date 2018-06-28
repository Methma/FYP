package com.Sentiment;

import edu.stanford.nlp.pipeline.*;
import java.util.*;

/**
 * Created by Methma Samaranayake on 2/18/2017.
 */
public class BasicPipelineExample {
    public static void main(String[] args) {

        // creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing, and coreference resolution
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // read some text in the text variable
        String text = "RT @cjwallace03: So apparently @apple put MB cap on your SMS with the new update. 25mb storage before it tells you your inbox is full. What is this 2001?";

        // create an empty Annotation just with the given text
        Annotation document = new Annotation(text);

        // run all Annotators on this text
        pipeline.annotate(document);
        System.out.println("document="+document);
        pipeline.getPrettyPrint();




    }
}
