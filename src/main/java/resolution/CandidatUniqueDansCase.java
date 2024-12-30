package resolution;

import model.grille.CaseEnCours;
import model.grille.Grille;

public class CandidatUniqueDansCase extends MethodeResolution {

	public CandidatUniqueDansCase(Grille grille) {
		super(grille);
	}

	@Override
	public boolean traiteCaseEnCours(boolean goPourChangement) {
		caseTrouvee = this.detecteConfiguration();
		
		if (!caseTrouvee) return false;
		
		solution = grille.calculValeurUnique(CaseEnCours.getNumCase());
		numCaseAction = CaseEnCours.getNumCase();
		return true;
	}

	@Override
	public String getSimpleName() {
		return this.getClass().getSimpleName();
	}

	private boolean detecteConfiguration() {

		return (grille.contientCandidatUnique(CaseEnCours.getNumCase()));
	}

}
