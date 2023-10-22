package resolution;

import model.grille.Grille;
import model.Model;
import model.Utils;

public abstract class PaireConjuguee extends MethodeResolution {

	protected PaireConjuguee(Model model, Grille grille) {
		super(model,grille);
		caseTrouvee=false;
	}

	@Override
	public boolean traiteCaseEnCours(boolean goPourChangement) {
		if (grille.getCaseEnCours().getNombreCandidats() != 2) return false;
		
		boolean trouve = this.detecteConfiguration();
		if (!trouve) {return false;}

		c1 = Utils.trouveCandidatNumero(grille.getCaseEnCours().getCandidats(), 1);
        c2 = Utils.trouveCandidatNumero(grille.getCaseEnCours().getCandidats(), 2);
		
        return this.detecteCandidatAEliminer();
	}
	
	protected abstract boolean detecteConfiguration();
	protected abstract boolean detecteCandidatAEliminer();
	
}
