package com.devon;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Objects;

public class SentenceDetectionME {
    public static void main(String[] args) {

        String sentence = "Hi. How are you? Welcome to My ApacheNLP ChatBot. "
                + "This is an example of Sentence Detection using the SentenceDetectorME.";

        // Loading sentence detector model
        try (InputStream inputStream = new FileInputStream(new File(
                Objects.requireNonNull(SentenceDetectionME.class.getClassLoader()
                        .getResource("en-sent.bin")).getFile()))) {
            SentenceModel model = new SentenceModel(inputStream);

            // Instantiating the SentenceDetectorME class
            SentenceDetectorME detector = new SentenceDetectorME(model);

            // Detecting the sentence
            String[] sentences = detector.sentDetect(sentence);

            // Printing the sentences
            for(String sent : sentences)
                System.out.println(sent);

            // Getting the probabilities of the last decoded sequence
            double[] probs = detector.getSentenceProbabilities();

            System.out.println("  ");

            for (double prob : probs) System.out.println(prob);
        } catch (Exception ex) {
            System.out.println("Exception while running SentenceDetectionME...");
            ex.printStackTrace();
        }
    }
}
