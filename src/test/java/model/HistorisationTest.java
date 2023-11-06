package model;

import static org.junit.jupiter.api.Assertions.*;

import model.grille.CaseEnCours;
import model.grille.Grille;
import model.grille.Historisation;
import org.junit.jupiter.api.BeforeAll;
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

	@Test
	void testHistoriseGrille() {
		histo.historiseGrille(grilleOrigine);
		assertEquals(6,histo.getHistoGrille(0).getValeurCase(1));
		assertTrue(histo.getHistoGrille(0).isCaseInitiale(1));
		assertTrue(histo.getHistoGrille(0).isCaseATrouver(2));
		assertTrue(histo.getHistoGrille(0).isCandidat(2, 1));
		assertFalse(histo.getHistoGrille(0).isCandidat(2, 2));
		assertTrue(histo.getHistoGrille(0).isCandidat(2, 3));
		assertFalse(histo.getHistoGrille(0).isCandidat(2, 4));
		assertFalse(histo.getHistoGrille(0).isCandidat(2, 5));
		assertFalse(histo.getHistoGrille(0).isCandidat(2, 6));
		assertFalse(histo.getHistoGrille(0).isCandidat(2, 7));
		assertFalse(histo.getHistoGrille(0).isCandidat(2, 8));
		assertFalse(histo.getHistoGrille(0).isCandidat(2, 9));
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
		assertTrue(histo.getHistoGrille(dernier-1).isCaseATrouver(50));
		assertTrue(histo.getHistoGrille(dernier-1).isCandidat(50,1));
		assertTrue(histo.getHistoGrille(dernier-1).isCandidat(50,2));
		assertFalse(histo.getHistoGrille(dernier-1).isCandidat(50,3));
		assertTrue(histo.getHistoGrille(dernier-1).isCandidat(50,4));
		assertFalse(histo.getHistoGrille(dernier-1).isCandidat(50,5));
		assertFalse(histo.getHistoGrille(dernier-1).isCandidat(50,6));
		assertTrue(histo.getHistoGrille(dernier-1).isCandidat(50,7));
		assertTrue(histo.getHistoGrille(dernier-1).isCandidat(50,8));
		assertFalse(histo.getHistoGrille(dernier-1).isCandidat(50,9));	
	}

}
