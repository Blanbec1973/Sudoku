package resolution;

import java.util.Arrays;

import modele.CaseEnCours;
import modele.Grille;
import modele.Modele;
import modele.Utils;

public class PaireConjugueeEnColonne extends MethodeResolution {

	public PaireConjugueeEnColonne(Modele modele, Grille grille) {
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
        	this.elimineCandidatCase(candidatAEliminer, CaseEnCours.getXSearch(),yAction);
        	modele.getControle().demandeRefreshAffichageCase(CaseEnCours.getXSearch(), CaseEnCours.getYSearch());
        }
        else {
			modele.getControle().demandeHighlightCase(CaseEnCours.getXSearch(), yAction);
        }
        return true;
	}
	
	private boolean detecteConfiguration() {
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
	
	private boolean detecteCandidatAEliminer() {
		for (yAction=0;yAction<9;yAction++) {
        	if (grille.getCase(CaseEnCours.getXSearch(),yAction).isCaseATrouver() &&
        			yAction!=CaseEnCours.getYSearch() && yAction !=y2) {
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
