package resolution;

import java.util.Arrays;

import model.grille.CaseEnCours;
import model.grille.Grille;
import model.Model;
import utils.Utils;

public class PaireConjugueeEnColonne extends PaireConjuguee {

	public PaireConjugueeEnColonne(Model model, Grille grille) {
		super(model,grille);
	}

	@Override
	public String getSimpleName() {
		return this.getClass().getSimpleName();
	}

	protected boolean detecteConfiguration() {
		for (y2=0;y2<9;y2++) {
            if (y2 != CaseEnCours.getYSearch() &&
                grille.isCaseATrouver(CaseEnCours.getXSearch(),y2) &&
                Arrays.equals(grille.getCandidatsTabBoolean(CaseEnCours.getNumCase()),
                		grille.getCandidatsTabBoolean(CaseEnCours.getXSearch(),y2))) {
            	return true;
            }
        }
		return false;
	}
	
	protected boolean detecteCandidatAEliminer() {
		for (int yAction=0;yAction<9;yAction++) {
        	if (grille.isCaseATrouver(CaseEnCours.getXSearch(),yAction) &&
        			yAction!=CaseEnCours.getYSearch() && yAction !=y2) {
        		numCaseAction=Utils.calculNumCase(CaseEnCours.getXSearch(), yAction);
        		if (grille.isCandidat(CaseEnCours.getXSearch(),yAction, c1) ) {
        			candidatAEliminer = c1;
        			return true;
        		}
        		if (grille.isCandidat(CaseEnCours.getXSearch(),yAction, c2) ) {
        			candidatAEliminer = c2;
        			return true;
        		}
            }
        }
		return false;
	}	
}
