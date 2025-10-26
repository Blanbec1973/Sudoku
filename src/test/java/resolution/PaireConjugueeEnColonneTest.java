package resolution;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import model.grille.CaseEnCours;
import model.grille.Grille;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PaireConjugueeEnColonneTest {
	private static PaireConjugueeEnColonne methode;
	private final Grille grille = new Grille();
	
	@BeforeAll
	void setUpBeforeClass() {
		grille.init(System.getProperty("user.dir")+"/src/test/resources/grillesTest/PaireConjugueeEnColonne.sud");
		methode = new PaireConjugueeEnColonne(grille);
	}

	@Test
	@Order(1)
	void testTraiteCaseEnCours() {
		CaseEnCours.setCaseEnCours(18);
		assertFalse(methode.traiteCaseEnCours(false).isPresent());
		CaseEnCours.setCaseEnCours(36);
		assertTrue(methode.traiteCaseEnCours(false).isPresent());
		assertEquals(3,methode.c1);
		assertEquals(7,methode.c2);
	}

	@Test
	@Order(2)
	void testDetecteConfiguration() {
		CaseEnCours.setCaseEnCours(2);
		assertFalse(methode.detecteConfiguration());
		CaseEnCours.setCaseEnCours(36);
		assertTrue(methode.detecteConfiguration());
	}
	
	@Test
	@Order(3)
	void testDetecteCandidatAEliminer() {
		CaseEnCours.setCaseEnCours(36);
		methode.c1=3;
		methode.c2=7;
		methode.y2=4;
		assertTrue(methode.detecteCandidatAEliminer());
		assertEquals(7, methode.candidatAEliminer);
		assertEquals(18,methode.numCaseAction);
		grille.elimineCandidat(18,7);

		assertTrue(methode.detecteCandidatAEliminer());
		assertEquals(7, methode.candidatAEliminer);
		assertEquals(27,methode.numCaseAction);
		grille.elimineCandidat(27,7);

		assertTrue(methode.detecteCandidatAEliminer());
		assertEquals(7, methode.candidatAEliminer);
		assertEquals(54,methode.numCaseAction);
		grille.elimineCandidat(54,7);

		assertTrue(methode.detecteCandidatAEliminer());
		assertEquals(3, methode.candidatAEliminer);
		assertEquals(63,methode.numCaseAction);
		grille.elimineCandidat(63,3);
		assertTrue(methode.detecteCandidatAEliminer());
		assertEquals(7, methode.candidatAEliminer);
		assertEquals(63,methode.numCaseAction);
		grille.elimineCandidat(63,7);

		assertTrue(methode.detecteCandidatAEliminer());
		assertEquals(3, methode.candidatAEliminer);
		assertEquals(72,methode.numCaseAction);
		grille.elimineCandidat(72,3);

		assertTrue(methode.detecteCandidatAEliminer());
		assertEquals(7, methode.candidatAEliminer);
		assertEquals(72,methode.numCaseAction);
		grille.elimineCandidat(72,7);

		assertFalse(methode.detecteCandidatAEliminer());
	}

	@Test
	@Order(4)
	void testGetSimpleName() {
		assertEquals("PaireConjugueeEnColonne",methode.getSimpleName());
	}
}
