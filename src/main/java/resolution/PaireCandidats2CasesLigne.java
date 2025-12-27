package resolution;

import model.grille.CandidatUtils;
import model.grille.CandidatsCase;
import model.grille.CaseContext;
import model.grille.Grille;
import utils.Utils;

public class PaireCandidats2CasesLigne extends PaireCandidats2Cases {

	public PaireCandidats2CasesLigne(Grille grille) {
		super(grille);
	}

	protected boolean traiteCouple(CaseContext context, CandidatsCase paireCandidats) {
		for (int i=0;i<9;i++) {
			if (grille.isCaseATrouver(i, context.getY()) && context.getX()!=i) {
				this.calculIntersectionDeuxCases(paireCandidats, grille.getCandidats(Utils.calculNumCase(i, context.getY())));
			}
		}
		
		if (nb2inter == 1 && nb1inter == 0) {
			c1 = CandidatUtils.trouveCandidatNumero(paireCandidats, 1);
			c2 = CandidatUtils.trouveCandidatNumero(paireCandidats, 2);
			return true;
		}
		
		return false;	
	}
	
}

