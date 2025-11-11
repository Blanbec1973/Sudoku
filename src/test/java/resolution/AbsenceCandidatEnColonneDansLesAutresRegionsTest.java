package resolution;

import model.grille.CaseContext;
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
		CaseContext context = new CaseContext(62);
		assertTrue(methode.traiteCaseEnCours(context, false).isPresent());
		assertEquals(70,methode.numCaseAction);
	}
	
	@Test
	void testTestCase() {
		CaseContext context = new CaseContext(62);
		assertFalse(methode.testCase(context, 0,5));
		assertTrue(methode.testCase(context, 0, 7));
	}
	
	@Test
	void testDetecteCandidatAEliminer() {
		CaseContext context = new CaseContext(62);
		methode.candidatAEliminer = 7;
		assertTrue(methode.detecteCandidatAEliminer(context));
		assertEquals(6,methode.xAction);
		assertEquals(7,methode.yAction);
	}

	@Test
	void testGetSimpleName() {
		assertEquals("AbsenceCandidatEnColonneDansLesAutresRegions",methode.getSimpleName());
	}
}