package resolution;

import model.grille.CandidatsCase;
import model.grille.CaseContext;
import model.grille.Grille;
import utils.Utils;

import java.util.Optional;

public class TripletteCandidatsEnColonne extends MethodeResolution {
	int xAction;
	int yAction;
	int candidatAEliminer;
	protected int c1;
	protected int c2;
	protected int c3;

	public TripletteCandidatsEnColonne(Grille grille) {
		super(grille);
	}

	@Override
	public Optional<ResolutionAction> traiteCaseEnCours(CaseContext context, boolean goPourChangement) {
		if (grilleService.calculNombreCaseATrouverDansColonne(context.getX())<=3) return Optional.empty();

		boolean trouve = this.detecteConfiguration(context);
        if (!trouve) return Optional.empty();
        
        trouve = this.detecteCandidatAEliminer(context);
        if (!trouve) return Optional.empty();
        
        xAction = context.getX();
        
        int numCaseAction= Grille.calculNumCase(xAction, yAction);
		int[] candidatsUtilises = {
				c1,
				c2,
				c3
		};
		return Optional.of(new ResolutionAction(numCaseAction, null, candidatAEliminer, this, context, candidatsUtilises));
	}

	private boolean detecteConfiguration(CaseContext context) {
        y2=0;
        y3=0;

        while (y2<9) {
            if (y2!=context.getY() && grille.isCaseATrouver(context.getX(),y2)) {
                y3=y2;
                while (y3<9) {
                    if (testTriplette(context)) {return true;}
                    y3+=1;
                }
            }
            y2+=1;
        }
        return false;
	}
	
	private boolean testTriplette(CaseContext context) {
		 return (y3!=y2 && y3!=context.getY() && grille.isCaseATrouver(context.getX(),y3) &&
		     this.detecteTripletteEnLigne(context, context.getX(), y2, y3) && this.detecteCandidatAEliminer(context));
	}
	
	private boolean detecteTripletteEnLigne(CaseContext context, int xSearch, int y2, int y3) {
        CandidatsCase testCandidats = new CandidatsCase();
        testCandidats.setCandidats(Utils.calculOuLogique2Candidats(grille.getCandidatsTabBoolean(context.getNumCase()),
                                                       grille.getCandidatsTabBoolean(xSearch,y2)));
        testCandidats.setCandidats(Utils.calculOuLogique2Candidats(testCandidats.getCandidats(),
                                                       grille.getCandidatsTabBoolean(xSearch,y3)));
        if (testCandidats.getNombreCandidats()>3) {return false;}
        
        c1 = Utils.trouveCandidatNumero(testCandidats, 1);
        c2 = Utils.trouveCandidatNumero(testCandidats, 2);
        c3 = Utils.trouveCandidatNumero(testCandidats, 3);

		return true;
	}

	private boolean detecteCandidatAEliminer(CaseContext context) {
		for (int ord = 0 ; ord < 9 ; ord++) {
			if (grille.isCaseATrouver(context.getX(), ord) &&
				ord != context.getY() && ord != y2 && ord != y3) {
				if (grille.isCandidat(context.getX(),ord, c1)) {
					candidatAEliminer = c1;
					yAction = ord;
					return true;
				}
				if (grille.isCandidat(context.getX(), ord, c2)) {
					candidatAEliminer = c2;
					yAction = ord;
					return true;
				}
				if (grille.isCandidat(context.getX(), ord, c3)) {
					candidatAEliminer = c3;
					yAction = ord;
					return true;
				}
			}
		}
		return false;
	}	
	
}

