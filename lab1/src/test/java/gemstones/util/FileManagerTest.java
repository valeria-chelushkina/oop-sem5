package gemstones.util;

import gemstones.gemstone.*;
import gemstones.necklace.Necklace;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileManagerTest {

    private File tempDir;
    private Necklace necklace;
    private Diamond diamond;
    private Ruby ruby;
    private Emerald emerald;
    private Amethyst amethyst;
    private Opal opal;
    private Aventurine aventurine;
    private PreciousStone preciousStone;
    private SemiPreciousStone semiPreciousStone;

    @Before
    public void setUp() {
        tempDir = new File(System.getProperty("java.io.tmpdir"));
        necklace = new Necklace();
        diamond = new Diamond(2.5, 50000, 0.95, "Adamantite", "White", "Africa", "Round brilliant");
        ruby = new Ruby(1.8, 30000, 0.85, "Subadamantine", "Red", "Myanmar", 0.9);
        emerald = new Emerald(2.0, 25000, 0.75, "Vitreous", "Green", "Columbia", false);
        amethyst = new Amethyst(3.0, 5000, 0.8, "Glassy", "Purple", "7", "Striped");
        opal = new Opal(2.2, 4000, 0.9, "Oily", "Green", "8", "Iridescent");
        aventurine = new Aventurine(2.2, 4000, 0.9, "Oily", "Green", "8", "Fuchsite");
        preciousStone = new PreciousStone("Sapphire", 1.5, 20000, 0.88, "Adamantine", "Blue", "Sri Lanka");
        semiPreciousStone = new SemiPreciousStone("Quartz", 2.8, 3000, 0.7, "Vitreous", "Clear", "7");
    }

    @Test
    public void testSaveAndLoadDiamond() throws IOException {
        necklace.addGemstone(diamond);
        File testFile = new File(tempDir, "test_diamond_" + System.currentTimeMillis() + ".txt");
        
        FileManager.saveNecklace(necklace, testFile.getAbsolutePath());
        Necklace loaded = FileManager.loadNecklace(testFile.getAbsolutePath());
        
        assertEquals(1, loaded.getGemstoneCount());
        Gemstone loadedStone = loaded.getGemstones().get(0);
        assertTrue(loadedStone instanceof Diamond);
        Diamond loadedDiamond = (Diamond) loadedStone;
        assertEquals("Diamond", loadedDiamond.getName());
        assertEquals(2.5, loadedDiamond.getWeight(), 0.001);
        assertEquals(50000, loadedDiamond.getCost(), 0.001);
        assertEquals(0.95, loadedDiamond.getTransparency(), 0.001);
        assertEquals("Round brilliant", loadedDiamond.getCut());
        assertEquals("Africa", loadedDiamond.getOrigin());
        
        testFile.delete();
    }

    @Test
    public void testSaveAndLoadRuby() throws IOException {
        necklace.addGemstone(ruby);
        File testFile = new File(tempDir, "test_ruby_" + System.currentTimeMillis() + ".txt");
        
        FileManager.saveNecklace(necklace, testFile.getAbsolutePath());
        Necklace loaded = FileManager.loadNecklace(testFile.getAbsolutePath());
        
        assertEquals(1, loaded.getGemstoneCount());
        Gemstone loadedStone = loaded.getGemstones().get(0);
        assertTrue(loadedStone instanceof Ruby);
        Ruby loadedRuby = (Ruby) loadedStone;
        assertEquals("Ruby", loadedRuby.getName());
        assertEquals(0.9, loadedRuby.getClarity(), 0.001);
        assertEquals("Myanmar", loadedRuby.getOrigin());
        
        testFile.delete();
    }

    @Test
    public void testSaveAndLoadEmerald() throws IOException {
        necklace.addGemstone(emerald);
        File testFile = new File(tempDir, "test_emerald_" + System.currentTimeMillis() + ".txt");
        
        FileManager.saveNecklace(necklace, testFile.getAbsolutePath());
        Necklace loaded = FileManager.loadNecklace(testFile.getAbsolutePath());
        
        assertEquals(1, loaded.getGemstoneCount());
        Gemstone loadedStone = loaded.getGemstones().get(0);
        assertTrue(loadedStone instanceof Emerald);
        Emerald loadedEmerald = (Emerald) loadedStone;
        assertEquals("Emerald", loadedEmerald.getName());
        assertFalse(loadedEmerald.isTreated());
        
        testFile.delete();
    }

    @Test
    public void testSaveAndLoadAmethyst() throws IOException {
        necklace.addGemstone(amethyst);
        File testFile = new File(tempDir, "test_amethyst_" + System.currentTimeMillis() + ".txt");
        
        FileManager.saveNecklace(necklace, testFile.getAbsolutePath());
        Necklace loaded = FileManager.loadNecklace(testFile.getAbsolutePath());
        
        assertEquals(1, loaded.getGemstoneCount());
        Gemstone loadedStone = loaded.getGemstones().get(0);
        assertTrue(loadedStone instanceof Amethyst);
        Amethyst loadedAmethyst = (Amethyst) loadedStone;
        assertEquals("Amethyst", loadedAmethyst.getName());
        assertEquals("Striped", loadedAmethyst.getPattern());
        assertEquals("7", loadedAmethyst.getHardness());
        
        testFile.delete();
    }

    @Test
    public void testSaveAndLoadOpal() throws IOException {
        necklace.addGemstone(opal);
        File testFile = new File(tempDir, "test_opal_" + System.currentTimeMillis() + ".txt");
        
        FileManager.saveNecklace(necklace, testFile.getAbsolutePath());
        Necklace loaded = FileManager.loadNecklace(testFile.getAbsolutePath());
        
        assertEquals(1, loaded.getGemstoneCount());
        Gemstone loadedStone = loaded.getGemstones().get(0);
        assertTrue(loadedStone instanceof Opal);
        Opal loadedOpal = (Opal) loadedStone;
        assertEquals("Opal", loadedOpal.getName());
        assertEquals("Iridescent", loadedOpal.getPlayOfColor());
        
        testFile.delete();
    }

    @Test
    public void testSaveAndLoadAventurine() throws IOException {
        necklace.addGemstone(aventurine);
        File testFile = new File(tempDir, "test_aventurine_" + System.currentTimeMillis() + ".txt");
        
        FileManager.saveNecklace(necklace, testFile.getAbsolutePath());
        Necklace loaded = FileManager.loadNecklace(testFile.getAbsolutePath());
        
        assertEquals(1, loaded.getGemstoneCount());
        Gemstone loadedStone = loaded.getGemstones().get(0);
        assertTrue(loadedStone instanceof Aventurine);
        Aventurine loadedAventurine = (Aventurine) loadedStone;
        assertEquals("Aventurine", loadedAventurine.getName());
        assertEquals("Fuchsite", loadedAventurine.getInclusions());
        
        testFile.delete();
    }

    @Test
    public void testSaveAndLoadPreciousStone() throws IOException {
        necklace.addGemstone(preciousStone);
        File testFile = new File(tempDir, "test_precious_" + System.currentTimeMillis() + ".txt");
        
        FileManager.saveNecklace(necklace, testFile.getAbsolutePath());
        Necklace loaded = FileManager.loadNecklace(testFile.getAbsolutePath());
        
        assertEquals(1, loaded.getGemstoneCount());
        Gemstone loadedStone = loaded.getGemstones().get(0);
        assertTrue(loadedStone instanceof PreciousStone);
        PreciousStone loadedPS = (PreciousStone) loadedStone;
        assertEquals("Sapphire", loadedPS.getName());
        assertEquals("Sri Lanka", loadedPS.getOrigin());
        
        testFile.delete();
    }

    @Test
    public void testSaveAndLoadSemiPreciousStone() throws IOException {
        necklace.addGemstone(semiPreciousStone);
        File testFile = new File(tempDir, "test_semiprecious_" + System.currentTimeMillis() + ".txt");
        
        FileManager.saveNecklace(necklace, testFile.getAbsolutePath());
        Necklace loaded = FileManager.loadNecklace(testFile.getAbsolutePath());
        
        assertEquals(1, loaded.getGemstoneCount());
        Gemstone loadedStone = loaded.getGemstones().get(0);
        assertTrue(loadedStone instanceof SemiPreciousStone);
        SemiPreciousStone loadedSPS = (SemiPreciousStone) loadedStone;
        assertEquals("Quartz", loadedSPS.getName());
        assertEquals("7", loadedSPS.getHardness());
        
        testFile.delete();
    }

    @Test
    public void testSaveAndLoadMultipleGemstones() throws IOException {
        necklace.addGemstone(diamond);
        necklace.addGemstone(ruby);
        necklace.addGemstone(emerald);
        necklace.addGemstone(amethyst);
        File testFile = new File(tempDir, "test_multiple_" + System.currentTimeMillis() + ".txt");
        
        FileManager.saveNecklace(necklace, testFile.getAbsolutePath());
        Necklace loaded = FileManager.loadNecklace(testFile.getAbsolutePath());
        
        assertEquals(4, loaded.getGemstoneCount());
        List<Gemstone> stones = loaded.getGemstones();
        assertTrue(stones.get(0) instanceof Diamond);
        assertTrue(stones.get(1) instanceof Ruby);
        assertTrue(stones.get(2) instanceof Emerald);
        assertTrue(stones.get(3) instanceof Amethyst);
        
        testFile.delete();
    }

    @Test
    public void testSaveAndLoadEmptyNecklace() throws IOException {
        File testFile = new File(tempDir, "test_empty_" + System.currentTimeMillis() + ".txt");
        
        FileManager.saveNecklace(necklace, testFile.getAbsolutePath());
        Necklace loaded = FileManager.loadNecklace(testFile.getAbsolutePath());
        
        assertEquals(0, loaded.getGemstoneCount());
        
        testFile.delete();
    }

    @Test(expected = IOException.class)
    public void testLoadNonExistentFile() throws IOException {
        FileManager.loadNecklace("nonexistent_file_" + System.currentTimeMillis() + ".txt");
    }

    @Test
    public void testLoadInvalidLine() throws IOException {
        File testFile = new File(tempDir, "test_invalid_" + System.currentTimeMillis() + ".txt");
        java.io.PrintWriter writer = new java.io.PrintWriter(new java.io.FileWriter(testFile));
        writer.println("Invalid|Format|Line");
        writer.println("Diamond|Diamond|2.5|50000.0|0.95|Adamantite|White|Africa|Round brilliant");
        writer.close();
        
        Necklace loaded = FileManager.loadNecklace(testFile.getAbsolutePath());
        // Should skip invalid line and load valid one
        assertEquals(1, loaded.getGemstoneCount());
        
        testFile.delete();
    }

    @Test
    public void testLoadWithEmptyLines() throws IOException {
        necklace.addGemstone(diamond);
        File testFile = new File(tempDir, "test_empty_lines_" + System.currentTimeMillis() + ".txt");
        
        FileManager.saveNecklace(necklace, testFile.getAbsolutePath());
        // Add empty lines manually
        java.io.PrintWriter writer = new java.io.PrintWriter(new java.io.FileWriter(testFile, true));
        writer.println("");
        writer.println("   ");
        writer.println("Diamond|Diamond|1.0|10000.0|0.9|Vitreous|Blue|Russia|Oval");
        writer.close();
        
        Necklace loaded = FileManager.loadNecklace(testFile.getAbsolutePath());
        // Should load both diamonds, skipping empty lines
        assertTrue(loaded.getGemstoneCount() >= 1);
        
        testFile.delete();
    }

    @Test
    public void testSaveAndLoadAllTypes() throws IOException {
        necklace.addGemstone(diamond);
        necklace.addGemstone(ruby);
        necklace.addGemstone(emerald);
        necklace.addGemstone(amethyst);
        necklace.addGemstone(opal);
        necklace.addGemstone(aventurine);
        necklace.addGemstone(preciousStone);
        necklace.addGemstone(semiPreciousStone);
        
        File testFile = new File(tempDir, "test_all_types_" + System.currentTimeMillis() + ".txt");
        FileManager.saveNecklace(necklace, testFile.getAbsolutePath());
        Necklace loaded = FileManager.loadNecklace(testFile.getAbsolutePath());
        
        assertEquals(8, loaded.getGemstoneCount());
        
        testFile.delete();
    }
}
