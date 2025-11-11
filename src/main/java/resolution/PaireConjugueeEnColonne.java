package resolution;

import model.grille.CaseContext;
import model.grille.Grille;

import java.util.Arrays;

public class PaireConjugueeEnColonne extends PaireConjuguee {

	public PaireConjugueeEnColonne(Grille grille) {
		super(grille);
	}

	protected boolean detecteConfiguration(CaseContext context) {
		for (y2=0;y2<9;y2++) {
            if (y2 != context.getY() &&
                grille.isCaseATrouver(context.getX(),y2) &&
                Arrays.equals(grille.getCandidatsTabBoolean(context.getNumCase()),
                		grille.getCandidatsTabBoolean(context.getX(),y2))) {
            	return true;
            }
        }
		return false;
	}
	
	protected boolean detecteCandidatAEliminer(CaseContext context) {
		for (int yAction=0;yAction<9;yAction++) {
        	if (grille.isCaseATrouver(context.getX(),yAction) &&
        			yAction!=context.getY() && yAction !=y2) {
        		numCaseAction= Grille.calculNumCase(context.getX(), yAction);
        		if (grille.isCandidat(context.getX(),yAction, c1) ) {
        			candidatAEliminer = c1;
        			return true;
        		}
        		if (grille.isCandidat(context.getX(),yAction, c2) ) {
        			candidatAEliminer = c2;
        			return true;
        		}
            }
        }
		return false;
	}	
}
