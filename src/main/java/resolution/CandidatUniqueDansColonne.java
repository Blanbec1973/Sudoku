package resolution;

import model.grille.CaseContext;
import model.grille.Grille;

public class CandidatUniqueDansColonne extends CandidatUniqueDansZone {

	public CandidatUniqueDansColonne(Grille grille) {
		super(grille);
	}

	@Override
	protected boolean checkPresenceCandidatZone(CaseContext context, int candidat) {
		return grilleService.checkPresenceCandidatColonne(context, candidat);
	}


}
