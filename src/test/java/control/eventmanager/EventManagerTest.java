
package control.eventmanager;

import control.command.UiCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

class EventManagerTest {

    private EventManager eventManager;
    private UiCommand mockCommand;

    @BeforeEach
    void setUp() {
        // On mocke une commande
        mockCommand = mock(UiCommand.class);

        // On crée une map et on y met la commande mockée
        Map<String, UiCommand> commandMap = new HashMap<>();
        commandMap.put("Avance", mockCommand);

        // On instancie EventManager avec la map mockée
        eventManager = new EventManager(commandMap);
    }

    @Test
    void testActionPerformed_executesCommand() {
        ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Avance");
        eventManager.actionPerformed(event);

        // On vérifie que la méthode execute() a bien été appelée
        verify(mockCommand, times(1)).execute();
    }

    @Test
    void testActionPerformed_unknownCommand_throwsException() {
        ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "INCONNUE");
        org.junit.jupiter.api.Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> eventManager.actionPerformed(event)
        );
    }

    @Test
    void testSimulateClick_executesCommand() {
        eventManager.simulateClick("Avance");
        verify(mockCommand, times(1)).execute();
    }
}



//package control.eventmanager;
//
//import control.MyProperties;
//import model.Model;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import view.ViewUpdater;
//
//import java.awt.event.ActionEvent;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.*;
//
//class EventManagerTest {
//
//    private Model model;
//    private EventManager eventManager;
//
//    @BeforeEach
//    void setUp() {
//        model = mock(Model.class);
//        ViewUpdater viewUpdater = mock(ViewUpdater.class);
//        MyProperties properties = mock(MyProperties.class);
//        ResolutionService resolutionService = Mockito.mock(ResolutionService.class);
//        NavigationService navigationService = Mockito.mock(NavigationService.class);
//        when(properties.getProperty("InitialMessage")).thenReturn("Initial message");
//        when(properties.getProperty("StartMessage")).thenReturn("Start message");
//
//        eventManager = new EventManager(null);
//    }
//
//    @Test
//    void testActionPerformedAvance() {
//        ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "AVANCE");
//        eventManager.actionPerformed(event);
//        verify(model).detecteSuivant(false);
//    }
//
//    @Test
//    void testActionPerformedExplique() {
//        ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Explique");
//        eventManager.actionPerformed(event);
//        verify(model).detecteSuivant(true);
//    }
//
//    @Test
//    void testActionPerformedReculeWithHistory() {
//// TODO à déplacer
//        //        when(model.canReloadLastHistoricization()).thenReturn(true);
////        Grille grille = mock(Grille.class);
////        when(model.getGrille()).thenReturn(grille);
////
////        eventManager.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Recule"));
////
////        verify(model).reloadLastHistoricization();
////        verify(viewUpdater).refreshGrilleDisplay(grille);
////        verify(viewUpdater).updateResolutionRank(-1);
////        verify(viewUpdater).removeLastLogLine();
//    }
//
//    @Test
//    void testActionPerformedReculeWithoutHistory() {
//        //TODO à déplacer
////        when(model.canReloadLastHistoricization()).thenReturn(false);
////
////        eventManager.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Recule"));
////
////        verify(viewUpdater).showMessageDialog(null, "Initial message");
////        verify(model, never()).reloadLastHistoricization();
//    }
//
//    @Test
//    void testReloadGrille() {
//        //TODO à déplacer
////        Grille grille = mock(Grille.class);
////        when(model.getGrille()).thenReturn(grille);
////        Path path  = Paths.get("testFile.txt").toAbsolutePath();
////        eventManager.reloadGrille(path);
////
////        verify(model).reload(path);
////        verify(viewUpdater).refreshGrilleDisplay(grille);
////        verify(viewUpdater).resetView("Start message");
//    }
//
//    @Test
//    void testOnEventFromModelHighlightCase() {
//        //TODO test à déplacer dans test du synchrnizer
////        EventFromModel event = new EventFromModel(EventFromModelType.HIGHLIGHT_CASE, 5, "Highlight");
////        eventManager.getSynchonizer().onEventFromModel(null, event);
////        verify(viewUpdater).highlightCase(5);
//    }
//
//    @Test
//    void testOnEventFromModelOtherEvent() {
//        //TODO test à déplacer dans test du synchrnizer
////        Grille grille = mock(Grille.class);
////        EventFromModel event = new EventFromModel(EventFromModelType.AJOUT_SOLUTION, 1,"Some message");
////        eventManager.getSynchonizer().onEventFromModel(grille, event);
////
////        verify(viewUpdater).refreshGrilleDisplay(grille);
////        verify(viewUpdater).insertDisplayMessage("Some message");
////        verify(viewUpdater).updateResolutionRank(1);
//    }
//
//    @Test
//    void testUnknownCommandThrowsException() {
//        ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "UNKNOWN");
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> eventManager.actionPerformed(event));
//        assertEquals("Commande inconnue !", exception.getMessage());
//    }
//}