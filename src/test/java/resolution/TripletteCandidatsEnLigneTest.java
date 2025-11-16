package resolution;

import model.grille.CaseContext;
import model.grille.Grille;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


class TripletteCandidatsEnLigneTest {
	private static Grille grille;
	private static TripletteCandidatsEnZone methode;
	
	@BeforeAll
	static void setUpBeforeClass() {
		grille = new Grille();
		String fileName = "src/test/resources/grillesTest/TripletteCandidatsEnLigne.sud";
		grille.init(Paths.get(fileName).toAbsolutePath());
		methode = new TripletteCandidatsEnZone(grille, ZoneType.LIGNE);
	}

	@Test
	void testTraiteCaseEnCours() {
		CaseContext context = new CaseContext(58);
		ResolutionAction action = methode.traiteCaseEnCours(context, false)
				.orElseThrow(()-> new AssertionError("Should be present"));
		assertEquals(61,action.getNumCaseAction());
		assertEquals(3,action.getCandidatAEliminer());

		grille.elimineCandidat(61, 3);
		ResolutionAction action2 = methode.traiteCaseEnCours(context, false)
				.orElseThrow(()-> new AssertionError("Should be present"));
		assertEquals(63,action2.getNumCaseAction());
		assertEquals(3,action2.getCandidatAEliminer());

		grille.elimineCandidat(63, 3);
		ResolutionAction action3 = methode.traiteCaseEnCours(context, false)
				.orElseThrow(()-> new AssertionError("Should be present"));
		assertEquals(63,action3.getNumCaseAction());
		assertEquals(5,action3.getCandidatAEliminer());

		grille.elimineCandidat(63, 5);
		assertFalse(methode.traiteCaseEnCours(context, false).isPresent());

	}

	@Test
	void testGetSimpleName() {
		assertEquals("TripletteCandidatsEnZone",methode.getSimpleName());
		assertEquals(ZoneType.LIGNE, methode.getZone());
	}
}
