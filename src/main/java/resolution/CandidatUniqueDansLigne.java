package resolution;

import modele.CaseEnCours;
import modele.Grille;
import modele.Modele;

public class CandidatUniqueDansLigne extends MethodeResolution {

	public CandidatUniqueDansLigne(Modele modele, Grille grille) {super(modele,grille);}

	@Override
	public boolean traiteCaseEnCours(boolean goPourChangement) {
		boolean trouve = false;
		int candidat =0;
		for (candidat=1;candidat<10;candidat++) {
  	        if (grille.getCaseEnCours().isCandidat(candidat) &&
	            !grille.checkPresenceCandidatLigne(candidat, CaseEnCours.getXSearch(),CaseEnCours.getYSearch())) {
  	          	trouve = true;
  	           	break;
  	        }
		}
		
		if (!trouve) return false;
		if (goPourChangement) modele.setValeurCaseEnCours(candidat, this.calculMessageLog(0));
			else modele.getControle().demandeHighlightCase(CaseEnCours.getXSearch(),
														   CaseEnCours.getYSearch());
			
		return true;
		
	}

}
