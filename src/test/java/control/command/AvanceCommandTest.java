package control.command;

import model.Model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class AvanceCommandTest {

    private Model mockModel;
    private AvanceCommand avanceCommand;

    @BeforeEach
    void setUp() {
        mockModel = mock(Model.class);
        avanceCommand = new AvanceCommand(mockModel);
    }

    @Test
    void testExecute_callsDetecteSuivantWithFalse() {
        avanceCommand.execute();
        verify(mockModel, times(1)).detecteSuivant(false);
    }
}
