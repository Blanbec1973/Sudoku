package resolution;

import modele.Grille;
import modele.Modele;
import modele.Utils;

public abstract class PaireConjuguee extends MethodeResolution {

	protected PaireConjuguee(Modele modele, Grille grille) {
		super(modele,grille);
		caseTrouvee=false;
	}

	@Override
	public boolean traiteCaseEnCours(boolean goPourChangement) {
		if (grille.getCaseEnCours().getNombreCandidats() != 2) return false;
		
		boolean trouve = this.detecteConfiguration();
		if (!trouve) {return false;}

		c1 = Utils.trouveCandidatNumero(grille.getCaseEnCours().getCandidats(), 1);
        c2 = Utils.trouveCandidatNumero(grille.getCaseEnCours().getCandidats(), 2);
		
        trouve = this.detecteCandidatAEliminer();
		if (!trouve) {return false;}
        
        return true;
	}
	
	protected abstract boolean detecteConfiguration();
	protected abstract boolean detecteCandidatAEliminer();
	
}
