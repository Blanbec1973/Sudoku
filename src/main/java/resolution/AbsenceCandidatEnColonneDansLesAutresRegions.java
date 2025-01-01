package resolution;

import model.grille.CaseEnCours;
import model.grille.Grille;

public class AbsenceCandidatEnColonneDansLesAutresRegions extends AbsenceCandidatDansLesAutresRegions {

	public AbsenceCandidatEnColonneDansLesAutresRegions(Grille grille) {
		super(grille);
	}

	protected boolean testCase(int rangCase, int candidat) {
		return !(grille.getRegion(CaseEnCours.getNumCase()) != grille.getRegion(Grille.calculNumCase(CaseEnCours.getX(),rangCase)) &&
				grille.isCaseATrouver(CaseEnCours.getX(),rangCase) &&
        	    grille.isCandidat(CaseEnCours.getX(),rangCase, candidat));
	}
	
	protected boolean detecteCandidatAEliminer() {
        for (int abs=CaseEnCours.getxRegion();abs<CaseEnCours.getxRegion()+3;abs++) {
            for (int ord=CaseEnCours.getyRegion();ord<CaseEnCours.getyRegion()+3;ord++) {
                if (abs!= CaseEnCours.getX() &&
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
