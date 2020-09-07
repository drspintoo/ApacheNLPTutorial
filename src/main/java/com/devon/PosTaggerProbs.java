package com.devon;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.WhitespaceTokenizer;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Objects;

public class PosTaggerProbs {
    public static void main(String[] args) {

        // Loading Parts of speech-maxent model
        try (InputStream inputStream = new FileInputStream(new File(
                Objects.requireNonNull(PosTaggerProbs.class.getClassLoader()
                        .getResource("en-pos-maxent.bin")).getFile()))) {
            POSModel model = new POSModel(inputStream);

            // Creating an object of WhitespaceTokenizer class
            WhitespaceTokenizer whitespaceTokenizer= WhitespaceTokenizer.INSTANCE;

            // Tokenizing the sentence
            String sentence = "Hi and welcome to the ChatBot Demo.";
            String[] tokens = whitespaceTokenizer.tokenize(sentence);

            // Instantiating POSTaggerME class
            POSTaggerME tagger = new POSTaggerME(model);

            // Generating tags
            String[] tags = tagger.tag(tokens);

            // Instantiating the POSSample class
            POSSample sample = new POSSample(tokens, tags);
            System.out.println(sample.toString());

            // Probabilities for each tag of the last tagged sentence.
            double [] probs = tagger.probs();
            System.out.println("  ");

            // Printing the probabilities
            for (double prob : probs) System.out.println(prob);
        } catch (Exception ex) {
            System.out.println("Exception while running PosTaggerProbs...");
            ex.printStackTrace();
        }


    }
}
