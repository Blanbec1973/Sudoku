package resolution;

import model.grille.CaseContext;
import model.grille.Grille;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CandidatDansLigneUniqueDuneRegionTest {
	private CandidatDansLigneUniqueDuneRegion methode;
	private final Grille grille = new Grille();
	
	@BeforeAll
	void setUpBeforeClass() {
		String fileName = "src/test/resources/grillesTest/CandidatDansLigneUniqueDuneRegion.sud";
		grille.init(Paths.get(fileName).toAbsolutePath());
		methode = new CandidatDansLigneUniqueDuneRegion(grille);
	}

	@Test
	void testTraiteCaseEnCours() {
		CaseContext context = new CaseContext(19);
		assertFalse(methode.traiteCaseEnCours(context, false).isPresent());

		CaseContext context2 = new CaseContext(4);
		assertTrue(methode.traiteCaseEnCours(context2, false).isPresent());
		assertEquals(1,methode.numCaseAction);
		assertEquals(6, methode.candidatAEliminer);
		assertEquals(0, methode.xAction);
		assertEquals(0,methode.yAction);

		grille.elimineCandidat(1,6);
		grille.elimineCandidat(9,6);
		assertFalse(methode.detecteCandidatAEliminer(context2));
	}

	@Test
	void testCandidatDansLigneUnique() {
		CaseContext context = new CaseContext(4);
		assertTrue(methode.candidatDansLigneUnique(context));

		CaseContext context2 = new CaseContext(57);
		methode.candidatAEliminer = 3;
		assertFalse(methode.candidatDansLigneUnique(context2));
		methode.candidatAEliminer = 7;
		assertFalse(methode.candidatDansLigneUnique(context2));
	}

	@Test
	void testGetSimpleName() {
		assertEquals("CandidatDansLigneUniqueDuneRegion",methode.getSimpleName());
	}
}
