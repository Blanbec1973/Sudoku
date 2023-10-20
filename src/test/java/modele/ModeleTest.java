package modele;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import controleur.MesProprietes;
import modele.grille.Grille;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import controleur.Controle;

class ModeleTest {
	private static Modele modele;
	private static Controle controle = Mockito.mock(Controle.class);
	private static MesProprietes mesProprietes = Mockito.mock(MesProprietes.class);

	@BeforeAll
	static void setUpBeforeClass() {
	}

	@AfterAll
	static void tearDownAfterClass() {
	}

	@BeforeEach
	void setUp() {
		when(mesProprietes.getFichierInitial()).thenReturn("/src/test/resources/grillesTest/init67-40.sud");
		modele = new Modele(controle, mesProprietes);
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void testModele() {
		assertNotNull(modele.getGrille());
	}

	@Test
	void testGetGrille() {
		assertTrue(modele.getGrille() instanceof Grille);
	}

	@Test
	void testDetecteSuivant() {
		modele.detecteSuivant(false);
		assertEquals(39,modele.getGrille().getCaseEnCours().getNumCase());
		assertTrue(modele.getGrille().getCase(39).isCaseATrouver());
		modele.detecteSuivant(true);
		assertEquals(6, modele.getGrille().getCase(39).getValeur());
		
		modele.detecteSuivant(true);
		modele.detecteSuivant(true);
		modele.detecteSuivant(true);
		modele.detecteSuivant(true);
		
		modele.detecteSuivant(false);
		assertEquals(36,modele.getGrille().getCaseEnCours().getNumCase());
		assertTrue(modele.getGrille().getCase(18).getCandidats().isCandidat(7));
		modele.detecteSuivant(true);
		assertFalse(modele.getGrille().getCase(18).getCandidats().isCandidat(7));
		
	}

	@Test
	void testRechargeDernierHistorique() {
		modele.detecteSuivant(true);
		modele.rechargeDernierHistorique();
		assertTrue(modele.getGrille().getCase(39).isCaseATrouver());
	}

}
