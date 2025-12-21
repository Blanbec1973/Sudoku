package resolution;

import model.grille.CaseContext;
import model.grille.Grille;
import utils.Utils;

import java.util.Optional;

public abstract class AbsenceCandidatDansLesAutresRegions extends MethodeResolution {
	int xAction;
	int yAction;
	int candidatAEliminer;
	
	protected AbsenceCandidatDansLesAutresRegions(Grille grille) {
		super(grille);
	}

	public Optional<ResolutionAction> traiteCaseEnCours(CaseContext context, boolean goPourChangement) {
		boolean changementAFaire = this.detecteConfiguration(context);
		
		if (changementAFaire) {
			int numCaseAction = Utils.calculNumCase(xAction, yAction);
			return Optional.of(new ResolutionAction(numCaseAction, null, candidatAEliminer, this, context, null));
		}
		else return Optional.empty();
	}
	
	private boolean detecteConfiguration(CaseContext context) {
		boolean candidatNonTrouve;
		for (int candidat=1;candidat<10;candidat++) {
			if (grille.isCandidat(context.getNumCase(),candidat)) {
				candidatNonTrouve = true;
				candidatAEliminer = candidat;
				for (int i=0;i<9;i++) {
					candidatNonTrouve = this.testCase(context, i, candidat);
					if (!candidatNonTrouve) break;
				}
				if (candidatNonTrouve && this.detecteCandidatAEliminer(context))
					return true;
			}
		}
		return false;
	}
	protected abstract boolean testCase(CaseContext context, int rangCase, int candidat);
	protected abstract boolean detecteCandidatAEliminer(CaseContext context);
	
}
