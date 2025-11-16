package resolution;

import model.grille.CandidatsCase;
import model.grille.CaseContext;
import model.grille.Grille;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaireCandidats2CasesTest {
	private Grille grille;
	private PaireCandidats2Cases paireCandidats2Cases;

    @BeforeEach
    void setUp() {
    	grille = new Grille();
    	paireCandidats2Cases = new PaireCandidats2CasesColonne(grille);
    }

    @Test
	void testInserePaireCandidatsDansTab() {
        CaseContext context = new CaseContext(1);
		grille.setAllCandidatsToFalse(context.getNumCase());
		grille.setCandidat(context.getNumCase(),1);
		grille.setCandidat(context.getNumCase(), 5);
		grille.setCandidat(context.getNumCase(), 6);
		grille.setCandidat(context.getNumCase(), 7);
		grille.setCandidat(context.getNumCase(), 8);
        List<CandidatsCase> tabCandidatsLocal = paireCandidats2Cases.inserePaireCandidatsDansTab(context);
    	assertEquals("100010000 / 15", tabCandidatsLocal.get(0).toString());
    	assertEquals("100001000 / 16",tabCandidatsLocal.get(1).toString());
    	assertEquals("100000100 / 17",tabCandidatsLocal.get(2).toString());
    	assertEquals("100000010 / 18",tabCandidatsLocal.get(3).toString());
    	assertEquals("000011000 / 56",tabCandidatsLocal.get(4).toString());
    	assertEquals("000010100 / 57",tabCandidatsLocal.get(5).toString());
    	assertEquals("000010010 / 58",tabCandidatsLocal.get(6).toString());
    	assertEquals("000001100 / 67",tabCandidatsLocal.get(7).toString());
    	assertEquals("000001010 / 68",tabCandidatsLocal.get(8).toString());
    	assertEquals("000000110 / 78",tabCandidatsLocal.get(9).toString());
	}

    @Test
    void testCalculIntersections2Cases() {
        boolean[] tab1 = {false, false, false, false, false, false, false, false, false, false};
        CandidatsCase c1 = new CandidatsCase(tab1);
        
        boolean[] tab2 = {false, true, false, false, false, false, false, false, false, false};
        CandidatsCase c2 = new CandidatsCase(tab2);
        
        boolean[] tab3 = {true, true, false, false, false, false, false, false, false, false};
        CandidatsCase c3 = new CandidatsCase(tab3);
        
        boolean[] tab4 = {false, true, true, true, true, true, true, true, true, true};
        CandidatsCase c4 = new CandidatsCase(tab4);
        
        boolean[] tab5 = {true, true, true, false, false, false, false, false, false, false};
        CandidatsCase c5 = new CandidatsCase(tab5);

        paireCandidats2Cases.calculIntersectionDeuxCases(c1, c2);
        assertEquals(0,paireCandidats2Cases.nb1inter);
        assertEquals(0,paireCandidats2Cases.nb2inter);
    	
        paireCandidats2Cases.calculIntersectionDeuxCases(c2, c3);
        assertEquals(1,paireCandidats2Cases.nb1inter);
        assertEquals(0,paireCandidats2Cases.nb2inter);
        
        paireCandidats2Cases.calculIntersectionDeuxCases(c3, c4);
        assertEquals(2,paireCandidats2Cases.nb1inter);
        assertEquals(0,paireCandidats2Cases.nb2inter);
        
        paireCandidats2Cases.calculIntersectionDeuxCases(c4, c5);
        assertEquals(2,paireCandidats2Cases.nb1inter);
        assertEquals(1,paireCandidats2Cases.nb2inter);
    }
    
    
}
