package resolution;

import model.grille.CaseContext;
import model.grille.Grille;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


class TripletteCandidatsEnBlocTest {
	private static Grille grille;
	private static TripletteCandidatsEnZone methode;
	
	@BeforeAll
	static void setUpBeforeClass() {
		grille = new Grille();
		String fileName = "src/test/resources/grillesTest/TripletteCandidatsEnBloc.sud";
		grille.init(Paths.get(fileName).toAbsolutePath());
		methode = new TripletteCandidatsEnZone(grille, ZoneType.BLOC);
	}

	@Test
	void testTraiteCaseEnCours() {
		CaseContext context = new CaseContext(67);
		ResolutionAction action = methode.traiteCaseEnCours(context, false)
				.orElseThrow(()-> new AssertionError("Should be present"));
		assertEquals(67,action.getNumCaseAction());
		assertEquals(2,action.getCandidatAEliminer());

		grille.elimineCandidat(67, 2);
		ResolutionAction action2 = methode.traiteCaseEnCours(context, false)
				.orElseThrow(()-> new AssertionError("Should be present"));
		assertEquals(67,action2.getNumCaseAction());
		assertEquals(4,action2.getCandidatAEliminer());

		grille.elimineCandidat(67, 4);
		ResolutionAction action3 = methode.traiteCaseEnCours(context, false)
				.orElseThrow(()-> new AssertionError("Should be present"));
		assertEquals(67,action3.getNumCaseAction());
		assertEquals(8,action3.getCandidatAEliminer());

		grille.elimineCandidat(67, 8);
		ResolutionAction action4 = methode.traiteCaseEnCours(context, false)
				.orElseThrow(()-> new AssertionError("Should be present"));
		assertEquals(76,action4.getNumCaseAction());
		assertEquals(2,action4.getCandidatAEliminer());

		grille.elimineCandidat(76, 2);
		grille.elimineCandidat(76, 4);
		grille.elimineCandidat(76, 8);
		grille.elimineCandidat(77, 2);
		grille.elimineCandidat(77, 4);
		grille.elimineCandidat(77, 8);
		assertFalse(methode.traiteCaseEnCours(context, false).isPresent());

	}

	@Test
	void testGetSimpleName() {
		assertEquals("TripletteCandidatsEnZone",methode.getSimpleName());
		assertEquals(ZoneType.BLOC, methode.getZone());
	}
}
