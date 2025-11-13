package resolution;

import model.grille.CaseContext;
import model.grille.Grille;
import org.junit.jupiter.api.*;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PaireConjugueeEnLigneTest {
	private PaireConjugueeEnLigne methode;
	private final Grille grille = new Grille();
	
	@BeforeAll
	void setUpBeforeClass() {
		String fileName = "src/test/resources/grillesTest/PaireConjugueeEnLigne.sud";
		grille.init(Paths.get(fileName).toAbsolutePath());
		methode = new PaireConjugueeEnLigne(grille);
	}
	
	@Test
	@Order(1)
	void testTraiteCaseEnCours() {
		CaseContext context = new CaseContext(3);
		assertTrue(methode.traiteCaseEnCours(context, false).isPresent());
		ResolutionAction action = methode.traiteCaseEnCours(context, false)
				.orElseThrow(()->new AssertionError("Should be present"));
		assertEquals(2,action.getCandidatUtilise(0));
		assertEquals(6,action.getCandidatUtilise(1));
	}

	@Test
	@Order(2)
	void testDetecteConfiguration() {
		CaseContext context = new CaseContext(3);
		assertTrue(methode.detecteConfiguration(context));
	}
	
	@Test
	@Order(3)
	void testDetecteCandidatAEliminer() {
		CaseContext context = new CaseContext(3);
		int[] candidatsUtilises = {2,6};
		ResolutionAction action = methode.detecteCandidatAEliminer(context, candidatsUtilises)
				.orElseThrow(()->new AssertionError("Should be present"));
		assertEquals(1,action.getNumCaseAction());
		assertEquals(2, action.getCandidatAEliminer());

		grille.elimineCandidat(1,2);
		grille.elimineCandidat(1,6);
		grille.elimineCandidat(2,2);
		grille.elimineCandidat(2,6);
		assertFalse(methode.detecteCandidatAEliminer(context, candidatsUtilises).isPresent());
	}

	@Test
	@Order(4)
	void testGetSimpleName() {
		assertEquals("PaireConjugueeEnLigne",methode.getSimpleName());
	}

}
