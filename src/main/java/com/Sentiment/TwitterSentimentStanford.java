package com.Sentiment;

import com.FileHandle.HandleCSV;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Properties;

/**
 * Created by Methma Samaranayake on 2/17/2017.
 */
public class TwitterSentimentStanford {

    HandleCSV handleCSV = new HandleCSV();
    public String fileName = "C:\\Users\\Methma Samaranayake\\IdeaProjects\\SentimentTwitter\\src\\main\\resources\\tweetSanders\\sentiment.csv";


    public void trainingData() throws IOException {
        //get training data
        handleCSV.readFromCSVOpenCSV(fileName);
        //TODO take some % from data set as training and testing

    }

    public static void stopWordRemoval(){
        //remove stop words
        Properties props = new Properties();
        props.put("annotators", "tokenize, ssplit, stopword");
//        props.setProperty("customAnnotatorClass.stopword", "intoxicant.analytics.coreNlp.StopwordAnnotator");
        String example = "I'm methma. She is a girl. They are lovely.";
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        Annotation document = new Annotation(example);
        pipeline.annotate(document);
        pipeline.prettyPrint(document, System.out);
        List<CoreLabel> tokens = document.get(CoreAnnotations.TokensAnnotation.class);
    }
    public static String text = "RT @cjwallace03: I am Metma. She is good. So apparently @apple put MB cap on your SMS with the new update. 25mb storage before it tells you your inbox is full. What is this 2001?";

    public static void tokenizer(){
        PTBTokenizer ptb = new PTBTokenizer(
                new StringReader(text.toLowerCase()), /*"He lives at 1511 W. Randolph."*/
                new CoreLabelTokenFactory(), null);
        while (ptb.hasNext()) {
            System.out.println(ptb.next());
        }
    }

    public void simpleTokenizer(){
        String text = "Mr. Smith went to 123 Washington avenue.";
        String tokens[] = text.split("\\s+");
        for(String token : tokens) {
            System.out.println(token);
        }

    }

    public static void main(String[] args) {
//        stopWordRemoval();
        tokenizer();
    }

    public void createFeatureVector(){
        //create feature vector-Binary
    }
}
