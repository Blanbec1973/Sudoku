package resolution;

import model.grille.CaseContext;
import model.grille.Grille;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


class TripletteCandidatsEnColonneTest {
	private static Grille grille;
	private static TripletteCandidatsEnZone methode;
	
	@BeforeAll
	static void setUpBeforeClass() {
		grille = new Grille();
		String fileName = "src/test/resources/grillesTest/TripletteCandidatsEnColonne.sud";
		grille.init(Paths.get(fileName).toAbsolutePath());
		methode = new TripletteCandidatsEnZone(grille, ZoneType.COLONNE);
	}
	@Test
	void testCasPassant() {
		CaseContext context = new CaseContext(27);
		ResolutionAction action = methode.traiteCaseEnCours(context, false)
				.orElseThrow(()-> new AssertionError("Should be present"));
		assertEquals(27,action.getNumCaseAction());
		assertEquals(6,action.getCandidatAEliminer());

		grille.elimineCandidat(27, 6);
		ResolutionAction action2 = methode.traiteCaseEnCours(context, false)
				.orElseThrow(()-> new AssertionError("Should be present"));
		assertEquals(27,action2.getNumCaseAction());
		assertEquals(9,action2.getCandidatAEliminer());

		grille.elimineCandidat(27, 9);
		ResolutionAction action3 = methode.traiteCaseEnCours(context, false)
				.orElseThrow(()-> new AssertionError("Should be present"));
		assertEquals(63,action3.getNumCaseAction());
		assertEquals(5,action3.getCandidatAEliminer());

		grille.elimineCandidat(63, 5);
		ResolutionAction action4 = methode.traiteCaseEnCours(context, false)
				.orElseThrow(()-> new AssertionError("Should be present"));
		assertEquals(81,action4.getNumCaseAction());
		assertEquals(5,action4.getCandidatAEliminer());

		grille.elimineCandidat(81, 5);
		ResolutionAction action5 = methode.traiteCaseEnCours(context, false)
				.orElseThrow(()-> new AssertionError("Should be present"));
		assertEquals(81,action5.getNumCaseAction());
		assertEquals(9,action5.getCandidatAEliminer());

		grille.elimineCandidat(81, 9);
		assertFalse(methode.traiteCaseEnCours(context, false).isPresent());

	}
	@Test
	void testTraiteCasNonPassant() {
		CaseContext context = new CaseContext(17);
		assertFalse(methode.traiteCaseEnCours(context, false).isPresent());
	}

	@Test
	void testGetSimpleName() {
		assertEquals("TripletteCandidatsEnZone",methode.getSimpleName());
		assertEquals(ZoneType.COLONNE, methode.getZone());
	}
}
