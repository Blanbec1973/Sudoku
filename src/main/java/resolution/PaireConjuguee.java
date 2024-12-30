package resolution;

import model.grille.CaseEnCours;
import model.grille.Grille;
import utils.Utils;

public abstract class PaireConjuguee extends MethodeResolution {

	protected PaireConjuguee(Grille grille) {
		super(grille);
		caseTrouvee=false;
	}

	@Override
	public boolean traiteCaseEnCours(boolean goPourChangement) {
		if (grille.getNombreCandidats(CaseEnCours.getNumCase()) != 2) return false;
		
		boolean trouve = this.detecteConfiguration();
		if (!trouve) {return false;}

		c1 = Utils.trouveCandidatNumero(grille.getCandidats(CaseEnCours.getNumCase()), 1);
        c2 = Utils.trouveCandidatNumero(grille.getCandidats(CaseEnCours.getNumCase()), 2);
		
        return this.detecteCandidatAEliminer();
	}
	
	protected abstract boolean detecteConfiguration();
	protected abstract boolean detecteCandidatAEliminer();
	
}
