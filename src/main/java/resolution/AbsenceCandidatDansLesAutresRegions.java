package resolution;

import modele.Grille;
import modele.Modele;
import modele.Utils;

public abstract class AbsenceCandidatDansLesAutresRegions extends MethodeResolution {
	int xAction;
	int yAction;
	
	protected AbsenceCandidatDansLesAutresRegions(Modele modele, Grille grille) {
		super(modele,grille);
	}

	public boolean traiteCaseEnCours(boolean goPourChangement) {	
		boolean changementAFaire = this.detecteConfiguration();
		
		if (changementAFaire) {
			numCaseAction=Utils.calculNumCase(xAction, yAction);
			return true;
		}
		else return false ;
	}
	
	private boolean detecteConfiguration() {
		boolean candidatNonTrouve;
		for (int candidat=1;candidat<10;candidat++) {
			if (grille.getCaseEnCours().isCandidat(candidat)) {
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
