package resolution;

import model.grille.CaseContext;
import model.grille.Grille;
import model.grille.GrilleUtils;
import utils.Utils;

public class AbsenceCandidatEnLigneDansLesAutresRegions extends AbsenceCandidatDansLesAutresRegions {

	public AbsenceCandidatEnLigneDansLesAutresRegions(Grille grille) {
		super(grille);
	}

	protected boolean testCase(CaseContext context, int rangCase, int candidat) {
		return !(GrilleUtils.getRegion(grille, context.getNumCase()) != GrilleUtils.getRegion(grille, Utils.calculNumCase(rangCase, context.getY())) &&
				grille.isCaseATrouver(rangCase, context.getY()) &&
        	    grille.isCandidat(rangCase, context.getY(),candidat));
	}
	
	protected boolean detecteCandidatAEliminer(CaseContext context) {
        for (int abs=context.getxRegion();abs<context.getxRegion()+3;abs++) {
            for (int ord=context.getyRegion();ord<context.getyRegion()+3;ord++) {
                if (ord!= context.getY() &&
                	grille.isCaseATrouver(abs, ord) &&
                    grille.isCandidat(abs, ord, candidatAEliminer)) {
                	xAction = abs;
                	yAction = ord;
                	return true;
                }  
            }
        }  
		return false;
	}
}
