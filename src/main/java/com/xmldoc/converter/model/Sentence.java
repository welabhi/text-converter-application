package com.xmldoc.converter.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * This  class is used to Represents a sentence which will have collection of sorted words.
 *
 */
@Getter
public class Sentence {

    private List<String> textWords = new ArrayList<>();


    public void addTextWords(String wordText) {
        textWords.add(wordText);
    }


    public void sortTextWords() {
//       Collections.sort(textWords);
        textWords.sort(String.CASE_INSENSITIVE_ORDER);
    }

}
