package com.epam.lab.task8;

import com.epam.lab.task3.model.Gem;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class JAXBService {

    private List<Gem> gems;

    public JAXBService(List<Gem> gems) {
        this.gems = gems;
    }

    public JAXBService() {

    }

    public boolean marshallObjects(File marshaledXmlFile) {
        try {
            Gems gems = new Gems(this.gems);
            JAXBContext jaxbContext = JAXBContext.newInstance(Gems.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(gems, marshaledXmlFile);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public List<Gem> unmarshallXML(File xmlFile) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Gems.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return ((Gems) unmarshaller.unmarshal(xmlFile)).getGems();
    }
}