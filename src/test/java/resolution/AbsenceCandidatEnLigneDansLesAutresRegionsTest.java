package resolution;

import model.grille.CaseContext;
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
		CaseContext context = new CaseContext(76);
		ResolutionAction action = methode.traiteCaseEnCours(context, false)
				.orElseThrow(()-> new AssertionError("Should be present"));
		assertTrue(methode.traiteCaseEnCours(context, false).isPresent());
		assertEquals(67,action.getNumCaseAction());
	}
	
	@Test
	void testTestCase() {
		CaseContext context = new CaseContext(76);
		assertFalse(methode.testCase(context, 0, 6));
		assertTrue(methode.testCase(context, 0, 3));
	}
	
	@Test
	void testDetecteCandidatAEliminer() {
		CaseContext context = new CaseContext(76);
		methode.candidatAEliminer = 3;
		assertTrue(methode.detecteCandidatAEliminer(context));
		assertEquals(3,methode.xAction);
		assertEquals(7,methode.yAction);
	}
	@Test
	void testGetSimpleName() {
		assertEquals("AbsenceCandidatEnLigneDansLesAutresRegions",methode.getSimpleName());
	}
}
