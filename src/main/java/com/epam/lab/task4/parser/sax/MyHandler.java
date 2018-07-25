package com.epam.lab.task4.parser.sax;

import com.epam.lab.task3.enums.Color;
import com.epam.lab.task3.enums.Preciousness;
import com.epam.lab.task3.model.Gem;
import com.epam.lab.task3.model.VisualParams;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.List;

public class MyHandler extends DefaultHandler {

    private List<Gem> list;
    private Gem gem;
    private VisualParams visualParams;
    private String value;

    public MyHandler(List<Gem> list) {
        this.list = list;
    }

    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) {
        if (qName.equals("gem")) {
            gem = new Gem();
            visualParams = new VisualParams();
        }
    }

    public void endElement(String uri, String localName,
                           String qName) {
        if (qName.equals("gem"))
            list.add(gem);
        if (qName.equals("visualParams"))
            gem.setVisualParams(visualParams);
        if (qName.equals("name"))
            gem.setName(value);
        if (qName.equals("preciousness"))
            gem.setPreciousness(Preciousness.valueOf(value));
        if (qName.equals("origin"))
            gem.setOrigin(value);
        if (qName.equals("value"))
            gem.setValue(Integer.parseInt(value));
        if (qName.equals("color"))
            visualParams.setColor(Color.valueOf(value));
        if (qName.equals("transparency"))
            visualParams.setTransparency(Double.parseDouble(value));
        if (qName.equals("facesNumber"))
            visualParams.setFacesNumber(Integer.parseInt(value));
    }

    public void characters(char ch[], int start, int length) {
        value = new String(ch, start, length);
    }
}