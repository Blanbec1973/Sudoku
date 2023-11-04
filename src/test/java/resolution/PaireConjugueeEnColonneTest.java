package resolution;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.grille.CaseEnCours;
import model.grille.Grille;


class PaireConjugueeEnColonneTest {
	private static PaireConjugueeEnColonne methode;
	
	@BeforeAll
	static void setUpBeforeClass() {
		Grille grille = new Grille();
		grille.init(System.getProperty("user.dir")+"/src/test/resources/grillesTest/PaireConjugueeEnColonne.sud");
		methode = new PaireConjugueeEnColonne(null, grille);
	}

	@Test
	void testTraiteCaseEnCours() {
		CaseEnCours.setCaseEnCours(36);
		assertTrue(methode.traiteCaseEnCours(false));
		assertEquals(3,methode.c1);
		assertEquals(7,methode.c2);
	}

	@Test
	void testDetecteConfiguration() {
		CaseEnCours.setCaseEnCours(36);
		assertTrue(methode.detecteConfiguration());
	}
	
	@Test
	void testDetecteCandidatAEliminer() {
		CaseEnCours.setCaseEnCours(36);
		methode.c1=3;
		methode.c2=7;
		assertTrue(methode.detecteCandidatAEliminer());
	}
}
