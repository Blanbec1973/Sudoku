package resolution;

import model.grille.CaseEnCours;
import model.grille.Grille;

import java.util.Optional;

abstract class CandidatUniqueDansZone extends MethodeResolution {

	protected CandidatUniqueDansZone(Grille grille) {
		super(grille);
	}

	@Override
	public Optional<ResolutionAction> traiteCaseEnCours(boolean goPourChangement) {
		caseTrouvee = false;
		int candidat;
		for (candidat=1;candidat<10;candidat++) {
			if (grille.isCandidat(CaseEnCours.getNumCase(),candidat) &&
				!checkPresenceCandidatZone(candidat, CaseEnCours.getX(),CaseEnCours.getY())) {
				caseTrouvee = true;
  	            break;
  	        }
		}
		
		if (!caseTrouvee) return Optional.empty();
		
		solution = candidat;
		numCaseAction = CaseEnCours.getNumCase();
		return Optional.of(new ResolutionAction(numCaseAction, solution, null, this));
	}

	protected abstract boolean checkPresenceCandidatZone(int candidat, int x, int y);

}
