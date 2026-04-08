package com.xmldoc.converter.service;

import com.xmldoc.converter.model.Sentence;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This Service is responsible for parsing raw text input into structured objects.
 * <p>
 * The parser reads text from an InputStream, splits it into sentences,
 * extracts words using a regular expression, and sorts the words within each sentence.
 * </p>
 *
 * <p>
 * Sentences are identified based on punctuation delimiters such as '.', '!', and '?'.
 * Words are extracted using a Unicode-aware pattern that matches letter sequences.
 * </p>
 */
@Service
public class TextParserService {


    /**
     * This expression will extract only words and ignoring special chars
     */
    private static final Pattern TEXT_PATTERN = Pattern.compile("\\b\\p{L}+\\b");

    public void textParserToXml(InputStream inputStream, File outFile) throws IOException {

        //Scanner scanner = new Scanner(inputStream);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        BufferedWriter xml = new BufferedWriter(new FileWriter(outFile));

        xmlHeaderWriter(xml);

        String lineText;

        while ((lineText = bufferedReader.readLine()) != null) {
            List<Sentence> strTexts = parseLineToSentences(lineText);

            for (Sentence s : strTexts) {
                writeSentenceToXml(s, xml);
            }

        }
        writeXmlFooter(xml);
    }

    private void writeSentenceToXml(Sentence sentence, BufferedWriter bw) throws IOException {
        bw.write("    <sentence>\n");
        for (String word : sentence.getTextWords()) {
            bw.write("        <word>" + word + "</word>\n");
        }
        bw.write("    </sentence>\n");
    }

    private void xmlHeaderWriter(BufferedWriter bw) throws IOException {
        bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n");
        bw.write("<text>\n");
    }

    private List<Sentence> parseLineToSentences(String line) {
        List<Sentence> textSentences = new ArrayList<>();
        String[] strTexts = line.split("[.!?]+");

        for (String s : strTexts) {
            Matcher matcher = TEXT_PATTERN.matcher(s);
            Sentence sentence = new Sentence();

            while (matcher.find()) {
                sentence.addTextWords(matcher.group());
            }

            if (!sentence.getTextWords().isEmpty()) {
                sentence.sortTextWords();
                textSentences.add(sentence);
            }
        }

        return textSentences;
    }

    private void writeXmlFooter(BufferedWriter bw) throws IOException {
        bw.write("</text>");
        bw.flush();
    }
}

