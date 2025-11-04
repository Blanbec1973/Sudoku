package model.grille;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class Grille2Test {
	private static Grille grille;
	
	@BeforeAll
	static void setUpBeforeClass() {
        grille =new Grille();
	}


	@BeforeEach
	void setUp() {
		String fileName = "src/test/resources/grillesTest/init67-40.sud";
		grille.init(Paths.get(fileName).toAbsolutePath());
	}


	@Test
	void testInitialiseurDeGrille() {
		assertNotNull(grille);
	}

	@Test
	void testInit() {
		assertEquals(6,grille.getCase(0, 0).getValeur());
	}
	
	@Test
	void testInitFichierInexistant() {
		assertTrue(true);
	}
	
	@Test
	void testCalculTousLesCandidats() {
		grille.getGrilleService().calculTousLesCandidats();
		assertTrue(grille.getCase(1, 0).isCandidat(1));
		assertFalse(grille.getCase(1, 0).isCandidat(2));
		assertTrue(grille.getCase(1, 0).isCandidat(3));
		assertFalse(grille.getCase(1, 0).isCandidat(4));
		assertFalse(grille.getCase(1, 0).isCandidat(5));
		assertFalse(grille.getCase(1, 0).isCandidat(6));
		assertFalse(grille.getCase(1, 0).isCandidat(7));
		assertFalse(grille.getCase(1, 0).isCandidat(8));
		assertFalse(grille.getCase(1, 0).isCandidat(9));
	}

	@Test
	void testCheckPresenceValeurLigne() {
		assertFalse(grille.getGrilleService().checkPresenceValeurLigne(1, 0));
		assertTrue(grille.getGrilleService().checkPresenceValeurLigne(2, 0));
		assertFalse(grille.getGrilleService().checkPresenceValeurLigne(3, 0));
		assertFalse(grille.getGrilleService().checkPresenceValeurLigne(4, 0));
		assertFalse(grille.getGrilleService().checkPresenceValeurLigne(5, 0));
		assertTrue(grille.getGrilleService().checkPresenceValeurLigne(6, 0));
		assertTrue(grille.getGrilleService().checkPresenceValeurLigne(7, 0));
		assertFalse(grille.getGrilleService().checkPresenceValeurLigne(8, 0));
		assertTrue(grille.getGrilleService().checkPresenceValeurLigne(9, 0));
	}

	@Test
	void testCheckPresenceValeurColonne() {
		assertFalse(grille.getGrilleService().checkPresenceValeurColonne(1, 0));
		assertFalse(grille.getGrilleService().checkPresenceValeurColonne(2, 0));
		assertFalse(grille.getGrilleService().checkPresenceValeurColonne(3, 0));
		assertFalse(grille.getGrilleService().checkPresenceValeurColonne(4, 0));
		assertFalse(grille.getGrilleService().checkPresenceValeurColonne(5, 0));
		assertTrue(grille.getGrilleService().checkPresenceValeurColonne(6, 0));
		assertTrue(grille.getGrilleService().checkPresenceValeurColonne(7, 0));
		assertFalse(grille.getGrilleService().checkPresenceValeurColonne(8, 0));
		assertFalse(grille.getGrilleService().checkPresenceValeurColonne(9, 0));
	}

	@Test
	void testCheckPresenceValeurRegion() {
		//CaseEnCours.setCaseEnCours(1);
		CaseContext context = new CaseContext(1);
		assertFalse(grille.getGrilleService().checkPresenceValeurRegion(context, 1));
		assertFalse(grille.getGrilleService().checkPresenceValeurRegion(context,2));
		assertFalse(grille.getGrilleService().checkPresenceValeurRegion(context,3));
		assertFalse(grille.getGrilleService().checkPresenceValeurRegion(context,4));
		assertTrue(grille.getGrilleService().checkPresenceValeurRegion(context,5));
		assertTrue(grille.getGrilleService().checkPresenceValeurRegion(context,6));
		assertFalse(grille.getGrilleService().checkPresenceValeurRegion(context,7));
		assertFalse(grille.getGrilleService().checkPresenceValeurRegion(context,8));
		assertFalse(grille.getGrilleService().checkPresenceValeurRegion(context,9));
	}

}
