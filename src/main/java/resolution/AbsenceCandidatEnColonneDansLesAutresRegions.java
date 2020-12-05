package resolution;

import modele.CaseEnCours;
import modele.Grille;
import modele.Modele;

public class AbsenceCandidatEnColonneDansLesAutresRegions extends MethodeResolution {

	public AbsenceCandidatEnColonneDansLesAutresRegions(Modele modele, Grille grille) {
		super(modele,grille);
	}


	public boolean traiteCaseEnCours(boolean goPourChangement) {	
		boolean changementAFaire = this.detecteConfiguration();
		
		if (changementAFaire) {
			if (goPourChangement) {
           		this.elimineCandidatCase(candidatAEliminer, xAction, yAction);
           		modele.getControle().demandeRefreshAffichageCase(CaseEnCours.getXSearch(), CaseEnCours.getYSearch());
            	return true;
            }
            else {
    			modele.getControle().demandeHighlightCase(xAction,yAction);
            	return true;}
		}
		else return false ;
	}
	
	private boolean detecteConfiguration() {
		boolean candidatNonTrouve;
		for (int candidat=1;candidat<10;candidat++) {
			if (grille.getCaseEnCours().isCandidat(candidat)) {
				candidatNonTrouve = true;
				candidatAEliminer = candidat;
				for (int i=0;i<9;i++) {
					if (grille.getCaseEnCours().getRegion() != grille.getCase(CaseEnCours.getXSearch(),i).getRegion() &&
						grille.getCase(CaseEnCours.getXSearch(),i).isCaseATrouver() &&
                	    grille.getCase(CaseEnCours.getXSearch(),i).isCandidat(candidat)) {
						candidatNonTrouve = false;
					}
				}
				
				if (candidatNonTrouve && this.detecteCandidatAEliminer())					
					return true;
				else 
					continue;	
			}
		}
		return false;
	}	
	
	private boolean detecteCandidatAEliminer() {
        for (int abs=CaseEnCours.getxRegion();abs<CaseEnCours.getxRegion()+3;abs++) {
            for (int ord=CaseEnCours.getyRegion();ord<CaseEnCours.getyRegion()+3;ord++) {
                if (abs!= CaseEnCours.getXSearch() && 
                	grille.getCase(abs, ord).isCaseATrouver() &&
                    grille.getCase(abs, ord).isCandidat(candidatAEliminer)) {
                	xAction = abs;
                	yAction = ord;
                	return true;
                }
                else
                	continue;     
            }
        }  
		return false;
	}
}
