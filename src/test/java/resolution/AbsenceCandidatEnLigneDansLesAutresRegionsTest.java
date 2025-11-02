package resolution;

import model.grille.CaseEnCours;
import model.grille.Grille;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;


class AbsenceCandidatEnLigneDansLesAutresRegionsTest {

	private static AbsenceCandidatEnLigneDansLesAutresRegions methode;
	
	@BeforeAll
	static void setUpBeforeClass() {
		Grille grille = new Grille();
		String fileName = "src/test/resources/grillesTest/AbsenceCandidatEnLigneDansLesAutresRegions.sud";
		grille.init(Paths.get(fileName).toAbsolutePath());
		methode = new AbsenceCandidatEnLigneDansLesAutresRegions(grille);
	}

	@Test
	void testTraiteCaseEnCours() {
		CaseEnCours.setCaseEnCours(76);
		assertTrue(methode.traiteCaseEnCours(false).isPresent());
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
	@Test
	void testGetSimpleName() {
		assertEquals("AbsenceCandidatEnLigneDansLesAutresRegions",methode.getSimpleName());
	}
}
