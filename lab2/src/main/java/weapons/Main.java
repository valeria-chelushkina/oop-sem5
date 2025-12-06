package weapons;

import weapons.comparator.KnifeComparator;
import weapons.model.Knife;
import weapons.model.KnifeCollection;
import weapons.parser.*;
import weapons.transformer.XMLTransformer;
import weapons.validator.XMLValidator;

import java.util.Collections;
import java.util.List;

public class Main {
    private static final String XML_FILE = "lab2\\src\\main\\resources\\meleeWeapon.xml";
    private static final String XSD_FILE = "lab2\\src\\main\\resources\\meleeWeapon.xsd";
    private static final String HTML_OUTPUT = "lab2\\src\\main\\resources\\knife_catalog.html";

    public static void main(String[] args) {
        try {
            System.out.println("=== XML Parser Demo ===\n");

            XMLValidator validator = new XMLValidator();
            System.out.println("1. Validating XML against XSD schema...");
            validator.validateAndPrint(XML_FILE, XSD_FILE);
            System.out.println();

            System.out.println("2. Parsing XML using SAX parser...");
            SAXParser saxParser = new SAXParser();
            KnifeCollection saxCollection = saxParser.parse(XML_FILE);
            printCollection(saxCollection, "SAX");
            System.out.println();

            System.out.println("3. Parsing XML using DOM parser...");
            DOMParser domParser = new DOMParser();
            KnifeCollection domCollection = domParser.parse(XML_FILE);
            printCollection(domCollection, "DOM");
            System.out.println();

            System.out.println("4. Parsing XML using StAX parser...");
            StAXParser staxParser = new StAXParser();
            KnifeCollection staxCollection = staxParser.parse(XML_FILE);
            printCollection(staxCollection, "StAX");
            System.out.println();

            System.out.println("5. Sorting knives using Comparator...");
            List<Knife> knives = staxCollection.getKnives();
            Collections.sort(knives, new KnifeComparator());
            System.out.println("Sorted knives:");
            for (Knife knife : knives) {
                System.out.println("  - " + knife.getType() + " from " + knife.getOrigin() + " (ID: " + knife.getId() + ")");
            }
            System.out.println();

            System.out.println("6. Transforming XML to HTML...");
            XMLTransformer transformer = new XMLTransformer();
            transformer.transformToHTML(staxCollection, HTML_OUTPUT);
            System.out.println("HTML catalog created: " + HTML_OUTPUT);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void printCollection(KnifeCollection collection, String parserName) {
        System.out.println("Parsed with " + parserName + " parser:");
        List<Knife> knives = collection.getKnives();
        for (Knife knife : knives) {
            System.out.println("  " + knife);
        }
        System.out.println("Total knives: " + knives.size());
    }
}

