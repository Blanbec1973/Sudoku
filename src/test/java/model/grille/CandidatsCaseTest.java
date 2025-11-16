package model.grille;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CandidatsCaseTest {
    CandidatsCase c1, c2, c3, c4, c5;
    @BeforeEach
    void setUp() {
        boolean[] tab1 = {false, false, false, false, false, false, false, false, false, false};
        this.c1 = new CandidatsCase(tab1);
        
        boolean[] tab2 = {false, true, false, false, false, false, false, false, false, false};
        this.c2 = new CandidatsCase(tab2);
        
        boolean[] tab3 = {true, true, false, false, false, false, false, false, false, false};
        this.c3 = new CandidatsCase(tab3);
        
        boolean[] tab4 = {false, true, true, true, true, true, true, true, true, true};
        this.c4 = new CandidatsCase(tab4);
        
        boolean[] tab5 = {true, true, true, true, true, true, true, true, true, true};
        this.c5 = new CandidatsCase(tab5);
    }
      
    @Test
    void shouldReturnCorrectNumberOfCandidates_whenInitializedWithBooleanArray() {
    	assertEquals(0,this.c1.getNombreCandidats());
    	assertEquals(1,this.c2.getNombreCandidats());
    	assertEquals(1,this.c3.getNombreCandidats());
    	assertEquals(9,this.c4.getNombreCandidats());
    	assertEquals(9,this.c5.getNombreCandidats());
    }
    
    @Test
    @DisplayName("Élimination d’un candidat existant")
    void testelimineCandidat() {
        c2.elimineCandidat(1);
        assertEquals(0, c2.getNombreCandidats());
        assertArrayEquals(c1.getCandidats(), c2.getCandidats());
    }
    
    @Test
    void testSetCandidats() {
        c1.setCandidats(c5.getCandidats());
        assertEquals(9, c1.getNombreCandidats());
        assertArrayEquals(c1.getCandidats(), c5.getCandidats());
    }
    @Test
    void testSetCandidatAvecValeurInvalide() {
        assertThrows(IllegalArgumentException.class, () -> c1.setCandidat(10));
        assertThrows(IllegalArgumentException.class, () -> c1.setCandidat(-1));
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
        assertTrue(c3.contientCandidatUnique());
        assertFalse(c4.contientCandidatUnique());
        assertFalse(c5.contientCandidatUnique());
    } 
    
    @Test
    void testCalculValeurUnique() {
        assertEquals(1,c2.calculValeurUnique());
    }
    @Test
    void testCalculValeurUniqueSansCandidatActif() {
        assertThrows(IllegalStateException.class, () -> c1.calculValeurUnique());
    }
    @Test
    void testDisplayCandidats() {
    	assertEquals("000000000 / ",c1.toString());
    	assertEquals("100000000 / 1",c2.toString());
    	assertEquals("100000000 / 1",c3.toString());
    	assertEquals("111111111 / 123456789",c4.toString());
    	assertEquals("111111111 / 123456789",c5.toString());
    }
    
}
