package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import control.MyProperties;
import model.grille.CaseEnCours;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import control.Control;

class ModelTest {
	private static Model model;
	private static final ModelListener modelListener = Mockito.mock(ModelListener.class);
	private static final MyProperties myProperties = new MyProperties("config.properties");

	@BeforeEach
	void setUp() {
		model = new Model(modelListener, myProperties);
		model.reload(System.getProperty("user.dir") + "/src/test/resources/grillesTest/init67-40.sud");
		doNothing().when(modelListener).onEventFromModel(any(),any());
	}

	@Test
	void testModele() {
		assertNotNull(model.getGrille());
	}

	@Test
	void testGetGrille() {
		assertNotNull(model.getGrille());
	}

	@Test
	void testDetecteSuivant() {
		model.detecteSuivant(false);
		assertEquals(39, CaseEnCours.getNumCase());
		assertTrue(model.getGrille().isCaseATrouver(39));
		model.detecteSuivant(true);
		assertEquals(6, model.getGrille().getValeurCase(39));
		
		model.detecteSuivant(true);
		model.detecteSuivant(true);
		model.detecteSuivant(true);
		model.detecteSuivant(true);
		
		model.detecteSuivant(false);
		assertEquals(36, CaseEnCours.getNumCase());
		assertTrue(model.getGrille().isCandidat(18,7));
		model.detecteSuivant(true);
		assertFalse(model.getGrille().isCandidat(18, 7));
		
	}

	@Test
	void testRechargeDernierHistorique() {
		model.detecteSuivant(true);
		model.reloadLastHistoricization();
		assertTrue(model.getGrille().isCaseATrouver(39));
	}

}
