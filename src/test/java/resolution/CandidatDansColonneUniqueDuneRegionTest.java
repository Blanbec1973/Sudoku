package resolution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import modele.CaseEnCours;
import modele.Grille;
import modele.InitialiseurDeGrille;

class CandidatDansColonneUniqueDuneRegionTest {
	private static Grille grille;
	private static CandidatDansColonneUniqueDuneRegion methode;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
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
	void testTestCase() {
		//CaseEnCours.setCaseEnCours(76);
		//assertFalse(methode.testCase(0, 6));
		//assertTrue(methode.testCase(0, 3));
	}
	
	@Test
	void testDetecteCandidatAEliminer() {
		//CaseEnCours.setCaseEnCours(76);
		//methode.candidatAEliminer = 3;
		//assertTrue(methode.detecteCandidatAEliminer());
		//assertEquals(3,methode.xAction);
		//assertEquals(7,methode.yAction);
	}

}
