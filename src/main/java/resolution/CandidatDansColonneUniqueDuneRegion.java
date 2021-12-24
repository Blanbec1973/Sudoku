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
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean traiteCaseEnCours(boolean goPourChangement) {
		numColonne = CaseEnCours.getXSearch();
		boolean trouve = this.detecteConfiguration();   	
        if (!trouve) return false;
        
        trouve = this.detecteCandidatAEliminer();
        if (!trouve) return false;
		return true;
	}

	private boolean detecteConfiguration() {
		// Pour tous les candidats de la case en cours, je regarde si je ne suis que dans la colonne de ma région
		//System.out.println("Case en cours : "+CaseEnCours.getXSearch()+" "+CaseEnCours.getYSearch());
		for (int i=1; i < 9 ;i++) {
			candidatAEliminer = i;
			System.out.println(i+" "+grille.getCaseEnCours().isCandidat(i));
			if (grille.getCaseEnCours().isCandidat(i) && candidatDansColonneUnique()) return true;
		}
	
		return false;
	}
	
	private boolean candidatDansColonneUnique() {
		int col1 = 0, col2=0; // les deux colonnes de la région où on devra chercher le candidat.
		
		switch (CaseEnCours.getXSearch()) {
			case 0:
				col1 = 1;
				col2 = 2;
				break;
			case 1:
				col1 = 0;
				col2 = 2;
				break;
			case 2:
				col1 = 0;
				col2 = 1;
				break;
			case 3:
				col1 = 4;
				col2 = 5;
				break;
			case 4:
				col1 = 3;
				col2 = 5;
				break;
			case 5:
				col1 = 3;
				col2 = 4;
				break;
			case 6:
				col1 = 7;
				col2 = 8;
				break;
			case 7:
				col1 = 6;
				col2 = 8;
				break;
			case 8:
				col1 = 6;
				col2 = 7;
				break;
		}
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
