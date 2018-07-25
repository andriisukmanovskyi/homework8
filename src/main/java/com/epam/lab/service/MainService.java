package com.epam.lab.service;

import com.epam.lab.task3.enums.Color;
import com.epam.lab.task3.enums.Preciousness;
import com.epam.lab.task3.model.Gem;
import com.epam.lab.task3.model.VisualParams;
import com.epam.lab.task4.parser.dom.DOMParser;
import com.epam.lab.task4.parser.sax.GemSAXParser;
import com.epam.lab.task4.parser.stax.GemSTAXParser;
import com.epam.lab.task5.XMLValidation;
import com.epam.lab.task6_7.XSLTransformer.XSLTransformer;
import com.epam.lab.task6_7.comparator.GemValueComparator;
import com.epam.lab.task8.JAXBService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainService {
    private static final Logger LOG = LogManager.getLogger(MainService.class.getName());

    private static final String GEMS_XML = "src/main/resources/task1_2/gems.xml";
    private static final String GEMS_XSD = "src/main/resources/task1_2/gems.xsd";
    private static final String GEMS_XSL = "src/main/resources/task6/gems.xsl";
    private static final String RENAME_ROOT_GEM_XSL = "src/main/resources/task6/rename_gem.xsl";
    private static final String OUTPUT_MARSHALED_GEMS_XML = "output/marshaled_gems.xml";
    private static final String OUTPUT_NEW_GEMS_ROOT_XML = "output/new_gems_root.xml";
    private static final String OUTPUT_SORTED_GEMS_XML = "output/sorted_gems.xml";
    private static final String OUTPUT_GEMS_HTML = "output/gems.html";

    public static void startService() throws IOException, SAXException, ParserConfigurationException, NoSuchFieldException, XMLStreamException, JAXBException, TransformerException {
        File xmlFile = new File(GEMS_XML);
        File xsdFile = new File(GEMS_XSD);
        File xslFile = new File(GEMS_XSL);
        File renameRootXslFile = new File(RENAME_ROOT_GEM_XSL);
        parseWithDOM(xmlFile);
        parseWithSAX(xmlFile);
        parseWithSTAX(xmlFile);
        validateXML(xmlFile, xsdFile);
        writeSortedHTML(xmlFile, xslFile);
        renameRoot(xmlFile, renameRootXslFile);
        marshallGems(createGemsList());
        unmarshallGems(xmlFile);
    }

    private static List<Gem> parseWithDOM(File xmlFile) throws ParserConfigurationException, SAXException, IOException {
        LOG.info("\nDOM parser:");
        List<Gem> gems = DOMParser.parseXML(xmlFile);
        gems.forEach(LOG::info);
        return gems;
    }

    private static void parseWithSAX(File xmlFile) throws ParserConfigurationException, NoSuchFieldException, SAXException, IOException {
        LOG.info("\nSAX parser:");
        List<Gem> gems = GemSAXParser.parseXML(xmlFile);
        gems.forEach(LOG::info);
    }

    private static void parseWithSTAX(File xmlFile) throws IOException, XMLStreamException {
        LOG.info("\nStAX parser:");
        List<Gem> gems = GemSTAXParser.parseXML(xmlFile);
        gems.forEach(LOG::info);
    }

    private static void validateXML(File xmlFile, File xsdFile) throws FileNotFoundException {
        boolean valid = XMLValidation.validateAgainstXSD(new FileReader(xmlFile), new FileReader(xsdFile));
        LOG.info("\nValidation:\n" + String.format(valid ? "XML is valid" : "XML is NOT valid"));
    }

    private static void writeSortedHTML(File xmlFile, File xslFile) throws ParserConfigurationException, SAXException, IOException, JAXBException, TransformerException {
        LOG.info("\nHTML creation:");
        createOutputDirectory();
        List<Gem> gems = DOMParser.parseXML(xmlFile);
        LOG.info("XML is parsed");
        gems.sort(new GemValueComparator());
        LOG.info("XML is sorted by <Gem.value>");
        File sortedXmlFile = new File(OUTPUT_SORTED_GEMS_XML);
        JAXBService jaxbService = new JAXBService(gems);
        jaxbService.marshallObjects(sortedXmlFile);
        LOG.info("New sorted XML is created");
        XSLTransformer.transformXML(sortedXmlFile, xslFile, new File(OUTPUT_GEMS_HTML));
        LOG.info("HTML is successfully created");
    }

    private static void renameRoot(File xmlFile, File xslFile) throws FileNotFoundException, TransformerException {
        LOG.info("\nRoot name changing:");
        createOutputDirectory();
        XSLTransformer.transformXML(xmlFile, xslFile, new File(OUTPUT_NEW_GEMS_ROOT_XML));
        LOG.info("Root name is successfully changed");
    }

    private static void marshallGems(List<Gem> gems) {
        LOG.info("\nGems marshalling:");
        createOutputDirectory();
        JAXBService jaxbService = new JAXBService(gems);
        jaxbService.marshallObjects(new File(OUTPUT_MARSHALED_GEMS_XML));
        LOG.info("Collection of gems is successfully marshaled");
    }

    private static void unmarshallGems(File xmlFile) throws JAXBException {
        LOG.info("\nGems unmarshalling:");
        JAXBService jaxbService = new JAXBService();
        List<Gem> gems = jaxbService.unmarshallXML(xmlFile);
        gems.forEach(LOG::info);
    }

    private static List<Gem> createGemsList() {
        List<Gem> gems = new ArrayList<>();
        gems.add(new Gem("someGem444", Preciousness.PRECIOUS, "Canada,Toronto",
                new VisualParams(Color.PINK, 0.55, 7), 4));
        gems.add(new Gem("someGem555", Preciousness.SEMIPRECIOUS, "Singapore, Singapore",
                new VisualParams(Color.PINK, 0.78, 11), 2));
        gems.add(new Gem("someGem888", Preciousness.PRECIOUS, "South Africa,  Cape Town",
                new VisualParams(Color.PINK, 0.97, 9), 6));
        return gems;
    }

    private static void createOutputDirectory() {
        File directory = new File("output");
        if (!directory.exists()) {
            directory.mkdir();
        }
    }
}