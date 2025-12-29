package control.command;

import control.eventmanager.GridSaverService;
import model.Model;
import model.grille.Grille;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.MyView;
import view.ViewUpdater;

import static org.mockito.Mockito.*;

class SaveCommandTest {
    private ViewUpdater mockView;
    private Model mockModel;
    private GridSaverService mockService;
    private SaveCommand command;

    @BeforeEach
    void setUp() {
        mockModel = mock(Model.class);
        mockView = mock(MyView.class);
        mockService = mock(GridSaverService.class);
        command = new SaveCommand(mockView, mockModel, mockService);
    }

    @Test    void saveCommandTest() {
        Grille mockGrille = mock(Grille.class);
        when(((MyView) mockView).afficheSaveFileDialog("SAVE")).thenReturn("target/result.sud");
        when(mockModel.getGrille()).thenReturn(mockGrille);
        command.execute();
        verify((MyView) mockView, times(1)).afficheSaveFileDialog("SAVE");
        verify(mockService, times(1)).saveGrid(mockGrille, "target/result.sud");    }
}