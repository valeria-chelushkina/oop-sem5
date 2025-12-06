package weapons.parser;

import weapons.model.*;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.math.BigDecimal;

public class StAXParser {
    
    public KnifeCollection parse(String filePath) throws Exception {
        KnifeCollection collection = new KnifeCollection();
        
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(filePath));
        
        Knife currentKnife = null;
        Visual currentVisual = null;
        Blade currentBlade = null;
        Handle currentHandle = null;
        String currentElement = null;
        boolean inBlade = false;
        boolean inHandle = false;
        
        while (reader.hasNext()) {
            int event = reader.next();
            
            switch (event) {
                case XMLStreamConstants.START_ELEMENT:
                    String elementName = reader.getLocalName();
                    currentElement = elementName;
                    
                    switch (elementName) {
                        case "knife":
                            currentKnife = new Knife();
                            String id = reader.getAttributeValue(null, "id");
                            if (id != null) {
                                currentKnife.setId(id);
                            }
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
                    break;
                    
                case XMLStreamConstants.CHARACTERS:
                    String text = reader.getText().trim();
                    if (text.isEmpty()) {
                        break;
                    }
                    
                    if (currentKnife != null) {
                        switch (currentElement) {
                            case "type":
                                currentKnife.setType(text);
                                break;
                            case "handy":
                                currentKnife.setHandy(text);
                                break;
                            case "origin":
                                currentKnife.setOrigin(text);
                                break;
                            case "value":
                                currentKnife.setValue(Boolean.parseBoolean(text));
                                break;
                        }
                    }
                    
                    if (currentVisual != null) {
                        switch (currentElement) {
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
                                currentVisual.setBloodGroove(Boolean.parseBoolean(text));
                                break;
                        }
                    }
                    break;
                    
                case XMLStreamConstants.END_ELEMENT:
                    String endElementName = reader.getLocalName();
                    
                    switch (endElementName) {
                        case "knife":
                            collection.addKnife(currentKnife);
                            currentKnife = null;
                            break;
                        case "visual":
                            if (currentKnife != null && currentVisual != null) {
                                currentKnife.addVisual(currentVisual);
                                currentVisual = null;
                            }
                            break;
                        case "blade":
                            if (currentVisual != null && currentBlade != null) {
                                currentVisual.setBlade(currentBlade);
                                currentBlade = null;
                                inBlade = false;
                            }
                            break;
                        case "handle":
                            if (currentVisual != null && currentHandle != null) {
                                currentVisual.setHandle(currentHandle);
                                currentHandle = null;
                                inHandle = false;
                            }
                            break;
                    }
                    currentElement = null;
                    break;
            }
        }
        
        reader.close();
        return collection;
    }
}

