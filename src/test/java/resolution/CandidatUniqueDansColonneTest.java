package resolution;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.grille.CaseEnCours;
import model.grille.Grille;


class CandidatUniqueDansColonneTest {
	private static CandidatUniqueDansColonne methode;
	
	@BeforeAll
	static void setUpBeforeClass() {
		Grille grille = new Grille();
		grille.init(System.getProperty("user.dir")+"/src/test/resources/grillesTest/CandidatUniqueDansColonne.sud");
		methode = new CandidatUniqueDansColonne(null, grille);
	}

	@Test
	void testTraiteCaseEnCours() {
		CaseEnCours.setCaseEnCours(2);
		assertFalse(methode.traiteCaseEnCours(false));
		
		CaseEnCours.setCaseEnCours(53);
		assertTrue(methode.traiteCaseEnCours(false));
		assertEquals(9, methode.getSolution());
	}

	@Test
	void testGetSimpleName() {
		assertEquals("CandidatUniqueDansColonne",methode.getSimpleName());
	}
}
