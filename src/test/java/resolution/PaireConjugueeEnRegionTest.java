package resolution;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import modele.CaseEnCours;
import modele.Grille;
import modele.InitialiseurDeGrille;

class PaireConjugueeEnRegionTest {
	private static Grille grille;
	private static PaireConjugueeEnRegion methode;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		grille = new Grille();
		InitialiseurDeGrille i = new InitialiseurDeGrille(grille);
		i.init("C:\\Users\\heynerr\\Documents\\W-Workspace\\Sudoku\\src\\test\\resources\\grillesTest\\PaireConjugueeEnRegion.sud");
		i.calculTousLesCandidats();
		methode = new PaireConjugueeEnRegion(null, grille);
	}

	@Test
	void testTraiteCaseEnCours() {
		CaseEnCours.setCaseEnCours(36);
		assertTrue(methode.traiteCaseEnCours(false));
		assertEquals(3,methode.c1);
		assertEquals(7,methode.c2);
	}

	@Test
	void testDetecteConfiguration() {
		CaseEnCours.setCaseEnCours(36);
		assertTrue(methode.detecteConfiguration());
	}
	
	@Test
	void testDetecteCandidatAEliminer() {
		CaseEnCours.setCaseEnCours(36);
		methode.c1=3;
		methode.c2=7;
		assertTrue(methode.detecteCandidatAEliminer());
		assertEquals(43,methode.numCaseAction);
	}

}
