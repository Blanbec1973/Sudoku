package resolution;

import modele.Grille;
import modele.Modele;

public class AbsenceCandidatEnColonneDansLesAutresRegions extends MethodeResolution {

	public AbsenceCandidatEnColonneDansLesAutresRegions(Modele modele, Grille grille) {
		super(modele,grille);
	}

	@Override
	public boolean detecteSuivant(boolean goPourChangement) {
		boolean trouve = false;
		int i=0;
		while (i < grille.getCasesAtrouver().size() & !trouve) {
			grille.setCaseEnCours(grille.getCasesAtrouver().get(i));
			if (this.traiteCase(goPourChangement)) trouve = true;
			i+=1;
		}
		
		if (!trouve) return false;
        
		

		
		
		return false;
	}

	private boolean traiteCase(boolean goPourChangement) {	
		
	  boolean candidatTrouve;		
      for (int candidat=1;candidat<10;candidat++) {
          if (grille.getCaseEnCours().isCandidat(candidat)) {
              candidatTrouve=false;
              for (int i=0;i<9;i++) {
                  if (grille.getCaseEnCours().getRegion() != grille.getCase(grille.getxSearch(),i).getRegion() &&
                	  grille.getCase(grille.getxSearch(), i).nEstPasCaseInitiale() &&
                	  grille.getCase(grille.getxSearch(), i).nEstPasCaseTrouvee() &&
                	  grille.getCase(grille.getxSearch(),i).isCandidat(candidat)) {
                      candidatTrouve = true;
                  }
              }    
              if (!candidatTrouve) {
            	  if (grille.CheckPresenceCandidatRegionSaufColonne(candidat,grille.getxSearch())) {
            		  if (goPourChangement) {
            			  grille.elimineCandidatRegionSaufColonne(candidat, grille.getxSearch());
            			  
            			  return true;
            		  }
            		  else {
            			  return true;
            		  }
            	  }
//                  javax.swing.JOptionPane.showMessageDialog(null,"Candidat "
//                                                                 +String.valueOf(candidat)
//                                                                 + " dans colonne "
//                                                                 +String.valueOf(xSearch+1)
//                                                                 +"uniquement dans région"
//                                                                 +String.valueOf(this.getCaseEnCours().getRegion())); 
//                  this.elimineCandidatRegionSaufColonne(candidat, xSearch);
              }        
          }    
      }
      return false;
	}

}
