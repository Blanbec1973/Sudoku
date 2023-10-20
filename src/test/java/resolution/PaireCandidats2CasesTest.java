package resolution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modele.grille.CandidatsCase;
import modele.grille.CaseEnCours;
import modele.grille.Grille;

class PaireCandidats2CasesTest {
	private Grille grille;
	private PaireCandidats2Cases paireCandidats2Cases;
	
    public PaireCandidats2CasesTest() {
    }
    
    @BeforeEach
    public void setUp() {
    	grille = new Grille();
    	CaseEnCours.setCaseEnCours(1);
    	paireCandidats2Cases = new PaireCandidats2CasesColonne(null,grille);
    }

    @Test
	void testInserePaireCandidatsDansTab() {
		grille.getCaseEnCours().getCandidats().setAllCandidatsToFalse();
		grille.getCaseEnCours().getCandidats().setCandidat(1);
		grille.getCaseEnCours().getCandidats().setCandidat(5);
		grille.getCaseEnCours().getCandidats().setCandidat(6);
		grille.getCaseEnCours().getCandidats().setCandidat(7);
		grille.getCaseEnCours().getCandidats().setCandidat(8);
		paireCandidats2Cases.inserePaireCandidatsDansTab();
    	assertEquals("100010000",paireCandidats2Cases.tabCandidats.get(0).toString());
    	assertEquals("100001000",paireCandidats2Cases.tabCandidats.get(1).toString());
    	assertEquals("100000100",paireCandidats2Cases.tabCandidats.get(2).toString());
    	assertEquals("100000010",paireCandidats2Cases.tabCandidats.get(3).toString());
    	assertEquals("000011000",paireCandidats2Cases.tabCandidats.get(4).toString());
    	assertEquals("000010100",paireCandidats2Cases.tabCandidats.get(5).toString());
    	assertEquals("000010010",paireCandidats2Cases.tabCandidats.get(6).toString());
    	assertEquals("000001100",paireCandidats2Cases.tabCandidats.get(7).toString());
    	assertEquals("000001010",paireCandidats2Cases.tabCandidats.get(8).toString());
    	assertEquals("000000110",paireCandidats2Cases.tabCandidats.get(9).toString());
	}

    @Test
    void testCalculIntersections2Cases() {
        boolean tab1 [] = {false, false, false, false, false, false, false, false, false, false};
        CandidatsCase c1 = new CandidatsCase(tab1);
        
        boolean tab2 [] = {false, true, false, false, false, false, false, false, false, false};
        CandidatsCase c2 = new CandidatsCase(tab2);
        
        boolean tab3 [] = {true, true, false, false, false, false, false, false, false, false};
        CandidatsCase c3 = new CandidatsCase(tab3);
        
        boolean tab4 [] = {false, true, true, true, true, true, true, true, true, true};
        CandidatsCase c4 = new CandidatsCase(tab4);
        
        boolean tab5 [] = {true, true, true, false, false, false, false, false, false, false};
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
