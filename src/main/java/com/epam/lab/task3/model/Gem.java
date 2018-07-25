package com.epam.lab.task3.model;

import com.epam.lab.task3.enums.Preciousness;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "gem")
@XmlAccessorType(XmlAccessType.FIELD)
public class Gem {

    private String name;
    private Preciousness preciousness;
    private String origin;
    private VisualParams visualParams;
    private int value;

    public Gem(String name, Preciousness preciousness, String origin, VisualParams visualParams, int value) {
        this.name = name;
        this.preciousness = preciousness;
        this.origin = origin;
        this.visualParams = visualParams;
        this.value = value;
    }

    public Gem() {
    }

    public String getName() {
        return name;
    }

    public Preciousness getPreciousness() {
        return preciousness;
    }

    public String getOrigin() {
        return origin;
    }

    public VisualParams getVisualParams() {
        return visualParams;
    }

    public int getValue() {
        return value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPreciousness(Preciousness preciousness) {
        this.preciousness = preciousness;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setVisualParams(VisualParams visualParams) {
        this.visualParams = visualParams;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Gem{" +
                "name='" + name + '\'' +
                ", preciousness=" + preciousness +
                ", origin='" + origin + '\'' +
                ", visualParams=" + visualParams.toString() +
                ", value=" + value +
                '}';
    }
}