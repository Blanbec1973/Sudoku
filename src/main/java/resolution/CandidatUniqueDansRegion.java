package resolution;

import model.grille.CaseContext;
import model.grille.Grille;

public class CandidatUniqueDansRegion extends CandidatUniqueDansZone {

	public CandidatUniqueDansRegion(Grille grille) {
		super(grille);
	}

	@Override
	protected boolean checkPresenceCandidatZone(CaseContext context, int candidat) {
		return grilleService.checkPresenceCandidatRegion(context, candidat);
	}

}
