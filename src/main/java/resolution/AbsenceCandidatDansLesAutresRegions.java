package resolution;

import model.grille.CaseEnCours;
import model.grille.Grille;
import model.Model;
import utils.Utils;

public abstract class AbsenceCandidatDansLesAutresRegions extends MethodeResolution {
	int xAction;
	int yAction;
	
	protected AbsenceCandidatDansLesAutresRegions(Model model, Grille grille) {
		super(model,grille);
	}

	public boolean traiteCaseEnCours(boolean goPourChangement) {	
		boolean changementAFaire = this.detecteConfiguration();
		
		if (changementAFaire) {
			numCaseAction=grille.calculNumCase(xAction, yAction);
			return true;
		}
		else return false ;
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
