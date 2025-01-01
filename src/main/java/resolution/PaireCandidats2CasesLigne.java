package resolution;

import model.grille.CandidatsCase;
import model.grille.CaseEnCours;
import model.grille.Grille;
import utils.Utils;

public class PaireCandidats2CasesLigne extends PaireCandidats2Cases {

	public PaireCandidats2CasesLigne(Grille grille) {
		super(grille);
	}

	protected boolean traiteCouple(CandidatsCase paireCandidats) {
		for (int i=0;i<9;i++) {
			if (grille.isCaseATrouver(i, CaseEnCours.getY()) && CaseEnCours.getX()!=i) {
				this.calculIntersectionDeuxCases(paireCandidats, grille.getCandidats(Grille.calculNumCase(i, CaseEnCours.getY())));
			}
		}
		
		if (nb2inter == 1 && nb1inter == 0) {
			c1 = Utils.trouveCandidatNumero(paireCandidats, 1);
			c2 = Utils.trouveCandidatNumero(paireCandidats, 2);
			return true;
		}
		
		return false;	
	}
	
}

