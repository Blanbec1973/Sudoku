package resolution;

import modele.CaseEnCours;
import modele.Grille;
import modele.Modele;

public class CandidatUniqueDansLigne extends MethodeResolution {

	public CandidatUniqueDansLigne(Modele modele, Grille grille) {super(modele,grille);}

	@Override
	public boolean traiteCaseEnCours(boolean goPourChangement) {
		caseTrouvee = false;
		int candidat =0;
		for (candidat=1;candidat<10;candidat++) {
  	        if (grille.getCaseEnCours().isCandidat(candidat) &&
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

}
