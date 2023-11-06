package resolution;

import model.Model;
import model.grille.CandidatsCase;
import model.grille.CaseEnCours;
import model.grille.Grille;
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

	@Override
	public String getSimpleName() {
		return this.getClass().getSimpleName();
	}

	private boolean detecteConfiguration() {
        x2=0;
        x3=0;

        while (x2<9) {
            if (x2!=CaseEnCours.getXSearch() && grille.isCaseATrouver(Utils.calculNumCase(x2,CaseEnCours.getYSearch()))) {
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
		 return (x3!=x2 && x3!=CaseEnCours.getXSearch() && grille.isCaseATrouver(Utils.calculNumCase(x3,CaseEnCours.getYSearch())) &&
		     this.detecteTripletteEnLigne(CaseEnCours.getYSearch(), x2, x3) && this.detecteCandidatAEliminer()) ;
	}
	
	private boolean detecteTripletteEnLigne(int ySearch, int x2, int x3) {
        CandidatsCase testCandidats = new CandidatsCase();
        testCandidats.setCandidats(Utils.calculOuLogique2Candidats(grille.getCandidatsTabBoolean(CaseEnCours.getNumCase()),
                                                       grille.getCandidatsTabBoolean(Utils.calculNumCase(x2, ySearch))));
        testCandidats.setCandidats(Utils.calculOuLogique2Candidats(testCandidats.getCandidats(),
                                                       grille.getCandidatsTabBoolean(Utils.calculNumCase(x3, ySearch))));
        if (testCandidats.getNombreCandidats()>3) {return false;}
        
        c1 = Utils.trouveCandidatNumero(testCandidats, 1);
        c2 = Utils.trouveCandidatNumero(testCandidats, 2);
        c3 = Utils.trouveCandidatNumero(testCandidats, 3);

		return true;
	}

	private boolean detecteCandidatAEliminer() {
		for (int abs = 0 ; abs < 9 ; abs++) {
			if (grille.isCaseATrouver(Utils.calculNumCase(abs, CaseEnCours.getYSearch())) &&
				abs != CaseEnCours.getXSearch() && abs != x2 && abs != x3) {
				if (grille.isCandidat(Utils.calculNumCase(abs, CaseEnCours.getYSearch()), c1)) {
					candidatAEliminer = c1;
					xAction = abs;
					return true;
				}
				if (grille.isCandidat(Utils.calculNumCase(abs, CaseEnCours.getYSearch()), c2)) {
					candidatAEliminer = c2;
					xAction = abs;
					return true;
				}
				if (grille.isCandidat(Utils.calculNumCase(abs, CaseEnCours.getYSearch()), c3)) {
					candidatAEliminer = c3;
					xAction = abs;
					return true;
				}
			}
		}
		return false;
	}	
	
}

