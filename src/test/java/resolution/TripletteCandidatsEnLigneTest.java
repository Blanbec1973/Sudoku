package resolution;

import model.grille.CaseContext;
import model.grille.Grille;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;


class TripletteCandidatsEnLigneTest {
	private static Grille grille;
	private static TripletteCandidatsEnLigne methode;
	
	@BeforeAll
	static void setUpBeforeClass() {
		grille = new Grille();
		String fileName = "src/test/resources/grillesTest/TripletteCandidatsEnLigne.sud";
		grille.init(Paths.get(fileName).toAbsolutePath());
		methode = new TripletteCandidatsEnLigne(grille);
	}

	@Test
	void testTraiteCaseEnCours() {
		CaseContext context = new CaseContext(74);
		grille.elimineCandidat(75, 8);
		assertTrue(methode.traiteCaseEnCours(context, false).isPresent());
		assertEquals(76,methode.numCaseAction);
		assertEquals(3,methode.candidatAEliminer);

		grille.elimineCandidat(76, 3);
		assertTrue(methode.traiteCaseEnCours(context, false).isPresent());
		assertEquals(78,methode.numCaseAction);
		assertEquals(6,methode.candidatAEliminer);

		grille.elimineCandidat(78, 6);
		assertTrue(methode.traiteCaseEnCours(context, false).isPresent());
		assertEquals(79,methode.numCaseAction);
		assertEquals(1,methode.candidatAEliminer);

		grille.elimineCandidat(79, 1);
		assertTrue(methode.traiteCaseEnCours(context, false).isPresent());
		assertEquals(79,methode.numCaseAction);
		assertEquals(3,methode.candidatAEliminer);

		grille.elimineCandidat(79, 3);
		assertTrue(methode.traiteCaseEnCours(context, false).isPresent());
		assertEquals(79,methode.numCaseAction);
		assertEquals(6,methode.candidatAEliminer);

		grille.elimineCandidat(79, 6);
		assertFalse(methode.traiteCaseEnCours(context, false).isPresent());

		CaseContext context2 = new CaseContext(2);
		assertFalse(methode.traiteCaseEnCours(context2, false).isPresent());
	}

	@Test
	void testGetSimpleName() {
		assertEquals("TripletteCandidatsEnLigne",methode.getSimpleName());
	}
}
