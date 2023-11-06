package resolution;

import model.grille.CaseEnCours;
import model.grille.Grille;
import model.Model;

public class CandidatUniqueDansLigne extends MethodeResolution {

	public CandidatUniqueDansLigne(Model model, Grille grille) {super(model,grille);}

	@Override
	public boolean traiteCaseEnCours(boolean goPourChangement) {
		caseTrouvee = false;
		int candidat =0;
		for (candidat=1;candidat<10;candidat++) {
  	        if (grille.isCandidat(CaseEnCours.getNumCase(),candidat) &&
	            !grille.checkPresenceCandidatLigne(candidat, CaseEnCours.getXSearch(),CaseEnCours.getYSearch())) {
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
