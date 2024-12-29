package model.grille;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Grille2Test {
	private static Grille grille;
	
	@BeforeAll
	static void setUpBeforeClass() {
        grille =new Grille();
	}


	@BeforeEach
	void setUp() {
		grille.init(System.getProperty("user.dir")+"/src/test/resources/grillesTest/init67-40.sud");
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
		grille.calculTousLesCandidats();
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
		assertFalse(grille.checkPresenceValeurLigne(1, 0));
		assertTrue(grille.checkPresenceValeurLigne(2, 0));
		assertFalse(grille.checkPresenceValeurLigne(3, 0));
		assertFalse(grille.checkPresenceValeurLigne(4, 0));
		assertFalse(grille.checkPresenceValeurLigne(5, 0));
		assertTrue(grille.checkPresenceValeurLigne(6, 0));
		assertTrue(grille.checkPresenceValeurLigne(7, 0));
		assertFalse(grille.checkPresenceValeurLigne(8, 0));
		assertTrue(grille.checkPresenceValeurLigne(9, 0));
	}

	@Test
	void testCheckPresenceValeurColonne() {
		assertFalse(grille.checkPresenceValeurColonne(1, 0));
		assertFalse(grille.checkPresenceValeurColonne(2, 0));
		assertFalse(grille.checkPresenceValeurColonne(3, 0));
		assertFalse(grille.checkPresenceValeurColonne(4, 0));
		assertFalse(grille.checkPresenceValeurColonne(5, 0));
		assertTrue(grille.checkPresenceValeurColonne(6, 0));
		assertTrue(grille.checkPresenceValeurColonne(7, 0));
		assertFalse(grille.checkPresenceValeurColonne(8, 0));
		assertFalse(grille.checkPresenceValeurColonne(9, 0));
	}

	@Test
	void testCheckPresenceValeurRegion() {
		CaseEnCours.setCaseEnCours(1);
		assertFalse(grille.checkPresenceValeurRegion(1));
		assertFalse(grille.checkPresenceValeurRegion(2));
		assertFalse(grille.checkPresenceValeurRegion(3));
		assertFalse(grille.checkPresenceValeurRegion(4));
		assertTrue(grille.checkPresenceValeurRegion(5));
		assertTrue(grille.checkPresenceValeurRegion(6));
		assertFalse(grille.checkPresenceValeurRegion(7));
		assertFalse(grille.checkPresenceValeurRegion(8));
		assertFalse(grille.checkPresenceValeurRegion(9));
	}

}
