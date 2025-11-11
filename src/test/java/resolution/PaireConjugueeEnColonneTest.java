package resolution;

import model.grille.CaseContext;
import model.grille.Grille;
import org.junit.jupiter.api.*;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PaireConjugueeEnColonneTest {
	private static PaireConjugueeEnColonne methode;
	private final Grille grille = new Grille();
	
	@BeforeAll
	void setUpBeforeClass() {
		String fileName = "src/test/resources/grillesTest/PaireConjugueeEnColonne.sud";
		grille.init(Paths.get(fileName).toAbsolutePath());
		methode = new PaireConjugueeEnColonne(grille);
	}

	@Test
	@Order(1)
	void testTraiteCaseEnCours() {
		CaseContext context = new CaseContext(18);
		assertFalse(methode.traiteCaseEnCours(context, false).isPresent());
		CaseContext context2 = new CaseContext(36);
		assertTrue(methode.traiteCaseEnCours(context2, false).isPresent());
		assertEquals(3,methode.c1);
		assertEquals(7,methode.c2);
	}

	@Test
	@Order(2)
	void testDetecteConfiguration() {
		CaseContext context = new CaseContext(2);
		assertFalse(methode.detecteConfiguration(context));
		CaseContext context2 = new CaseContext(36);
		assertTrue(methode.detecteConfiguration(context2));
	}
	
	@Test
	@Order(3)
	void testDetecteCandidatAEliminer() {
		CaseContext context = new CaseContext(36);
		methode.c1=3;
		methode.c2=7;
		methode.y2=4;
		assertTrue(methode.detecteCandidatAEliminer(context));
		assertEquals(7, methode.candidatAEliminer);
		assertEquals(18,methode.numCaseAction);
		grille.elimineCandidat(18,7);

		assertTrue(methode.detecteCandidatAEliminer(context));
		assertEquals(7, methode.candidatAEliminer);
		assertEquals(27,methode.numCaseAction);
		grille.elimineCandidat(27,7);

		assertTrue(methode.detecteCandidatAEliminer(context));
		assertEquals(7, methode.candidatAEliminer);
		assertEquals(54,methode.numCaseAction);
		grille.elimineCandidat(54,7);

		assertTrue(methode.detecteCandidatAEliminer(context));
		assertEquals(3, methode.candidatAEliminer);
		assertEquals(63,methode.numCaseAction);
		grille.elimineCandidat(63,3);
		assertTrue(methode.detecteCandidatAEliminer(context));
		assertEquals(7, methode.candidatAEliminer);
		assertEquals(63,methode.numCaseAction);
		grille.elimineCandidat(63,7);

		assertTrue(methode.detecteCandidatAEliminer(context));
		assertEquals(3, methode.candidatAEliminer);
		assertEquals(72,methode.numCaseAction);
		grille.elimineCandidat(72,3);

		assertTrue(methode.detecteCandidatAEliminer(context));
		assertEquals(7, methode.candidatAEliminer);
		assertEquals(72,methode.numCaseAction);
		grille.elimineCandidat(72,7);

		assertFalse(methode.detecteCandidatAEliminer(context));
	}

	@Test
	@Order(4)
	void testGetSimpleName() {
		assertEquals("PaireConjugueeEnColonne",methode.getSimpleName());
	}
}
