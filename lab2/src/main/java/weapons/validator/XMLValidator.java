package weapons.validator;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XMLValidator {
    
    public boolean validate(String xmlPath, String xsdPath) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
            return true;
        } catch (SAXException | IOException e) {
            System.err.println("Validation error: " + e.getMessage());
            return false;
        }
    }
    
    public void validateAndPrint(String xmlPath, String xsdPath) {
        if (validate(xmlPath, xsdPath)) {
            System.out.println("XML document is valid against XSD schema.");
        } else {
            System.out.println("XML document is NOT valid against XSD schema.");
        }
    }
}

