package resolution;

import model.grille.CaseContext;
import model.grille.Grille;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;


class TripletteCandidatsEnColonneTest {
	private static Grille grille;
	private static TripletteCandidatsEnColonne methode;
	
	@BeforeAll
	static void setUpBeforeClass() {
		grille = new Grille();
		String fileName = "src/test/resources/grillesTest/TripletteCandidatsEnColonne.sud";
		grille.init(Paths.get(fileName).toAbsolutePath());
		methode = new TripletteCandidatsEnColonne(grille);
	}

	@Test
	void testTraiteCaseEnCours() {
		CaseContext context = new CaseContext(18);
		grille.elimineCandidat(27, 8);
		ResolutionAction action = methode.traiteCaseEnCours(context, false)
				.orElseThrow(()-> new AssertionError("Should be present"));
		assertEquals(36,action.getNumCaseAction());
		assertEquals(3,action.getCandidatAEliminer());

		grille.elimineCandidat(36, 3);
		ResolutionAction action2 = methode.traiteCaseEnCours(context, false)
				.orElseThrow(()-> new AssertionError("Should be present"));
		assertEquals(54,action2.getNumCaseAction());
		assertEquals(6,action2.getCandidatAEliminer());

		grille.elimineCandidat(54, 6);
		ResolutionAction action3 = methode.traiteCaseEnCours(context, false)
				.orElseThrow(()-> new AssertionError("Should be present"));
		assertEquals(63,action3.getNumCaseAction());
		assertEquals(1,action3.getCandidatAEliminer());

		grille.elimineCandidat(63, 1);
		ResolutionAction action4 = methode.traiteCaseEnCours(context, false)
				.orElseThrow(()-> new AssertionError("Should be present"));
		assertEquals(63,action4.getNumCaseAction());
		assertEquals(3,action4.getCandidatAEliminer());

		grille.elimineCandidat(63, 3);
		ResolutionAction action5 = methode.traiteCaseEnCours(context, false)
				.orElseThrow(()-> new AssertionError("Should be present"));
		assertEquals(63,action5.getNumCaseAction());
		assertEquals(6,action5.getCandidatAEliminer());

		grille.elimineCandidat(63, 6);
		assertFalse(methode.traiteCaseEnCours(context, false).isPresent());

		CaseContext context2 = new CaseContext(10);
		assertFalse(methode.traiteCaseEnCours(context2, false).isPresent());
	}

	@Test
	void testGetSimpleName() {
		assertEquals("TripletteCandidatsEnColonne",methode.getSimpleName());
	}
}
