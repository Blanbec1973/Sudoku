package resolution;

import modele.CaseEnCours;
import modele.Grille;
import modele.Modele;
import modele.Utils;

import java.util.ArrayList;

import modele.CandidatsCase;

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
//     PaireConjugueeEnLigne   : une case de le ligne comporte les mêmes 2 candidats que la caseEnCours
//     PaireConjugueeEnColonne : une case de le colonne comporte les mêmes 2 candidats que la caseEnCours
//     PaireConjugueeEnRegion  : une case de le région comporte les mêmes 2 candidats que la caseEnCours
//


public abstract class MethodeResolution {
	protected Modele modele;
	protected Grille grille;
	protected ArrayList<CandidatsCase> tabCandidats;
	protected int c1;
	protected int c2;
	protected int x2;
	protected int y2;
	protected int candidatAEliminer;
	
	public MethodeResolution(Modele modele, Grille grille) {
		this.modele=modele;
		this.grille=grille;
		this.tabCandidats = new ArrayList <>();
		c1=0;
		c2=0;
	}
	
	public boolean detecteSuivant(boolean goPourChangement) {
		boolean trouve = false;
		int i=0;
		while (i < grille.getCasesAtrouver().size() && !trouve) {
			CaseEnCours.setCaseEnCours(grille.getCasesAtrouver().get(i));
			trouve = this.traiteCaseEnCours(goPourChangement);
			i+=1;
		}
		return trouve ;
	}
	
	public abstract boolean traiteCaseEnCours(boolean goPourChangement);
	
	public void setValeurCaseEnCours(int solution) {
		grille.setValeurCaseEnCours(solution);
		grille.elimineCandidatsCaseTrouvee(CaseEnCours.getXSearch(), CaseEnCours.getYSearch(), solution);
		modele.getControle().demandeRefreshAffichageCase(CaseEnCours.getXSearch(), CaseEnCours.getYSearch());
		modele.getControle().demandeAfficheCommande(this.calculMessageLog(0));
		modele.getControle().demandeIncrementRangResolution();
	}
	
	public void elimineCandidatCaseEnCours(int candidatAEliminer) {
		grille.getCaseEnCours().elimineCandidat(candidatAEliminer);
		modele.getControle().demandeRefreshAffichageCase(CaseEnCours.getXSearch(), CaseEnCours.getYSearch());
		modele.getControle().demandeAfficheCommande(this.calculMessageLog(candidatAEliminer));
		modele.getControle().demandeIncrementRangResolution();
	}
	
	public void elimineCandidatCase(int candidatAEliminer, int x, int y) {
		grille.getCase(x,y).elimineCandidat(candidatAEliminer);
		modele.getControle().demandeRefreshAffichageCase(x, y);
		modele.getControle().demandeAfficheCommande(this.calculMessageLog(candidatAEliminer));
		modele.getControle().demandeIncrementRangResolution();		
		
	}
	
	protected String calculMessageLog(int candidat) {
		String message = "";
		message+= "Case x="+CaseEnCours.getXSearchEdition();
		message+= " y="+CaseEnCours.getYSearchEdition();
		message+= " ";
		if (this instanceof CandidatUniqueDansCase) message+= "Candidat unique dans la case";
		if (this instanceof CandidatUniqueDansLigne) message+= "Candidat unique dans la ligne "+CaseEnCours.getYSearchEdition();
		if (this instanceof CandidatUniqueDansColonne) message+= "Candidat unique dans la colonne "+CaseEnCours.getXSearchEdition();
		if (this instanceof CandidatUniqueDansRegion) message+= "Candidat unique dans la Région "+grille.getCaseEnCours().getRegion();
		if (this instanceof AbsenceCandidatEnColonneDansLesAutresRegions)
			message+="Candidat "+candidat+ " en colonne "+CaseEnCours.getXSearchEdition()+" absent dans autres régions de la colonne.";
		if (this instanceof PaireCandidats2CasesColonne) {
			message+= " Couple ";
			message+= String.valueOf(c1)+String.valueOf(c2);
			message+= " dans deux cases de la colonne, élimination candidat ";
			message+= String.valueOf(candidat);
			return message;
		}
		if (this instanceof PaireConjugueeEnLigne) {
			message+= " Couple conjugué ";
			message+= String.valueOf(c1)+String.valueOf(c2);
			message+= " dans deux cases de la ligne "+CaseEnCours.getYSearchEdition();
			message+= ", élimination candidat ";
			message+= String.valueOf(candidat);
			message+= " dans les autres cases de la ligne.";
			return message;
		}
		if (this instanceof PaireConjugueeEnRegion) {
			message+= " Couple conjugué ";
			message+= String.valueOf(c1)+String.valueOf(c2);
			message+= " dans deux cases de la région ";
			message+= String.valueOf(Utils.calculNumeroRegion(CaseEnCours.getNumCase()));
			message+= ", élimination candidat ";
			message+= String.valueOf(candidat);
			message+= " dans les autres cases de la région.";
			return message;
		}
		message+=", solution="+grille.getCaseEnCours().getValeur();
		return message;
	}
	
}