package resolution;

import model.grille.CaseEnCours;
import model.grille.Grille;

abstract class CandidatUniqueDansZone extends MethodeResolution {

	protected CandidatUniqueDansZone(Grille grille) {
		super(grille);
	}

	@Override
	public boolean traiteCaseEnCours(boolean goPourChangement) {
		caseTrouvee = false;
		int candidat;
		for (candidat=1;candidat<10;candidat++) {
			if (grille.isCandidat(CaseEnCours.getNumCase(),candidat) &&
				!checkPresenceCandidatZone(candidat, CaseEnCours.getX(),CaseEnCours.getY())) {
				caseTrouvee = true;
  	            break;
  	        }
		}
		
		if (!caseTrouvee) return false;
		
		solution = candidat;
		numCaseAction = CaseEnCours.getNumCase();
		return true;
	}

	protected abstract boolean checkPresenceCandidatZone(int candidat, int x, int y);

	@Override
	public String getSimpleName() {
		return this.getClass().getSimpleName();
	}

}
