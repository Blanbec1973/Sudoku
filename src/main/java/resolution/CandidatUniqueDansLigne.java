package resolution;

import model.grille.CaseContext;
import model.grille.Grille;

public class CandidatUniqueDansLigne extends CandidatUniqueDansZone {

	public CandidatUniqueDansLigne(Grille grille) {super(grille);}

	@Override
	protected boolean checkPresenceCandidatZone(CaseContext context, int candidat) {
		return grilleService.checkPresenceCandidatLigne(context, candidat);
	}

}
