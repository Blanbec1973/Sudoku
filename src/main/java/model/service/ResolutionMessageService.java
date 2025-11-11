package model.service;

import model.MessageManager;
import model.grille.CaseContext;
import resolution.MethodeResolution;

public class ResolutionMessageService {
    private final MessageManager messageManager;

    public ResolutionMessageService(MessageManager messageManager) {
        this.messageManager = messageManager;
    }

    public String createSolutionMessage(MethodeResolution methodeResolution, CaseContext context) {
        return messageManager.createMessageSolution(methodeResolution, context);
    }

    public String createEliminationMessage(MethodeResolution methodeResolution, CaseContext context) {
        return messageManager.createMessageElimination(methodeResolution, context);
    }
}