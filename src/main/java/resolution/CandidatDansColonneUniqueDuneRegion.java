package resolution;

import modele.CaseEnCours;
import modele.Grille;
import modele.Modele;
import modele.Utils;

public class CandidatDansColonneUniqueDuneRegion extends MethodeResolution {
	int xAction;
	int yAction;
	int numColonne;
	
	protected CandidatDansColonneUniqueDuneRegion(Modele modele, Grille grille) {
		super(modele, grille);
	}

	@Override
	public boolean traiteCaseEnCours(boolean goPourChangement) {
		numColonne = CaseEnCours.getXSearch();
		boolean trouve = this.detecteConfiguration();   	
        if (!trouve) return false;
        
        trouve = this.detecteCandidatAEliminer();
        return trouve;
	}

	private boolean detecteConfiguration() {
		// Pour tous les candidats de la case en cours, je regarde si je ne suis que dans la colonne de ma région
		for (int i=1; i < 9 ;i++) {
			candidatAEliminer = i;
			if (grille.getCaseEnCours().isCandidat(i) && candidatDansColonneUnique()) return true;
		}
	
		return false;
	}
	
	boolean candidatDansColonneUnique() {
		// les deux colonnes de la région où on devra chercher le candidat :
		int col1 = Utils.calculAutresLignesOuColonnesDuneRegion(CaseEnCours.getXSearch(),1) ;
		int col2 = Utils.calculAutresLignesOuColonnesDuneRegion(CaseEnCours.getXSearch(),2) ;

		// vérification absence candidat col1 :
		for (int i=CaseEnCours.getyRegion(); i<CaseEnCours.getyRegion()+3;i++) {
			if (grille.getCase(col1, i).isCaseATrouver() && grille.getCase(col1, i).isCandidat(candidatAEliminer)) return false;
		}
		
		for (int i=CaseEnCours.getyRegion(); i<CaseEnCours.getyRegion()+3;i++) {
			if (grille.getCase(col2, i).isCaseATrouver() && grille.getCase(col2, i).isCandidat(candidatAEliminer)) return false;
		}
		return true;
	}

	private boolean detecteCandidatAEliminer() {
		// Recherche présence candidatAEliminer dans les autres régions de la colonne
		for (int i=0;i<9;i++) {
			if (CaseEnCours.getNumRegion() != Utils.calculNumeroRegion(Utils.calculNumCase(numColonne,i)) &&
			    grille.getCase(numColonne,i).isCandidat(candidatAEliminer)) {
				xAction=numColonne;
				yAction=i;
				numCaseAction=Utils.calculNumCase(xAction,yAction);
				return true;
			}
		}
		return false;
	}



	
	
	
	
}