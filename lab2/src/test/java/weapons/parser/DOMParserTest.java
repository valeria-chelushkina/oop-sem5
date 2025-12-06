package weapons.parser;

import weapons.model.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DOMParserTest {

    private DOMParser parser;
    private String testXmlFile;

    @Before
    public void setUp() throws IOException {
        parser = new DOMParser();
        testXmlFile = createTestXmlFile();
    }

    private String createTestXmlFile() throws IOException {
        File tempFile = File.createTempFile("test_knives", ".xml");
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<Knife>\n");
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
            writer.write("                <woodType>oak</woodType>\n");
            writer.write("            </handle>\n");
            writer.write("            <bloodGroove>true</bloodGroove>\n");
            writer.write("        </visual>\n");
            writer.write("        <value>true</value>\n");
            writer.write("    </knife>\n");
            writer.write("    <knife id=\"k2\">\n");
            writer.write("        <type>dagger</type>\n");
            writer.write("        <handy>one-handed</handy>\n");
            writer.write("        <origin>Germany</origin>\n");
            writer.write("        <visual>\n");
            writer.write("            <blade>\n");
            writer.write("                <length>20.0</length>\n");
            writer.write("                <width>25</width>\n");
            writer.write("            </blade>\n");
            writer.write("            <material>steel</material>\n");
            writer.write("            <handle>\n");
            writer.write("                <material>plastic</material>\n");
            writer.write("            </handle>\n");
            writer.write("            <bloodGroove>true</bloodGroove>\n");
            writer.write("        </visual>\n");
            writer.write("        <value>false</value>\n");
            writer.write("    </knife>\n");
            writer.write("</Knife>\n");
        }
        return tempFile.getAbsolutePath();
    }

    @Test
    public void testParse() throws Exception {
        KnifeCollection collection = parser.parse(testXmlFile);
        
        assertNotNull(collection);
        assertNotNull(collection.getKnives());
        assertEquals(2, collection.getKnives().size());
    }

    @Test
    public void testParseKnifeAttributes() throws Exception {
        KnifeCollection collection = parser.parse(testXmlFile);
        List<Knife> knives = collection.getKnives();
        
        Knife knife1 = knives.get(0);
        assertEquals("k1", knife1.getId());
        assertEquals("knife", knife1.getType());
        assertEquals("one-handed", knife1.getHandy());
        assertEquals("Ukraine", knife1.getOrigin());
        assertTrue(knife1.isValue());
        
        Knife knife2 = knives.get(1);
        assertEquals("k2", knife2.getId());
        assertEquals("dagger", knife2.getType());
        assertEquals("one-handed", knife2.getHandy());
        assertEquals("Germany", knife2.getOrigin());
        assertFalse(knife2.isValue());
    }

    @Test
    public void testParseVisual() throws Exception {
        KnifeCollection collection = parser.parse(testXmlFile);
        Knife knife = collection.getKnives().get(0);
        
        assertEquals(1, knife.getVisuals().size());
        Visual visual = knife.getVisuals().get(0);
        assertEquals("steel", visual.getMaterial());
        assertTrue(visual.isBloodGroove());
        assertNotNull(visual.getBlade());
        assertNotNull(visual.getHandle());
    }

    @Test
    public void testParseBlade() throws Exception {
        KnifeCollection collection = parser.parse(testXmlFile);
        Knife knife = collection.getKnives().get(0);
        Visual visual = knife.getVisuals().get(0);
        Blade blade = visual.getBlade();
        
        assertNotNull(blade);
        assertNotNull(blade.getLength());
        assertNotNull(blade.getWidth());
        assertEquals(0, blade.getLength().compareTo(new java.math.BigDecimal("25.5")));
        assertEquals(0, blade.getWidth().compareTo(new java.math.BigDecimal("35")));
    }

    @Test
    public void testParseHandle() throws Exception {
        KnifeCollection collection = parser.parse(testXmlFile);
        Knife knife = collection.getKnives().get(0);
        Visual visual = knife.getVisuals().get(0);
        Handle handle = visual.getHandle();
        
        assertNotNull(handle);
        assertEquals("wooden", handle.getMaterial());
        assertEquals("oak", handle.getWoodType());
    }

    @Test
    public void testParseHandleWithoutWoodType() throws Exception {
        KnifeCollection collection = parser.parse(testXmlFile);
        Knife knife = collection.getKnives().get(1);
        Visual visual = knife.getVisuals().get(0);
        Handle handle = visual.getHandle();
        
        assertNotNull(handle);
        assertEquals("plastic", handle.getMaterial());
        // woodType may be null or empty
    }

    @Test(expected = Exception.class)
    public void testParseNonExistentFile() throws Exception {
        parser.parse("nonexistent_file.xml");
    }

    @Test(expected = Exception.class)
    public void testParseInvalidXml() throws Exception {
        File tempFile = File.createTempFile("invalid", ".xml");
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("<?xml version=\"1.0\"?><invalid>");
        }
        parser.parse(tempFile.getAbsolutePath());
    }
}

