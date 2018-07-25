package com.epam.lab.task4.parser.stax;

import com.epam.lab.task3.enums.Color;
import com.epam.lab.task3.enums.Preciousness;
import com.epam.lab.task3.model.Gem;
import com.epam.lab.task3.model.VisualParams;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class GemSTAXParser {

    private static List<Gem> list;
    private static Gem gem;
    private static VisualParams visualParams;

    public static List<Gem> parseXML(File file) throws XMLStreamException, FileNotFoundException {
        list = new ArrayList<>();
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader eventReader = factory.createXMLEventReader(new FileReader(file));
        while (eventReader.hasNext()) {
            XMLEvent event = eventReader.nextEvent();
            if (event.isStartElement()) {
                handleStartElement(eventReader, event);
            }else if (event.isEndElement()) {
                handleEndElement(event);
            }
        }
        return list;
    }

    private static void handleEndElement(XMLEvent event) {
        if(event.asEndElement().getName().getLocalPart().equals("visualParams"))
            gem.setVisualParams(visualParams);
        else if(event.asEndElement().getName().getLocalPart().equals("gem"))
            list.add(gem);
    }

    private static void handleStartElement(XMLEventReader eventReader, XMLEvent event) throws XMLStreamException {
        StartElement startElement = event.asStartElement();
        String startElementName = startElement.getName().getLocalPart();
        if (startElementName.equals("gem")) {
            gem = new Gem();
            visualParams = new VisualParams();
        } else if (startElementName.equals("name")) {
            event = eventReader.nextEvent();
            gem.setName(event.asCharacters().getData());
        } else if (startElementName.equals("preciousness")) {
            event = eventReader.nextEvent();
            gem.setPreciousness(Preciousness.valueOf(event.asCharacters().getData()));
        } else if (startElementName.equals("origin")) {
            event = eventReader.nextEvent();
            gem.setOrigin(event.asCharacters().getData());
        } else if (startElementName.equals("value")) {
            event = eventReader.nextEvent();
            gem.setValue(Integer.parseInt(event.asCharacters().getData()));
        } else if (startElementName.equals("color")) {
            event = eventReader.nextEvent();
            visualParams.setColor(Color.valueOf(event.asCharacters().getData()));
        } else if (startElementName.equals("transparency")) {
            event = eventReader.nextEvent();
            visualParams.setTransparency(Double.parseDouble(event.asCharacters().getData()));
        } else if (startElementName.equals("facesNumber")) {
            event = eventReader.nextEvent();
            visualParams.setFacesNumber(Integer.parseInt(event.asCharacters().getData()));
        }
    }
}