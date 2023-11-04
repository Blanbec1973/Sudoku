package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import control.MyProperties;
import model.grille.Grille;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import control.Control;

class ModelTest {
	private static Model model;
	private static final Control control = Mockito.mock(Control.class);
	private static final MyProperties myProperties = new MyProperties("config.properties");

	@BeforeEach
	void setUp() {
		when(control.getInitFileName()).thenReturn(System.getProperty("user.dir") + "/src/test/resources/grillesTest/init67-40.sud");
		when(control.getProperties()).thenReturn(myProperties);
		model = new Model(control);
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
		model.reloadLastHistoricization();
		assertTrue(model.getGrille().getCase(39).isCaseATrouver());
	}

}
