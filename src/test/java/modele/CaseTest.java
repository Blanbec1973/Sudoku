package modele;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CaseTest extends TestCase {
    Case maCase;
    
    public CaseTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        maCase = new Case(0,0,0);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testEtatCase() {
        assertTrue(maCase.nEstPasCaseTrouvee());
        maCase.setCaseInitiale();
        assertTrue(maCase.isCaseInitiale());
        maCase.setValeurCase(5);
        assertTrue(maCase.isCaseTrouvee());
    }
    
}
