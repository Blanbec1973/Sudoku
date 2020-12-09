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
			modele.setValeurCaseEnCours(grille.getCaseEnCours().calculValeurUnique(), this.calculMessageLog(0));
		else 
			modele.getControle().demandeHighlightCase(CaseEnCours.getXSearch(),
			   									      CaseEnCours.getYSearch());
		return true;
	}
	
	private boolean detecteConfiguration() {
		return (grille.getCaseEnCours().contientCandidatUnique());
	}

}
