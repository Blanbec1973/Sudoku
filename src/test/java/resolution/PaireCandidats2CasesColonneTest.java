package resolution;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modele.CaseEnCours;
import modele.Grille;

class PaireCandidats2CasesColonneTest {
	private Grille grille;
	private PaireCandidats2CasesColonne paireCandidats2CasesColonne;
	
    public PaireCandidats2CasesColonneTest() {
    }
    
    @BeforeEach
    public void setUp() {
    	grille = new Grille();
    	CaseEnCours.setCaseEnCours(1);
    	paireCandidats2CasesColonne = new PaireCandidats2CasesColonne(null,grille);
    }

    @Test
	void testInserePaireCandidatsDansTab() {
		grille.getCaseEnCours().getCandidats().setAllCandidatsToFalse();
		grille.getCaseEnCours().getCandidats().setCandidat(1);
		grille.getCaseEnCours().getCandidats().setCandidat(5);
		grille.getCaseEnCours().getCandidats().setCandidat(6);
		grille.getCaseEnCours().getCandidats().setCandidat(7);
		grille.getCaseEnCours().getCandidats().setCandidat(8);
		paireCandidats2CasesColonne.inserePaireCandidatsDansTab();
    	assertEquals("100010000",paireCandidats2CasesColonne.tabCandidats.get(0).displayCandidats());
    	assertEquals("100001000",paireCandidats2CasesColonne.tabCandidats.get(1).displayCandidats());
    	assertEquals("100000100",paireCandidats2CasesColonne.tabCandidats.get(2).displayCandidats());
    	assertEquals("100000010",paireCandidats2CasesColonne.tabCandidats.get(3).displayCandidats());
    	assertEquals("000011000",paireCandidats2CasesColonne.tabCandidats.get(4).displayCandidats());
    	assertEquals("000010100",paireCandidats2CasesColonne.tabCandidats.get(5).displayCandidats());
    	assertEquals("000010010",paireCandidats2CasesColonne.tabCandidats.get(6).displayCandidats());
    	assertEquals("000001100",paireCandidats2CasesColonne.tabCandidats.get(7).displayCandidats());
    	assertEquals("000001010",paireCandidats2CasesColonne.tabCandidats.get(8).displayCandidats());
    	assertEquals("000000110",paireCandidats2CasesColonne.tabCandidats.get(9).displayCandidats());
	}

}
