package modele;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InitialiseurDeGrilleTest {
	private static Grille grille;
	private static InitialiseurDeGrille initialiseurDeGrille;
	
	@BeforeAll
	static void setUpBeforeClass() {
        grille =new Grille();
        initialiseurDeGrille  = new InitialiseurDeGrille(grille);
	}

	@AfterAll
	static void tearDownAfterClass() {
	}

	@BeforeEach
	void setUp() {
        initialiseurDeGrille.init(System.getProperty("user.dir")+"/src/test/resources/grillesTest/init67-40.sud");
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void testInitialiseurDeGrille() {
		assertTrue(initialiseurDeGrille instanceof InitialiseurDeGrille);
	}

	@Test
	void testInit() {
		assertEquals(6,grille.getCase(0, 0).getValeur());
	}
	
	@Test
	void testInitFichierInexistant() {
		initialiseurDeGrille.init(System.getProperty("user.dir")+"/toto.sud");
		assertTrue(true);
	}
	
	@Test
	void testCalculTousLesCandidats() {
		initialiseurDeGrille.calculTousLesCandidats();
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
		assertFalse(initialiseurDeGrille.checkPresenceValeurLigne(1, 0));
		assertTrue(initialiseurDeGrille.checkPresenceValeurLigne(2, 0));
		assertFalse(initialiseurDeGrille.checkPresenceValeurLigne(3, 0));
		assertFalse(initialiseurDeGrille.checkPresenceValeurLigne(4, 0));
		assertFalse(initialiseurDeGrille.checkPresenceValeurLigne(5, 0));
		assertTrue(initialiseurDeGrille.checkPresenceValeurLigne(6, 0));
		assertTrue(initialiseurDeGrille.checkPresenceValeurLigne(7, 0));
		assertFalse(initialiseurDeGrille.checkPresenceValeurLigne(8, 0));
		assertTrue(initialiseurDeGrille.checkPresenceValeurLigne(9, 0));
	}

	@Test
	void testCheckPresenceValeurColonne() {
		assertFalse(initialiseurDeGrille.checkPresenceValeurColonne(1, 0));
		assertFalse(initialiseurDeGrille.checkPresenceValeurColonne(2, 0));
		assertFalse(initialiseurDeGrille.checkPresenceValeurColonne(3, 0));
		assertFalse(initialiseurDeGrille.checkPresenceValeurColonne(4, 0));
		assertFalse(initialiseurDeGrille.checkPresenceValeurColonne(5, 0));
		assertTrue(initialiseurDeGrille.checkPresenceValeurColonne(6, 0));
		assertTrue(initialiseurDeGrille.checkPresenceValeurColonne(7, 0));
		assertFalse(initialiseurDeGrille.checkPresenceValeurColonne(8, 0));
		assertFalse(initialiseurDeGrille.checkPresenceValeurColonne(9, 0));
	}

	@Test
	void testCheckPresenceValeurRegion() {
		CaseEnCours.setCaseEnCours(1);
		assertFalse(initialiseurDeGrille.checkPresenceValeurRegion(1));
		assertFalse(initialiseurDeGrille.checkPresenceValeurRegion(2));
		assertFalse(initialiseurDeGrille.checkPresenceValeurRegion(3));
		assertFalse(initialiseurDeGrille.checkPresenceValeurRegion(4));
		assertTrue(initialiseurDeGrille.checkPresenceValeurRegion(5));
		assertTrue(initialiseurDeGrille.checkPresenceValeurRegion(6));
		assertFalse(initialiseurDeGrille.checkPresenceValeurRegion(7));
		assertFalse(initialiseurDeGrille.checkPresenceValeurRegion(8));
		assertFalse(initialiseurDeGrille.checkPresenceValeurRegion(9));
	}

}
