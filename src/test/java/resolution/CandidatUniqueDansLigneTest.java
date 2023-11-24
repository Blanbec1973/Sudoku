package resolution;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.grille.CaseEnCours;
import model.grille.Grille;


class CandidatUniqueDansLigneTest {
	private static CandidatUniqueDansLigne methode;
	
	@BeforeAll
	static void setUpBeforeClass() {
		Grille grille = new Grille();
		grille.init(System.getProperty("user.dir")+"/src/test/resources/grillesTest/CandidatUniqueDansLigne.sud");
		methode = new CandidatUniqueDansLigne(null, grille);
	}

	@Test
	void testTraiteCaseEnCours() {
		CaseEnCours.setCaseEnCours(2);
		assertFalse(methode.traiteCaseEnCours(false));
		
		CaseEnCours.setCaseEnCours(39);
		assertTrue(methode.traiteCaseEnCours(false));
		assertEquals(6, methode.getSolution());
	}
	@Test
	void testGetSimpleName() {
		assertEquals("CandidatUniqueDansLigne",methode.getSimpleName());
	}

}
