package resolution;

import model.grille.CaseContext;
import model.grille.Grille;
import utils.Utils;

import java.util.Optional;

public class CandidatDansColonneUniqueDuneRegion extends MethodeResolution {
	int xAction;
	int yAction;
	int numColonne;
	
	public CandidatDansColonneUniqueDuneRegion(Grille grille) {
		super(grille);
	}

	@Override
	public Optional<ResolutionAction> traiteCaseEnCours(CaseContext context, boolean goPourChangement) {
		numColonne = context.getX();
		boolean trouve = this.detectConfiguration(context);
        if (!trouve) return Optional.empty();
        
        if (this.detecteCandidatAEliminer(context))
        	return Optional.of(new ResolutionAction(numCaseAction, null, candidatAEliminer, this, context));
		else
			return Optional.empty();
	}

	private boolean detectConfiguration(CaseContext context) {
		// Pour tous les candidats de la case en cours, je regarde si je ne suis que dans la colonne de ma région
		for (int i=1; i <= 9 ;i++) {
			candidatAEliminer = i;
			if (grille.isCandidat(context.getNumCase(), i) && candidatDansColonneUnique(context)) return true;
		}
	
		return false;
	}
	
	boolean candidatDansColonneUnique(CaseContext context) {
		// les deux colonnes de la région où on devra chercher le candidat :
		int col1 = Utils.calculAutresLignesOuColonnesDuneRegion(context.getX(),1) ;
		int col2 = Utils.calculAutresLignesOuColonnesDuneRegion(context.getX(),2) ;

		// vérification absence candidat col1 :
		for (int numLigne=context.getyRegion(); numLigne<context.getyRegion()+3;numLigne++) {
			if (grille.isCaseATrouver(col1, numLigne) && grille.isCandidat(col1, numLigne, candidatAEliminer)) return false;
		}
		
		for (int i=context.getyRegion(); i<context.getyRegion()+3;i++) {
			if (grille.isCaseATrouver(col2, i) && grille.isCandidat(col2, i, candidatAEliminer)) return false;
		}
		return true;
	}

	boolean detecteCandidatAEliminer(CaseContext context) {
		// Recherche présence candidatAEliminer dans les autres régions de la colonne
		for (int i=0;i<9;i++) {
			if (context.getNumRegion() != Utils.calculNumeroRegion(Grille.calculNumCase(numColonne,i)) &&
			    grille.isCaseATrouver(numColonne,i) &&
					grille.isCandidat(numColonne,i,candidatAEliminer)) {
				xAction=numColonne;
				yAction=i;
				numCaseAction= Grille.calculNumCase(xAction,yAction);
				return true;
			}
		}
		return false;
	}



	
	
	
	
}
