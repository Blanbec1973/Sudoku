package resolution;

import model.grille.CaseContext;
import model.grille.Grille;

import java.util.Arrays;
import java.util.Optional;

public class PaireConjugueeEnRegion extends PaireConjuguee {

	public PaireConjugueeEnRegion(Grille grille) {
		super(grille);
	}

	protected boolean detecteConfiguration(CaseContext context) {
		if (grille.getNombreCandidats(context.getNumCase()) != 2) return false;
		for (int abs=context.getxRegion();abs<context.getxRegion()+3;abs++) {
            for (int ord=context.getyRegion();ord<context.getyRegion()+3;ord++) {
                if ((context.getX() != abs || context.getY() != ord) &&
                    grille.isCaseATrouver(abs, ord) &&
                    Arrays.equals(grille.getCandidatsTabBoolean(context.getNumCase()),
                    		      grille.getCandidatsTabBoolean(abs, ord))) {
                	return true;
                }
            }
        }
		return false;
	}
	
	protected Optional<ResolutionAction> detecteCandidatAEliminer(CaseContext context) {
        for (int xAction=context.getxRegion();xAction<context.getxRegion()+3;xAction++) {
            for (int yAction=context.getyRegion();yAction<context.getyRegion()+3;yAction++) {
                if (grille.isCaseATrouver(Grille.calculNumCase(xAction, yAction)) &&
                    !Arrays.equals(grille.getCandidatsTabBoolean(context.getNumCase()),
                    grille.getCandidatsTabBoolean(xAction, yAction))) {
                	if (grille.isCandidat(Grille.calculNumCase(xAction, yAction), c1) ) {
						return Optional.of(creerResolutionAction(xAction,
								yAction,
								c1,
								context));
            		}
            		if (grille.isCandidat(Grille.calculNumCase(xAction, yAction), c2) ) {
						return Optional.of(creerResolutionAction(xAction,
								yAction,
								c2,
								context));
            		}
                }
            }
        }
		return Optional.empty();
	}
}
