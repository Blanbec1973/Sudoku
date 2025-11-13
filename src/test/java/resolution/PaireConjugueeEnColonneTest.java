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
		//assertTrue(methode.traiteCaseEnCours(context2, false).isPresent());
		ResolutionAction action = methode.traiteCaseEnCours(context2, false)
				.orElseThrow(()->new AssertionError("Should be present"));
		assertEquals(3,action.getCandidatUtilise(0));
		assertEquals(7,action.getCandidatUtilise(1));
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
		int[] candidatsUtilises = {3, 7};
		methode.y2=4;

		ResolutionAction action = methode.detecteCandidatAEliminer(context, candidatsUtilises)
				.orElseThrow(()->new AssertionError("Should be present"));
		assertEquals(7, action.getCandidatAEliminer());
		assertEquals(18,action.getNumCaseAction());
		grille.elimineCandidat(18,7);

		ResolutionAction action2 = methode.detecteCandidatAEliminer(context, candidatsUtilises)
				.orElseThrow(()->new AssertionError("Should be present"));
		assertEquals(7, action2.getCandidatAEliminer());
		assertEquals(27,action2.getNumCaseAction());
		grille.elimineCandidat(27,7);

		ResolutionAction action3 = methode.detecteCandidatAEliminer(context, candidatsUtilises)
				.orElseThrow(()->new AssertionError("Should be present"));
		assertEquals(7, action3.getCandidatAEliminer());
		assertEquals(54,action3.getNumCaseAction());
		grille.elimineCandidat(54,7);

		ResolutionAction action4 = methode.detecteCandidatAEliminer(context, candidatsUtilises)
				.orElseThrow(()->new AssertionError("Should be present"));
		assertEquals(3, action4.getCandidatAEliminer());
		assertEquals(63,action4.getNumCaseAction());
		grille.elimineCandidat(63,3);

		ResolutionAction action5 = methode.detecteCandidatAEliminer(context, candidatsUtilises)
				.orElseThrow(()->new AssertionError("Should be present"));
		assertEquals(7, action5.getCandidatAEliminer());
		assertEquals(63,action5.getNumCaseAction());
		grille.elimineCandidat(63,7);

		ResolutionAction action6 = methode.detecteCandidatAEliminer(context, candidatsUtilises)
				.orElseThrow(()->new AssertionError("Should be present"));
		assertEquals(3, action6.getCandidatAEliminer());
		assertEquals(72,action6.getNumCaseAction());
		grille.elimineCandidat(72,3);

		ResolutionAction action7 = methode.detecteCandidatAEliminer(context, candidatsUtilises)
				.orElseThrow(()->new AssertionError("Should be present"));
		assertEquals(7, action7.getCandidatAEliminer());
		assertEquals(72,action7.getNumCaseAction());
		grille.elimineCandidat(72,7);

		assertFalse(methode.detecteCandidatAEliminer(context, candidatsUtilises).isPresent());
	}

	@Test
	@Order(4)
	void testGetSimpleName() {
		assertEquals("PaireConjugueeEnColonne",methode.getSimpleName());
	}
}
