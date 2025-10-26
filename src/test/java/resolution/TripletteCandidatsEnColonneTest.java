package resolution;

import model.grille.CaseEnCours;
import model.grille.Grille;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class TripletteCandidatsEnColonneTest {
	private static Grille grille;
	private static TripletteCandidatsEnColonne methode;
	
	@BeforeAll
	static void setUpBeforeClass() {
		grille = new Grille();
		grille.init(System.getProperty("user.dir")+"/src/test/resources/grillesTest/TripletteCandidatsEnColonne.sud");
		methode = new TripletteCandidatsEnColonne(grille);
	}

	@Test
	void testTraiteCaseEnCours() {
		CaseEnCours.setCaseEnCours(18);
		grille.elimineCandidat(27, 8);
		assertTrue(methode.traiteCaseEnCours(false).isPresent());
		assertEquals(36,methode.numCaseAction);
		assertEquals(3,methode.candidatAEliminer);

		grille.elimineCandidat(36, 3);
		assertTrue(methode.traiteCaseEnCours(false).isPresent());
		assertEquals(54,methode.numCaseAction);
		assertEquals(6,methode.candidatAEliminer);

		grille.elimineCandidat(54, 6);
		assertTrue(methode.traiteCaseEnCours(false).isPresent());
		assertEquals(63,methode.numCaseAction);
		assertEquals(1,methode.candidatAEliminer);

		grille.elimineCandidat(63, 1);
		assertTrue(methode.traiteCaseEnCours(false).isPresent());
		assertEquals(63,methode.numCaseAction);
		assertEquals(3,methode.candidatAEliminer);

		grille.elimineCandidat(63, 3);
		assertTrue(methode.traiteCaseEnCours(false).isPresent());
		assertEquals(63,methode.numCaseAction);
		assertEquals(6,methode.candidatAEliminer);

		grille.elimineCandidat(63, 6);
		assertFalse(methode.traiteCaseEnCours(false).isPresent());

		CaseEnCours.setCaseEnCours(10);
		assertFalse(methode.traiteCaseEnCours(false).isPresent());
	}

	@Test
	void testGetSimpleName() {
		assertEquals("TripletteCandidatsEnColonne",methode.getSimpleName());
	}
}
