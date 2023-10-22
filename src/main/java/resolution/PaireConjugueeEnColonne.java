package resolution;

import java.util.Arrays;

import model.grille.CaseEnCours;
import model.grille.Grille;
import model.Model;
import model.Utils;

public class PaireConjugueeEnColonne extends PaireConjuguee {

	public PaireConjugueeEnColonne(Model model, Grille grille) {
		super(model,grille);
	}
	
	protected boolean detecteConfiguration() {
		for (y2=0;y2<9;y2++) {
            if (y2 != CaseEnCours.getYSearch() &&
                grille.getCase( CaseEnCours.getXSearch(),y2).isCaseATrouver() && 
                Arrays.equals(grille.getCaseEnCours().getCandidatsTabBoolean(), 
                		grille.getCase(CaseEnCours.getXSearch(),y2).getCandidatsTabBoolean())) {
            	return true;
            }
        }
		return false;
	}
	
	protected boolean detecteCandidatAEliminer() {
		for (int yAction=0;yAction<9;yAction++) {
        	if (grille.getCase(CaseEnCours.getXSearch(),yAction).isCaseATrouver() &&
        			yAction!=CaseEnCours.getYSearch() && yAction !=y2) {
        		numCaseAction=Utils.calculNumCase(CaseEnCours.getXSearch(), yAction);
        		if (grille.getCase(CaseEnCours.getXSearch(),yAction).isCandidat(c1) ) {
        			candidatAEliminer = c1;
        			return true;
        		}
        		if (grille.getCase(CaseEnCours.getXSearch(),yAction).isCandidat(c2) ) {
        			candidatAEliminer = c2;
        			return true;
        		}
            }
        }
		return false;
	}	
}
