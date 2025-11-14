package resolution;

import model.grille.CandidatsCase;
import model.grille.CaseContext;
import model.grille.Grille;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TripletteCandidatsEnColonne extends MethodeResolution {

	public TripletteCandidatsEnColonne(Grille grille) {
		super(grille);
	}

	@Override
	public Optional<ResolutionAction> traiteCaseEnCours(CaseContext context, boolean goPourChangement) {
		if (grilleService.calculNombreCaseATrouverDansColonne(context.getX())<=3) return Optional.empty();

		//Etape 1 : trouver les paires de cases dans la colonne :
		List<int[]> paires = trouverPairesCasesDansColonne(context);
		if (paires.isEmpty()) return Optional.empty();


		for (int[] paire : paires) {
			Optional<TripletteResult> tripletteOpt = calculerTriplette(context, paire[0], paire[1]);
			if (tripletteOpt.isPresent()) {
				TripletteResult triplette = tripletteOpt.get();
				Optional<ActionCandidate> actionOpt = detecteCandidatAEliminer(context, triplette);
				if (actionOpt.isPresent()) {
					ActionCandidate action = actionOpt.get();
					return Optional.of(creerResolutionAction(context, triplette, action));
				}
			}
		}
		return Optional.empty();
	}
	List<int[]> trouverPairesCasesDansColonne(CaseContext context) {
		List<int[]> paires = new ArrayList<>();
		for (int y2 = 0; y2 < 9; y2++) {
			if (y2 != context.getY() && grille.isCaseATrouver(context.getX(), y2)) {
				for (int y3 = y2 + 1; y3 < 9; y3++) {
					if (y3 != context.getY() && grille.isCaseATrouver(context.getX(), y3)) {
						paires.add(new int[]{y2, y3});
					}
				}
			}
		}
		return paires;
	}

	Optional<TripletteResult> calculerTriplette(CaseContext context, int y2, int y3) {
		int x = context.getX();

		CandidatsCase testCandidats = new CandidatsCase();
		testCandidats.setCandidats(Utils.calculOuLogique2Candidats(
				grille.getCandidatsTabBoolean(context.getNumCase()),
				grille.getCandidatsTabBoolean(x, y2)
		));
		testCandidats.setCandidats(Utils.calculOuLogique2Candidats(
				testCandidats.getCandidats(),
				grille.getCandidatsTabBoolean(x, y3)
		));

		if (testCandidats.getNombreCandidats() > 3) return Optional.empty();

		int c1 = Utils.trouveCandidatNumero(testCandidats, 1);
		int c2 = Utils.trouveCandidatNumero(testCandidats, 2);
		int c3 = Utils.trouveCandidatNumero(testCandidats, 3);

		return Optional.of(new TripletteResult(c1, c2, c3, y2, y3));
	}

	Optional<ActionCandidate> detecteCandidatAEliminer(CaseContext context, TripletteResult triplette) {
		int x = context.getX();
		int y = context.getY();

		for (int ord = 0; ord < 9; ord++) {
			if (grille.isCaseATrouver(x, ord) && ord != y && ord != triplette.y2 && ord != triplette.y3) {
				if (grille.isCandidat(x, ord, triplette.c1)) return Optional.of(new ActionCandidate(x, ord, triplette.c1));
				if (grille.isCandidat(x, ord, triplette.c2)) return Optional.of(new ActionCandidate(x, ord, triplette.c2));
				if (grille.isCandidat(x, ord, triplette.c3)) return Optional.of(new ActionCandidate(x, ord, triplette.c3));
			}
		}
		return Optional.empty();
	}

	ResolutionAction creerResolutionAction(CaseContext context, TripletteResult triplette, ActionCandidate action) {
		int numCaseAction = Grille.calculNumCase(action.xAction, action.yAction);
		int[] candidatsUtilises = { triplette.c1, triplette.c2, triplette.c3 };
		return new ResolutionAction(numCaseAction, null, action.candidatAEliminer, this, context, candidatsUtilises);
	}

	private static class TripletteResult {
		int c1;
		int c2;
		int c3;
		int y2;
		int y3;

		public TripletteResult(int c1, int c2, int c3, int y2, int y3) {
			this.c1 = c1;
			this.c2 = c2;
			this.c3 = c3;
			this.y2 = y2;
			this.y3 = y3;
		}
	}

	private static class ActionCandidate {
		int xAction;
		int yAction;
		int candidatAEliminer;

		public ActionCandidate(int xAction, int yAction, int candidatAEliminer) {
			this.xAction = xAction;
			this.yAction = yAction;
			this.candidatAEliminer = candidatAEliminer;
		}
	}
}

