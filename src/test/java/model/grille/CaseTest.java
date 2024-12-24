package model.grille;

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
        assertEquals("<html>123<br>456<br>789</html>",maCase.construitLibelleCandidats());
        maCase.setCaseInitiale(1);
        assertTrue(maCase.isCaseInitiale());
        assertFalse(maCase.nEstPasCaseInitiale());
        assertTrue(maCase.nEstPasCaseTrouvee());
        
        
        maCase.setCaseTrouvee(5);
        assertTrue(maCase.isCaseTrouvee());
        assertFalse(maCase.isCaseInitiale());
        assertTrue(maCase.nEstPasCaseInitiale());
        assertEquals(5,maCase.getValeur());
        assertFalse(maCase.nEstPasCaseTrouvee());
    }

    @Test
    void testToString() {
        assertEquals("Case 1 to be found 0 / 111111111 |", maCase.toString());
        maCase.setCaseTrouvee(3);
        assertEquals("Case 1 found 3 / 001000000 |", maCase.toString());
        maCase.setCaseInitiale(7);
        assertEquals("Case 1 initial 7 / 000000100 |", maCase.toString());

    }
}
