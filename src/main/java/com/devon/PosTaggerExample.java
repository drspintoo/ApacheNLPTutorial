package com.devon;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.WhitespaceTokenizer;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Objects;

public class PosTaggerExample {
    public static void main(String[] args) {

        // Loading Parts of speech-maxent model
        try (InputStream inputStream = new FileInputStream(new File(
                Objects.requireNonNull(PosTaggerExample.class.getClassLoader()
                        .getResource("en-pos-maxent.bin")).getFile()))) {
            POSModel model = new POSModel(inputStream);

            // Instantiating POSTaggerME class
            POSTaggerME tagger = new POSTaggerME(model);

            String sentence = "Hi Devon, welcome to my ChatBot demo!";

            // Tokenizing the sentence using WhitespaceTokenizer class
            WhitespaceTokenizer whitespaceTokenizer= WhitespaceTokenizer.INSTANCE;
            String[] tokens = whitespaceTokenizer.tokenize(sentence);

            // Generating tags
            String[] tags = tagger.tag(tokens);

            // Instantiating the POSSample class
            POSSample sample = new POSSample(tokens, tags);
            System.out.println(sample.toString());
        } catch (Exception ex) {
            System.out.println("Exception while loading speech-maxent model...");
            ex.printStackTrace();
        }

    }
}
