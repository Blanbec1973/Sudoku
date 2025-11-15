package resolution.paireconjuguee;

import model.grille.CaseContext;
import model.grille.Grille;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import resolution.ResolutionAction;
import resolution.ZoneType;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PaireConjugueeEnRegionTest {
	private PaireConjugueeDansZone methode;
	private final Grille grille = new Grille();



	@BeforeAll
	void setUpBeforeClass() {
		String fileName = "src/test/resources/grillesTest/PaireConjugueeEnRegion.sud";
		grille.init(Paths.get(fileName).toAbsolutePath());
		methode = new PaireConjugueeDansZone(grille, ZoneType.BLOC);
	}

	@Test
	void testTraiteCaseEnCours() {
		CaseContext context = new CaseContext(36);
		ResolutionAction action = methode.traiteCaseEnCours(context, false)
				.orElseThrow(()->new AssertionError("Should be present"));
		assertEquals(3,action.getCandidatUtilise(0));
		assertEquals(7,action.getCandidatUtilise(1));

		assertEquals(43, action.getNumCaseAction());
		assertEquals(3, action.getCandidatAEliminer());
		grille.elimineCandidat(43,3);

		ResolutionAction action2 = methode.traiteCaseEnCours(context, false)
				.orElseThrow(()->new AssertionError("Should be present"));
		assertEquals(43, action2.getNumCaseAction());
		assertEquals(7, action2.getCandidatAEliminer());
		grille.elimineCandidat(43,7);

		ResolutionAction action3 = methode.traiteCaseEnCours(context, false)
				.orElseThrow(()->new AssertionError("Should be present"));
		assertEquals(52, action3.getNumCaseAction());
		assertEquals(7, action3.getCandidatAEliminer());
		grille.elimineCandidat(52,7);

		ResolutionAction action4 = methode.traiteCaseEnCours(context, false)
				.orElseThrow(()->new AssertionError("Should be present"));
		assertEquals(54, action4.getNumCaseAction());
		assertEquals(7, action4.getCandidatAEliminer());
		grille.elimineCandidat(54,7);

		assertFalse(methode.traiteCaseEnCours(context, false).isPresent());

		CaseContext context2 = new CaseContext(18);
		assertFalse(methode.traiteCaseEnCours(context2, false).isPresent());

	}
	@Test
	@Order(4)
	void testGetSimpleName() {
		assertEquals("PaireConjugueeDansZone",methode.getSimpleName());
		assertEquals(ZoneType.BLOC,methode.getZone());
	}
}
