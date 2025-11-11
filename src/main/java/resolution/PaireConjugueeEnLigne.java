package resolution;

import model.grille.CaseContext;
import model.grille.Grille;

import java.util.Arrays;

public class PaireConjugueeEnLigne extends PaireConjuguee {

	public PaireConjugueeEnLigne(Grille grille) {
		super(grille);
	}

	protected boolean detecteConfiguration(CaseContext context) {
        for (x2=0;x2<9;x2++) {
            if (x2 != context.getX() &&
                grille.isCaseATrouver(x2, context.getY()) &&
                Arrays.equals(grille.getCandidatsTabBoolean(context.getNumCase()),
                		grille.getCandidatsTabBoolean(x2, context.getY()))) {
            	return true;
            }
        }
		return false;
	}
	
	protected boolean detecteCandidatAEliminer(CaseContext context) {
        for (int xAction=0;xAction<9;xAction++) {
        	if (grille.isCaseATrouver(Grille.calculNumCase(xAction, context.getY())) &&
        			xAction!=context.getX() && xAction !=x2) {
        		numCaseAction= Grille.calculNumCase(xAction, context.getY());
        		if (grille.isCandidat(Grille.calculNumCase(xAction, context.getY()), c1) ) {
        			candidatAEliminer = c1;
        			return true;
        		}
        		if (grille.isCandidat(Grille.calculNumCase(xAction, context.getY()), c2) ) {
        			candidatAEliminer = c2;
        			return true;
        		}
            }
        }
		return false;
	}
}
