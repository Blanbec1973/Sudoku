package resolution;

import java.util.Arrays;

import modele.CaseEnCours;
import modele.Grille;
import modele.Modele;
import modele.Utils;

public class PaireConjugueeEnRegion extends MethodeResolution {
	private int x2,y2, candidatAEliminer, xAction, yAction;
	public PaireConjugueeEnRegion(Modele modele, Grille grille) {
		super(modele,grille);
		x2=0;
		y2=0;
	}

	@Override
	public boolean traiteCaseEnCours(boolean goPourChangement) {
		boolean trouve = this.detecteConfiguration();
		
		if (!trouve) return false;
        c1 = Utils.trouveCandidatNumero(grille.getCaseEnCours().getCandidats(), 1);
        c2 = Utils.trouveCandidatNumero(grille.getCaseEnCours().getCandidats(), 2);
		trouve = this.detecteCandidatAEliminer();
	
        if (!trouve) return false;
        
        if (goPourChangement) {
        	this.elimineCandidatCase(candidatAEliminer, xAction,yAction);
        }
        else {
			modele.getControle().demandeHighlightCase(xAction,yAction,
                    grille.getCaseEnCours().construitLibelleCandidats());
        }
        return true;
	}

	private boolean detecteConfiguration() {
		if (grille.getCaseEnCours().getNombreCandidats() != 2) return false;
		for (int abs=CaseEnCours.getxRegion();abs<CaseEnCours.getxRegion()+3;abs++) {
            for (int ord=CaseEnCours.getyRegion();ord<CaseEnCours.getyRegion()+3;ord++) {
                if ((CaseEnCours.getXSearch() != abs || CaseEnCours.getYSearch() != ord) &&
                    grille.getCase(abs, ord).isCaseATrouver() &&
                    Arrays.equals(grille.getCaseEnCours().getCandidatsTabBoolean(),
                    		      grille.getCase(abs, ord).getCandidatsTabBoolean())) {
                    x2 = abs;
                    y2 = ord;
                	return true;
                }
            }
        }
		return false;
	}
	
	private boolean detecteCandidatAEliminer() {
        for (int abs=CaseEnCours.getxRegion();abs<CaseEnCours.getxRegion()+3;abs++) {
            for (int ord=CaseEnCours.getyRegion();ord<CaseEnCours.getyRegion()+3;ord++) {
                if (grille.getCase(abs, ord).isCaseATrouver() &&
                    !Arrays.equals(grille.getCaseEnCours().getCandidatsTabBoolean(),
                    grille.getCase(abs, ord).getCandidatsTabBoolean())) {
            		xAction = abs;
            		yAction = ord;
                	if (grille.getCase(abs, ord).isCandidat(c1) ) {
            			candidatAEliminer = c1;
            			return true;
            		}
            		if (grille.getCase(abs, ord).isCandidat(c2) ) {
            			candidatAEliminer = c2;
            			return true;
            		}
                }
            }
        }
        return false;
	}
}