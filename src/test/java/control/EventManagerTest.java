package control;

import model.EventFromModel;
import model.EventFromModelType;
import model.Model;
import model.grille.Grille;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.ViewUpdater;

import java.awt.event.ActionEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class EventManagerTest {

    private Model model;
    private ViewUpdater viewUpdater;
    private EventManager eventManager;

    @BeforeEach
    void setUp() {
        model = mock(Model.class);
        viewUpdater = mock(ViewUpdater.class);
        MyProperties properties = mock(MyProperties.class);
        when(properties.getProperty("InitialMessage")).thenReturn("Initial message");
        when(properties.getProperty("StartMessage")).thenReturn("Start message");

        eventManager = new EventManager(viewUpdater, properties);
        eventManager.setModel(model);
    }

    @Test
    void testActionPerformedAvance() {
        ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "AVANCE");
        eventManager.actionPerformed(event);
        verify(model).detecteSuivant(false);
    }

    @Test
    void testActionPerformedExplique() {
        ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "EXPLIQUE");
        eventManager.actionPerformed(event);
        verify(model).detecteSuivant(true);
    }

    @Test
    void testActionPerformedReculeWithHistory() {
        when(model.canReloadLastHistoricization()).thenReturn(true);
        Grille grille = mock(Grille.class);
        when(model.getGrille()).thenReturn(grille);

        eventManager.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "RECULE"));

        verify(model).reloadLastHistoricization();
        verify(viewUpdater).refreshGrilleDisplay(grille);
        verify(viewUpdater).updateResolutionRank(-1);
        verify(viewUpdater).removeLastLogLine();
    }

    @Test
    void testActionPerformedReculeWithoutHistory() {
        when(model.canReloadLastHistoricization()).thenReturn(false);

        eventManager.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "RECULE"));

        verify(viewUpdater).showMessageDialog(null, "Initial message");
        verify(model, never()).reloadLastHistoricization();
    }

    @Test
    void testReloadGrille() {
        Grille grille = mock(Grille.class);
        when(model.getGrille()).thenReturn(grille);

        eventManager.reloadGrille("testFile.txt");

        verify(model).reload("testFile.txt");
        verify(viewUpdater).refreshGrilleDisplay(grille);
        verify(viewUpdater).resetView("Start message");
    }

    @Test
    void testOnEventFromModelHighlightCase() {
        EventFromModel event = new EventFromModel(EventFromModelType.HIGHLIGHT_CASE, 5, "Highlight");
        eventManager.onEventFromModel(null, event);
        verify(viewUpdater).highlightCase(5);
    }

    @Test
    void testOnEventFromModelOtherEvent() {
        Grille grille = mock(Grille.class);
        EventFromModel event = new EventFromModel(EventFromModelType.AJOUT_SOLUTION, 1,"Some message");
        eventManager.onEventFromModel(grille, event);

        verify(viewUpdater).refreshGrilleDisplay(grille);
        verify(viewUpdater).insertDisplayMessage("Some message");
        verify(viewUpdater).updateResolutionRank(1);
    }

    @Test
    void testUnknownCommandThrowsException() {
        ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "UNKNOWN");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> eventManager.actionPerformed(event));
        assertEquals("Commande inconnue !", exception.getMessage());
    }
}