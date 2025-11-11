package model.service;

import model.MessageManager;
import resolution.ResolutionAction;

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