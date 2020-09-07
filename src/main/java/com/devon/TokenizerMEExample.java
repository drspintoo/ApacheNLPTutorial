package com.devon;

import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Objects;

public class TokenizerMEExample {
    public static void main(String[] args) {

        String sentence = "Hi. How are you? Welcome to My ApacheNLP ChatBot. "
                + "This is an example of using Maximum Entropy tokenization.";

        // Loading the Tokenizer model
        try (InputStream inputStream = new FileInputStream(new File(
                Objects.requireNonNull(TokenizerMEExample.class.getClassLoader()
                        .getResource("en-token.bin")).getFile()))) {
            TokenizerModel tokenModel = new TokenizerModel(inputStream);

            // Instantiating the TokenizerME class
            TokenizerME tokenizer = new TokenizerME(tokenModel);

            // Tokenizing the given raw text
            String[] tokens = tokenizer.tokenize(sentence);

            //Printing the tokens
            for (String a : tokens)
                System.out.println(a);
        } catch (Exception ex) {
            System.out.println("Exception while running TokenizerMEExample...");
            ex.printStackTrace();
        }

    }
}
