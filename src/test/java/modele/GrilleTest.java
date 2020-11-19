package modele;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GrilleTest extends TestCase {
    Grille grille;
    
    public GrilleTest() {
        grille = new Grille(null);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getCase method, of class Grille.
     */
    @Test
    public void testBidon() {
        assertTrue(true);
    }
    
}
