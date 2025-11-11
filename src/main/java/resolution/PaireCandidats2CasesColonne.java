package resolution;

import model.grille.CandidatsCase;
import model.grille.CaseContext;
import model.grille.Grille;
import utils.Utils;

public class PaireCandidats2CasesColonne extends PaireCandidats2Cases {

	public PaireCandidats2CasesColonne(Grille grille) {
		super(grille);
	}

	protected boolean traiteCouple(CaseContext context, CandidatsCase paireCandidats) {
		for (int i=0;i<9;i++) {
			if (grille.isCaseATrouver(context.getX(),i) && context.getY()!=i) {
				this.calculIntersectionDeuxCases(paireCandidats, grille.getCandidats(Grille.calculNumCase(context.getX(), i)));
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
