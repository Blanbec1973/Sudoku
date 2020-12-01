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
		boolean trouve = false;
		int indBalayage =0;
		int candidatAEliminer =0, y2;
		if (grille.getCaseEnCours().getNombreCandidats() != 2) return false;
		
        for (indBalayage=0;indBalayage<9;indBalayage++) {
            if (indBalayage != CaseEnCours.getYSearch() &&
                grille.getCase( CaseEnCours.getXSearch(),indBalayage).isCaseATrouver() && 
                Arrays.equals(grille.getCaseEnCours().getCandidatsTabBoolean(), 
                		grille.getCase(CaseEnCours.getXSearch(),indBalayage).getCandidatsTabBoolean())) {
            	trouve = true;
            	break;
            }
        }    	
        
        if (trouve) trouve = false ; else return false;
        c1 = Utils.trouveCandidatNumero(grille.getCaseEnCours().getCandidats(), 1);
        c2 = Utils.trouveCandidatNumero(grille.getCaseEnCours().getCandidats(), 2);
        
        
        // Recherche s'il y a un candidat à éliminer :
        for (y2=0;y2<9;y2++) {
        	if (grille.getCase(CaseEnCours.getXSearch(),y2).isCaseATrouver() &&
                y2!=CaseEnCours.getYSearch() && y2 !=indBalayage) {
        		if (grille.getCase(CaseEnCours.getXSearch(),y2).isCandidat(c1) ) {
        			candidatAEliminer = c1;
        			trouve = true;
        			break;
        		}
        		if (grille.getCase(CaseEnCours.getXSearch(),y2).isCandidat(c2) ) {
        			candidatAEliminer = c2;
        			trouve = true;
        			break;
        		}
            }
        }
    	
        if (!trouve) return false;
        
        if (goPourChangement) {
        	this.elimineCandidatCase(candidatAEliminer, CaseEnCours.getXSearch(),y2);
        }
        else {
			modele.getControle().demandeHighlightCase(CaseEnCours.getXSearch(), y2,
                    grille.getCaseEnCours().construitLibelleCandidats());
        }
        return true;
	}
}
