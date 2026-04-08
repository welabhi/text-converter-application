package com.xmldoc.converter.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import static org.assertj.core.api.Assertions.assertThat;

public class TextParserServiceTest {


    private TextParserService textParserService = new TextParserService();

    private File xmlFile;


    @BeforeEach
    void setUp() throws IOException {
        xmlFile = new File("target/output.xml");
        deleteFileIfExists(xmlFile);
    }

    private void deleteFileIfExists(File file) throws IOException {
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void testParseTextToXmlWithSmallInput() throws Exception {

        String input = "Mary had a little lamb. Peter called for the wolf!";

        InputStream inputStream = new ByteArrayInputStream(input.getBytes());

        // Execute
        textParserService.textParserToXml(inputStream, xmlFile);

        // Read outputs
        String xmlOutput = Files.readString(xmlFile.toPath());

        assertThat(xmlOutput).startsWith("<?xml version=\"1.0\"");
        assertThat(xmlOutput).contains("<text>");
        assertThat(xmlOutput).contains("</text>");

        // Check words (sorted)
        assertThat(xmlOutput).contains("<word>a</word>");
        assertThat(xmlOutput).contains("<word>lamb</word>");
        assertThat(xmlOutput).contains("<word>Mary</word>");

        // Check sentence structure
        assertThat(xmlOutput).contains("<sentence>");
        assertThat(xmlOutput).contains("</sentence>");

    }

    @Test
    void testParseTextToXmlWithSmallInputFile() throws Exception {

        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("small.txt");

        // Execute
        textParserService.textParserToXml(resourceAsStream, xmlFile);

        // Read outputs
        String xmlOutput = Files.readString(xmlFile.toPath());

        assertThat(xmlOutput).startsWith("<?xml version=\"1.0\"");
        assertThat(xmlOutput).contains("<text>");
        assertThat(xmlOutput).contains("</text>");

        // Check words (sorted)
        assertThat(xmlOutput).contains("<word>couldn</word>");
        assertThat(xmlOutput).contains("<word>understand</word>");

        // Check sentence structure
        assertThat(xmlOutput).contains("<sentence>");
        assertThat(xmlOutput).contains("</sentence>");

    }

    @Test
    void testParseTextToXmlWithLargeInputFile() throws Exception {

        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("large.in");

        // Execute
        textParserService.textParserToXml(resourceAsStream, xmlFile);

        // Read outputs
        String xmlOutput = Files.readString(xmlFile.toPath());

        assertThat(xmlOutput).startsWith("<?xml version=\"1.0\"");
        assertThat(xmlOutput).contains("<text>");
        assertThat(xmlOutput).contains("</text>");

        // Check words (sorted)
//        assertThat(xmlOutput).contains("<word>sdef</word>");
//        assertThat(xmlOutput).contains("<word>99877</word>");

        // Check sentence structure
        assertThat(xmlOutput).contains("<sentence>");
        assertThat(xmlOutput).contains("</sentence>");

    }
}

