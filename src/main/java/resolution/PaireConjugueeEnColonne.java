package resolution;

import model.grille.CandidatsCase;
import model.grille.CaseContext;
import model.grille.Grille;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaireConjugueeEnColonne extends MethodeResolution {

	public PaireConjugueeEnColonne(Grille grille) {
		super(grille);
	}

	@Override
	public Optional<ResolutionAction> traiteCaseEnCours(CaseContext context, boolean goPourChangement) {
		if (grilleService.calculNombreCaseATrouverDansColonne(context.getX()) <= 2) return Optional.empty();

		List<int[]> paires = trouverPairesDansColonne(context);

		for (int[] paire : paires) {
			Optional<PaireResult> paireOpt = calculerPaire(context, paire[0]);
			if (paireOpt.isPresent()) {
				PaireResult paireResult = paireOpt.get();

				Optional<ActionCandidate> actionOpt = detecteCandidatAEliminer(context, paireResult);
				if (actionOpt.isPresent()) {
					return Optional.of(creerResolutionAction(context, paireResult, actionOpt.get()));
				}
			}
		}
		return Optional.empty();
	}

	private List<int[]> trouverPairesDansColonne(CaseContext context) {
		List<int[]> paires = new ArrayList<>();
		int x = context.getX();
		int y = context.getY();

		for (int y2 = 0; y2 < 9; y2++) {
			if (y2 != y && grille.isCaseATrouver(x, y2)) {
				paires.add(new int[]{y2});
			}
		}
		return paires;
	}

	private Optional<PaireResult> calculerPaire(CaseContext context, int y2) {
		int x = context.getX();

		CandidatsCase testCandidats = new CandidatsCase();
		testCandidats.setCandidats(Utils.calculOuLogique2Candidats(
				grille.getCandidatsTabBoolean(context.getNumCase()),
				grille.getCandidatsTabBoolean(x, y2)
		));

		if (testCandidats.getNombreCandidats() != 2) return Optional.empty();

		int c1 = Utils.trouveCandidatNumero(testCandidats, 1);
		int c2 = Utils.trouveCandidatNumero(testCandidats, 2);

		return Optional.of(new PaireResult(c1, c2, y2));
	}

	private Optional<ActionCandidate> detecteCandidatAEliminer(CaseContext context, PaireResult paireResult) {
		int x = context.getX();

		for (int ord = 0; ord < 9; ord++) {
			if (grille.isCaseATrouver(x, ord) && ord != context.getY() && ord != paireResult.y2) {
				if (grille.isCandidat(x, ord, paireResult.c1)) return Optional.of(new ActionCandidate(x, ord, paireResult.c1));
				if (grille.isCandidat(x, ord, paireResult.c2)) return Optional.of(new ActionCandidate(x, ord, paireResult.c2));
			}
		}
		return Optional.empty();
	}

	private ResolutionAction creerResolutionAction(CaseContext context, PaireResult paireResult, ActionCandidate action) {
		int numCaseAction = Grille.calculNumCase(action.xAction, action.yAction);
		int[] candidatsUtilises = { paireResult.c1, paireResult.c2 };
		return new ResolutionAction(numCaseAction, null, action.candidatAEliminer, this, context, candidatsUtilises);
	}

	private static class PaireResult {
		int c1;
		int c2;
		int y2;
		PaireResult(int c1, int c2, int y2) {
			this.c1 = c1; this.c2 = c2; this.y2 = y2;
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
