package model.service;

import model.MessageManager;
import model.grille.CaseContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import resolution.MethodeResolution;
import resolution.ResolutionAction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ResolutionMessageServiceTest {

    private MessageManager mockMessageManager;
    private ResolutionMessageService messageService;
    private ResolutionAction mockAction;

    @BeforeEach
    void setUp() {
        mockMessageManager = mock(MessageManager.class);
        messageService = new ResolutionMessageService(mockMessageManager);
        mockAction = mock(ResolutionAction.class);
    }

    @Test
    void testCreateSolutionMessage() {
        when(mockMessageManager.createMessageSolution(mockAction)).thenReturn("Message de solution");

        String result = messageService.createSolutionMessage(mockAction);

        assertEquals("Message de solution", result);
        verify(mockMessageManager, times(1)).createMessageSolution(mockAction);
    }

    @Test
    void testCreateEliminationMessage() {
        when(mockMessageManager.createMessageElimination(mockAction)).thenReturn("Message d'élimination");

        String result = messageService.createEliminationMessage(mockAction);

        assertEquals("Message d'élimination", result);
        verify(mockMessageManager, times(1)).createMessageElimination(mockAction);
    }
}