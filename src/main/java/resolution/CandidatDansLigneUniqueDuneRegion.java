package resolution;

import model.grille.CaseEnCours;
import model.grille.Grille;
import utils.Utils;

public class CandidatDansLigneUniqueDuneRegion extends MethodeResolution {
	int xAction;
	int yAction;
	int numLigne;

	public CandidatDansLigneUniqueDuneRegion(Grille grille) {
		super(grille);
	}

	@Override
	public boolean traiteCaseEnCours(boolean goPourChangement) {
		numLigne = CaseEnCours.getY();
		boolean trouve = this.detectConfiguration();
        if (!trouve) return false;
        
        trouve = this.detecteCandidatAEliminer();
        return trouve;
	}

	@Override
	public String getSimpleName() {
		return this.getClass().getSimpleName();
	}

	private boolean detectConfiguration() {
		// Pour tous les candidats de la case en cours, je regarde si je ne suis que dans la ligne de ma région
		for (int i=1; i <= 9 ;i++) {
			candidatAEliminer = i;
			if (grille.isCandidat(CaseEnCours.getNumCase(), i) && candidatDansLigneUnique()) return true;
		}
	
		return false;
	}
	
	boolean candidatDansLigneUnique() {
		// les deux lignes de la région où on devra chercher le candidat :
		int lig1 = Utils.calculAutresLignesOuColonnesDuneRegion(CaseEnCours.getY(),1) ;
		int lig2 = Utils.calculAutresLignesOuColonnesDuneRegion(CaseEnCours.getY(),2) ;

		// vérification absence candidat lig1 :
		for (int x=CaseEnCours.getxRegion(); x<CaseEnCours.getxRegion()+3;x++) {
			if (grille.isCaseATrouver(x, lig1) && grille.isCandidat(x, lig1, candidatAEliminer)) return false;
		}
		
		for (int x=CaseEnCours.getxRegion(); x<CaseEnCours.getxRegion()+3;x++) {
			if (grille.isCaseATrouver(x,lig2) && grille.isCandidat(x, lig2, candidatAEliminer)) return false;
		}
		return true;
	}

	boolean detecteCandidatAEliminer() {
		// Recherche présence candidatAEliminer dans les autres régions de la ligne
		for (int i=0;i<9;i++) {
			if (CaseEnCours.getNumRegion() != Utils.calculNumeroRegion(Grille.calculNumCase(i, numLigne)) &&
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
