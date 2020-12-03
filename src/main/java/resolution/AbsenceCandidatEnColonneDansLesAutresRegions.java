package resolution;

import modele.CaseEnCours;
import modele.Grille;
import modele.Modele;

public class AbsenceCandidatEnColonneDansLesAutresRegions extends MethodeResolution {

	public AbsenceCandidatEnColonneDansLesAutresRegions(Modele modele, Grille grille) {
		super(modele,grille);
	}


	public boolean traiteCaseEnCours(boolean goPourChangement) {	
		boolean candidatTrouve;		
		for (int candidat=1;candidat<10;candidat++) {
			if (grille.getCaseEnCours().isCandidat(candidat)) {
				candidatTrouve=false;
				for (int i=0;i<9;i++) {
					if (grille.getCaseEnCours().getRegion() != grille.getCase(CaseEnCours.getXSearch(),i).getRegion() &&
						grille.getCase(CaseEnCours.getXSearch(), i).nEstPasCaseInitiale() &&
                	    grille.getCase(CaseEnCours.getXSearch(), i).nEstPasCaseTrouvee() &&
                	    grille.getCase(CaseEnCours.getXSearch(),i).isCandidat(candidat)) {
						candidatTrouve = true;
					}
				}    
		if (!candidatTrouve) {
        	  if (grille.checkPresenceCandidatRegionSaufColonne(candidat,CaseEnCours.getXSearch())) {
           		  if (goPourChangement) {
           			  grille.elimineCandidatRegionSaufColonne(candidat, CaseEnCours.getXSearch());   			  
            			  return true;
            		  }
            		  else {
            			  return true;
            		  }
            	  }

              }        
          }    
      }
      return false;
	}

}
