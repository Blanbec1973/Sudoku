package resolution;

import modele.CandidatsCase;
import modele.CaseEnCours;
import modele.Grille;
import modele.Modele;
import modele.Utils;

public class PaireCandidats2CasesColonne extends PaireCandidats2Cases {

	public PaireCandidats2CasesColonne(Modele modele, Grille grille) {
		super(modele,grille);
		c1=0;
		c2=0;
	}
		
	protected boolean traiteCouple(CandidatsCase paireCandidats) {
		int nb2inter = 0;
		int nb1inter = 0;
		
		for (int i=0;i<9;i++) {
			if (grille.getCase(CaseEnCours.getXSearch(),i).isCaseATrouver() &&
				CaseEnCours.getYSearch()!=i) {
				
				switch (Utils.calculNombreCandidats(
						Utils.calculEtLogique2Candidats(paireCandidats.getCandidats(), 
								grille.getCase(CaseEnCours.getXSearch(), i).getCandidatsTabBoolean()))) {
					case 1 -> {nb1inter+=1;}
					case 2 -> {nb2inter+=1;}
				}				
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
