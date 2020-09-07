package com.devon;

import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.Span;

public class WhitespaceTokenizerSpans {
    public static void main(String[] args){

        String sent = "Hi. How are you? Welcome to My ApacheNLP ChatBot. "
                + "This is an example of whitespace tokenization with spans.";

        // Instantiating SimpleTokenizer class
        WhitespaceTokenizer whitespaceTokenizer = WhitespaceTokenizer.INSTANCE;

        // Retrieving the tokens
        Span[] tokens = whitespaceTokenizer.tokenizePos(sent);

        // Printing the spans of tokens
        for( Span token : tokens)
            System.out.println(token + " " + sent.substring(token.getStart(), token.getEnd()));
    }
}
