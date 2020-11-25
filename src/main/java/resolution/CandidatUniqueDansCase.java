package resolution;

import modele.CaseEnCours;
import modele.Grille;
import modele.Modele;

public class CandidatUniqueDansCase extends MethodeResolution {

	public CandidatUniqueDansCase(Modele modele, Grille grille) {
		super(modele, grille);
	}

	@Override
	public boolean detecteSuivant(boolean goPourChangement) {
		boolean trouve = false;
		int i=0;
		while (i < grille.getCasesAtrouver().size() & !trouve) {
			CaseEnCours.setCaseEnCours(grille.getCasesAtrouver().get(i));
			if (grille.getCaseEnCours().contientCandidatUnique()) trouve = true;
			i+=1;
		}
		
		if (!trouve) return false;
		
		if (goPourChangement) this.setValeurCaseEnCours(goPourChangement, grille.getCaseEnCours().calculValeurUnique());
			else modele.getControle().demandeHighlightCase(CaseEnCours.getXSearch(),
														   CaseEnCours.getYSearch(),
						                                   grille.getCaseEnCours().construitLibelleCandidats());
		return true;
	}

}
