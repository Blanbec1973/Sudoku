package model.service;

import model.MessageManager;
import model.grille.CaseContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import resolution.MethodeResolution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ResolutionMessageServiceTest {

    private MessageManager mockMessageManager;
    private ResolutionMessageService messageService;
    private MethodeResolution mockResolution;
    private CaseContext mockContext;

    @BeforeEach
    void setUp() {
        mockMessageManager = mock(MessageManager.class);
        messageService = new ResolutionMessageService(mockMessageManager);
        mockResolution = mock(MethodeResolution.class);
        mockContext= mock(CaseContext.class);

    }

    @Test
    void testCreateSolutionMessage() {
        when(mockMessageManager.createMessageSolution(mockResolution, mockContext)).thenReturn("Message de solution");

        String result = messageService.createSolutionMessage(mockResolution, mockContext);

        assertEquals("Message de solution", result);
        verify(mockMessageManager, times(1)).createMessageSolution(mockResolution, mockContext);
    }

    @Test
    void testCreateEliminationMessage() {
        when(mockMessageManager.createMessageElimination(mockResolution,mockContext)).thenReturn("Message d'élimination");

        String result = messageService.createEliminationMessage(mockResolution, mockContext);

        assertEquals("Message d'élimination", result);
        verify(mockMessageManager, times(1)).createMessageElimination(mockResolution, mockContext);
    }
}