package resolution;

import model.grille.CandidatsCase;
import model.grille.CaseEnCours;
import model.grille.Grille;
import model.Model;
import utils.Utils;

public class PaireCandidats2CasesColonne extends PaireCandidats2Cases {

	public PaireCandidats2CasesColonne(Model model, Grille grille) {
		super(model,grille);
	}
		
	protected boolean traiteCouple(CandidatsCase paireCandidats) {		
		for (int i=0;i<9;i++) {
			if (grille.getCase(CaseEnCours.getXSearch(),i).isCaseATrouver() && CaseEnCours.getYSearch()!=i) {
				this.calculIntersectionDeuxCases(paireCandidats, grille.getCase(CaseEnCours.getXSearch(), i).getCandidats());
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
