package com.devon;

import opennlp.tools.tokenize.SimpleTokenizer;

public class SimpleTokenizerExample {
    public static void main(String[] args){

        String sentence = "Hi. How are you? Welcome to My ApacheNLP ChatBot."
                + "This is an example of sentence tokenization.";

        // Instantiating SimpleTokenizer class
        SimpleTokenizer simpleTokenizer = SimpleTokenizer.INSTANCE;

        // Tokenizing the given sentence
        String[] tokens = simpleTokenizer.tokenize(sentence);

        // Printing the tokens
        for(String token : tokens) {
            System.out.println(token);
        }
    }
}
