package resolution;

import modele.grille.CandidatsCase;
import modele.grille.CaseEnCours;
import modele.grille.Grille;
import modele.Modele;
import modele.Utils;

public class PaireCandidats2CasesLigne extends PaireCandidats2Cases {

	public PaireCandidats2CasesLigne(Modele modele, Grille grille) {
		super(modele,grille);
	}

	
	protected boolean traiteCouple(CandidatsCase paireCandidats) {
		for (int i=0;i<9;i++) {
			if (grille.getCase(i, CaseEnCours.getYSearch()).isCaseATrouver() && CaseEnCours.getXSearch()!=i) {
				this.calculIntersectionDeuxCases(paireCandidats, grille.getCase(i, CaseEnCours.getYSearch()).getCandidats());			
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

