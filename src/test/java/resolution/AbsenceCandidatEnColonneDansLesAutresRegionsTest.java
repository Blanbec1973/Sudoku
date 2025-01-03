package resolution;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.grille.CaseEnCours;
import model.grille.Grille;

class AbsenceCandidatEnColonneDansLesAutresRegionsTest {
	private static AbsenceCandidatEnColonneDansLesAutresRegions methode;
	
	@BeforeAll
	static void setUpBeforeClass() {
		Grille grille = new Grille();
		grille.init(System.getProperty("user.dir")+"/src/test/resources/grillesTest/AbsenceCandidatEnColonneDansLesAutresRegions.sud");
		methode = new AbsenceCandidatEnColonneDansLesAutresRegions(grille);
	}

	@Test
	void testTraiteCaseEnCours() {
		CaseEnCours.setCaseEnCours(62);
		assertTrue(methode.traiteCaseEnCours(false));
		assertEquals(70,methode.numCaseAction);
	}
	
	@Test
	void testTestCase() {
		CaseEnCours.setCaseEnCours(62);
		assertFalse(methode.testCase(0,5));
		assertTrue(methode.testCase(0, 7));
	}
	
	@Test
	void testDetecteCandidatAEliminer() {
		CaseEnCours.setCaseEnCours(62);
		methode.candidatAEliminer = 7;
		assertTrue(methode.detecteCandidatAEliminer());
		assertEquals(6,methode.xAction);
		assertEquals(7,methode.yAction);
	}

	@Test
	void testGetSimpleName() {
		assertEquals("AbsenceCandidatEnColonneDansLesAutresRegions",methode.getSimpleName());
	}
}