package model.service;

import model.MessageManager;
import org.springframework.stereotype.Service;
import resolution.ResolutionAction;

@Service
public class ResolutionMessageService {
    private final MessageManager messageManager;

    public ResolutionMessageService(MessageManager messageManager) {
        this.messageManager = messageManager;
    }

    public String createSolutionMessage(ResolutionAction action) {
        return messageManager.createMessageSolution(action);
    }

    public String createEliminationMessage(ResolutionAction action) {
        return messageManager.createMessageElimination(action);
    }
}