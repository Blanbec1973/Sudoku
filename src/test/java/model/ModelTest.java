package model;

import control.MyProperties;
import model.grille.CaseEnCours;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

class ModelTest {
	private static Model model;
	private static final ModelEventPublisher publisher = Mockito.mock(ModelEventPublisher.class);
	private static final MyProperties myProperties = new MyProperties("config.properties");

	@BeforeEach
	void setUp() {
		model = new Model(publisher, myProperties);
		model.reload(System.getProperty("user.dir") + "/src/test/resources/grillesTest/init67-40.sud");
		doNothing().when(publisher).publish(any(),any());
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
