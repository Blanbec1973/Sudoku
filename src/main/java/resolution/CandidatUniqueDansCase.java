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
		caseTrouvee = this.detecteConfiguration();
		
		if (!caseTrouvee) return false;
		
		solution = grille.getCaseEnCours().calculValeurUnique();
		numCaseAction = CaseEnCours.getNumCase();
		return true;
	}
	
	private boolean detecteConfiguration() {
		return (grille.getCaseEnCours().contientCandidatUnique());
	}

}
