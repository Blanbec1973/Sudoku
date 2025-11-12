package resolution;

import model.grille.CaseContext;
import model.grille.Grille;

import java.util.Optional;

abstract class CandidatUniqueDansZone extends MethodeResolution {

	protected CandidatUniqueDansZone(Grille grille) {
		super(grille);
	}

	@Override
	public Optional<ResolutionAction> traiteCaseEnCours(CaseContext context, boolean goPourChangement) {
		boolean caseTrouvee = false;
		int candidat;
		for (candidat=1;candidat<10;candidat++) {
			if (grille.isCandidat(context.getNumCase(),candidat) &&
				!checkPresenceCandidatZone(context, candidat)) {
				caseTrouvee = true;
  	            break;
  	        }
		}
		
		if (!caseTrouvee) return Optional.empty();
		
		int solution = candidat;
		int numCaseAction = context.getNumCase();
		return Optional.of(new ResolutionAction(numCaseAction, solution, null, this, context));
	}

	protected abstract boolean checkPresenceCandidatZone(CaseContext context, int candidat);

}
