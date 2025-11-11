package resolution;

import model.grille.CaseContext;
import model.grille.Grille;

import java.util.Optional;

public class CandidatUniqueDansCase extends MethodeResolution {

	public CandidatUniqueDansCase(Grille grille) {
		super(grille);
	}

	@Override
	public Optional<ResolutionAction> traiteCaseEnCours(CaseContext context, boolean goPourChangement) {
		caseTrouvee = this.detecteConfiguration(context);
		
		if (!caseTrouvee) return Optional.empty();
		
		int solution = grille.calculValeurUnique(context.getNumCase());
		numCaseAction = context.getNumCase();
		return Optional.of(new ResolutionAction(numCaseAction, solution, null, this, context));
	}

	private boolean detecteConfiguration(CaseContext context) {

		return (grille.contientCandidatUnique(context.getNumCase()));
	}

}
