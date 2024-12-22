package resolution;

import model.grille.CaseEnCours;
import model.grille.Grille;
import model.Model;

public class CandidatUniqueDansRegion extends MethodeResolution {

	public CandidatUniqueDansRegion(Model model, Grille grille) {
		super(model, grille);
	}

	@Override
	public boolean traiteCaseEnCours(boolean goPourChangement) {
		caseTrouvee = false;
		
		int candidat;
		for (candidat=1;candidat<10;candidat++) {
  	        if (grille.isCandidat(CaseEnCours.getNumCase(),candidat) &&
	           !grille.checkPresenceCandidatRegion(candidat, CaseEnCours.getX(),CaseEnCours.getY())) {
  	        	caseTrouvee = true;
  	           	break;
  	        }
		}
		
		if (!caseTrouvee) return false;
		
		solution = candidat;
		numCaseAction = CaseEnCours.getNumCase();
		return true;		
	}

	@Override
	public String getSimpleName() {
		return this.getClass().getSimpleName();
	}

}
