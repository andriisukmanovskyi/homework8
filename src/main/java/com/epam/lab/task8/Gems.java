package com.epam.lab.task8;

import com.epam.lab.task3.model.Gem;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "gems")
@XmlAccessorType(XmlAccessType.FIELD)
public class Gems {

    @XmlElement(name = "gem")
    private List<Gem> gems;

    public Gems() {
    }

    public Gems(List<Gem> gems) {
        this.gems = gems;
    }

    public List<Gem> getGems() {
        return gems;
    }
}