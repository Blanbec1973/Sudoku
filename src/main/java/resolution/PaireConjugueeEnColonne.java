package resolution;

import java.util.Arrays;

import modele.CaseEnCours;
import modele.Grille;
import modele.Modele;

public class PaireConjugueeEnColonne extends PaireConjuguee {

	public PaireConjugueeEnColonne(Modele modele, Grille grille) {
		super(modele,grille);
	}
	
	protected boolean detecteConfiguration() {
		xAction = CaseEnCours.getXSearch();
		for (y2=0;y2<9;y2++) {
            if (y2 != CaseEnCours.getYSearch() &&
                grille.getCase( CaseEnCours.getXSearch(),y2).isCaseATrouver() && 
                Arrays.equals(grille.getCaseEnCours().getCandidatsTabBoolean(), 
                		grille.getCase(CaseEnCours.getXSearch(),y2).getCandidatsTabBoolean())) {
            	return true;
            }
        }
		return false;
	}
	
	protected boolean detecteCandidatAEliminer() {
		for (yAction=0;yAction<9;yAction++) {
        	if (grille.getCase(CaseEnCours.getXSearch(),yAction).isCaseATrouver() &&
        			yAction!=CaseEnCours.getYSearch() && yAction !=y2) {
        		if (grille.getCase(CaseEnCours.getXSearch(),yAction).isCandidat(c1) ) {
        			candidatAEliminer = c1;
        			return true;
        		}
        		if (grille.getCase(CaseEnCours.getXSearch(),yAction).isCandidat(c2) ) {
        			candidatAEliminer = c2;
        			return true;
        		}
            }
        }
		return false;
	}	
}
