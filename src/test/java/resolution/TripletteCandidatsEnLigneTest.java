package resolution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import modele.CaseEnCours;
import modele.Grille;
import modele.InitialiseurDeGrille;

class TripletteCandidatsEnLigneTest {
	private static Grille grille;
	private static TripletteCandidatsEnLigne methode;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		grille = new Grille();
		InitialiseurDeGrille i = new InitialiseurDeGrille(grille);
		i.init(System.getProperty("user.dir")+"/src/test/resources/grillesTest/TripletteCandidatsEnLigne.sud");
		i.calculTousLesCandidats();
		methode = new TripletteCandidatsEnLigne(null, grille);
	}

	@Test
	void testTraiteCaseEnCours() {
		CaseEnCours.setCaseEnCours(74);
		grille.getCase(75).elimineCandidat(8);
		assertTrue(methode.traiteCaseEnCours(false));
		assertEquals(76,methode.numCaseAction);
		assertEquals(3,methode.candidatAEliminer);
	}

}
