package resolution;

import model.grille.CaseEnCours;
import model.grille.Grille;

import java.util.Optional;

public abstract class AbsenceCandidatDansLesAutresRegions extends MethodeResolution {
	int xAction;
	int yAction;
	
	protected AbsenceCandidatDansLesAutresRegions(Grille grille) {
		super(grille);
	}

	public Optional<ResolutionAction> traiteCaseEnCours(boolean goPourChangement) {	
		boolean changementAFaire = this.detecteConfiguration();
		
		if (changementAFaire) {
			numCaseAction= Grille.calculNumCase(xAction, yAction);
			return Optional.of(new ResolutionAction(numCaseAction, null, candidatAEliminer, this));
		}
		else return Optional.empty();
	}
	
	private boolean detecteConfiguration() {
		boolean candidatNonTrouve;
		for (int candidat=1;candidat<10;candidat++) {
			if (grille.isCandidat(CaseEnCours.getNumCase(),candidat)) {
				candidatNonTrouve = true;
				candidatAEliminer = candidat;
				for (int i=0;i<9;i++) {
					candidatNonTrouve = this.testCase(i, candidat);
					if (!candidatNonTrouve) break;
				}
				if (candidatNonTrouve && this.detecteCandidatAEliminer())					
					return true;
			}
		}
		return false;
	}
	protected abstract boolean testCase(int rangCase, int candidat);
	protected abstract boolean detecteCandidatAEliminer();
	
}
