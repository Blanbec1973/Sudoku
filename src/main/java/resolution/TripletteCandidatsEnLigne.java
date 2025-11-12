package resolution;

import model.grille.CandidatsCase;
import model.grille.CaseContext;
import model.grille.Grille;
import utils.Utils;

import java.util.Optional;

public class TripletteCandidatsEnLigne extends MethodeResolution {
	int xAction;
	int yAction;
	
	public TripletteCandidatsEnLigne(Grille grille) {
		super(grille);
	}

	@Override
	public Optional<ResolutionAction> traiteCaseEnCours(CaseContext context, boolean goPourChangement) {
		if (grilleService.calculNombreCaseATrouverDansLigne(context.getY())<=3) return Optional.empty();

		boolean trouve = this.detecteConfiguration(context);
        if (!trouve) return Optional.empty();
        
        trouve = this.detecteCandidatAEliminer(context);
        if (!trouve) return Optional.empty();
        
        yAction = context.getY();
        
        int numCaseAction= Grille.calculNumCase(xAction, yAction);
        return Optional.of(new ResolutionAction(numCaseAction, null, candidatAEliminer, this, context));
	}

	private boolean detecteConfiguration(CaseContext context) {
        x2=0;
        x3=0;

        while (x2<9) {
            if (x2!=context.getX() && grille.isCaseATrouver(x2,context.getY())) {
                x3=x2;
                while (x3<9) {
                    if (testTriplette(context)) {return true;}
                    x3+=1;
                }
            }
            x2+=1;
        }
        return false;
	}
	
	private boolean testTriplette(CaseContext context) {
		 return (x3!=x2 && x3!=context.getX() && grille.isCaseATrouver(x3,context.getY()) &&
		     this.detecteTripletteEnLigne(context, context.getY(), x2, x3) && this.detecteCandidatAEliminer(context)) ;
	}
	
	private boolean detecteTripletteEnLigne(CaseContext context, int ySearch, int x2, int x3) {
        CandidatsCase testCandidats = new CandidatsCase();
        testCandidats.setCandidats(Utils.calculOuLogique2Candidats(grille.getCandidatsTabBoolean(context.getNumCase()),
                                                       grille.getCandidatsTabBoolean(x2, ySearch)));
        testCandidats.setCandidats(Utils.calculOuLogique2Candidats(testCandidats.getCandidats(),
                                                       grille.getCandidatsTabBoolean(x3, ySearch)));
        if (testCandidats.getNombreCandidats()>3) {return false;}
        
        c1 = Utils.trouveCandidatNumero(testCandidats, 1);
        c2 = Utils.trouveCandidatNumero(testCandidats, 2);
        c3 = Utils.trouveCandidatNumero(testCandidats, 3);

		return true;
	}

	private boolean detecteCandidatAEliminer(CaseContext context) {
		for (int abs = 0 ; abs < 9 ; abs++) {
			if (grille.isCaseATrouver(abs, context.getY()) &&
				abs != context.getX() && abs != x2 && abs != x3) {
				if (grille.isCandidat(abs, context.getY(), c1)) {
					candidatAEliminer = c1;
					xAction = abs;
					return true;
				}
				if (grille.isCandidat(abs, context.getY(), c2)) {
					candidatAEliminer = c2;
					xAction = abs;
					return true;
				}
				if (grille.isCandidat(abs, context.getY(), c3)) {
					candidatAEliminer = c3;
					xAction = abs;
					return true;
				}
			}
		}
		return false;
	}	
	
}

