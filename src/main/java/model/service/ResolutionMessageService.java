package model.service;

import model.MessageManager;
import resolution.MethodeResolution;

public class ResolutionMessageService {
    private final MessageManager messageManager;

    public ResolutionMessageService(MessageManager messageManager) {
        this.messageManager = messageManager;
    }

    public String createSolutionMessage(MethodeResolution methodeResolution) {
        return messageManager.createMessageSolution(methodeResolution);
    }

    public String createEliminationMessage(MethodeResolution methodeResolution) {
        return messageManager.createMessageElimination(methodeResolution);
    }
}