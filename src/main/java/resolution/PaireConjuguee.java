package resolution;

import model.grille.CaseContext;
import model.grille.Grille;
import utils.Utils;

import java.util.Optional;

public abstract class PaireConjuguee extends MethodeResolution {

	protected PaireConjuguee(Grille grille) {
		super(grille);
		caseTrouvee=false;
	}

	@Override
	public Optional<ResolutionAction> traiteCaseEnCours(CaseContext context, boolean goPourChangement) {
		if (grille.getNombreCandidats(context.getNumCase()) != 2) return Optional.empty();
		
		boolean trouve = this.detecteConfiguration(context);
		if (!trouve) {return Optional.empty();}

		c1 = Utils.trouveCandidatNumero(grille.getCandidats(context.getNumCase()), 1);
        c2 = Utils.trouveCandidatNumero(grille.getCandidats(context.getNumCase()), 2);
		
        if (this.detecteCandidatAEliminer(context)) {
			return Optional.of(new ResolutionAction(numCaseAction, null, candidatAEliminer, this, context));
		}
		else {
			return Optional.empty();
		}
	}
	
	protected abstract boolean detecteConfiguration(CaseContext context);
	protected abstract boolean detecteCandidatAEliminer(CaseContext context);
	
}
