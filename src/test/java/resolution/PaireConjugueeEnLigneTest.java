package resolution;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.grille.CaseEnCours;
import model.grille.Grille;

class PaireConjugueeEnLigneTest {
	private static PaireConjugueeEnLigne methode;
	
	@BeforeAll
	static void setUpBeforeClass() {
		Grille grille = new Grille();
		grille.init(System.getProperty("user.dir")+"/src/test/resources/grillesTest/PaireConjugueeEnLigne.sud");
		methode = new PaireConjugueeEnLigne(null, grille);
	}
	
	@Test
	void testTraiteCaseEnCours() {
		CaseEnCours.setCaseEnCours(3);
		assertTrue(methode.traiteCaseEnCours(false));
		assertEquals(2,methode.c1);
		assertEquals(6,methode.c2);
	}

	@Test
	void testDetecteConfiguration() {
		CaseEnCours.setCaseEnCours(3);
		assertTrue(methode.detecteConfiguration());
	}
	
	@Test
	void testDetecteCandidatAEliminer() {
		CaseEnCours.setCaseEnCours(3);
		methode.c1=2;
		methode.c2=6;
		assertTrue(methode.detecteCandidatAEliminer());
		assertEquals(2, methode.candidatAEliminer);
		assertEquals(1,methode.numCaseAction);
	}

}
