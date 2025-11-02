package resolution;

import model.grille.CaseEnCours;
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
		CaseEnCours.setCaseEnCours(1);
		assertFalse(methode.traiteCaseEnCours(false).isPresent());
		
		CaseEnCours.setCaseEnCours(69);
		assertTrue(methode.traiteCaseEnCours(false).isPresent());
		assertEquals(7, methode.getSolution());
		assertEquals(69,methode.getNumCaseAction());
	}

	@Test
	void testGetSimpleName() {
		assertEquals("CandidatUniqueDansCase",methode.getSimpleName());
	}

}
