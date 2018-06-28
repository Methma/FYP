package com.PreProcess;


import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

/**
 * Created by Methma Samaranayake on 6/5/2017.
 */
public class Lemmatizer {
    public static void main(String arg[]){
        String documentText = "I had slowed a very bad day. I am doing this. She accomplished it by achieving. I can't do this. I am coming. dancing, taking, making" ;
        Lemmatizer lemmatizer = new Lemmatizer();
        lemmatizer.lemmatize(documentText);
        System.out.println(lemmatizer.lemmatize(documentText));
    }

    protected StanfordCoreNLP pipeline;

    public Lemmatizer() {
        Properties props;
        props = new Properties();
        props.put("annotators", "tokenize, ssplit, pos, lemma");
        this.pipeline = new StanfordCoreNLP(props);
    }


    public List<String> lemmatize(String documentText) {
        List<String> lemmas = new LinkedList<String>();
        Annotation document = new Annotation(documentText);
        this.pipeline.annotate(document);
        List<CoreMap> sentences = document.get(SentencesAnnotation.class);
        for (CoreMap sentence : sentences) {
            for (CoreLabel token : sentence.get(TokensAnnotation.class)) {
                lemmas.add(token.get(LemmaAnnotation.class));
            }
        }
        return lemmas;
    }

}
