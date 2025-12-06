package weapons.parser;

import weapons.model.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SAXParser extends DefaultHandler {
    private KnifeCollection collection;
    private Knife currentKnife;
    private Visual currentVisual;
    private Blade currentBlade;
    private Handle currentHandle;
    private StringBuilder currentText;
    private boolean inBlade;
    private boolean inHandle;

    public KnifeCollection parse(String filePath) throws Exception {
        collection = new KnifeCollection();
        currentText = new StringBuilder();
        
        SAXParserFactory factory = SAXParserFactory.newInstance();
        javax.xml.parsers.SAXParser parser = factory.newSAXParser();
        parser.parse(new File(filePath), this);
        
        return collection;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        currentText.setLength(0);
        
        switch (qName) {
            case "knife":
                currentKnife = new Knife();
                currentKnife.setId(attributes.getValue("id"));
                break;
            case "visual":
                currentVisual = new Visual();
                break;
            case "blade":
                currentBlade = new Blade();
                inBlade = true;
                break;
            case "handle":
                currentHandle = new Handle();
                inHandle = true;
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        currentText.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        String text = currentText.toString().trim();
        
        switch (qName) {
            case "knife":
                collection.addKnife(currentKnife);
                currentKnife = null;
                break;
            case "type":
                if (currentKnife != null) {
                    currentKnife.setType(text);
                }
                break;
            case "handy":
                if (currentKnife != null) {
                    currentKnife.setHandy(text);
                }
                break;
            case "origin":
                if (currentKnife != null) {
                    currentKnife.setOrigin(text);
                }
                break;
            case "value":
                if (currentKnife != null) {
                    currentKnife.setValue(Boolean.parseBoolean(text));
                }
                break;
            case "visual":
                if (currentKnife != null && currentVisual != null) {
                    currentKnife.addVisual(currentVisual);
                    currentVisual = null;
                }
                break;
            case "length":
                if (inBlade && currentBlade != null) {
                    currentBlade.setLength(new BigDecimal(text));
                }
                break;
            case "width":
                if (inBlade && currentBlade != null) {
                    currentBlade.setWidth(new BigDecimal(text));
                }
                break;
            case "material":
                if (currentVisual != null && !inHandle) {
                    currentVisual.setMaterial(text);
                } else if (inHandle && currentHandle != null) {
                    currentHandle.setMaterial(text);
                }
                break;
            case "woodType":
                if (inHandle && currentHandle != null) {
                    currentHandle.setWoodType(text);
                }
                break;
            case "bloodGroove":
                if (currentVisual != null) {
                    currentVisual.setBloodGroove(Boolean.parseBoolean(text));
                }
                break;
            case "blade":
                if (currentVisual != null) {
                    currentVisual.setBlade(currentBlade);
                    currentBlade = null;
                    inBlade = false;
                }
                break;
            case "handle":
                if (currentVisual != null) {
                    currentVisual.setHandle(currentHandle);
                    currentHandle = null;
                    inHandle = false;
                }
                break;
        }
    }
}

