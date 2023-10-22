package model;

import static org.junit.jupiter.api.Assertions.*;

import model.grille.CaseEnCours;
import model.grille.Grille;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HistorisationTest {
	private static Grille grilleOrigine;
	private static Historisation histo;
	
	@BeforeAll
	static void setUpBeforeClass() {
		grilleOrigine =new Grille();
        grilleOrigine.init(System.getProperty("user.dir")+"/src/test/resources/grillesTest/init67-40.sud");
        histo = new Historisation();
	}

	@AfterAll
	static void tearDownAfterClass() {
	}

	@BeforeEach
	void setUp() {
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void testHistoriseGrille() {
		histo.historiseGrille(grilleOrigine);
		assertEquals(6,histo.getHistoGrille(0).getCase(0, 0).getValeur());
		assertTrue(histo.getHistoGrille(0).getCase(0, 0).isCaseInitiale());
		assertTrue(histo.getHistoGrille(0).getCase(1, 0).isCaseATrouver());
		assertTrue(histo.getHistoGrille(0).getCase(1, 0).isCandidat(1));
		assertFalse(histo.getHistoGrille(0).getCase(1, 0).isCandidat(2));
		assertTrue(histo.getHistoGrille(0).getCase(1, 0).isCandidat(3));
		assertFalse(histo.getHistoGrille(0).getCase(1, 0).isCandidat(4));
		assertFalse(histo.getHistoGrille(0).getCase(1, 0).isCandidat(5));
		assertFalse(histo.getHistoGrille(0).getCase(1, 0).isCandidat(6));
		assertFalse(histo.getHistoGrille(0).getCase(1, 0).isCandidat(7));
		assertFalse(histo.getHistoGrille(0).getCase(1, 0).isCandidat(8));
		assertFalse(histo.getHistoGrille(0).getCase(1, 0).isCandidat(9));
		assertEquals(grilleOrigine.toString(),histo.getHistoGrille(0).toString());
	}

	@Test
	void testSupprimeDerniereGrille() {
		
		CaseEnCours.setCaseEnCours(39);
		grilleOrigine.setValeurCaseEnCours(6);
		histo.historiseGrille(grilleOrigine);
		
		CaseEnCours.setCaseEnCours(50);
		grilleOrigine.setValeurCaseEnCours(8);
		histo.historiseGrille(grilleOrigine);
		
		histo.supprimeDerniereGrille(grilleOrigine);
		int dernier = histo.getHistoGrille().size();	
		assertEquals(2, dernier);
		assertTrue(histo.getHistoGrille(dernier-1).getCase(50).isCaseATrouver());
		assertTrue(histo.getHistoGrille(dernier-1).getCase(50).isCandidat(1));
		assertTrue(histo.getHistoGrille(dernier-1).getCase(50).isCandidat(2));
		assertFalse(histo.getHistoGrille(dernier-1).getCase(50).isCandidat(3));
		assertTrue(histo.getHistoGrille(dernier-1).getCase(50).isCandidat(4));
		assertFalse(histo.getHistoGrille(dernier-1).getCase(50).isCandidat(5));
		assertFalse(histo.getHistoGrille(dernier-1).getCase(50).isCandidat(6));
		assertTrue(histo.getHistoGrille(dernier-1).getCase(50).isCandidat(7));
		assertTrue(histo.getHistoGrille(dernier-1).getCase(50).isCandidat(8));
		assertFalse(histo.getHistoGrille(dernier-1).getCase(50).isCandidat(9));	
	}

}
