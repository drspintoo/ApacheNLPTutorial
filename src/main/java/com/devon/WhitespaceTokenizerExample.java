package com.devon;

import opennlp.tools.tokenize.WhitespaceTokenizer;

public class WhitespaceTokenizerExample {
    public static void main(String[] args){

        String sentence = "Hi. How are you? Welcome to My ApacheNLP ChatBot. "
                + "This is an example of whitespace tokenization.";

        // Instantiating whitespaceTokenizer class
        WhitespaceTokenizer whitespaceTokenizer = WhitespaceTokenizer.INSTANCE;

        // Tokenizing the given paragraph
        String[] tokens = whitespaceTokenizer.tokenize(sentence);

        // Printing the tokens
        for(String token : tokens)
            System.out.println(token);
    }
}
