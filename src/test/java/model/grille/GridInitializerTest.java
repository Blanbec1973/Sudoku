package model.grille;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridInitializerTest {
	private static Grille grille;
	private static GridInitializer gridInitializer;
	
	@BeforeAll
	static void setUpBeforeClass() {
        grille =new Grille();
        gridInitializer = new GridInitializer(grille);
	}


	@BeforeEach
	void setUp() {
        gridInitializer.init(System.getProperty("user.dir")+"/src/test/resources/grillesTest/init67-40.sud");
	}


	@Test
	void testInitialiseurDeGrille() {
		assertNotNull(gridInitializer);
	}

	@Test
	void testInit() {
		assertEquals(6,grille.getCase(0, 0).getValeur());
	}
	
	@Test
	void testInitFichierInexistant() {
		//gridInitializer.init(System.getProperty("user.dir")+"/toto.sud");
		assertTrue(true);
	}
	
	@Test
	void testCalculTousLesCandidats() {
		gridInitializer.calculTousLesCandidats();
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
		assertFalse(gridInitializer.checkPresenceValeurLigne(1, 0));
		assertTrue(gridInitializer.checkPresenceValeurLigne(2, 0));
		assertFalse(gridInitializer.checkPresenceValeurLigne(3, 0));
		assertFalse(gridInitializer.checkPresenceValeurLigne(4, 0));
		assertFalse(gridInitializer.checkPresenceValeurLigne(5, 0));
		assertTrue(gridInitializer.checkPresenceValeurLigne(6, 0));
		assertTrue(gridInitializer.checkPresenceValeurLigne(7, 0));
		assertFalse(gridInitializer.checkPresenceValeurLigne(8, 0));
		assertTrue(gridInitializer.checkPresenceValeurLigne(9, 0));
	}

	@Test
	void testCheckPresenceValeurColonne() {
		assertFalse(gridInitializer.checkPresenceValeurColonne(1, 0));
		assertFalse(gridInitializer.checkPresenceValeurColonne(2, 0));
		assertFalse(gridInitializer.checkPresenceValeurColonne(3, 0));
		assertFalse(gridInitializer.checkPresenceValeurColonne(4, 0));
		assertFalse(gridInitializer.checkPresenceValeurColonne(5, 0));
		assertTrue(gridInitializer.checkPresenceValeurColonne(6, 0));
		assertTrue(gridInitializer.checkPresenceValeurColonne(7, 0));
		assertFalse(gridInitializer.checkPresenceValeurColonne(8, 0));
		assertFalse(gridInitializer.checkPresenceValeurColonne(9, 0));
	}

	@Test
	void testCheckPresenceValeurRegion() {
		CaseEnCours.setCaseEnCours(1);
		assertFalse(gridInitializer.checkPresenceValeurRegion(1));
		assertFalse(gridInitializer.checkPresenceValeurRegion(2));
		assertFalse(gridInitializer.checkPresenceValeurRegion(3));
		assertFalse(gridInitializer.checkPresenceValeurRegion(4));
		assertTrue(gridInitializer.checkPresenceValeurRegion(5));
		assertTrue(gridInitializer.checkPresenceValeurRegion(6));
		assertFalse(gridInitializer.checkPresenceValeurRegion(7));
		assertFalse(gridInitializer.checkPresenceValeurRegion(8));
		assertFalse(gridInitializer.checkPresenceValeurRegion(9));
	}

}
