package resolution;

import modele.CaseEnCours;
import modele.Grille;
import modele.Modele;

public class CandidatUniqueDansCase extends MethodeResolution {

	public CandidatUniqueDansCase(Modele modele, Grille grille) {
		super(modele, grille);
	}

	@Override
	public boolean traiteCaseEnCours(boolean goPourChangement) {
		boolean trouve = false;
		if (grille.getCaseEnCours().contientCandidatUnique()) trouve = true;
		
		if (!trouve) return false;
		
		if (goPourChangement) this.setValeurCaseEnCours(goPourChangement, grille.getCaseEnCours().calculValeurUnique());
			else modele.getControle().demandeHighlightCase(CaseEnCours.getXSearch(),
														   CaseEnCours.getYSearch(),
						                                   grille.getCaseEnCours().construitLibelleCandidats());
		return true;
	}

}
