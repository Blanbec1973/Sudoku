package model.service;

import model.MessageManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import resolution.MethodeResolution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ResolutionMessageServiceTest {

    private MessageManager mockMessageManager;
    private ResolutionMessageService messageService;
    private MethodeResolution mockResolution;

    @BeforeEach
    void setUp() {
        mockMessageManager = mock(MessageManager.class);
        messageService = new ResolutionMessageService(mockMessageManager);
        mockResolution = mock(MethodeResolution.class);
    }

    @Test
    void testCreateSolutionMessage() {
        when(mockMessageManager.createMessageSolution(mockResolution)).thenReturn("Message de solution");

        String result = messageService.createSolutionMessage(mockResolution);

        assertEquals("Message de solution", result);
        verify(mockMessageManager, times(1)).createMessageSolution(mockResolution);
    }

    @Test
    void testCreateEliminationMessage() {
        when(mockMessageManager.createMessageElimination(mockResolution)).thenReturn("Message d'élimination");

        String result = messageService.createEliminationMessage(mockResolution);

        assertEquals("Message d'élimination", result);
        verify(mockMessageManager, times(1)).createMessageElimination(mockResolution);
    }
}