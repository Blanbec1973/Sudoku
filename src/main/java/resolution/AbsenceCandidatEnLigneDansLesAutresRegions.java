package resolution;

import model.grille.CaseEnCours;
import model.grille.Grille;

public class AbsenceCandidatEnLigneDansLesAutresRegions extends AbsenceCandidatDansLesAutresRegions {

	public AbsenceCandidatEnLigneDansLesAutresRegions(Grille grille) {
		super(grille);
	}

	protected boolean testCase(int rangCase, int candidat) {
		return !(grille.getRegion(CaseEnCours.getNumCase()) != grille.getRegion(Grille.calculNumCase(rangCase, CaseEnCours.getY())) &&
				grille.isCaseATrouver(rangCase, CaseEnCours.getY()) &&
        	    grille.isCandidat(rangCase, CaseEnCours.getY(),candidat));
	}
	
	protected boolean detecteCandidatAEliminer() {
        for (int abs=CaseEnCours.getxRegion();abs<CaseEnCours.getxRegion()+3;abs++) {
            for (int ord=CaseEnCours.getyRegion();ord<CaseEnCours.getyRegion()+3;ord++) {
                if (ord!= CaseEnCours.getY() &&
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
