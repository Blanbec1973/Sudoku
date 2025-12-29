package control.command;

import control.eventmanager.ResolutionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class ResolutionCommandTest {
    private ResolutionService mockService;
    private ResolutionCommand command;

    @BeforeEach
    void setUp() {
        mockService = mock(ResolutionService.class);
        command = new ResolutionCommand(mockService);
    }

    @Test
    void reculeCommandeTest() {
        command.execute();
        verify(mockService, times(1)).resolution();
    }

}