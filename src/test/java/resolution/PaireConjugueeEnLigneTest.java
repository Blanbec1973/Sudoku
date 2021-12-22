package resolution;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import modele.CaseEnCours;
import modele.Grille;
import modele.InitialiseurDeGrille;

class PaireConjugueeEnLigneTest {
	private static Grille grille;
	private static PaireConjugueeEnLigne methode;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		grille = new Grille();
		InitialiseurDeGrille i = new InitialiseurDeGrille(grille);
		i.init(System.getProperty("user.dir")+"/src/test/resources/grillesTest/PaireConjugueeEnLigne.sud");
		i.calculTousLesCandidats();
		methode = new PaireConjugueeEnLigne(null, grille);
	}
	
	@Test
	void testTraiteCaseEnCours() {
		CaseEnCours.setCaseEnCours(3);
		assertTrue(methode.traiteCaseEnCours(false));
		assertEquals(2,methode.c1);
		assertEquals(6,methode.c2);
	}

	@Test
	void testDetecteConfiguration() {
		CaseEnCours.setCaseEnCours(3);
		assertTrue(methode.detecteConfiguration());
	}
	
	@Test
	void testDetecteCandidatAEliminer() {
		CaseEnCours.setCaseEnCours(3);
		methode.c1=2;
		methode.c2=6;
		assertTrue(methode.detecteCandidatAEliminer());
		assertEquals(2, methode.candidatAEliminer);
		assertEquals(1,methode.numCaseAction);
	}

}
