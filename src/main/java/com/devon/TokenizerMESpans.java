package com.devon;

import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Objects;

public class TokenizerMESpans {
    public static void main(String[] args) {
        String sent = "Hi. How are you? Welcome to My ApacheNLP ChatBot. "
                + "This is an example of tokenization ME with spans.";

        //Loading the Tokenizer model
        try (InputStream inputStream = new FileInputStream(new File(
                Objects.requireNonNull(TokenizerMESpans.class.getClassLoader()
                        .getResource("en-token.bin")).getFile()))) {
            TokenizerModel tokenModel = new TokenizerModel(inputStream);

            // Instantiating the TokenizerME class
            TokenizerME tokenizer = new TokenizerME(tokenModel);

            // Retrieving the positions of the tokens
            Span[] tokens = tokenizer.tokenizePos(sent);

            // Printing the spans of tokens
            for(Span token : tokens)
                System.out.println(token +" "+sent.substring(token.getStart(), token.getEnd()));
        } catch (Exception ex) {
            System.out.println("Exception while running TokenizerMESpans...");
            ex.printStackTrace();
        }

    }
}
