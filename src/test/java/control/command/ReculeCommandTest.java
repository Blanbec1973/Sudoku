package control.command;

import control.eventmanager.NavigationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class ReculeCommandTest {
    private NavigationService mockService;
    private ReculeCommand command;

    @BeforeEach
    void setUp() {
        mockService = mock(NavigationService.class);
        command = new ReculeCommand(mockService);
    }

    @Test
    void reculeCommandeTest() {
        command.execute();
        verify(mockService, times(1)).handleRecule();
    }

}