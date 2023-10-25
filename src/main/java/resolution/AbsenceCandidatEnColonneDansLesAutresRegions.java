package resolution;

import model.grille.CaseEnCours;
import model.grille.Grille;
import model.Model;

public class AbsenceCandidatEnColonneDansLesAutresRegions extends AbsenceCandidatDansLesAutresRegions {

	public AbsenceCandidatEnColonneDansLesAutresRegions(Model model, Grille grille) {
		super(model,grille);
	}

	@Override
	public String getSimpleName() {
		return this.getClass().getSimpleName();
	}

	protected boolean testCase(int rangCase, int candidat) {
		return !(grille.getCaseEnCours().getRegion() != grille.getCase(CaseEnCours.getXSearch(),rangCase).getRegion() &&
				grille.getCase(CaseEnCours.getXSearch(),rangCase).isCaseATrouver() &&
        	    grille.getCase(CaseEnCours.getXSearch(),rangCase).isCandidat(candidat));
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
