package resolution;

import model.grille.CandidatsCase;
import model.grille.CaseEnCours;
import model.grille.Grille;
import utils.Utils;

import java.util.Optional;

public class TripletteCandidatsEnLigne extends MethodeResolution {
	int xAction;
	int yAction;
	
	public TripletteCandidatsEnLigne(Grille grille) {
		super(grille);
		caseTrouvee=false;
	}

	@Override
	public Optional<ResolutionAction> traiteCaseEnCours(boolean goPourChangement) {
		if (grilleService.calculNombreCaseATrouverDansLigne(CaseEnCours.getY())<=3) return Optional.empty();

		boolean trouve = this.detecteConfiguration();   	
        if (!trouve) return Optional.empty();
        
        trouve = this.detecteCandidatAEliminer();
        if (!trouve) return Optional.empty();
        
        yAction = CaseEnCours.getY();
        
        numCaseAction= Grille.calculNumCase(xAction, yAction);
        return Optional.of(new ResolutionAction(numCaseAction, null, candidatAEliminer, this));
	}

	private boolean detecteConfiguration() {
        x2=0;
        x3=0;

        while (x2<9) {
            if (x2!=CaseEnCours.getX() && grille.isCaseATrouver(x2,CaseEnCours.getY())) {
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
		 return (x3!=x2 && x3!=CaseEnCours.getX() && grille.isCaseATrouver(x3,CaseEnCours.getY()) &&
		     this.detecteTripletteEnLigne(CaseEnCours.getY(), x2, x3) && this.detecteCandidatAEliminer()) ;
	}
	
	private boolean detecteTripletteEnLigne(int ySearch, int x2, int x3) {
        CandidatsCase testCandidats = new CandidatsCase();
        testCandidats.setCandidats(Utils.calculOuLogique2Candidats(grille.getCandidatsTabBoolean(CaseEnCours.getNumCase()),
                                                       grille.getCandidatsTabBoolean(x2, ySearch)));
        testCandidats.setCandidats(Utils.calculOuLogique2Candidats(testCandidats.getCandidats(),
                                                       grille.getCandidatsTabBoolean(x3, ySearch)));
        if (testCandidats.getNombreCandidats()>3) {return false;}
        
        c1 = Utils.trouveCandidatNumero(testCandidats, 1);
        c2 = Utils.trouveCandidatNumero(testCandidats, 2);
        c3 = Utils.trouveCandidatNumero(testCandidats, 3);

		return true;
	}

	private boolean detecteCandidatAEliminer() {
		for (int abs = 0 ; abs < 9 ; abs++) {
			if (grille.isCaseATrouver(abs, CaseEnCours.getY()) &&
				abs != CaseEnCours.getX() && abs != x2 && abs != x3) {
				if (grille.isCandidat(abs, CaseEnCours.getY(), c1)) {
					candidatAEliminer = c1;
					xAction = abs;
					return true;
				}
				if (grille.isCandidat(abs, CaseEnCours.getY(), c2)) {
					candidatAEliminer = c2;
					xAction = abs;
					return true;
				}
				if (grille.isCandidat(abs, CaseEnCours.getY(), c3)) {
					candidatAEliminer = c3;
					xAction = abs;
					return true;
				}
			}
		}
		return false;
	}	
	
}

