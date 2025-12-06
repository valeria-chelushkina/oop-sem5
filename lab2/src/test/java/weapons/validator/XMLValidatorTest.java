package weapons.validator;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class XMLValidatorTest {

    private XMLValidator validator;
    private String validXmlFile;
    private String validXsdFile;
    private String invalidXmlFile;

    @Before
    public void setUp() throws IOException {
        validator = new XMLValidator();
        validXmlFile = createValidXmlFile();
        validXsdFile = createValidXsdFile();
        invalidXmlFile = createInvalidXmlFile();
    }

    private String createValidXmlFile() throws IOException {
        File tempFile = File.createTempFile("valid", ".xml");
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<Knife xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n");
            writer.write("       xsi:noNamespaceSchemaLocation=\"meleeWeapon.xsd\">\n");
            writer.write("    <knife id=\"k1\">\n");
            writer.write("        <type>knife</type>\n");
            writer.write("        <handy>one-handed</handy>\n");
            writer.write("        <origin>Ukraine</origin>\n");
            writer.write("        <visual>\n");
            writer.write("            <blade>\n");
            writer.write("                <length>25.5</length>\n");
            writer.write("                <width>35</width>\n");
            writer.write("            </blade>\n");
            writer.write("            <material>steel</material>\n");
            writer.write("            <handle>\n");
            writer.write("                <material>wooden</material>\n");
            writer.write("            </handle>\n");
            writer.write("            <bloodGroove>true</bloodGroove>\n");
            writer.write("        </visual>\n");
            writer.write("        <value>true</value>\n");
            writer.write("    </knife>\n");
            writer.write("</Knife>\n");
        }
        return tempFile.getAbsolutePath();
    }

    private String createValidXsdFile() throws IOException {
        File tempFile = File.createTempFile("schema", ".xsd");
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<xs:schema xmlns:xs=\"http://www.w3.org/2001/XMLSchema\">\n");
            writer.write("    <xs:element name=\"Knife\">\n");
            writer.write("        <xs:complexType>\n");
            writer.write("            <xs:sequence>\n");
            writer.write("                <xs:element name=\"knife\" maxOccurs=\"unbounded\">\n");
            writer.write("                    <xs:complexType>\n");
            writer.write("                        <xs:sequence>\n");
            writer.write("                            <xs:element name=\"type\" type=\"xs:string\"/>\n");
            writer.write("                            <xs:element name=\"handy\" type=\"xs:string\"/>\n");
            writer.write("                            <xs:element name=\"origin\" type=\"xs:string\"/>\n");
            writer.write("                            <xs:element name=\"visual\" maxOccurs=\"unbounded\">\n");
            writer.write("                                <xs:complexType>\n");
            writer.write("                                    <xs:sequence>\n");
            writer.write("                                        <xs:element name=\"blade\">\n");
            writer.write("                                            <xs:complexType>\n");
            writer.write("                                                <xs:sequence>\n");
            writer.write("                                                    <xs:element name=\"length\" type=\"xs:decimal\"/>\n");
            writer.write("                                                    <xs:element name=\"width\" type=\"xs:decimal\"/>\n");
            writer.write("                                                </xs:sequence>\n");
            writer.write("                                            </xs:complexType>\n");
            writer.write("                                        </xs:element>\n");
            writer.write("                                        <xs:element name=\"material\" type=\"xs:string\"/>\n");
            writer.write("                                        <xs:element name=\"handle\">\n");
            writer.write("                                            <xs:complexType>\n");
            writer.write("                                                <xs:sequence>\n");
            writer.write("                                                    <xs:element name=\"material\" type=\"xs:string\"/>\n");
            writer.write("                                                    <xs:element name=\"woodType\" type=\"xs:string\" minOccurs=\"0\"/>\n");
            writer.write("                                                </xs:sequence>\n");
            writer.write("                                            </xs:complexType>\n");
            writer.write("                                        </xs:element>\n");
            writer.write("                                        <xs:element name=\"bloodGroove\" type=\"xs:boolean\"/>\n");
            writer.write("                                    </xs:sequence>\n");
            writer.write("                                </xs:complexType>\n");
            writer.write("                            </xs:element>\n");
            writer.write("                            <xs:element name=\"value\" type=\"xs:boolean\"/>\n");
            writer.write("                        </xs:sequence>\n");
            writer.write("                        <xs:attribute name=\"id\" type=\"xs:string\" use=\"required\"/>\n");
            writer.write("                    </xs:complexType>\n");
            writer.write("                </xs:element>\n");
            writer.write("            </xs:sequence>\n");
            writer.write("        </xs:complexType>\n");
            writer.write("    </xs:element>\n");
            writer.write("</xs:schema>\n");
        }
        return tempFile.getAbsolutePath();
    }

    private String createInvalidXmlFile() throws IOException {
        File tempFile = File.createTempFile("invalid", ".xml");
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("<?xml version=\"1.0\"?>\n");
            writer.write("<Knife>\n");
            writer.write("    <knife>\n"); // Missing required id attribute
            writer.write("        <type>knife</type>\n");
            writer.write("    </knife>\n");
            writer.write("</Knife>\n");
        }
        return tempFile.getAbsolutePath();
    }

    @Test
    public void testValidateValidXml() {
        boolean result = validator.validate(validXmlFile, validXsdFile);
        assertTrue("Valid XML should pass validation", result);
    }

    @Test
    public void testValidateInvalidXml() {
        boolean result = validator.validate(invalidXmlFile, validXsdFile);
        assertFalse("Invalid XML should fail validation", result);
    }

    @Test
    public void testValidateNonExistentXml() {
        boolean result = validator.validate("nonexistent.xml", validXsdFile);
        assertFalse("Non-existent XML file should fail", result);
    }

    @Test
    public void testValidateNonExistentXsd() {
        boolean result = validator.validate(validXmlFile, "nonexistent.xsd");
        assertFalse("Non-existent XSD file should fail", result);
    }

    @Test
    public void testValidateAndPrintValid() {
        // This test checks that the method doesn't throw exceptions
        validator.validateAndPrint(validXmlFile, validXsdFile);
    }

    @Test
    public void testValidateAndPrintInvalid() {
        // This test checks that the method doesn't throw exceptions
        validator.validateAndPrint(invalidXmlFile, validXsdFile);
    }
}

