package resolution;

import java.util.Arrays;

import modele.CaseEnCours;
import modele.Grille;
import modele.Modele;

public class PaireConjugueeEnRegion extends PaireConjuguee {

	public PaireConjugueeEnRegion(Modele modele, Grille grille) {
		super(modele,grille);
	}

	protected boolean detecteConfiguration() {
		if (grille.getCaseEnCours().getNombreCandidats() != 2) return false;
		for (int abs=CaseEnCours.getxRegion();abs<CaseEnCours.getxRegion()+3;abs++) {
            for (int ord=CaseEnCours.getyRegion();ord<CaseEnCours.getyRegion()+3;ord++) {
                if ((CaseEnCours.getXSearch() != abs || CaseEnCours.getYSearch() != ord) &&
                    grille.getCase(abs, ord).isCaseATrouver() &&
                    Arrays.equals(grille.getCaseEnCours().getCandidatsTabBoolean(),
                    		      grille.getCase(abs, ord).getCandidatsTabBoolean())) {
                	return true;
                }
            }
        }
		return false;
	}
	
	protected boolean detecteCandidatAEliminer() {
        for (xAction=CaseEnCours.getxRegion();xAction<CaseEnCours.getxRegion()+3;xAction++) {
            for (yAction=CaseEnCours.getyRegion();yAction<CaseEnCours.getyRegion()+3;yAction++) {
                if (grille.getCase(xAction, yAction).isCaseATrouver() &&
                    !Arrays.equals(grille.getCaseEnCours().getCandidatsTabBoolean(),
                    grille.getCase(xAction, yAction).getCandidatsTabBoolean())) {
                	if (grille.getCase(xAction, yAction).isCandidat(c1) ) {
            			candidatAEliminer = c1;
            			return true;
            		}
            		if (grille.getCase(xAction, yAction).isCandidat(c2) ) {
            			candidatAEliminer = c2;
            			return true;
            		}
                }
            }
        }
        return false;
	}
}
