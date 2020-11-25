package resolution;

import modele.CaseEnCours;
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
			CaseEnCours.setCaseEnCours(grille.getCasesAtrouver().get(i));
			if (this.traiteCase(goPourChangement)) trouve = true;
			i+=1;
		}
		
		if (!trouve) return false;
        
		return true;
	}

	private boolean traiteCase(boolean goPourChangement) {	
		
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
            	  if (grille.CheckPresenceCandidatRegionSaufColonne(candidat,CaseEnCours.getXSearch())) {
            		  if (goPourChangement) {
            			  grille.elimineCandidatRegionSaufColonne(candidat, CaseEnCours.getXSearch());
            			  
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
