package resolution;

import model.grille.CaseEnCours;
import model.grille.Grille;

import java.util.Optional;

public class CandidatUniqueDansCase extends MethodeResolution {

	public CandidatUniqueDansCase(Grille grille) {
		super(grille);
	}

	@Override
	public Optional<ResolutionAction> traiteCaseEnCours(boolean goPourChangement) {
		caseTrouvee = this.detecteConfiguration();
		
		if (!caseTrouvee) return Optional.empty();
		
		solution = grille.calculValeurUnique(CaseEnCours.getNumCase());
		numCaseAction = CaseEnCours.getNumCase();
		return Optional.of(new ResolutionAction(numCaseAction, solution, null, this));
	}

	private boolean detecteConfiguration() {

		return (grille.contientCandidatUnique(CaseEnCours.getNumCase()));
	}

}
