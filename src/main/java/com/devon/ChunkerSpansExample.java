package com.devon;

import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.Span;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Objects;

public class ChunkerSpansExample {
    public static void main(String[] args) {
        // Load the parts of speech model
        File file = new File(Objects.requireNonNull(ChunkerSpansExample.class.getClassLoader().getResource("en-pos-maxent.bin")).getFile());
        POSModel model = new POSModelLoader().load(file);

        // Constructing the tagger
        POSTaggerME tagger = new POSTaggerME(model);

        // Tokenizing the sentence
        String sentence = "Hello, and welcome to the ChatBot demo!";
        WhitespaceTokenizer whitespaceTokenizer = WhitespaceTokenizer.INSTANCE;
        String[] tokens = whitespaceTokenizer.tokenize(sentence);

        //Generating tags from the tokens
        String[] tags = tagger.tag(tokens);

        // Loading the chunker model
        try (InputStream inputStream = new FileInputStream(new File(
                Objects.requireNonNull(ChunkerSpansExample.class.getClassLoader().getResource("en-chunker.bin")).getFile()))) {
            ChunkerModel chunkerModel = new ChunkerModel(inputStream);
            ChunkerME chunkerME = new ChunkerME(chunkerModel);

            //Generating the tagged chunk spans
            Span[] span = chunkerME.chunkAsSpans(tokens, tags);

            for (Span s : span)
                System.out.println(s.toString());
        } catch (Exception ex) {
            System.out.println("Exception while chunking...");
            ex.printStackTrace();
        }

    }
}
