package com.epam.lab.task3.model;

import com.epam.lab.task3.enums.Color;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "visualParams")
@XmlAccessorType(XmlAccessType.FIELD)
public class VisualParams {

    private Color color;
    private double transparency;
    private int facesNumber;

    public VisualParams(Color color, double transparency, int facesNumber) {
        this.color = color;
        this.transparency = checkTransparency(transparency);
        this.facesNumber = checkFacesNumber(facesNumber);
    }

    public VisualParams() {

    }

    public Color getColor() {
        return color;
    }

    public double getTransparency() {
        return transparency;
    }

    public int getFacesNumber() {
        return facesNumber;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setTransparency(double transparency) {
        this.transparency = transparency;
    }

    public void setFacesNumber(int facesNumber) {
        this.facesNumber = facesNumber;
    }

    private double checkTransparency(double transparency) {
        if (transparency < 0)
            return 0;
        else if (transparency > 1)
            return 1;
        else
            return transparency;
    }

    private int checkFacesNumber(int facesNumber) {
        if (facesNumber < 4)
            return 4;
        else if (facesNumber > 15)
            return 15;
        else
            return facesNumber;
    }

    @Override
    public String toString() {
        return "VisualParams{" +
                "color=" + color +
                ", transparency=" + transparency +
                ", facesNumber=" + facesNumber +
                '}';
    }
}