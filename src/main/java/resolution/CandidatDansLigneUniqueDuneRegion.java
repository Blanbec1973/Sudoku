package resolution;

import model.grille.CaseContext;
import model.grille.Grille;
import utils.Utils;

import java.util.Optional;

public class CandidatDansLigneUniqueDuneRegion extends MethodeResolution {
	int xAction;
	int yAction;
	int numLigne;
	int numCaseAction;
	int candidatAEliminer;

	public CandidatDansLigneUniqueDuneRegion(Grille grille) {
		super(grille);
	}

	@Override
	public Optional<ResolutionAction> traiteCaseEnCours(CaseContext context, boolean goPourChangement) {
		numLigne = context.getY();
		boolean trouve = this.detectConfiguration(context);
        if (!trouve) return Optional.empty();
        
        if (this.detecteCandidatAEliminer(context)) {
			return Optional.of(new ResolutionAction(numCaseAction, null, candidatAEliminer, this, context, null));
		}
		else {
			return Optional.empty();
		}
	}

	private boolean detectConfiguration(CaseContext context) {
		// Pour tous les candidats de la case en cours, je regarde si je ne suis que dans la ligne de ma région
		for (int i=1; i <= 9 ;i++) {
			candidatAEliminer = i;
			if (grille.isCandidat(context.getNumCase(), i) && candidatDansLigneUnique(context)) return true;
		}
	
		return false;
	}
	
	boolean candidatDansLigneUnique(CaseContext context) {
		// les deux lignes de la région où on devra chercher le candidat :
		int lig1 = Utils.calculAutresLignesOuColonnesDuneRegion(context.getY(),1) ;
		int lig2 = Utils.calculAutresLignesOuColonnesDuneRegion(context.getY(),2) ;

		// vérification absence candidat lig1 :
		for (int x=context.getxRegion(); x<context.getxRegion()+3;x++) {
			if (grille.isCaseATrouver(x, lig1) && grille.isCandidat(x, lig1, candidatAEliminer)) return false;
		}
		
		for (int x=context.getxRegion(); x<context.getxRegion()+3;x++) {
			if (grille.isCaseATrouver(x,lig2) && grille.isCandidat(x, lig2, candidatAEliminer)) return false;
		}
		return true;
	}

	boolean detecteCandidatAEliminer(CaseContext context) {
		// Recherche présence candidatAEliminer dans les autres régions de la ligne
		for (int i=0;i<9;i++) {
			if (context.getNumRegion() != Utils.calculNumeroRegion(Grille.calculNumCase(i, numLigne)) &&
			    grille.isCaseATrouver(Grille.calculNumCase(i, numLigne)) &&
					grille.isCandidat(Grille.calculNumCase(i, numLigne),candidatAEliminer)) {
				xAction=i;
				yAction=numLigne;
				numCaseAction= Grille.calculNumCase(xAction,yAction);
				return true;
			}
		}
		return false;
	}



	
	
	
	
}
