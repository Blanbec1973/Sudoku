package resolution;

import model.grille.CaseEnCours;
import model.grille.Grille;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class AbsenceCandidatEnColonneDansLesAutresRegionsTest {
	private static AbsenceCandidatEnColonneDansLesAutresRegions methode;

	
	@BeforeAll
	static void setUpBeforeClass() {
		String fileName = "src/test/resources/grillesTest/AbsenceCandidatEnColonneDansLesAutresRegions.sud";
		Grille grille = new Grille();
		grille.init(Paths.get(fileName).toAbsolutePath());
		methode = new AbsenceCandidatEnColonneDansLesAutresRegions(grille);
	}

	@Test
	void testTraiteCaseEnCours() {
		CaseEnCours.setCaseEnCours(62);
		assertTrue(methode.traiteCaseEnCours(false).isPresent());
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