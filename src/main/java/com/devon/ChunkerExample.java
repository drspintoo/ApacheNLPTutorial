package com.devon;

import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.WhitespaceTokenizer;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Objects;

public class ChunkerExample {
    public static void main(String[] args) {
        // Tokenizing the sentence
        String sentence = "Hi and welcome to our ChatBot Demo.";
        WhitespaceTokenizer whitespaceTokenizer = WhitespaceTokenizer.INSTANCE;
        String[] tokens = whitespaceTokenizer.tokenize(sentence);

        // Generating the POS tags
        // Load the parts of speech model
        File file = new File(Objects.requireNonNull(ChunkerExample.class.getClassLoader().getResource("en-pos-maxent.bin")).getFile());
        POSModel model = new POSModelLoader().load(file);

        // Constructing the tagger
        POSTaggerME tagger = new POSTaggerME(model);

        // Generating tags from the tokens
        String[] tags = tagger.tag(tokens);

        // Loading the chunker model
        try (InputStream inputStream = new FileInputStream(new File(
                Objects.requireNonNull(ChunkerExample.class.getClassLoader().getResource("en-chunker.bin")).getFile()))) {
            ChunkerModel chunkerModel = new ChunkerModel(inputStream);

            // Instantiate the ChunkerME class
            ChunkerME chunkerME = new ChunkerME(chunkerModel);

            // Generating the chunks
            String[] result = chunkerME.chunk(tokens, tags);

            for (String s : result)
                System.out.println(s);
        } catch (Exception ex) {
            System.out.println("Exception while chunking...");
            ex.printStackTrace();
        }

    }
}
