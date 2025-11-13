package resolution;

import model.grille.CaseContext;
import model.grille.Grille;

import java.util.Arrays;
import java.util.Optional;

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
	
	protected Optional<ResolutionAction> detecteCandidatAEliminer(CaseContext context, int[] candidatsUtilises) {
		int c1=candidatsUtilises[0];
		int c2 = candidatsUtilises[1];
		for (int yAction=0;yAction<9;yAction++) {
        	if (grille.isCaseATrouver(context.getX(),yAction) &&
        			yAction!=context.getY() && yAction !=y2) {
        		if (grille.isCandidat(context.getX(),yAction, c1) ) {
        			return Optional.of(creerResolutionAction(context.getX(),
							                                 yAction,
							                                 c1,
							                                 context, candidatsUtilises));
        		}
        		if (grille.isCandidat(context.getX(),yAction, c2) ) {
					return Optional.of(creerResolutionAction(context.getX(),
							yAction,
							c2,
							context, candidatsUtilises));
        		}
            }
        }
		return Optional.empty();
	}	
}
