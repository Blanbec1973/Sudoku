package resolution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import modele.CaseEnCours;
import modele.Grille;
import modele.InitialiseurDeGrille;

class AbsenceCandidatEnLigneDansLesAutresRegionsTest {

	private static Grille grille;
	private static AbsenceCandidatEnLigneDansLesAutresRegions methode;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		grille = new Grille();
		InitialiseurDeGrille i = new InitialiseurDeGrille(grille);
		i.init(System.getProperty("user.dir")+"/src/test/resources/grillesTest/AbsenceCandidatEnLigneDansLesAutresRegions.sud");
		i.calculTousLesCandidats();
		methode = new AbsenceCandidatEnLigneDansLesAutresRegions(null, grille);
	}

	@Test
	void testTraiteCaseEnCours() {
		CaseEnCours.setCaseEnCours(76);
		assertTrue(methode.traiteCaseEnCours(false));
		assertEquals(67,methode.numCaseAction);
	}
	
	@Test
	void testTestCase() {
		CaseEnCours.setCaseEnCours(76);
		assertFalse(methode.testCase(0, 6));
		assertTrue(methode.testCase(0, 3));
	}
	
	@Test
	void testDetecteCandidatAEliminer() {
		CaseEnCours.setCaseEnCours(76);
		methode.candidatAEliminer = 3;
		assertTrue(methode.detecteCandidatAEliminer());
		assertEquals(3,methode.xAction);
		assertEquals(7,methode.yAction);
	}

}
