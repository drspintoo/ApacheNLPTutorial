package com.devon;

import opennlp.tools.cmdline.PerformanceMonitor;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.WhitespaceTokenizer;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Objects;

public class PosTagger_Performance {
    public static void main(String[] args) {
        // Loading Parts of speech-maxent model
        try (InputStream inputStream = new FileInputStream(new File(
                Objects.requireNonNull(PosTagger_Performance.class.getClassLoader()
                        .getResource("en-pos-maxent.bin")).getFile()))) {
            POSModel model = new POSModel(inputStream);

            // Creating an object of WhitespaceTokenizer class
            WhitespaceTokenizer whitespaceTokenizer= WhitespaceTokenizer.INSTANCE;

            // Tokenizing the sentence
            String sentence = "Hi Devon, welcome to the ChatBot Demo.";
            String[] tokens = whitespaceTokenizer.tokenize(sentence);

            // Instantiating POSTaggerME class
            POSTaggerME tagger = new POSTaggerME(model);

            // Generating tags
            String[] tags = tagger.tag(tokens);

            // Instantiating POSSample class
            POSSample sample = new POSSample(tokens, tags);
            System.out.println(sample.toString());

            // Monitoring the performance of POS tagger
            PerformanceMonitor perfMon = new PerformanceMonitor(System.err, "sent");
            perfMon.start();
            perfMon.incrementCounter();
            perfMon.stopAndPrintFinalResult();
        } catch (Exception ex) {
            System.out.println("Exception while loading speech-maxent model...");
            ex.printStackTrace();
        }

    }
}
