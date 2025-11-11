package model;

import model.grille.CaseContext;
import model.grille.Grille;
import model.grille.Historisation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import resolution.ResolutionAction;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class HistorisationTest {
	private static Grille grilleOrigine;
	private static Historisation histo;
	
	@BeforeAll
	static void setUpBeforeClass() {
		grilleOrigine =new Grille();
		String fileName = "src/test/resources/grillesTest/init67-40.sud";
        grilleOrigine.init(Paths.get(fileName).toAbsolutePath());
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
		grilleOrigine.setValeurCaseEnCours(new ResolutionAction(39,6, null, null, new CaseContext(39)));
		histo.historiseGrille(grilleOrigine);

		grilleOrigine.setValeurCaseEnCours(new ResolutionAction(50,8, null, null, new CaseContext(50)));
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

	@Test
	void testReloadGrille() {
		Grille grille2 = new Grille();
		String fileName = "src/test/resources/grillesTest/initFacile.sud";
		grille2.init(Paths.get(fileName).toAbsolutePath());
		histo.reloadGrille(grille2);
		assertEquals(1,histo.getHistoGrille().size());
		assertTrue(histo.getHistoGrille(0).isCaseInitiale(1));
		assertTrue(histo.getHistoGrille(0).isCaseInitiale(2));
		assertTrue(histo.getHistoGrille(0).isCaseATrouver(3));
	}

}
