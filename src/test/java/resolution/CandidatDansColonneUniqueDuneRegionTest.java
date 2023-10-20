package resolution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import modele.grille.CaseEnCours;
import modele.grille.Grille;
import modele.InitialiseurDeGrille;

class CandidatDansColonneUniqueDuneRegionTest {
	private static Grille grille;
	private static CandidatDansColonneUniqueDuneRegion methode;
	
	@BeforeAll
	static void setUpBeforeClass() {
		grille = new Grille();
		InitialiseurDeGrille i = new InitialiseurDeGrille(grille);
		i.init(System.getProperty("user.dir")+"/src/test/resources/grillesTest/CandidatDansColonneUniqueDuneRegion.sud");
		i.calculTousLesCandidats();
		methode = new CandidatDansColonneUniqueDuneRegion(null, grille);
	}

	@Test
	void testTraiteCaseEnCours() {
		CaseEnCours.setCaseEnCours(28);
		assertTrue(methode.traiteCaseEnCours(false));
		assertEquals(1,methode.numCaseAction);
		assertEquals(6, methode.candidatAEliminer);
		assertEquals(0, methode.xAction);
		assertEquals(0,methode.yAction);
	}

	@Test
	void testCandidatDansColonneUnique() {
		CaseEnCours.setCaseEnCours(28);
		assertTrue(methode.candidatDansColonneUnique());
	}

}
