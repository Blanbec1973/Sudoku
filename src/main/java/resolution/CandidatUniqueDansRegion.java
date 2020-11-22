package resolution;

import modele.Grille;
import modele.Modele;

public class CandidatUniqueDansRegion extends MethodeResolution {

	public CandidatUniqueDansRegion(Modele modele, Grille grille) {
		super(modele, grille);
	}

	@Override
	public boolean detecteSuivant(Grille grille, boolean goPourChangement) {
		boolean trouve = false;
		int i=0;
		int candidat =0;
		while (i < grille.getCasesAtrouver().size() & !trouve) {
			grille.setCaseEnCours(grille.getCasesAtrouver().get(i));
			for (candidat=1;candidat<10;candidat++) {
  	            if (grille.getCaseEnCours().isCandidat(candidat) &&
	                !grille.checkPresenceCandidatRegion(candidat, grille.getxSearch(),grille.getySearch())) {
  	            	trouve = true;
  	            	break;
  	            }
			}   
			i+=1;
		}
		
		if (!trouve) return false;
		if (goPourChangement) this.setValeurCaseEnCours(goPourChangement, candidat);
			else modele.getControle().demandeHighlightCase(grille.getxSearch(),
				  		                                   grille.getySearch(),
						                                   grille.getCaseEnCours().construitLibelleCandidats());
			
		return true;		
	}

}
