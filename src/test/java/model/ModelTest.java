package model;

import model.grille.CaseContext;
import model.service.HistorisationService;
import model.service.ModelEventService;
import model.service.ResolutionMessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ModelTest {
	private static Model model;
	private static final ModelEventService eventService = Mockito.mock(ModelEventService.class);
	private static final ResolutionMessageService messageService = Mockito.mock(ResolutionMessageService.class);
	private static final HistorisationService historisationService = Mockito.mock(HistorisationService.class);

	@BeforeEach
	void setUp() {
		model = new Model(eventService, messageService, historisationService);
		String fileName = "src/test/resources/grillesTest/init67-40.sud";
		model.reload(Paths.get(fileName).toAbsolutePath());
		doNothing().when(eventService).publishHighlight(any(), anyInt());
		doNothing().when(eventService).publishSolution(any(), anyInt(), anyString());
		doNothing().when(eventService).publishElimination(any(), anyInt(), anyString());
		when(messageService.createSolutionMessage(Mockito.any())).thenReturn("Solution");
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
		assertTrue(model.getGrille().isCaseATrouver(39));
		model.detecteSuivant(true);
		assertEquals(6, model.getGrille().getValeurCase(39));
		verify(messageService, times(1)).createSolutionMessage(Mockito.any());
	}

	@Test
	void testReloadLastHistoricizationCallsService() {
		HistorisationService mockHistorisation = Mockito.mock(HistorisationService.class);
		model = new Model(eventService, messageService, mockHistorisation);
		model.reloadLastHistoricization();
		verify(mockHistorisation).supprimeDerniereGrille(Mockito.any());
	}

}
