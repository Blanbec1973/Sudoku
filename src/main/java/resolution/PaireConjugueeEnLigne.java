package resolution;

import java.util.Arrays;

import modele.grille.CaseEnCours;
import modele.grille.Grille;
import modele.Modele;
import modele.Utils;

public class PaireConjugueeEnLigne extends PaireConjuguee {

	public PaireConjugueeEnLigne(Modele modele, Grille grille) {
		super(modele,grille);
	}

	protected boolean detecteConfiguration() {
        for (x2=0;x2<9;x2++) {
            if (x2 != CaseEnCours.getXSearch() &&
                grille.getCase(x2, CaseEnCours.getYSearch()).isCaseATrouver() && 
                Arrays.equals(grille.getCaseEnCours().getCandidatsTabBoolean(), 
                		grille.getCase(x2, CaseEnCours.getYSearch()).getCandidatsTabBoolean())) {
            	return true;
            }
        }
		return false;
	}
	
	protected boolean detecteCandidatAEliminer() {
        for (int xAction=0;xAction<9;xAction++) {
        	if (grille.getCase(xAction, CaseEnCours.getYSearch()).isCaseATrouver() &&
        			xAction!=CaseEnCours.getXSearch() && xAction !=x2) {
        		numCaseAction=Utils.calculNumCase(xAction, CaseEnCours.getYSearch());
        		if (grille.getCase(xAction, CaseEnCours.getYSearch()).isCandidat(c1) ) {
        			candidatAEliminer = c1;
        			return true;
        		}
        		if (grille.getCase(xAction, CaseEnCours.getYSearch()).isCandidat(c2) ) {
        			candidatAEliminer = c2;
        			return true;
        		}
            }
        }
		return false;
	}
}
