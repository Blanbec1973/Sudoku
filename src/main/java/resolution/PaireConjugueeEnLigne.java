package resolution;

import java.util.Arrays;

import model.grille.CaseEnCours;
import model.grille.Grille;
import model.Model;
import utils.Utils;

public class PaireConjugueeEnLigne extends PaireConjuguee {

	public PaireConjugueeEnLigne(Model model, Grille grille) {
		super(model,grille);
	}

	@Override
	public String getSimpleName() {
		return this.getClass().getSimpleName();
	}

	protected boolean detecteConfiguration() {
        for (x2=0;x2<9;x2++) {
            if (x2 != CaseEnCours.getXSearch() &&
                grille.isCaseATrouver(x2, CaseEnCours.getYSearch()) &&
                Arrays.equals(grille.getCandidatsTabBoolean(CaseEnCours.getNumCase()),
                		grille.getCandidatsTabBoolean(x2, CaseEnCours.getYSearch()))) {
            	return true;
            }
        }
		return false;
	}
	
	protected boolean detecteCandidatAEliminer() {
        for (int xAction=0;xAction<9;xAction++) {
        	if (grille.isCaseATrouver(Utils.calculNumCase(xAction, CaseEnCours.getYSearch())) &&
        			xAction!=CaseEnCours.getXSearch() && xAction !=x2) {
        		numCaseAction=Utils.calculNumCase(xAction, CaseEnCours.getYSearch());
        		if (grille.isCandidat(Utils.calculNumCase(xAction, CaseEnCours.getYSearch()), c1) ) {
        			candidatAEliminer = c1;
        			return true;
        		}
        		if (grille.isCandidat(Utils.calculNumCase(xAction, CaseEnCours.getYSearch()), c2) ) {
        			candidatAEliminer = c2;
        			return true;
        		}
            }
        }
		return false;
	}
}
