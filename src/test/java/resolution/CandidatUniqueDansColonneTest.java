package resolution;

import model.grille.CaseContext;
import model.grille.Grille;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;


class CandidatUniqueDansColonneTest {
	private static CandidatUniqueDansColonne methode;
	
	@BeforeAll
	static void setUpBeforeClass() {
		Grille grille = new Grille();
		String fileName = "src/test/resources/grillesTest/CandidatUniqueDansColonne.sud";
		grille.init(Paths.get(fileName).toAbsolutePath());
		methode = new CandidatUniqueDansColonne(grille);
	}

	@Test
	void testTraiteCaseEnCours() {
		CaseContext context = new CaseContext(2);
		assertFalse(methode.traiteCaseEnCours(context, false).isPresent());
		
		CaseContext context2 = new CaseContext(53);
		ResolutionAction action = methode.traiteCaseEnCours(context2, false)
				.orElseThrow(()-> new AssertionError("Should be present"));
		assertEquals(9, action.getSolution());
	}

	@Test
	void testGetSimpleName() {
		assertEquals("CandidatUniqueDansColonne",methode.getSimpleName());
	}
}
