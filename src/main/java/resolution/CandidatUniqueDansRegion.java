package resolution;

import modele.grille.CaseEnCours;
import modele.grille.Grille;
import modele.Modele;

public class CandidatUniqueDansRegion extends MethodeResolution {

	public CandidatUniqueDansRegion(Modele modele, Grille grille) {
		super(modele, grille);
	}

	@Override
	public boolean traiteCaseEnCours(boolean goPourChangement) {
		caseTrouvee = false;
		
		int candidat =0;
		for (candidat=1;candidat<10;candidat++) {
  	        if (grille.getCaseEnCours().isCandidat(candidat) &&
	           !grille.checkPresenceCandidatRegion(candidat, CaseEnCours.getXSearch(),CaseEnCours.getYSearch())) {
  	        	caseTrouvee = true;
  	           	break;
  	        }
		}
		
		if (!caseTrouvee) return false;
		
		solution = candidat;
		numCaseAction = CaseEnCours.getNumCase();
		return true;		
	}

}
