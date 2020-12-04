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
		boolean trouve = false;
		int indBalayage =0;
		int candidatAEliminer =0;
		int x2;
		if (grille.getCaseEnCours().getNombreCandidats() != 2) return false;
		
        for (indBalayage=0;indBalayage<9;indBalayage++) {
            if (indBalayage != CaseEnCours.getXSearch() &&
                grille.getCase(indBalayage, CaseEnCours.getYSearch()).isCaseATrouver() && 
                Arrays.equals(grille.getCaseEnCours().getCandidatsTabBoolean(), 
                		grille.getCase(indBalayage, CaseEnCours.getYSearch()).getCandidatsTabBoolean())) {
            	trouve = true;
            	break;
            }
        }    	
        
        if (trouve) trouve = false ; else return false;
        
        c1 = Utils.trouveCandidatNumero(grille.getCaseEnCours().getCandidats(), 1);
        c2 = Utils.trouveCandidatNumero(grille.getCaseEnCours().getCandidats(), 2);
        
        // Recherche s'il y a un candidat à éliminer :
        for (x2=0;x2<9;x2++) {
        	if (grille.getCase(x2, CaseEnCours.getYSearch()).isCaseATrouver() &&
                x2!=CaseEnCours.getXSearch() && x2 !=indBalayage) {
        		if (grille.getCase(x2, CaseEnCours.getYSearch()).isCandidat(c1) ) {
        			candidatAEliminer = c1;
        			trouve = true;
        			break;
        		}
        		if (grille.getCase(x2, CaseEnCours.getYSearch()).isCandidat(c2) ) {
        			candidatAEliminer = c2;
        			trouve = true;
        			break;
        		}
            }
        }
    	
        if (!trouve) return false;
        
        if (goPourChangement) {
        	this.elimineCandidatCase(candidatAEliminer, x2, CaseEnCours.getYSearch());
        }
        else {
			modele.getControle().demandeHighlightCase(x2,
                    CaseEnCours.getYSearch());
        }
        return true;
	}
}
