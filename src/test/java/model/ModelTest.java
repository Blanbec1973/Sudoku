package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import control.MyProperties;
import model.grille.Grille;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import control.Control;

class ModelTest {
	private static Model model;
	private static Control control = Mockito.mock(Control.class);
	private static MyProperties myProperties = Mockito.mock(MyProperties.class);

	@BeforeAll
	static void setUpBeforeClass() {

	}

	@AfterAll
	static void tearDownAfterClass() {
	}

	@BeforeEach
	void setUp() {
		when(control.getInitFileName()).thenReturn(System.getProperty("user.dir") + "/src/test/resources/grillesTest/init67-40.sud");
		model = new Model(control, myProperties);
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void testModele() {
		assertNotNull(model.getGrille());
	}

	@Test
	void testGetGrille() {
		assertTrue(model.getGrille() instanceof Grille);
	}

	@Test
	void testDetecteSuivant() {
		model.detecteSuivant(false);
		assertEquals(39, model.getGrille().getCaseEnCours().getNumCase());
		assertTrue(model.getGrille().getCase(39).isCaseATrouver());
		model.detecteSuivant(true);
		assertEquals(6, model.getGrille().getCase(39).getValeur());
		
		model.detecteSuivant(true);
		model.detecteSuivant(true);
		model.detecteSuivant(true);
		model.detecteSuivant(true);
		
		model.detecteSuivant(false);
		assertEquals(36, model.getGrille().getCaseEnCours().getNumCase());
		assertTrue(model.getGrille().getCase(18).getCandidats().isCandidat(7));
		model.detecteSuivant(true);
		assertFalse(model.getGrille().getCase(18).getCandidats().isCandidat(7));
		
	}

	@Test
	void testRechargeDernierHistorique() {
		model.detecteSuivant(true);
		model.rechargeDernierHistorique();
		assertTrue(model.getGrille().getCase(39).isCaseATrouver());
	}

}
