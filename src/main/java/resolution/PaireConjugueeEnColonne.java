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
            if (y2 != CaseEnCours.getY() &&
                grille.isCaseATrouver(CaseEnCours.getX(),y2) &&
                Arrays.equals(grille.getCandidatsTabBoolean(CaseEnCours.getNumCase()),
                		grille.getCandidatsTabBoolean(CaseEnCours.getX(),y2))) {
            	return true;
            }
        }
		return false;
	}
	
	protected boolean detecteCandidatAEliminer() {
		for (int yAction=0;yAction<9;yAction++) {
        	if (grille.isCaseATrouver(CaseEnCours.getX(),yAction) &&
        			yAction!=CaseEnCours.getY() && yAction !=y2) {
        		numCaseAction=grille.calculNumCase(CaseEnCours.getX(), yAction);
        		if (grille.isCandidat(CaseEnCours.getX(),yAction, c1) ) {
        			candidatAEliminer = c1;
        			return true;
        		}
        		if (grille.isCandidat(CaseEnCours.getX(),yAction, c2) ) {
        			candidatAEliminer = c2;
        			return true;
        		}
            }
        }
		return false;
	}	
}
