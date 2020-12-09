package modele;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CaseTest {
    Case maCase;
    
    public CaseTest() {
    }
    
    @BeforeEach
    public void setUp() {
        maCase = new Case(1,0,0);
    }

    @Test
    void testEtatCase() {
    	assertEquals(1,maCase.getNumCase());
    	assertEquals(0,maCase.getxCase());
    	assertEquals(0,maCase.getyCase());
        assertTrue(maCase.nEstPasCaseTrouvee());
        maCase.setCaseInitiale();
        assertTrue(maCase.isCaseInitiale());
        assertFalse(maCase.nEstPasCaseInitiale());
        assertTrue(maCase.nEstPasCaseTrouvee());
        
        
        maCase.setValeurCase(5);
        assertTrue(maCase.isCaseTrouvee());
        assertFalse(maCase.isCaseInitiale());
        assertTrue(maCase.nEstPasCaseInitiale());
        assertEquals(5,maCase.getValeur());
    }
    
}
