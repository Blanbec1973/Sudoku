package control.command;

import model.Model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class ExpliqueCommandTest {

    private Model mockModel;
    private ExpliqueCommand expliqueCommand;

    @BeforeEach
    void setUp() {
        mockModel = mock(Model.class);
        expliqueCommand = new ExpliqueCommand(mockModel);
    }

    @Test
    void testExecute_callsDetecteSuivantWithFalse() {
        expliqueCommand.execute();
        verify(mockModel, times(1)).detecteSuivant(true);
    }
}
