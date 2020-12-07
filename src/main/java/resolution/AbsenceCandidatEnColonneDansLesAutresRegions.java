package resolution;

import modele.CaseEnCours;
import modele.Grille;
import modele.Modele;

public class AbsenceCandidatEnColonneDansLesAutresRegions extends AbsenceCandidatDansLesAutresRegions {

	public AbsenceCandidatEnColonneDansLesAutresRegions(Modele modele, Grille grille) {
		super(modele,grille);
	}
	
	protected boolean testCase(int rangCase, int candidat) {
		if (grille.getCaseEnCours().getRegion() != grille.getCase(CaseEnCours.getXSearch(),rangCase).getRegion() &&
				grille.getCase(CaseEnCours.getXSearch(),rangCase).isCaseATrouver() &&
        	    grille.getCase(CaseEnCours.getXSearch(),rangCase).isCandidat(candidat)) {
				return false;
			}
		return true;
	}
	
	protected boolean detecteCandidatAEliminer() {
        for (int abs=CaseEnCours.getxRegion();abs<CaseEnCours.getxRegion()+3;abs++) {
            for (int ord=CaseEnCours.getyRegion();ord<CaseEnCours.getyRegion()+3;ord++) {
                if (abs!= CaseEnCours.getXSearch() && 
                	grille.getCase(abs, ord).isCaseATrouver() &&
                    grille.getCase(abs, ord).isCandidat(candidatAEliminer)) {
                	xAction = abs;
                	yAction = ord;
                	return true;
                }  
            }
        }  
		return false;
	}
}
