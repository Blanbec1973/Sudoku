package resolution;

import model.grille.CaseEnCours;
import model.grille.Grille;

import java.util.Arrays;

public class PaireConjugueeEnLigne extends PaireConjuguee {

	public PaireConjugueeEnLigne(Grille grille) {
		super(grille);
	}

	protected boolean detecteConfiguration() {
        for (x2=0;x2<9;x2++) {
            if (x2 != CaseEnCours.getX() &&
                grille.isCaseATrouver(x2, CaseEnCours.getY()) &&
                Arrays.equals(grille.getCandidatsTabBoolean(CaseEnCours.getNumCase()),
                		grille.getCandidatsTabBoolean(x2, CaseEnCours.getY()))) {
            	return true;
            }
        }
		return false;
	}
	
	protected boolean detecteCandidatAEliminer() {
        for (int xAction=0;xAction<9;xAction++) {
        	if (grille.isCaseATrouver(Grille.calculNumCase(xAction, CaseEnCours.getY())) &&
        			xAction!=CaseEnCours.getX() && xAction !=x2) {
        		numCaseAction= Grille.calculNumCase(xAction, CaseEnCours.getY());
        		if (grille.isCandidat(Grille.calculNumCase(xAction, CaseEnCours.getY()), c1) ) {
        			candidatAEliminer = c1;
        			return true;
        		}
        		if (grille.isCandidat(Grille.calculNumCase(xAction, CaseEnCours.getY()), c2) ) {
        			candidatAEliminer = c2;
        			return true;
        		}
            }
        }
		return false;
	}
}
