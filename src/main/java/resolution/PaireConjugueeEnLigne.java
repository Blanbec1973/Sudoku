package resolution;

import model.grille.CaseContext;
import model.grille.Grille;

import java.util.Arrays;
import java.util.Optional;

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
	
	protected Optional<ResolutionAction> detecteCandidatAEliminer(CaseContext context, int[] candidatsUtilises) {
        int c1 = candidatsUtilises[0];
		int c2 = candidatsUtilises[1];
		for (int xAction=0;xAction<9;xAction++) {
        	if (grille.isCaseATrouver(Grille.calculNumCase(xAction, context.getY())) &&
        			xAction!=context.getX() && xAction !=x2) {
        		if (grille.isCandidat(Grille.calculNumCase(xAction, context.getY()), c1) ) {
					return Optional.of(creerResolutionAction(xAction,
							                                 context.getY(),
							                                 c1,
							                                 context,candidatsUtilises));
        		}
        		if (grille.isCandidat(Grille.calculNumCase(xAction, context.getY()), c2) ) {
					return Optional.of(creerResolutionAction(xAction,
							                                 context.getY(),
							                                 c2,
							                                 context,candidatsUtilises));
        		}
            }
        }
		return Optional.empty();
	}
}
