package resolution;

import modele.CandidatsCase;
import modele.CaseEnCours;
import modele.Grille;
import modele.Modele;
import modele.Utils;

public class TripletteCandidatsEnLigne extends MethodeResolution {

	public TripletteCandidatsEnLigne(Modele modele, Grille grille) {
		super(modele,grille);
	}

	@Override
	public boolean traiteCaseEnCours(boolean goPourChangement) {
		// On ne rentre dans cette méthode que si au moins 3 cases à trouver dans la ligne :
		if (Utils.calculNombreCaseATrouverDansLigne(this.grille, CaseEnCours.getYSearch())<=3) return false;

		boolean trouve = this.detecteConfiguration();   	
        if (!trouve) return false;
        
        c1 = Utils.trouveCandidatNumero(grille.getCaseEnCours().getCandidats(), 1);
        c2 = Utils.trouveCandidatNumero(grille.getCaseEnCours().getCandidats(), 2);
        trouve = this.detecteCandidatAEliminer();
        if (!trouve) return false;
        
        yAction = CaseEnCours.getYSearch();
        
        if (goPourChangement) {
        	this.elimineCandidatCase(candidatAEliminer, xAction ,yAction);
        	modele.getControle().demandeRefreshAffichageCase(xAction, yAction);
        }
        else {
			modele.getControle().demandeHighlightCase(xAction, yAction);
        }
        return true;
	}
	
	private boolean detecteConfiguration() {
        x2=0;
        x3=0;

        while (x2<9) {
            if (x2!=CaseEnCours.getXSearch() && grille.getCase(x2,CaseEnCours.getYSearch()).isCaseATrouver()) {
                x3=x2;
                while (x3<9) {
                    if (x3!=x2 && x3!=CaseEnCours.getXSearch() && grille.getCase(x3,CaseEnCours.getYSearch()).isCaseATrouver()) {
                        if (this.detecteTripletteEnLigne(CaseEnCours.getYSearch(), x2, x3) &&
                        	this.detecteCandidatAEliminer())
                        	return true;
                    }
                    x3+=1;
                }
            }
            x2+=1;
        }
        return false;
	}
	
	private boolean detecteTripletteEnLigne(int ySearch, int x2, int x3) {
        CandidatsCase testCandidats = new CandidatsCase();
        testCandidats.setCandidats(Utils.calculOuLogique2Candidats(grille.getCaseEnCours().getCandidatsTabBoolean(),
                                                       grille.getCase(x2, ySearch).getCandidatsTabBoolean()));
        testCandidats.setCandidats(Utils.calculOuLogique2Candidats(testCandidats.getCandidats(),
                                                       grille.getCase(x3, ySearch).getCandidatsTabBoolean()));
        if (testCandidats.getNombreCandidats()>3) {return false;}
        
        c1 = Utils.trouveCandidatNumero(testCandidats, 1);
        c2 = Utils.trouveCandidatNumero(testCandidats, 2);
        c3 = Utils.trouveCandidatNumero(testCandidats, 3);

		return true;
	}

	private boolean detecteCandidatAEliminer() {
		for (int abs = 0 ; abs < 9 ; abs++) {
			if (grille.getCase(abs, CaseEnCours.getYSearch()).isCaseATrouver() &&
				abs != CaseEnCours.getXSearch() && abs != x2 && abs != x3) {
				if (grille.getCase(abs, CaseEnCours.getYSearch()).isCandidat(c1)) {
					candidatAEliminer = c1;
					xAction = abs;
					return true;
				}
				if (grille.getCase(abs, CaseEnCours.getYSearch()).isCandidat(c2)) {
					candidatAEliminer = c2;
					xAction = abs;
					return true;
				}
				if (grille.getCase(abs, CaseEnCours.getYSearch()).isCandidat(c3)) {
					candidatAEliminer = c3;
					xAction = abs;
					return true;
				}
			}
		}
		return false;
	}	
	
}

