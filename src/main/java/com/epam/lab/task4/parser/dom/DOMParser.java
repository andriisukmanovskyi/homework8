package com.epam.lab.task4.parser.dom;


import com.epam.lab.task3.enums.Color;
import com.epam.lab.task3.enums.Preciousness;
import com.epam.lab.task3.model.Gem;
import com.epam.lab.task3.model.VisualParams;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DOMParser {

    public static List<Gem> parseXML(File file) throws IOException, SAXException, ParserConfigurationException {
        List<Gem> list = new ArrayList<>();
        NodeList nodeList = getNodeList(file);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                Element innerElement = (Element) element.getElementsByTagName("visualParams").item(0);
                list.add(getGem(element, innerElement));
            }
        }
        return list;
    }

    private static NodeList getNodeList(File file) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);
        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName("gem");
    }

    private static Gem getGem(Element element, Element innerElement) {
        return new Gem(element.getElementsByTagName("name").item(0).getTextContent(),
                Preciousness.valueOf(element.getElementsByTagName("preciousness").item(0).getTextContent()),
                element.getElementsByTagName("origin").item(0).getTextContent(),
                getVisualParams(innerElement),
                Integer.parseInt(element.getElementsByTagName("value").item(0).getTextContent()));
    }

    private static VisualParams getVisualParams(Element innerElement) {
        return new VisualParams(
                Color.valueOf(innerElement.getElementsByTagName("color").item(0).getTextContent()),
                Double.parseDouble(innerElement.getElementsByTagName("transparency").item(0).getTextContent()),
                Integer.parseInt(innerElement.getElementsByTagName("facesNumber").item(0).getTextContent()));
    }
}