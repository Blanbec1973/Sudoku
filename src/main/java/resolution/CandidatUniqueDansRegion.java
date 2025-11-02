package resolution;

import model.grille.CaseEnCours;
import model.grille.Grille;

public class CandidatUniqueDansRegion extends CandidatUniqueDansZone {

	public CandidatUniqueDansRegion(Grille grille) {
		super(grille);
	}

	@Override
	protected boolean checkPresenceCandidatZone(int candidat, int x, int y) {
		return grilleService.checkPresenceCandidatRegion(candidat, CaseEnCours.getX(),CaseEnCours.getY());
	}

}
