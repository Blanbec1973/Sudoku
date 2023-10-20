package resolution;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import modele.grille.CaseEnCours;
import modele.grille.Grille;
import modele.InitialiseurDeGrille;

class CandidatUniqueDansRegionTest {
	private static Grille grille;
	private static CandidatUniqueDansRegion methode;
	
	@BeforeAll
	static void setUpBeforeClass() {
		grille = new Grille();
		InitialiseurDeGrille i = new InitialiseurDeGrille(grille);
		i.init(System.getProperty("user.dir")+"/src/test/resources/grillesTest/CandidatUniqueDansRegion.sud");
		i.calculTousLesCandidats();
		methode = new CandidatUniqueDansRegion(null, grille);
	}

	@Test
	void testTraiteCaseEnCours() {
		CaseEnCours.setCaseEnCours(2);
		assertFalse(methode.traiteCaseEnCours(false));
		
		CaseEnCours.setCaseEnCours(42);
		assertTrue(methode.traiteCaseEnCours(false));
		assertEquals(4, methode.getSolution());
	}

}
