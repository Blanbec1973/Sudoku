package resolution;

import modele.grille.CandidatsCase;
import modele.grille.CaseEnCours;
import modele.grille.Grille;
import modele.Modele;
import modele.Utils;

public class PaireCandidats2CasesColonne extends PaireCandidats2Cases {

	public PaireCandidats2CasesColonne(Modele modele, Grille grille) {
		super(modele,grille);
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
