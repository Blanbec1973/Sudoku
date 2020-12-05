package modele;

import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CandidatsTest {
    CandidatsCase c1, c2, c3, c4, c5;
    
    public CandidatsTest() {
    }
    
    @BeforeEach
    void setUp() {
        boolean tab1 [] = {false, false, false, false, false, false, false, false, false, false};
        this.c1 = new CandidatsCase(tab1);
        
        boolean tab2 [] = {false, true, false, false, false, false, false, false, false, false};
        this.c2 = new CandidatsCase(tab2);
        
        boolean tab3 [] = {true, true, false, false, false, false, false, false, false, false};
        this.c3 = new CandidatsCase(tab3);
        
        boolean tab4 [] = {false, true, true, true, true, true, true, true, true, true};
        this.c4 = new CandidatsCase(tab4);
        
        boolean tab5 [] = {true, true, true, true, true, true, true, true, true, true};
        this.c5 = new CandidatsCase(tab5);
    }
    
    @Test
    void testcalculNombreCandidats() {
        assertEquals(0,this.c1.calculNombreCandidats());
        assertEquals(1,this.c2.calculNombreCandidats());
        assertEquals(2,this.c3.calculNombreCandidats());
        assertEquals(9,this.c4.calculNombreCandidats());
        assertEquals(10,this.c5.calculNombreCandidats());       
    }
    
    @Test
    void testelimineCandidat() {
        c2.elimineCandidat(1);
        assertEquals(0, c2.calculNombreCandidats());
        assertTrue(Arrays.equals(c1.getCandidats(), c2.getCandidats()));    
    }
    
    @Test
    void testSetCandidats() {
        c1.setCandidats(c5.getCandidats());
        assertEquals(10, c1.calculNombreCandidats());
        assertTrue(Arrays.equals(c1.getCandidats(), c5.getCandidats())); 
    }
    
    @Test
    void testConstruitLibelleCandidats() {
        assertEquals("<html>   <br>   <br>   </html>",c1.construitLibelleCandidats());
        assertEquals("<html>1  <br>   <br>   </html>",c2.construitLibelleCandidats());
        assertEquals("<html>1  <br>   <br>   </html>",c3.construitLibelleCandidats());
        assertEquals("<html>123<br>456<br>789</html>",c4.construitLibelleCandidats());
        assertEquals("<html>123<br>456<br>789</html>",c5.construitLibelleCandidats());
    }
    
    @Test
    void testContientCandidatUnique() {
        assertFalse(c1.contientCandidatUnique());
        assertTrue(c2.contientCandidatUnique());
        assertFalse(c3.contientCandidatUnique());
        assertFalse(c4.contientCandidatUnique());
        assertFalse(c5.contientCandidatUnique());
    } 
    
    @Test
    void testCalculValeurUnique() {
        assertEquals(1,c2.calculValeurUnique());
    }     
    
    @Test
    void testDisplayCandidats() {
    	assertEquals("000000000",c1.displayCandidats());
    	assertEquals("100000000",c2.displayCandidats());
    	assertEquals("100000000",c3.displayCandidats());
    	assertEquals("111111111",c4.displayCandidats());
    	assertEquals("111111111",c5.displayCandidats());
    }
    
}
