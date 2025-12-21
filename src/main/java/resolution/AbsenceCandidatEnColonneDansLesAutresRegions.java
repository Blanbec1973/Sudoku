package resolution;

import model.grille.CaseContext;
import model.grille.Grille;
import utils.Utils;

public class AbsenceCandidatEnColonneDansLesAutresRegions extends AbsenceCandidatDansLesAutresRegions {

	public AbsenceCandidatEnColonneDansLesAutresRegions(Grille grille) {
		super(grille);
	}

	protected boolean testCase(CaseContext context, int rangCase, int candidat) {
		return !(grille.getRegion(context.getNumCase()) != grille.getRegion(Utils.calculNumCase(context.getX(),rangCase)) &&
				grille.isCaseATrouver(context.getX(),rangCase) &&
        	    grille.isCandidat(context.getX(),rangCase, candidat));
	}
	
	protected boolean detecteCandidatAEliminer(CaseContext context) {
        for (int abs=context.getxRegion();abs<context.getxRegion()+3;abs++) {
            for (int ord=context.getyRegion();ord<context.getyRegion()+3;ord++) {
                if (abs!= context.getX() &&
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
