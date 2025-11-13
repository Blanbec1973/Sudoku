package resolution;

import model.grille.CaseContext;
import model.grille.Grille;
import utils.Utils;

import java.util.Optional;

public abstract class PaireConjuguee extends MethodeResolution {

	protected PaireConjuguee(Grille grille) {
		super(grille);
	}

	@Override
	public Optional<ResolutionAction> traiteCaseEnCours(CaseContext context, boolean goPourChangement) {
		if (grille.getNombreCandidats(context.getNumCase()) != 2) return Optional.empty();
		
		boolean trouve = this.detecteConfiguration(context);
		if (!trouve) {return Optional.empty();}

		int c1 = Utils.trouveCandidatNumero(grille.getCandidats(context.getNumCase()), 1);
        int c2 = Utils.trouveCandidatNumero(grille.getCandidats(context.getNumCase()), 2);
		int [] candidatsUtilises = {c1, c2};

		return this.detecteCandidatAEliminer(context, candidatsUtilises);
	}
	
	protected abstract boolean detecteConfiguration(CaseContext context);
	protected abstract Optional<ResolutionAction> detecteCandidatAEliminer(CaseContext context,int[]candidatsUtilises);

	protected ResolutionAction creerResolutionAction(int x, int y, int candidat, CaseContext context, int[] candidatsUtilises) {
		int numCaseAction = Grille.calculNumCase(x, y);
		return new ResolutionAction(numCaseAction, null,
				candidat, this, context, candidatsUtilises);
	}
	
}
