package model;

import model.grille.Grille;
import model.service.HistorisationService;
import model.service.ModelEventService;
import model.service.ResolutionMessageService;
import resolution.*;
import resolution.candidatunique.CandidatUniqueDansZone;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Optional;

public class Model {
	private final ModelEventService modelEventService;
	private final ResolutionMessageService messageService;
	private final Grille grille;
	private final ArrayList<MethodeResolution> listeMethodes;
	private final HistorisationService historisationService;

	public Model(ModelEventService modelEventService, ResolutionMessageService messageService, HistorisationService historisationService) {
		this.modelEventService = modelEventService;
		this.messageService = messageService;
		this.historisationService = historisationService;

		grille =new Grille();

	    listeMethodes = new ArrayList<>();
	    listeMethodes.add(new CandidatUniqueDansCase(grille));
	    listeMethodes.add(new CandidatUniqueDansZone(
				grille,
				grille.getGrilleService()::checkPresenceCandidatLigne,
				ZoneType.LIGNE)
		);
	    listeMethodes.add(new CandidatUniqueDansZone(
				grille,
				grille.getGrilleService()::checkPresenceCandidatColonne,
				ZoneType.COLONNE)
		);
	    listeMethodes.add(new CandidatUniqueDansZone(
				grille,
				grille.getGrilleService()::checkPresenceCandidatRegion,
				ZoneType.BLOC)
		);
	    listeMethodes.add(new PaireCandidats2CasesColonne(grille));
	    listeMethodes.add(new PaireConjugueeEnLigne(grille));
	    listeMethodes.add(new PaireConjugueeEnColonne(grille));
	    listeMethodes.add(new PaireConjugueeEnRegion(grille));
	    listeMethodes.add(new AbsenceCandidatEnColonneDansLesAutresRegions(grille));
	    listeMethodes.add(new AbsenceCandidatEnLigneDansLesAutresRegions(grille));
	    listeMethodes.add(new PaireCandidats2CasesLigne(grille));
	    listeMethodes.add(new TripletteCandidatsEnLigne(grille));
		listeMethodes.add(new CandidatDansColonneUniqueDuneRegion(grille));
		listeMethodes.add(new CandidatDansLigneUniqueDuneRegion(grille));
		listeMethodes.add(new TripletteCandidatsEnColonne(grille));
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
