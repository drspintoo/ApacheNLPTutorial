package com.devon;

import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.parser.Parse;
import opennlp.tools.parser.Parser;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Objects;

public class ParserExample {
    public static void main(String[] args) {
        // Loading parser model
        try (InputStream inputStream = new FileInputStream(new File(
                Objects.requireNonNull(ParserExample.class.getClassLoader()
                        .getResource("en-parser-chunking.bin")).getFile()))) {
            ParserModel model = new ParserModel(inputStream);

            // Creating a parser
            Parser parser = ParserFactory.create(model);

            // Parsing the sentence
            String sentence = "You are well on your way to learning NLP.";
            Parse[] topParses = ParserTool.parseLine(sentence, parser, 1);

            for (Parse p : topParses)
                p.show();
        } catch (Exception ex) {
            System.out.println("Exception while loading ParserModel...");
            ex.printStackTrace();
        }

    }
}
