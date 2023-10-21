package resolution;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import modele.grille.CaseEnCours;
import modele.grille.Grille;

class CandidatUniqueDansCaseTest {
	private static Grille grille;
	private static CandidatUniqueDansCase methode;
	
	@BeforeAll
	static void setUpBeforeClass() {
		grille = new Grille();
		grille.init(System.getProperty("user.dir")+"/src/test/resources/grillesTest/CandidatUniqueDansCase.sud");
		methode = new CandidatUniqueDansCase(null, grille);
	}

	@Test
	void testTraiteCaseEnCours() {
		CaseEnCours.setCaseEnCours(1);
		assertFalse(methode.traiteCaseEnCours(false));
		
		CaseEnCours.setCaseEnCours(69);
		assertTrue(methode.traiteCaseEnCours(false));
		assertEquals(7, methode.getSolution());
		assertEquals(69,methode.getNumCaseAction());
	}

}
