package com.devon;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Objects;

public class LocationFinder {
    public static void main(String[] args) {

        try (InputStream inputStream = new FileInputStream(new File(
                Objects.requireNonNull(LocationFinder.class.getClassLoader().getResource("en-token.bin")).getFile()))) {
            TokenizerModel tokenModel = new TokenizerModel(inputStream);

            String paragraph = "Ocho Rios is located in Jamaica.";

            // Instantiating the TokenizerME class
            TokenizerME tokenizer = new TokenizerME(tokenModel);
            String[] tokens = tokenizer.tokenize(paragraph);

            // Loading the NER-location model
            try (InputStream inputStreamNameFinder = new FileInputStream(new File(
                    Objects.requireNonNull(LocationFinder.class.getClassLoader().getResource("en-ner-location.bin")).getFile()))) {
                TokenNameFinderModel model = new TokenNameFinderModel(inputStreamNameFinder);

                // Instantiating the NameFinderME class
                NameFinderME nameFinder = new NameFinderME(model);

                // Finding the names of a location
                Span[] nameSpans = nameFinder.find(tokens);
                // Printing the spans of the locations in the sentence
                for(Span s: nameSpans)
                    System.out.println(s.toString() + "  " + tokens[s.getStart()]);
            } catch (Exception ex) {
                System.out.println("Exception while loading NER-location model...");
                ex.printStackTrace();
            }

        } catch (Exception ex) {
            System.out.println("Exception while running LocationFinder...");
            ex.printStackTrace();
        }
    }
}
