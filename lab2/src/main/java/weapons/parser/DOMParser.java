package weapons.parser;

import weapons.model.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.math.BigDecimal;

public class DOMParser {
    
    public KnifeCollection parse(String filePath) throws Exception {
        KnifeCollection collection = new KnifeCollection();
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(filePath));
        
        document.getDocumentElement().normalize();
        
        NodeList knifeNodes = document.getElementsByTagName("knife");
        
        for (int i = 0; i < knifeNodes.getLength(); i++) {
            Node knifeNode = knifeNodes.item(i);
            if (knifeNode.getNodeType() == Node.ELEMENT_NODE) {
                Element knifeElement = (Element) knifeNode;
                Knife knife = parseKnife(knifeElement);
                collection.addKnife(knife);
            }
        }
        
        return collection;
    }
    
    private Knife parseKnife(Element knifeElement) {
        Knife knife = new Knife();
        knife.setId(knifeElement.getAttribute("id"));
        knife.setType(getElementText(knifeElement, "type"));
        knife.setHandy(getElementText(knifeElement, "handy"));
        knife.setOrigin(getElementText(knifeElement, "origin"));
        knife.setValue(Boolean.parseBoolean(getElementText(knifeElement, "value")));
        
        NodeList visualNodes = knifeElement.getElementsByTagName("visual");
        for (int i = 0; i < visualNodes.getLength(); i++) {
            Node visualNode = visualNodes.item(i);
            if (visualNode.getNodeType() == Node.ELEMENT_NODE) {
                Visual visual = parseVisual((Element) visualNode);
                knife.addVisual(visual);
            }
        }
        
        return knife;
    }
    
    private Visual parseVisual(Element visualElement) {
        Visual visual = new Visual();
        
        Element bladeElement = (Element) visualElement.getElementsByTagName("blade").item(0);
        if (bladeElement != null) {
            Blade blade = new Blade();
            blade.setLength(new BigDecimal(getElementText(bladeElement, "length")));
            blade.setWidth(new BigDecimal(getElementText(bladeElement, "width")));
            visual.setBlade(blade);
        }
        
        visual.setMaterial(getElementText(visualElement, "material"));
        
        Element handleElement = (Element) visualElement.getElementsByTagName("handle").item(0);
        if (handleElement != null) {
            Handle handle = new Handle();
            handle.setMaterial(getElementText(handleElement, "material"));
            String woodType = getElementText(handleElement, "woodType");
            if (woodType != null && !woodType.isEmpty()) {
                handle.setWoodType(woodType);
            }
            visual.setHandle(handle);
        }
        
        visual.setBloodGroove(Boolean.parseBoolean(getElementText(visualElement, "bloodGroove")));
        
        return visual;
    }
    
    private String getElementText(Element parent, String tagName) {
        NodeList nodeList = parent.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            Node node = nodeList.item(0);
            return node.getTextContent().trim();
        }
        return "";
    }
}

