package resolution;

import model.grille.CaseContext;
import model.grille.Grille;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class CandidatUniqueDansCaseTest {
	private static CandidatUniqueDansCase methode;
	
	@BeforeAll
	static void setUpBeforeClass() {
		Grille grille = new Grille();
		String fileName = "src/test/resources/grillesTest/CandidatUniqueDansCase.sud";
		grille.init(Paths.get(fileName).toAbsolutePath());
		methode = new CandidatUniqueDansCase(grille);
	}

	@Test
	void testTraiteCaseEnCours() {
		CaseContext context = new CaseContext(1);
		assertFalse(methode.traiteCaseEnCours(context, false).isPresent());
		
		CaseContext context2 = new CaseContext(69);
		assertTrue(methode.traiteCaseEnCours(context2, false).isPresent());
		assertEquals(7, methode.getSolution());
		assertEquals(69,methode.getNumCaseAction());
	}

	@Test
	void testGetSimpleName() {
		assertEquals("CandidatUniqueDansCase",methode.getSimpleName());
	}

}
