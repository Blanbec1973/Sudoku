package resolution;

import model.grille.CaseEnCours;
import model.grille.Grille;
import utils.Utils;

import java.util.Optional;

public abstract class PaireConjuguee extends MethodeResolution {

	protected PaireConjuguee(Grille grille) {
		super(grille);
		caseTrouvee=false;
	}

	@Override
	public Optional<ResolutionAction> traiteCaseEnCours(boolean goPourChangement) {
		if (grille.getNombreCandidats(CaseEnCours.getNumCase()) != 2) return Optional.empty();
		
		boolean trouve = this.detecteConfiguration();
		if (!trouve) {return Optional.empty();}

		c1 = Utils.trouveCandidatNumero(grille.getCandidats(CaseEnCours.getNumCase()), 1);
        c2 = Utils.trouveCandidatNumero(grille.getCandidats(CaseEnCours.getNumCase()), 2);
		
        if (this.detecteCandidatAEliminer()) {
			return Optional.of(new ResolutionAction(numCaseAction, null, candidatAEliminer, this));
		}
		else {
			return Optional.empty();
		}
	}
	
	protected abstract boolean detecteConfiguration();
	protected abstract boolean detecteCandidatAEliminer();
	
}
