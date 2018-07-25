package com.epam.lab.task6_7.XSLTransformer;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class XSLTransformer {

    public static void transformXML(File xmlFile, File xslFile, File outputFile) throws FileNotFoundException, TransformerException {
        TransformerFactory tFactory = TransformerFactory.newInstance();
        Source xslDoc = new StreamSource(xslFile);
        Source xmlDoc = new StreamSource(xmlFile);
        OutputStream htmlFile = new FileOutputStream(outputFile);
        Transformer transform = tFactory.newTransformer(xslDoc);
        transform.transform(xmlDoc, new StreamResult(htmlFile));
    }
}