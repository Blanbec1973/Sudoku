package resolution;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.grille.CaseEnCours;
import model.grille.Grille;

import static org.junit.jupiter.api.Assertions.*;


class TripletteCandidatsEnLigneTest {
	private static Grille grille;
	private static TripletteCandidatsEnLigne methode;
	
	@BeforeAll
	static void setUpBeforeClass() {
		grille = new Grille();
		grille.init(System.getProperty("user.dir")+"/src/test/resources/grillesTest/TripletteCandidatsEnLigne.sud");
		methode = new TripletteCandidatsEnLigne(grille);
	}

	@Test
	void testTraiteCaseEnCours() {
		CaseEnCours.setCaseEnCours(74);
		grille.elimineCandidat(75, 8);
		assertTrue(methode.traiteCaseEnCours(false).isPresent());
		assertEquals(76,methode.numCaseAction);
		assertEquals(3,methode.candidatAEliminer);

		grille.elimineCandidat(76, 3);
		assertTrue(methode.traiteCaseEnCours(false).isPresent());
		assertEquals(78,methode.numCaseAction);
		assertEquals(6,methode.candidatAEliminer);

		grille.elimineCandidat(78, 6);
		assertTrue(methode.traiteCaseEnCours(false).isPresent());
		assertEquals(79,methode.numCaseAction);
		assertEquals(1,methode.candidatAEliminer);

		grille.elimineCandidat(79, 1);
		assertTrue(methode.traiteCaseEnCours(false).isPresent());
		assertEquals(79,methode.numCaseAction);
		assertEquals(3,methode.candidatAEliminer);

		grille.elimineCandidat(79, 3);
		assertTrue(methode.traiteCaseEnCours(false).isPresent());
		assertEquals(79,methode.numCaseAction);
		assertEquals(6,methode.candidatAEliminer);

		grille.elimineCandidat(79, 6);
		assertFalse(methode.traiteCaseEnCours(false).isPresent());

		CaseEnCours.setCaseEnCours(2);
		assertFalse(methode.traiteCaseEnCours(false).isPresent());
	}

	@Test
	void testGetSimpleName() {
		assertEquals("TripletteCandidatsEnLigne",methode.getSimpleName());
	}
}
