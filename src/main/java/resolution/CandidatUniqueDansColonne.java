package resolution;

import modele.CaseEnCours;
import modele.Grille;
import modele.Modele;

public class CandidatUniqueDansColonne extends MethodeResolution {

	public CandidatUniqueDansColonne(Modele modele, Grille grille) {
		super(modele, grille);
	}

	@Override
	public boolean traiteCaseEnCours(boolean goPourChangement) {
		boolean trouve = false;
		int candidat;
		for (candidat=1;candidat<10;candidat++) {
			if (grille.getCaseEnCours().isCandidat(candidat) &&
				!grille.checkPresenceCandidatColonne(candidat, CaseEnCours.getXSearch(),CaseEnCours.getYSearch())) {
  	            trouve = true;
  	            break;
  	        }
		}
		
		if (!trouve) return false;
		if (goPourChangement) this.setValeurCaseEnCours(goPourChangement, candidat);
			else modele.getControle().demandeHighlightCase(CaseEnCours.getXSearch(),
														   CaseEnCours.getYSearch(),
						                                   grille.getCaseEnCours().construitLibelleCandidats());
		return true;
	}

}
