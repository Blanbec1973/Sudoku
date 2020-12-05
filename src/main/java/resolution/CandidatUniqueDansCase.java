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
		boolean trouve = this.detecteConfiguration();
		
		if (!trouve) return false;
		
		if (goPourChangement) 
			this.setValeurCaseEnCours(grille.getCaseEnCours().calculValeurUnique());
		else 
			modele.getControle().demandeHighlightCase(CaseEnCours.getXSearch(),
			   									      CaseEnCours.getYSearch());
		return true;
	}
	
	private boolean detecteConfiguration() {
		return (grille.getCaseEnCours().contientCandidatUnique());
	}

}
