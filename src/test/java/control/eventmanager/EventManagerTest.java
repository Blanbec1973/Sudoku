
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
        mockCommand = mock(UiCommand.class);

        Map<String, UiCommand> commandMap = new HashMap<>();
        commandMap.put("avance", mockCommand);

        eventManager = new EventManager(commandMap);
    }

    @Test
    void testActionPerformed_executesCommand() {
        ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "avance");
        eventManager.actionPerformed(event);
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
        eventManager.simulateClick("avance");
        verify(mockCommand, times(1)).execute();
    }
}