package weapons.transformer;

import weapons.model.Knife;
import weapons.model.KnifeCollection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class XMLTransformer {
    
    public void transformToHTML(KnifeCollection collection, String outputPath) throws Exception {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();
        
        Element html = doc.createElement("html");
        doc.appendChild(html);
        
        Element head = doc.createElement("head");
        html.appendChild(head);
        
        Element title = doc.createElement("title");
        title.setTextContent("Cold Weapons Catalog");
        head.appendChild(title);
        
        Element style = doc.createElement("style");
        style.setTextContent(
            "body { font-family: Arial, sans-serif; margin: 20px; } " +
            "table { border-collapse: collapse; width: 100%; } " +
            "th, td { border: 1px solid #ddd; padding: 8px; text-align: left; } " +
            "th { background-color: #4CAF50; color: white; } " +
            "tr:nth-child(even) { background-color: #f2f2f2; }"
        );
        head.appendChild(style);
        
        Element body = doc.createElement("body");
        html.appendChild(body);
        
        Element h1 = doc.createElement("h1");
        h1.setTextContent("Cold Weapons Catalog");
        body.appendChild(h1);
        
        Element table = doc.createElement("table");
        body.appendChild(table);
        
        Element thead = doc.createElement("thead");
        table.appendChild(thead);
        
        Element tr = doc.createElement("tr");
        thead.appendChild(tr);
        
        String[] headers = {"ID", "Type", "Handy", "Origin", "Collectible", "Visual Count"};
        for (String header : headers) {
            Element th = doc.createElement("th");
            th.setTextContent(header);
            tr.appendChild(th);
        }
        
        Element tbody = doc.createElement("tbody");
        table.appendChild(tbody);
        
        List<Knife> knives = collection.getKnives();
        for (Knife knife : knives) {
            Element row = doc.createElement("tr");
            tbody.appendChild(row);
            
            addCell(doc, row, knife.getId());
            addCell(doc, row, knife.getType());
            addCell(doc, row, knife.getHandy());
            addCell(doc, row, knife.getOrigin());
            addCell(doc, row, String.valueOf(knife.isValue()));
            addCell(doc, row, String.valueOf(knife.getVisuals().size()));
        }
        
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.METHOD, "html");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(outputPath));
        transformer.transform(source, result);
    }
    
    private void addCell(Document doc, Element row, String text) {
        Element cell = doc.createElement("td");
        cell.setTextContent(text);
        row.appendChild(cell);
    }
}

