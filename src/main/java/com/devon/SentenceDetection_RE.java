package com.devon;

public class SentenceDetection_RE {
    public static void main(String[] args) {

        String sentence = " Hi. How are you? Welcome to My ApacheNLP ChatBot. "
                + "This is an example of Sentence Detection Using Java.";

        String simple = "[.?!]";
        String[] splitString = (sentence.split(simple));
        for (String string : splitString)
            System.out.println(string);
    }
}
