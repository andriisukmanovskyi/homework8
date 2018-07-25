package com.epam.lab;


import com.epam.lab.service.MainService;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import java.io.*;

public class Main {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, NoSuchFieldException, XMLStreamException, TransformerException, JAXBException {
        MainService.startService();
    }
}