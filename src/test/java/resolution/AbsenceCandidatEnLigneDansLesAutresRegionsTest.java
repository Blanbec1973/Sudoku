package resolution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.grille.CaseEnCours;
import model.grille.Grille;


class AbsenceCandidatEnLigneDansLesAutresRegionsTest {

	private static AbsenceCandidatEnLigneDansLesAutresRegions methode;
	
	@BeforeAll
	static void setUpBeforeClass() {
		Grille grille = new Grille();
		grille.init(System.getProperty("user.dir")+"/src/test/resources/grillesTest/AbsenceCandidatEnLigneDansLesAutresRegions.sud");
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
