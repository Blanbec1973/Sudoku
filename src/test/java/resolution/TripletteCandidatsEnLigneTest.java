package resolution;

import model.grille.CaseContext;
import model.grille.Grille;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;


class TripletteCandidatsEnLigneTest {
	private static Grille grille;
	private static TripletteCandidatsEnLigne methode;
	
	@BeforeAll
	static void setUpBeforeClass() {
		grille = new Grille();
		String fileName = "src/test/resources/grillesTest/TripletteCandidatsEnLigne.sud";
		grille.init(Paths.get(fileName).toAbsolutePath());
		methode = new TripletteCandidatsEnLigne(grille);
	}

	@Test
	void testTraiteCaseEnCours() {
		CaseContext context = new CaseContext(74);
		grille.elimineCandidat(75, 8);
		ResolutionAction action = methode.traiteCaseEnCours(context, false)
				.orElseThrow(()-> new AssertionError("Should be present"));
		assertEquals(76,action.getNumCaseAction());
		assertEquals(3,methode.candidatAEliminer);

		grille.elimineCandidat(76, 3);
		ResolutionAction action2 = methode.traiteCaseEnCours(context, false)
				.orElseThrow(()-> new AssertionError("Should be present"));
		assertEquals(78,action2.getNumCaseAction());
		assertEquals(6,methode.candidatAEliminer);

		grille.elimineCandidat(78, 6);
		ResolutionAction action3 = methode.traiteCaseEnCours(context, false)
				.orElseThrow(()-> new AssertionError("Should be present"));
		assertEquals(79,action3.getNumCaseAction());
		assertEquals(1,methode.candidatAEliminer);

		grille.elimineCandidat(79, 1);
		ResolutionAction action4 = methode.traiteCaseEnCours(context, false)
				.orElseThrow(()-> new AssertionError("Should be present"));
		assertEquals(79,action4.getNumCaseAction());
		assertEquals(3,methode.candidatAEliminer);

		grille.elimineCandidat(79, 3);
		ResolutionAction action5 = methode.traiteCaseEnCours(context, false)
				.orElseThrow(()-> new AssertionError("Should be present"));
		assertEquals(79,action5.getNumCaseAction());
		assertEquals(6,methode.candidatAEliminer);

		grille.elimineCandidat(79, 6);
		assertFalse(methode.traiteCaseEnCours(context, false).isPresent());

		CaseContext context2 = new CaseContext(2);
		assertFalse(methode.traiteCaseEnCours(context2, false).isPresent());
	}

	@Test
	void testGetSimpleName() {
		assertEquals("TripletteCandidatsEnLigne",methode.getSimpleName());
	}
}
