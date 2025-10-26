package resolution;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.grille.CaseEnCours;
import model.grille.Grille;

class CandidatUniqueDansRegionTest {
	private static CandidatUniqueDansRegion methode;
	
	@BeforeAll
	static void setUpBeforeClass() {
		Grille grille = new Grille();
		grille.init(System.getProperty("user.dir")+"/src/test/resources/grillesTest/CandidatUniqueDansRegion.sud");
		methode = new CandidatUniqueDansRegion(grille);
	}

	@Test
	void testTraiteCaseEnCours() {
		CaseEnCours.setCaseEnCours(2);
		assertFalse(methode.traiteCaseEnCours(false).isPresent());
		
		CaseEnCours.setCaseEnCours(42);
		assertTrue(methode.traiteCaseEnCours(false).isPresent());
		assertEquals(4, methode.getSolution());
	}

	@Test
	void testGetSimpleName() {
		assertEquals("CandidatUniqueDansRegion",methode.getSimpleName());
	}
}
