package resolution;

import model.grille.CandidatsCase;
import model.grille.CaseContext;
import model.grille.Grille;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TripletteCandidatsEnLigne extends MethodeResolution {

	public TripletteCandidatsEnLigne(Grille grille) {
		super(grille);
	}

	@Override
	public Optional<ResolutionAction> traiteCaseEnCours(CaseContext context, boolean goPourChangement) {
		if (grilleService.calculNombreCaseATrouverDansLigne(context.getY()) <= 3) return Optional.empty();

		List<int[]> paires = trouverPairesDansLigne(context);

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

	private List<int[]> trouverPairesDansLigne(CaseContext context) {
		List<int[]> paires = new ArrayList<>();
		int y = context.getY();
		int x = context.getX();

		for (int x2 = 0; x2 < 9; x2++) {
			if (x2 != x && grille.isCaseATrouver(x2, y)) {
				for (int x3 = x2 + 1; x3 < 9; x3++) {
					if (x3 != x && grille.isCaseATrouver(x3, y)) {
						paires.add(new int[]{x2, x3});
					}
				}
			}
		}
		return paires;
	}

	private Optional<TripletteResult> calculerTriplette(CaseContext context, int x2, int x3) {
		int y = context.getY();

		CandidatsCase testCandidats = new CandidatsCase();
		testCandidats.setCandidats(Utils.calculOuLogique2Candidats(
				grille.getCandidatsTabBoolean(context.getNumCase()),
				grille.getCandidatsTabBoolean(x2, y)
		));
		testCandidats.setCandidats(Utils.calculOuLogique2Candidats(
				testCandidats.getCandidats(),
				grille.getCandidatsTabBoolean(x3, y)
		));

		if (testCandidats.getNombreCandidats() > 3) return Optional.empty();

		int c1 = Utils.trouveCandidatNumero(testCandidats, 1);
		int c2 = Utils.trouveCandidatNumero(testCandidats, 2);
		int c3 = Utils.trouveCandidatNumero(testCandidats, 3);

		return Optional.of(new TripletteResult(c1, c2, c3, x2, x3));
	}

	private Optional<ActionCandidate> detecteCandidatAEliminer(CaseContext context, TripletteResult triplette) {
		int y = context.getY();

		for (int abs = 0; abs < 9; abs++) {
			if (grille.isCaseATrouver(abs, y) && abs != context.getX() && abs != triplette.x2 && abs != triplette.x3) {
				if (grille.isCandidat(abs, y, triplette.c1)) return Optional.of(new ActionCandidate(abs, y, triplette.c1));
				if (grille.isCandidat(abs, y, triplette.c2)) return Optional.of(new ActionCandidate(abs, y, triplette.c2));
				if (grille.isCandidat(abs, y, triplette.c3)) return Optional.of(new ActionCandidate(abs, y, triplette.c3));
			}
		}
		return Optional.empty();
	}

	private ResolutionAction creerResolutionAction(CaseContext context, TripletteResult triplette, ActionCandidate action) {
		int numCaseAction = Grille.calculNumCase(action.xAction, action.yAction);
		int[] candidatsUtilises = { triplette.c1, triplette.c2, triplette.c3 };
		return new ResolutionAction(numCaseAction, null, action.candidatAEliminer, this, context, candidatsUtilises);
	}

	private static class TripletteResult {
		int c1;
		int c2;
		int c3;
		int x2;
		int x3;
		TripletteResult(int c1, int c2, int c3, int x2, int x3) {
			this.c1 = c1; this.c2 = c2; this.c3 = c3; this.x2 = x2; this.x3 = x3;
		}
	}

	private static class ActionCandidate {
		int xAction;
		int yAction;
		int candidatAEliminer;
		ActionCandidate(int xAction, int yAction, int candidatAEliminer) {
			this.xAction = xAction; this.yAction = yAction; this.candidatAEliminer = candidatAEliminer;
		}
	}
}