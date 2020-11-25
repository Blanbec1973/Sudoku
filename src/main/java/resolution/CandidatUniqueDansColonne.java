package resolution;

import modele.CaseEnCours;
import modele.Grille;
import modele.Modele;

public class CandidatUniqueDansColonne extends MethodeResolution {

	public CandidatUniqueDansColonne(Modele modele, Grille grille) {
		super(modele, grille);
	}

	@Override
	public boolean detecteSuivant(boolean goPourChangement) {
		boolean trouve = false;
		int i=0;
		int candidat =0;
		while (i < grille.getCasesAtrouver().size() & !trouve) {
			CaseEnCours.setCaseEnCours(grille.getCasesAtrouver().get(i));
			for (candidat=1;candidat<10;candidat++) {
  	            if (grille.getCaseEnCours().isCandidat(candidat) &&
	                !grille.checkPresenceCandidatColonne(candidat, CaseEnCours.getXSearch(),CaseEnCours.getYSearch())) {
  	            	trouve = true;
  	            	break;
  	            }
			}   
			i+=1;
		}
		
		if (!trouve) return false;
		if (goPourChangement) this.setValeurCaseEnCours(goPourChangement, candidat);
			else modele.getControle().demandeHighlightCase(CaseEnCours.getXSearch(),
														   CaseEnCours.getYSearch(),
						                                   grille.getCaseEnCours().construitLibelleCandidats());
		return true;
	}

}
