package resolution;

import model.grille.CaseContext;
import model.grille.Grille;
import utils.Utils;

import java.util.Optional;

public abstract class PaireConjuguee extends MethodeResolution {
	protected int c1;
	protected  int c2;

	protected PaireConjuguee(Grille grille) {
		super(grille);
	}

	@Override
	public Optional<ResolutionAction> traiteCaseEnCours(CaseContext context, boolean goPourChangement) {
		if (grille.getNombreCandidats(context.getNumCase()) != 2) return Optional.empty();
		
		boolean trouve = this.detecteConfiguration(context);
		if (!trouve) {return Optional.empty();}

		c1 = Utils.trouveCandidatNumero(grille.getCandidats(context.getNumCase()), 1);
        c2 = Utils.trouveCandidatNumero(grille.getCandidats(context.getNumCase()), 2);

		return this.detecteCandidatAEliminer(context);
	}
	
	protected abstract boolean detecteConfiguration(CaseContext context);
	protected abstract Optional<ResolutionAction> detecteCandidatAEliminer(CaseContext context);

	protected ResolutionAction creerResolutionAction(int x, int y, int candidat, CaseContext context) {
		int numCaseAction = Grille.calculNumCase(x, y);

		int[] candidatsUtilises = {
				c1,
				c2
		};

		return new ResolutionAction(numCaseAction, null,
				candidat, this, context, candidatsUtilises);
	}
	
}
