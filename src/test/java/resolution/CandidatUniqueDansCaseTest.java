package resolution;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import modele.CaseEnCours;
import modele.Grille;
import modele.InitialiseurDeGrille;

class CandidatUniqueDansCaseTest {
	private static Grille grille;
	private static CandidatUniqueDansCase methode;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		grille = new Grille();
		InitialiseurDeGrille i = new InitialiseurDeGrille(grille);
		i.init("C:\\Users\\heynerr\\Documents\\W-Workspace\\Sudoku\\src\\test\\resources\\grillesTest\\CandidatUniqueDansCase.sud");
		i.calculTousLesCandidats();
		methode = new CandidatUniqueDansCase(null, grille);
	}

	@Test
	void testTraiteCaseEnCours() {
		CaseEnCours.setCaseEnCours(1);
		assertFalse(methode.traiteCaseEnCours(false));
		
		CaseEnCours.setCaseEnCours(69);
		assertTrue(methode.traiteCaseEnCours(false));
		assertEquals(7, methode.getSolution());
		assertEquals(69,methode.getNumCaseAction());
	}

}
