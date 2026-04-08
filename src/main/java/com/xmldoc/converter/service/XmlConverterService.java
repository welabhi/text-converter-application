package com.xmldoc.converter.service;

import com.xmldoc.converter.model.Sentence;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class XmlConverterService {

    public String convertToXml(List<Sentence> textSentences){

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n");

        for(Sentence s: textSentences){
            stringBuilder.append("<sentence>\n");

            for(String str: s.getTextWords()) {
                stringBuilder.append("        <word>").append(str).append("</word>\n");
            }
            stringBuilder.append("    </sentence>\n");
        }
        stringBuilder.append("</text>");

        return stringBuilder.toString();
    }

}
