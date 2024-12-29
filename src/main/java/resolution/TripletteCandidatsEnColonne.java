package resolution;

import model.Model;
import model.grille.CandidatsCase;
import model.grille.CaseEnCours;
import model.grille.Grille;
import utils.Utils;

public class TripletteCandidatsEnColonne extends MethodeResolution {
	int xAction;
	int yAction;

	public TripletteCandidatsEnColonne(Model model, Grille grille) {
		super(model,grille);
		caseTrouvee=false;
	}

	@Override
	public boolean traiteCaseEnCours(boolean goPourChangement) {
		if (grille.calculNombreCaseATrouverDansColonne(CaseEnCours.getX())<=3) return false;

		boolean trouve = this.detecteConfiguration();   	
        if (!trouve) return false;
        
        trouve = this.detecteCandidatAEliminer();
        if (!trouve) return false;
        
        xAction = CaseEnCours.getX();
        
        numCaseAction=grille.calculNumCase(xAction, yAction);
        return true;
	}

	@Override
	public String getSimpleName() {
		return this.getClass().getSimpleName();
	}

	private boolean detecteConfiguration() {
        y2=0;
        y3=0;

        while (y2<9) {
            if (y2!=CaseEnCours.getY() && grille.isCaseATrouver(CaseEnCours.getX(),y2)) {
                y3=y2;
                while (y3<9) {
                    if (testTriplette()) {return true;}
                    y3+=1;
                }
            }
            y2+=1;
        }
        return false;
	}
	
	private boolean testTriplette() {
		 return (y3!=y2 && y3!=CaseEnCours.getY() && grille.isCaseATrouver(CaseEnCours.getX(),y3) &&
		     this.detecteTripletteEnLigne(CaseEnCours.getX(), y2, y3) && this.detecteCandidatAEliminer()) ;
	}
	
	private boolean detecteTripletteEnLigne(int xSearch, int y2, int y3) {
        CandidatsCase testCandidats = new CandidatsCase();
        testCandidats.setCandidats(Utils.calculOuLogique2Candidats(grille.getCandidatsTabBoolean(CaseEnCours.getNumCase()),
                                                       grille.getCandidatsTabBoolean(xSearch,y2)));
        testCandidats.setCandidats(Utils.calculOuLogique2Candidats(testCandidats.getCandidats(),
                                                       grille.getCandidatsTabBoolean(xSearch,y3)));
        if (testCandidats.getNombreCandidats()>3) {return false;}
        
        c1 = Utils.trouveCandidatNumero(testCandidats, 1);
        c2 = Utils.trouveCandidatNumero(testCandidats, 2);
        c3 = Utils.trouveCandidatNumero(testCandidats, 3);

		return true;
	}

	private boolean detecteCandidatAEliminer() {
		for (int ord = 0 ; ord < 9 ; ord++) {
			if (grille.isCaseATrouver(CaseEnCours.getX(), ord) &&
				ord != CaseEnCours.getY() && ord != y2 && ord != y3) {
				if (grille.isCandidat(CaseEnCours.getX(),ord, c1)) {
					candidatAEliminer = c1;
					yAction = ord;
					return true;
				}
				if (grille.isCandidat(CaseEnCours.getX(), ord, c2)) {
					candidatAEliminer = c2;
					yAction = ord;
					return true;
				}
				if (grille.isCandidat(CaseEnCours.getX(), ord, c3)) {
					candidatAEliminer = c3;
					yAction = ord;
					return true;
				}
			}
		}
		return false;
	}	
	
}

