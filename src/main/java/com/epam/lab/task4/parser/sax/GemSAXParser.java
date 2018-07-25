package com.epam.lab.task4.parser.sax;

import com.epam.lab.task3.model.Gem;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GemSAXParser {

    public static List<Gem> parseXML(File file) throws ParserConfigurationException, SAXException, IOException, NoSuchFieldException {
        List<Gem> list = new ArrayList<>();
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        DefaultHandler handler = new MyHandler(list);
        saxParser.parse(file, handler);
        return list;
    }
}