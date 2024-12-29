package resolution;

import model.Model;
import model.grille.CaseEnCours;
import model.grille.Grille;
import utils.Utils;

import java.util.Arrays;

public class PaireConjugueeEnRegion extends PaireConjuguee {

	public PaireConjugueeEnRegion(Model model, Grille grille) {
		super(model,grille);
	}

	@Override
	public String getSimpleName() {
		return this.getClass().getSimpleName();
	}

	protected boolean detecteConfiguration() {
		if (grille.getNombreCandidats(CaseEnCours.getNumCase()) != 2) return false;
		for (int abs=CaseEnCours.getxRegion();abs<CaseEnCours.getxRegion()+3;abs++) {
            for (int ord=CaseEnCours.getyRegion();ord<CaseEnCours.getyRegion()+3;ord++) {
                if ((CaseEnCours.getX() != abs || CaseEnCours.getY() != ord) &&
                    grille.isCaseATrouver(abs, ord) &&
                    Arrays.equals(grille.getCandidatsTabBoolean(CaseEnCours.getNumCase()),
                    		      grille.getCandidatsTabBoolean(abs, ord))) {
                	return true;
                }
            }
        }
		return false;
	}
	
	protected boolean detecteCandidatAEliminer() {
        for (int xAction=CaseEnCours.getxRegion();xAction<CaseEnCours.getxRegion()+3;xAction++) {
            for (int yAction=CaseEnCours.getyRegion();yAction<CaseEnCours.getyRegion()+3;yAction++) {
                if (grille.isCaseATrouver(grille.calculNumCase(xAction, yAction)) &&
                    !Arrays.equals(grille.getCandidatsTabBoolean(CaseEnCours.getNumCase()),
                    grille.getCandidatsTabBoolean(xAction, yAction))) {
                	numCaseAction=grille.calculNumCase(xAction, yAction);
                	if (grille.isCandidat(grille.calculNumCase(xAction, yAction), c1) ) {
            			candidatAEliminer = c1;
            			return true;
            		}
            		if (grille.isCandidat(grille.calculNumCase(xAction, yAction), c2) ) {
            			candidatAEliminer = c2;
            			return true;
            		}
                }
            }
        }
        return false;
	}
}
