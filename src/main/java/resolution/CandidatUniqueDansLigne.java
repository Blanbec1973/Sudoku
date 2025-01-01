package resolution;

import model.grille.CaseEnCours;
import model.grille.Grille;

public class CandidatUniqueDansLigne extends CandidatUniqueDansZone {

	public CandidatUniqueDansLigne(Grille grille) {super(grille);}

	@Override
	protected boolean checkPresenceCandidatZone(int candidat, int x, int y) {
		return grille.checkPresenceCandidatLigne(candidat, CaseEnCours.getX(),CaseEnCours.getY());
	}

	@Override
	public String getSimpleName() {
		return this.getClass().getSimpleName();
	}

}
