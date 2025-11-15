package resolution.paireconjuguee;

import model.grille.CaseContext;
import model.grille.Grille;
import org.junit.jupiter.api.*;
import resolution.ResolutionAction;
import resolution.ZoneType;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PaireConjugueeEnColonneTest {
	private static PaireConjugueeDansZone methode;
	private final Grille grille = new Grille();
	
	@BeforeAll
	void setUpBeforeClass() {
		String fileName = "src/test/resources/grillesTest/PaireConjugueeEnColonne.sud";
		grille.init(Paths.get(fileName).toAbsolutePath());
		methode = new PaireConjugueeDansZone(grille, ZoneType.COLONNE);
	}

	@Test
	void testTraiteCaseEnCours() {
		CaseContext context = new CaseContext(18);
		assertFalse(methode.traiteCaseEnCours(context, false).isPresent());

		CaseContext context2 = new CaseContext(36);
		ResolutionAction action = methode.traiteCaseEnCours(context2, false)
				.orElseThrow(()->new AssertionError("Should be present"));
		assertEquals(3,action.getCandidatUtilise(0));
		assertEquals(7,action.getCandidatUtilise(1));
		assertEquals(7, action.getCandidatAEliminer());
		assertEquals(18,action.getNumCaseAction());
		grille.elimineCandidat(18,7);

		ResolutionAction action2 = methode.traiteCaseEnCours(context2, false)
				.orElseThrow(()->new AssertionError("Should be present"));
		assertEquals(7, action2.getCandidatAEliminer());
		assertEquals(27,action2.getNumCaseAction());
		grille.elimineCandidat(27,7);

		ResolutionAction action3 = methode.traiteCaseEnCours(context2, false)
				.orElseThrow(()->new AssertionError("Should be present"));
		assertEquals(54,action3.getNumCaseAction());
		assertEquals(7, action3.getCandidatAEliminer());
		grille.elimineCandidat(54,7);

		ResolutionAction action4 = methode.traiteCaseEnCours(context2, false)
				.orElseThrow(()->new AssertionError("Should be present"));
		assertEquals(3, action4.getCandidatAEliminer());
		assertEquals(63,action4.getNumCaseAction());
		grille.elimineCandidat(63,3);

		ResolutionAction action5 = methode.traiteCaseEnCours(context2, false)
				.orElseThrow(()->new AssertionError("Should be present"));
		assertEquals(7, action5.getCandidatAEliminer());
		assertEquals(63,action5.getNumCaseAction());
		grille.elimineCandidat(63,7);

		ResolutionAction action6 = methode.traiteCaseEnCours(context2, false)
				.orElseThrow(()->new AssertionError("Should be present"));
		assertEquals(3, action6.getCandidatAEliminer());
		assertEquals(72,action6.getNumCaseAction());
		grille.elimineCandidat(72,3);

		ResolutionAction action7 = methode.traiteCaseEnCours(context2, false)
				.orElseThrow(()->new AssertionError("Should be present"));
		assertEquals(7, action7.getCandidatAEliminer());
		assertEquals(72,action7.getNumCaseAction());
		grille.elimineCandidat(72,7);

		assertFalse(methode.traiteCaseEnCours(context2, false).isPresent());
	}

	@Test
	void testGetSimpleName() {
		assertEquals("PaireConjugueeDansZone",methode.getSimpleName());
		assertEquals(ZoneType.COLONNE,methode.getZone());
	}
}
