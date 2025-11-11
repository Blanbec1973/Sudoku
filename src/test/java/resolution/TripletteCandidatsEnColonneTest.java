package resolution;

import model.grille.CaseContext;
import model.grille.Grille;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;


class TripletteCandidatsEnColonneTest {
	private static Grille grille;
	private static TripletteCandidatsEnColonne methode;
	
	@BeforeAll
	static void setUpBeforeClass() {
		grille = new Grille();
		String fileName = "src/test/resources/grillesTest/TripletteCandidatsEnColonne.sud";
		grille.init(Paths.get(fileName).toAbsolutePath());
		methode = new TripletteCandidatsEnColonne(grille);
	}

	@Test
	void testTraiteCaseEnCours() {
		CaseContext context = new CaseContext(18);
		grille.elimineCandidat(27, 8);
		assertTrue(methode.traiteCaseEnCours(context, false).isPresent());
		assertEquals(36,methode.numCaseAction);
		assertEquals(3,methode.candidatAEliminer);

		grille.elimineCandidat(36, 3);
		assertTrue(methode.traiteCaseEnCours(context, false).isPresent());
		assertEquals(54,methode.numCaseAction);
		assertEquals(6,methode.candidatAEliminer);

		grille.elimineCandidat(54, 6);
		assertTrue(methode.traiteCaseEnCours(context, false).isPresent());
		assertEquals(63,methode.numCaseAction);
		assertEquals(1,methode.candidatAEliminer);

		grille.elimineCandidat(63, 1);
		assertTrue(methode.traiteCaseEnCours(context, false).isPresent());
		assertEquals(63,methode.numCaseAction);
		assertEquals(3,methode.candidatAEliminer);

		grille.elimineCandidat(63, 3);
		assertTrue(methode.traiteCaseEnCours(context, false).isPresent());
		assertEquals(63,methode.numCaseAction);
		assertEquals(6,methode.candidatAEliminer);

		grille.elimineCandidat(63, 6);
		assertFalse(methode.traiteCaseEnCours(context, false).isPresent());

		CaseContext context2 = new CaseContext(10);
		assertFalse(methode.traiteCaseEnCours(context2, false).isPresent());
	}

	@Test
	void testGetSimpleName() {
		assertEquals("TripletteCandidatsEnColonne",methode.getSimpleName());
	}
}
