package resolution;

import model.grille.CaseEnCours;
import model.grille.Grille;

public class CandidatUniqueDansColonne extends CandidatUniqueDansZone {

	public CandidatUniqueDansColonne(Grille grille) {
		super(grille);
	}

	@Override
	protected boolean checkPresenceCandidatZone(int candidat, int x, int y) {
		return grille.checkPresenceCandidatColonne(candidat, CaseEnCours.getX(),CaseEnCours.getY());
	}


}
