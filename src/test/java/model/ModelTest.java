package model;

import model.grille.CaseEnCours;
import model.service.HistorisationService;
import model.service.ModelEventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ModelTest {
	private static Model model;
	private static final ModelEventService eventService = Mockito.mock(ModelEventService.class);
	private static final MessageManager messageManager = Mockito.mock(MessageManager.class);
	private static final HistorisationService historisationService = Mockito.mock(HistorisationService.class);

	@BeforeEach
	void setUp() {
		model = new Model(eventService, messageManager, historisationService);
		model.reload(System.getProperty("user.dir") + "/src/test/resources/grillesTest/init67-40.sud");
		doNothing().when(eventService).publishHighlight(any(), anyInt());
		doNothing().when(eventService).publishSolution(any(), anyInt(), anyString());
		doNothing().when(eventService).publishElimination(any(), anyInt(), anyString());

	}

	@Test
	void testPublicationHighlight() {
		model.detecteSuivant(false); // mode highlight
		verify(eventService, atLeastOnce()).publishHighlight(any(), anyInt());
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
	void testReloadLastHistoricizationCallsService() {
		HistorisationService mockHistorisation = Mockito.mock(HistorisationService.class);
		Model model = new Model(eventService, messageManager, mockHistorisation);
		model.reloadLastHistoricization();
		verify(mockHistorisation).supprimeDerniereGrille(Mockito.any());
	}

}
