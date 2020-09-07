package com.devon;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.Span;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Objects;

public class NameFinderME_Example {
    public static void main(String[] args) {
        // Loading the NER - Person model
        try (InputStream inputStream = new FileInputStream(new File(
                Objects.requireNonNull(NameFinderME_Example.class.getClassLoader().getResource("en-ner-person.bin")).getFile()))) {
            TokenNameFinderModel model = new TokenNameFinderModel(inputStream);

            // Instantiating the NameFinder class
            NameFinderME nameFinder = new NameFinderME(model);

            // Building the sentence directly in the form of a String array
            String[] sentence = new String[] {
                    "Jack",
                    "and",
                    "Barbara",
                    "are",
                    "cousins",
                    "who",
                    "discovered",
                    "other",
                    "relatives",
                    "like",
                    "Mary",
                    ",",
                    "Peter",
                    ",",
                    "James",
                    ",",
                    "and",
                    "John",
                    "while",
                    "visiting",
                    "uncle",
                    "Justin"
            };

            // Finding the names in the sentence
            Span[] nameSpans = nameFinder.find(sentence);

            // Printing the names from the String array along with the spans of the names found in the sentence
            for(Span s: nameSpans)
                System.out.println(sentence[s.getStart()] + " is a " + s.toString());
        } catch (Exception ex) {
            System.out.println("Exception while running NameFinderME_Example...");
            ex.printStackTrace();
        }

    }
}
