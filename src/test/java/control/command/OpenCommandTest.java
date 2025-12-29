package control.command;

import control.eventmanager.NavigationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.MyView;
import view.ViewUpdater;

import static org.mockito.Mockito.*;

class OpenCommandTest {
    private ViewUpdater viewUpdater;
    private NavigationService navigationService;
    private OpenCommand openCommand;

    @BeforeEach
    void setUp() {
        viewUpdater = mock(MyView.class);
        navigationService = mock(NavigationService.class);
        openCommand = new OpenCommand(viewUpdater, navigationService);
    }

    @Test
    void testExecute_callOpen() {

        when(((MyView) viewUpdater).afficheSaveFileDialog("OPEN"))
                .thenReturn("src/test/resources/grillesTest/init7.sud");

        openCommand.execute();

        verify((MyView) viewUpdater, times(1)).afficheSaveFileDialog("OPEN");
        verify(navigationService, times(1)).reloadGrille(any());

    }

}