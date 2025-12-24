package model;

import model.grille.Grille;
import model.service.HistorisationService;
import model.service.ModelEventService;
import model.service.ResolutionMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import resolution.MethodeResolution;
import resolution.MethodeResolutionFactory;
import resolution.ResolutionAction;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Optional;

@Component
public class Model {
	private final ModelEventService modelEventService;
	private final ResolutionMessageService messageService;
	private final Grille grille;
	private final ArrayList<MethodeResolution> listeMethodes;
	private final HistorisationService historisationService;

    @Autowired
	public Model(ModelEventService modelEventService, ResolutionMessageService messageService, HistorisationService historisationService) {
		this.modelEventService = modelEventService;
		this.messageService = messageService;
		this.historisationService = historisationService;

		grille =new Grille();

	    listeMethodes = (ArrayList<MethodeResolution>) MethodeResolutionFactory.createAll(grille);
	}

	public Grille getGrille() {return grille;}

	public boolean detecteSuivant(boolean goPourChangement) {
		Optional<ResolutionAction> action = findNextAction(goPourChangement);

		if (action.isPresent()) {
			ResolutionAction a = action.get();

			if (goPourChangement) {
				traiteChangement(a);
			} else {
				modelEventService.publishHighlight(grille, a.getNumCaseAction());
			}
			return true;
		}
		javax.swing.JOptionPane.showMessageDialog(null, "Fin algorithme !");
		return false;
	}

	private Optional<ResolutionAction> findNextAction(boolean goPourChangement) {
		for (MethodeResolution methode : listeMethodes) {
			Optional<ResolutionAction> action = methode.detecteSuivant(goPourChangement);
			if (action.isPresent()) {
				return action;
			}
		}
		return Optional.empty();
	}
	
	private void traiteChangement(ResolutionAction action) {
		// Box is founded :
		if (action.isCaseTrouvee()) {
			setValeurCaseEnCours(action,
					messageService.createSolutionMessage(action));
			return;
		}
		
		// A candidate must be eliminated :
		elimineCandidatCase(action.getCandidatAEliminer(),
				            action.getNumCaseAction(),
				messageService.createEliminationMessage(action));
	}

	private void setValeurCaseEnCours(ResolutionAction action, String message) {
		grille.setValeurCaseEnCours(action);
		modelEventService.publishSolution(grille, action.getContext().getNumCase(), message);
		historisationService.historiseGrille(grille);
	}
	
	private void elimineCandidatCase(int candidatAEliminer, int numCaseAction, String message) {
		grille.elimineCandidat(numCaseAction, candidatAEliminer);
		modelEventService.publishElimination(grille,numCaseAction, message);
		historisationService.historiseGrille(grille);
	}

	public void reloadLastHistoricization() {
		historisationService.supprimeDerniereGrille(grille);
	}

    public void reload(Path path) {
		grille.init(path);
		historisationService.reloadGrille(grille);
    }
	public boolean canReloadLastHistoricization() {
		return historisationService.canReloadLastHistoricization();
	}
}
