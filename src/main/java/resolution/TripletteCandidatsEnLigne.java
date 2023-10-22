package resolution;

import model.grille.CandidatsCase;
import model.grille.CaseEnCours;
import model.grille.Grille;
import model.Model;
import utils.Utils;

public class TripletteCandidatsEnLigne extends MethodeResolution {
	int xAction;
	int yAction;
	
	public TripletteCandidatsEnLigne(Model model, Grille grille) {
		super(model,grille);
		caseTrouvee=false;
	}

	@Override
	public boolean traiteCaseEnCours(boolean goPourChangement) {
		if (Utils.calculNombreCaseATrouverDansLigne(this.grille, CaseEnCours.getYSearch())<=3) return false;

		boolean trouve = this.detecteConfiguration();   	
        if (!trouve) return false;
        
        trouve = this.detecteCandidatAEliminer();
        if (!trouve) return false;
        
        yAction = CaseEnCours.getYSearch();
        
        numCaseAction=Utils.calculNumCase(xAction, yAction);
        return true;
	}
	
	private boolean detecteConfiguration() {
        x2=0;
        x3=0;

        while (x2<9) {
            if (x2!=CaseEnCours.getXSearch() && grille.getCase(x2,CaseEnCours.getYSearch()).isCaseATrouver()) {
                x3=x2;
                while (x3<9) {
                    if (testTriplette()) {return true;}
                    x3+=1;
                }
            }
            x2+=1;
        }
        return false;
	}
	
	private boolean testTriplette() {
		 return (x3!=x2 && x3!=CaseEnCours.getXSearch() && grille.getCase(x3,CaseEnCours.getYSearch()).isCaseATrouver() &&
		     this.detecteTripletteEnLigne(CaseEnCours.getYSearch(), x2, x3) && this.detecteCandidatAEliminer()) ;
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

