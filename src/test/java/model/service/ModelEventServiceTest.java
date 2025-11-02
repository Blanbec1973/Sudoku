package model.service;

import model.EventFromModel;
import model.EventFromModelType;
import model.ModelEventPublisher;
import model.grille.Grille;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ModelEventServiceTest {

    private ModelEventPublisher mockPublisher;
    private ModelEventService eventService;
    private Grille grille;

    @BeforeEach
    void setUp() {
        mockPublisher = mock(ModelEventPublisher.class);
        eventService = new ModelEventService(mockPublisher);
        grille = new Grille();
    }

    @Test
    void testPublishHighlight() {
        eventService.publishHighlight(grille, 42);

        ArgumentCaptor<EventFromModel> captor = ArgumentCaptor.forClass(EventFromModel.class);
        verify(mockPublisher).publish(eq(grille), captor.capture());

        EventFromModel event = captor.getValue();
        assertEquals(EventFromModelType.HIGHLIGHT_CASE, event.getEventFromModelType());
        assertEquals(42, event.getNumCase());
        assertEquals("", event.getMessage());
    }

    @Test
    void testPublishSolution() {
        eventService.publishSolution(grille, 39, "Ajout de la solution");

        ArgumentCaptor<EventFromModel> captor = ArgumentCaptor.forClass(EventFromModel.class);
        verify(mockPublisher).publish(eq(grille), captor.capture());

        EventFromModel event = captor.getValue();
        assertEquals(EventFromModelType.AJOUT_SOLUTION, event.getEventFromModelType());
        assertEquals(39, event.getNumCase());
        assertEquals("Ajout de la solution", event.getMessage());
    }

    @Test
    void testPublishElimination() {
        eventService.publishElimination(grille, 18, "Élimination du candidat");

        ArgumentCaptor<EventFromModel> captor = ArgumentCaptor.forClass(EventFromModel.class);
        verify(mockPublisher).publish(eq(grille), captor.capture());

        EventFromModel event = captor.getValue();
        assertEquals(EventFromModelType.ELIMINE_CANDIDAT, event.getEventFromModelType());
        assertEquals(18, event.getNumCase());
        assertEquals("Élimination du candidat", event.getMessage());
    }
}