package control;

import model.Model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.ViewUpdater;

import java.awt.event.ActionEvent;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ControlTest {
    private Control control;
    private EventManager mockEventManager;
    private Model mockModel;
    private ViewUpdater mockView;

    @BeforeEach
    void setUp() {
        mockEventManager = mock(EventManager.class);
        mockModel = mock(Model.class);
        mockView = mock(ViewUpdater.class);
        control = new Control(mockEventManager, mockModel);
    }
    @Test
    void testReloadGrilleDelegatesToEventManager() {
        //String fileName = System.getProperty("user.dir")+"src/test/resources/grillesTest/initFacile.sud";
        Path path = Paths.get("src/test/resources/grillesTest/initFacile.sud").toAbsolutePath();
        doNothing().when(mockEventManager).reloadGrille(path);

        control.reloadGrille(path);

        verify(mockEventManager, times(1)).reloadGrille(path);
    }
    @Test
    void testSimulateClickDelegatesToEventManager() {
        String command = "AVANCE";
        doNothing().when(mockEventManager).actionPerformed(any(ActionEvent.class));

        control.simulateClick(command);

        verify(mockEventManager, times(1)).actionPerformed(any(ActionEvent.class));
    }
    @Test
    void testInitializeCallsModelReloadAndViewRefresh() {
        Path path = Paths.get("initFacile.sud").toAbsolutePath();
        doNothing().when(mockModel).reload(path);
        doNothing().when(mockView).refreshGrilleDisplay(any());

        control.initialize(mockView, new MyProperties("config.properties"));

        verify(mockModel, times(1)).reload(any());
        verify(mockView, times(1)).refreshGrilleDisplay(any());
    }
    @Test
    void testGetViewUpdaterReturnsEventManager() {
        ViewUpdater updater = control.getViewUpdater();
        assertEquals(mockEventManager, updater);
    }
    @Test
    void testGetModelReturnsInjectedModel() {
        assertEquals(mockModel, control.getModel());
    }
}