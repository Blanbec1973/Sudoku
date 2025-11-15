package resolution.paireconjuguee;

import model.grille.CaseContext;
import model.grille.Grille;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import resolution.ResolutionAction;
import resolution.ZoneType;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PaireConjugueeEnLigneTest {
	private PaireConjugueeDansZone methode;
	private final Grille grille = new Grille();
	
	@BeforeAll
	void setUpBeforeClass() {
		String fileName = "src/test/resources/grillesTest/PaireConjugueeEnLigne.sud";
		grille.init(Paths.get(fileName).toAbsolutePath());
		methode = new PaireConjugueeDansZone(grille, ZoneType.LIGNE);
	}
	
	@Test
	void testTraiteCaseEnCours() {
		CaseContext context = new CaseContext(3);
		assertTrue(methode.traiteCaseEnCours(context, false).isPresent());
		ResolutionAction action = methode.traiteCaseEnCours(context, false)
				.orElseThrow(()->new AssertionError("Should be present"));
		assertEquals(2,action.getCandidatUtilise(0));
		assertEquals(6,action.getCandidatUtilise(1));
		assertEquals(1,action.getNumCaseAction());
		assertEquals(2, action.getCandidatAEliminer());
		grille.elimineCandidat(1,2);
		grille.elimineCandidat(1,6);
		grille.elimineCandidat(2,2);
		grille.elimineCandidat(2,6);
		assertFalse(methode.traiteCaseEnCours(context, false).isPresent());
	}


	@Test
	void testGetSimpleName() {
		assertEquals("PaireConjugueeDansZone",methode.getSimpleName());
		assertEquals(ZoneType.LIGNE,methode.getZone());
	}

}
