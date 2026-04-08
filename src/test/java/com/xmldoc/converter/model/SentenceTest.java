package com.xmldoc.converter.model;


import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SentenceTest {

    Sentence s = new Sentence();

    @Test
    void testAddTextWordToTheListVerify(){
        String inp = "XmlConverter";
        String inp1 = "CsvConverter";
        s.addTextWords(inp);
        s.addTextWords(inp1);
        assertThat(s.getTextWords().size()).isEqualTo(2);
        assertThat(s.getTextWords().get(0)).isEqualTo("XmlConverter");
        assertThat(s.getTextWords().get(1)).isEqualTo("CsvConverter");

    }

    @Test
    void testAddNullWordToTheListVerify(){
        String inp = "   ";
        s.addTextWords(inp);
        assertThat(s.getTextWords().size()).isEqualTo(1);

    }

    @Test
    void testIsSortedTextVerify(){
        String inp = "Xml";
        String inp1 = "Java";
        String inp2 = "C++";
        s.addTextWords(inp);
        s.addTextWords(inp1);
        s.addTextWords(inp2);
        s.sortTextWords();
        assertThat(s.getTextWords()).isSorted();
        assertThat(s.getTextWords().get(0)).isEqualTo("C++");
        assertThat(s.getTextWords().get(1)).isEqualTo("Java");
        assertThat(s.getTextWords().get(2)).isEqualTo("Xml");

    }
}
