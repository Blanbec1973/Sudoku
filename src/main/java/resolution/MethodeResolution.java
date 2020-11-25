package resolution;

import modele.CaseEnCours;
import modele.Grille;
import modele.Modele;

//Méthodes de résolution par ordre hiérarchique :
//Méthodes qui permettent de trouver une case : 
//    CandidatUniqueDansCase    : La case n'a plus qu'un candidat
//	  CandidatUniqueDansLigne   : un candidat de la case n'existe pas dans les cases de la ligne
//    CandidatUniqueDansColonne : un candidat de la case n'existe pas dans les cases de la colonne
//    CandidatUniqueDansRegion  : un candidat de la case n'existe pas dans les cases de la région
//
//Méthodes qui permettent d'éliminer des candidats :
//  PaireCandidats2CasesColonne : paire de candidats seulement dans 2 cases d'un colonne.
//	Recherche d'absence de candidats en colonne dans les autres régions.
//	Recherche de triplette de candidat dans 3 cases en ligne : 
//	Case en cours avec 2 candidats seulement : 
//     PaireDeCandidatEnLigne   : une case de le ligne comporte les mêmes 2 candidats que la caseEnCours
//     PaireDeCandidatEnColonne : une case de le colonne comporte les mêmes 2 candidats que la caseEnCours
//     PaireDeCandidatEnRegion  : une case de le région comporte les mêmes 2 candidats que la caseEnCours
//


public abstract class MethodeResolution {
	protected Modele modele;
	protected Grille grille;
	
	public MethodeResolution(Modele modele, Grille grille) {
		this.modele=modele;
		this.grille=grille;
	}
	
	public abstract boolean detecteSuivant(boolean goPourChangement);
	
	public void setValeurCaseEnCours(boolean goPourChangement, int solution) {
		grille.setValeurCaseEnCours(solution);
		grille.elimineCandidatsCaseTrouvee(CaseEnCours.getXSearch(), CaseEnCours.getYSearch(), solution);
		modele.getControle().demandeAfficheCommande(this.calculMessageLog(0));
		modele.getControle().demandeIncrementRangResolution();
	}

	protected String calculMessageLog(int candidat) {
		String message = "";
		message+= "Case x="+String.valueOf(CaseEnCours.getXSearch()+1);
		message+= " y="+String.valueOf(CaseEnCours.getYSearch()+1);
		message+= " ";
		if (this instanceof CandidatUniqueDansCase) message+= "Candidat unique dans la case";
		if (this instanceof CandidatUniqueDansLigne) message+= "Candidat unique dans la ligne "+String.valueOf(CaseEnCours.getYSearch()+1);
		if (this instanceof CandidatUniqueDansColonne) message+= "Candidat unique dans la colonne "+String.valueOf(CaseEnCours.getXSearch()+1);
		if (this instanceof CandidatUniqueDansRegion) message+= "Candidat unique dans la Région "+String.valueOf(grille.getCaseEnCours().getRegion());
		if (this instanceof AbsenceCandidatEnColonneDansLesAutresRegions)
			message+="Candidat "+candidat+ " en colonne "+String.valueOf(CaseEnCours.getXSearch()+1)+" absent dans autres régions de la colonne.";
		message+=", solution="+grille.getCaseEnCours().getValeur();
		return message;
	}
	
}