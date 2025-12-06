package weapons.transformer;

import weapons.model.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

public class XMLTransformerTest {

    private XMLTransformer transformer;
    private KnifeCollection collection;
    private String outputFile;

    @Before
    public void setUp() throws IOException {
        transformer = new XMLTransformer();
        collection = new KnifeCollection();
        
        // Create test knives
        Knife knife1 = new Knife("k1", "knife", "one-handed", "Ukraine", true);
        Visual visual1 = new Visual();
        visual1.setMaterial("steel");
        visual1.setBlade(new Blade(new BigDecimal("25.5"), new BigDecimal("35")));
        visual1.setHandle(new Handle("wooden", "oak"));
        visual1.setBloodGroove(true);
        knife1.addVisual(visual1);
        collection.addKnife(knife1);
        
        Knife knife2 = new Knife("k2", "dagger", "one-handed", "Germany", false);
        Visual visual2 = new Visual();
        visual2.setMaterial("titanium");
        visual2.setBlade(new Blade(new BigDecimal("20.0"), new BigDecimal("25")));
        visual2.setHandle(new Handle("plastic", null));
        visual2.setBloodGroove(false);
        knife2.addVisual(visual2);
        collection.addKnife(knife2);
        
        outputFile = File.createTempFile("test_catalog", ".html").getAbsolutePath();
    }

    @Test
    public void testTransformToHTML() throws Exception {
        transformer.transformToHTML(collection, outputFile);
        
        File file = new File(outputFile);
        assertTrue("HTML file should be created", file.exists());
        assertTrue("HTML file should not be empty", file.length() > 0);
    }

    @Test
    public void testTransformToHTMLContent() throws Exception {
        transformer.transformToHTML(collection, outputFile);
        
        String content = new String(java.nio.file.Files.readAllBytes(new File(outputFile).toPath()));
        assertTrue("Should contain HTML structure", content.contains("<html"));
        assertTrue("Should contain title", content.contains("Cold Weapons Catalog"));
        assertTrue("Should contain table", content.contains("<table"));
        assertTrue("Should contain knife ID", content.contains("k1"));
        assertTrue("Should contain knife type", content.contains("knife"));
    }

    @Test
    public void testTransformToHTMLEmptyCollection() throws Exception {
        KnifeCollection emptyCollection = new KnifeCollection();
        String emptyOutput = File.createTempFile("empty_catalog", ".html").getAbsolutePath();
        
        transformer.transformToHTML(emptyCollection, emptyOutput);
        
        File file = new File(emptyOutput);
        assertTrue("HTML file should be created even for empty collection", file.exists());
        
        String content = new String(java.nio.file.Files.readAllBytes(file.toPath()));
        assertTrue("Should contain table header", content.contains("<th"));
    }

    @Test
    public void testTransformToHTMLMultipleKnives() throws Exception {
        Knife knife3 = new Knife("k3", "sword", "two-handed", "Japan", true);
        collection.addKnife(knife3);
        
        transformer.transformToHTML(collection, outputFile);
        
        String content = new String(java.nio.file.Files.readAllBytes(new File(outputFile).toPath()));
        assertTrue("Should contain all knife IDs", content.contains("k1") && content.contains("k2") && content.contains("k3"));
    }

    @Test
    public void testTransformToHTMLTableHeaders() throws Exception {
        transformer.transformToHTML(collection, outputFile);
        
        String content = new String(java.nio.file.Files.readAllBytes(new File(outputFile).toPath()));
        assertTrue("Should contain ID header", content.contains("ID"));
        assertTrue("Should contain Type header", content.contains("Type"));
        assertTrue("Should contain Handy header", content.contains("Handy"));
        assertTrue("Should contain Origin header", content.contains("Origin"));
        assertTrue("Should contain Collectible header", content.contains("Collectible"));
        assertTrue("Should contain Visual Count header", content.contains("Visual Count"));
    }

    @Test
    public void testTransformToHTMLWithVisualCount() throws Exception {
        Knife knife = new Knife("k4", "machete", "one-handed", "USA", false);
        knife.addVisual(new Visual());
        knife.addVisual(new Visual());
        collection.addKnife(knife);
        
        transformer.transformToHTML(collection, outputFile);
        
        String content = new String(java.nio.file.Files.readAllBytes(new File(outputFile).toPath()));
        assertTrue("Should contain visual count", content.contains("2"));
    }

    @Test(expected = Exception.class)
    public void testTransformToHTMLInvalidPath() throws Exception {
        transformer.transformToHTML(collection, "/invalid/path/that/does/not/exist/file.html");
    }
}

