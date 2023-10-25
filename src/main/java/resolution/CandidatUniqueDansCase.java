package resolution;

import model.grille.CaseEnCours;
import model.grille.Grille;
import model.Model;

public class CandidatUniqueDansCase extends MethodeResolution {

	public CandidatUniqueDansCase(Model model, Grille grille) {
		super(model, grille);
	}

	@Override
	public boolean traiteCaseEnCours(boolean goPourChangement) {
		caseTrouvee = this.detecteConfiguration();
		
		if (!caseTrouvee) return false;
		
		solution = grille.getCaseEnCours().calculValeurUnique();
		numCaseAction = CaseEnCours.getNumCase();
		return true;
	}

	@Override
	public String getSimpleName() {
		return this.getClass().getSimpleName();
	}

	private boolean detecteConfiguration() {
		return (grille.getCaseEnCours().contientCandidatUnique());
	}

}
