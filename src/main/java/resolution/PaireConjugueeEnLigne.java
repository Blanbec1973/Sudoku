package resolution;

import model.grille.CandidatsCase;
import model.grille.CaseContext;
import model.grille.Grille;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaireConjugueeEnLigne extends MethodeResolution {

	public PaireConjugueeEnLigne(Grille grille) {
		super(grille);
	}

	@Override
	public Optional<ResolutionAction> traiteCaseEnCours(CaseContext context, boolean goPourChangement) {
		if (grilleService.calculNombreCaseATrouverDansLigne(context.getY()) <= 2) return Optional.empty();

		List<int[]> paires = trouverPairesDansLigne(context);

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

	private List<int[]> trouverPairesDansLigne(CaseContext context) {
		List<int[]> paires = new ArrayList<>();
		int y = context.getY();
		int x = context.getX();

		for (int x2 = 0; x2 < 9; x2++) {
			if (x2 != x && grille.isCaseATrouver(x2, y)) {
				paires.add(new int[]{x2});
			}
		}
		return paires;
	}

	private Optional<PaireResult> calculerPaire(CaseContext context, int x2) {
		int y = context.getY();

		CandidatsCase testCandidats = new CandidatsCase();
		testCandidats.setCandidats(Utils.calculOuLogique2Candidats(
				grille.getCandidatsTabBoolean(context.getNumCase()),
				grille.getCandidatsTabBoolean(x2, y)
		));

		if (testCandidats.getNombreCandidats() != 2) return Optional.empty();

		int c1 = Utils.trouveCandidatNumero(testCandidats, 1);
		int c2 = Utils.trouveCandidatNumero(testCandidats, 2);

		return Optional.of(new PaireResult(c1, c2, x2));
	}

	private Optional<ActionCandidate> detecteCandidatAEliminer(CaseContext context, PaireResult paireResult) {
		int y = context.getY();

		for (int abs = 0; abs < 9; abs++) {
			if (grille.isCaseATrouver(abs, y) && abs != context.getX() && abs != paireResult.x2) {
				if (grille.isCandidat(abs, y, paireResult.c1)) return Optional.of(new ActionCandidate(abs, y, paireResult.c1));
				if (grille.isCandidat(abs, y, paireResult.c2)) return Optional.of(new ActionCandidate(abs, y, paireResult.c2));
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
		int x2;
		PaireResult(int c1, int c2, int x2) {
			this.c1 = c1; this.c2 = c2; this.x2 = x2;
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