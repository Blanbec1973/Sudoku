package resolution;

import modele.CaseEnCours;
import modele.Grille;
import modele.Modele;

public class AbsenceCandidatEnLigneDansLesAutresRegions extends AbsenceCandidatDansLesAutresRegions {

	public AbsenceCandidatEnLigneDansLesAutresRegions(Modele modele, Grille grille) {
		super(modele,grille);
	}

	protected boolean detecteConfiguration() {
		boolean candidatNonTrouve;
		for (int candidat=1;candidat<10;candidat++) {
			if (grille.getCaseEnCours().isCandidat(candidat)) {
				candidatNonTrouve = true;
				candidatAEliminer = candidat;
				for (int i=0;i<9;i++) {
					if (grille.getCaseEnCours().getRegion() != grille.getCase(i, CaseEnCours.getYSearch()).getRegion() &&
						grille.getCase(i, CaseEnCours.getYSearch()).isCaseATrouver() &&
                	    grille.getCase(i, CaseEnCours.getYSearch()).isCandidat(candidat)) {
						candidatNonTrouve = false;
					}
				}
				if (candidatNonTrouve && this.detecteCandidatAEliminer())					
					return true;
			}
		}
		return false;
	}	
	
	protected boolean detecteCandidatAEliminer() {
        for (int abs=CaseEnCours.getxRegion();abs<CaseEnCours.getxRegion()+3;abs++) {
            for (int ord=CaseEnCours.getyRegion();ord<CaseEnCours.getyRegion()+3;ord++) {
                if (ord!= CaseEnCours.getYSearch() && 
                	grille.getCase(abs, ord).isCaseATrouver() &&
                    grille.getCase(abs, ord).isCandidat(candidatAEliminer)) {
                	xAction = abs;
                	yAction = ord;
                	return true;
                }  
            }
        }  
		return false;
	}
}
