package com.PreProcess;

/**
 * Created by Methma Samaranayake on 5/30/2017.
 */
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.SentenceUtils;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import edu.stanford.nlp.util.logging.Redwood;

import java.io.*;
import java.util.List;

public class StanfordPosTagger {

    /** A logger for this class */
    private static Redwood.RedwoodChannels log = Redwood.channels(StanfordPosTagger.class);

    private StanfordPosTagger() {}

    public static void main(String[] args) throws Exception {

        String inputFile= "src\\main\\resources\\tweetSanders\\sentiment.csv";
        String outputFile = "";

        MaxentTagger tagger = new MaxentTagger("C:/StanfordNLP/stanford-postagger-full-2016-10-31/models/english-caseless-left3words-distsim.tagger");

        TokenizerFactory<CoreLabel> ptbTokenizerFactory = PTBTokenizer.factory(new CoreLabelTokenFactory(),
                "untokenizable=noneKeep");

        BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out, "utf-8"));
        DocumentPreprocessor documentPreprocessor = new DocumentPreprocessor(r);
        documentPreprocessor.setTokenizerFactory(ptbTokenizerFactory);

        for (List<HasWord> sentence : documentPreprocessor) {
            List<TaggedWord> tSentence = tagger.tagSentence(sentence);
            pw.println(SentenceUtils.listToString(tSentence, false));
            PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
            System.setOut(out);
        }

        pw.close();
    }

}
