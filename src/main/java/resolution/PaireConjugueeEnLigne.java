package resolution;

import java.util.Arrays;

import modele.CaseEnCours;
import modele.Grille;
import modele.Modele;
import modele.Utils;

public class PaireConjugueeEnLigne extends MethodeResolution {

	public PaireConjugueeEnLigne(Modele modele, Grille grille) {
		super(modele,grille);
	}

	@Override
	public boolean traiteCaseEnCours(boolean goPourChangement) {
		if (grille.getCaseEnCours().getNombreCandidats() != 2) return false;
		
		boolean trouve = this.detecteConfiguration();
		if (!trouve) return false;

		c1 = Utils.trouveCandidatNumero(grille.getCaseEnCours().getCandidats(), 1);
        c2 = Utils.trouveCandidatNumero(grille.getCaseEnCours().getCandidats(), 2);
		trouve = this.detecteCandidatAEliminer();
		if (!trouve) return false;
        
        if (goPourChangement) {
        	this.elimineCandidatCase(candidatAEliminer, xAction, CaseEnCours.getYSearch());
        	modele.getControle().demandeRefreshAffichageCase(CaseEnCours.getXSearch(), CaseEnCours.getYSearch());
        }
        else {
			modele.getControle().demandeHighlightCase(xAction, CaseEnCours.getYSearch());
        }
        return true;
	}
	
	private boolean detecteConfiguration() {
        for (x2=0;x2<9;x2++) {
            if (x2 != CaseEnCours.getXSearch() &&
                grille.getCase(x2, CaseEnCours.getYSearch()).isCaseATrouver() && 
                Arrays.equals(grille.getCaseEnCours().getCandidatsTabBoolean(), 
                		grille.getCase(x2, CaseEnCours.getYSearch()).getCandidatsTabBoolean())) {
            	return true;
            }
        }
		return false;
	}
	
	private boolean detecteCandidatAEliminer() {
        for (xAction=0;xAction<9;xAction++) {
        	if (grille.getCase(xAction, CaseEnCours.getYSearch()).isCaseATrouver() &&
        			xAction!=CaseEnCours.getXSearch() && xAction !=x2) {
        		if (grille.getCase(xAction, CaseEnCours.getYSearch()).isCandidat(c1) ) {
        			candidatAEliminer = c1;
        			return true;
        		}
        		if (grille.getCase(xAction, CaseEnCours.getYSearch()).isCandidat(c2) ) {
        			candidatAEliminer = c2;
        			return true;
        		}
            }
        }
		return false;
	}
}
