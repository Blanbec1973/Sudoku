package resolution;

import modele.CaseEnCours;
import modele.Grille;
import modele.Modele;

public abstract class AbsenceCandidatDansLesAutresRegions extends MethodeResolution {

	public AbsenceCandidatDansLesAutresRegions(Modele modele, Grille grille) {
		super(modele,grille);
	}

	public boolean traiteCaseEnCours(boolean goPourChangement) {	
		boolean changementAFaire = this.detecteConfiguration();
		
		if (changementAFaire) {
			if (goPourChangement) {
           		this.elimineCandidatCase(candidatAEliminer, xAction, yAction);
           		modele.getControle().demandeRefreshAffichageCase(CaseEnCours.getXSearch(), CaseEnCours.getYSearch());
            	return true;
            }
            else {
    			modele.getControle().demandeHighlightCase(xAction,yAction);
            	return true;}
		}
		else return false ;
	}
	
	protected abstract boolean detecteConfiguration();
	protected abstract boolean detecteCandidatAEliminer();
	
}
