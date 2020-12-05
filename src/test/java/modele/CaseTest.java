package modele;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CaseTest {
    Case maCase;
    
    public CaseTest() {
    }
    
    @BeforeEach
    public void setUp() {
        maCase = new Case(1,0,0);
    }

    @Test
    void testEtatCase() {
        assertTrue(maCase.nEstPasCaseTrouvee());
        maCase.setCaseInitiale();
        assertTrue(maCase.isCaseInitiale());
        maCase.setValeurCase(5);
        assertTrue(maCase.isCaseTrouvee());
    }
    
}
