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

public class NameFinderSentences {
    public static void main(String[] args) {

        // Loading the tokenizer model
        try (InputStream inputStreamTokenizer = new FileInputStream(new File(
                Objects.requireNonNull(NameFinderSentences.class.getClassLoader().getResource("en-token.bin")).getFile()))) {
            TokenizerModel tokenModel = new TokenizerModel(inputStreamTokenizer);

            // Instantiating the TokenizerME class
            TokenizerME tokenizer = new TokenizerME(tokenModel);

            // Tokenizing the sentence string into a String array using the tokenizer.
            String sentence = "Jack and Barbara are cousin who discovered other relatives " +
                    "like Mary, Peter, James, and John while visiting uncle Justin";
            String[] tokens = tokenizer.tokenize(sentence);

            // Loading the NER-person model
            try (InputStream inputStreamNameFinder = new FileInputStream(new File(
                    Objects.requireNonNull(NameFinderSentences.class.getClassLoader().getResource("en-ner-person.bin")).getFile()))) {
                TokenNameFinderModel model = new TokenNameFinderModel(inputStreamNameFinder);

                // Instantiating the NameFinderME class
                NameFinderME nameFinder = new NameFinderME(model);

                // Finding the names in the sentence
                Span[] nameSpans = nameFinder.find(tokens);

                // Printing the names from the String array along with the spans of the names found in the sentence
                for(Span s: nameSpans)
                    System.out.println(tokens[s.getStart()] + " is a " + s.toString());
            } catch (Exception ex) {
                System.out.println("Exception while loading TokenNameFinderModel...");
                ex.printStackTrace();
            }

        } catch (Exception ex) {
            System.out.println("Exception while running NameFinderSentences...");
            ex.printStackTrace();
        }

    }
}
