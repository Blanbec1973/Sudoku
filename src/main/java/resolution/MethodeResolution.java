package resolution;

import modele.grille.CaseEnCours;
import modele.grille.Grille;
import modele.Modele;
import modele.Utils;

import java.util.ArrayList;

import modele.grille.CandidatsCase;

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
	protected int c3;
	protected int x2;
	protected int x3;
	protected int y2;
	protected boolean caseTrouvee;
	protected int numCaseAction;
	protected int candidatAEliminer;
	protected int solution;
    public static final String COUPLE_CONJUGUEE = " Couple conjugué ";
    public static final String ELIMINATION_CANDIDATS = ", élimination candidat ";
	
	protected MethodeResolution(Modele modele, Grille grille) {
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
			CaseEnCours.setCaseEnCours(grille.getCasesAtrouver().get(i)); //Valorisation de la case en cours.
			System.out.println(CaseEnCours.getNumCase());
			trouve = this.traiteCaseEnCours(goPourChangement);
			i+=1;
		}
		return trouve ;
	}
	
	public boolean isCaseTrouvee() {return caseTrouvee;}
	public int getNumCaseAction() {return numCaseAction;}
	public int getSolution() {return solution;}
	public int getcandidatAEliminer() {return candidatAEliminer;}
	
	public abstract boolean traiteCaseEnCours(boolean goPourChangement);
	
	public String calculMessageLog() {
		String message = "";
		message+= "Case x="+CaseEnCours.getXSearchEdition();
		message+= " y="+CaseEnCours.getYSearchEdition();
		message+= " ";
		if (this instanceof CandidatUniqueDansCase) message+= "Candidat unique dans la case";
		if (this instanceof CandidatUniqueDansLigne) message+= "Candidat unique dans la ligne "+CaseEnCours.getYSearchEdition();
		if (this instanceof CandidatUniqueDansColonne) message+= "Candidat unique dans la colonne "+CaseEnCours.getXSearchEdition();
		if (this instanceof CandidatUniqueDansRegion) message+= "Candidat unique dans la Région "+grille.getCaseEnCours().getRegion();
		if (this instanceof AbsenceCandidatEnColonneDansLesAutresRegions)
			message+="Candidat "+candidatAEliminer+ " en colonne "+CaseEnCours.getXSearchEdition()+" absent dans autres régions de la colonne.";
		if (this instanceof PaireCandidats2CasesColonne) {
			message+= " Couple ";
			message+= String.valueOf(c1)+String.valueOf(c2);
			message+= " dans deux cases de la colonne, élimination candidat ";
			message+= String.valueOf(candidatAEliminer);
			return message;
		}
		if (this instanceof PaireCandidats2CasesLigne) {
			message+= " Couple ";
			message+= String.valueOf(c1)+String.valueOf(c2);
			message+= " dans deux cases de la ligne, élimination candidat ";
			message+= String.valueOf(candidatAEliminer);
			return message;
		}
		if (this instanceof PaireConjugueeEnLigne) {
			message+= COUPLE_CONJUGUEE;
			message+= String.valueOf(c1)+String.valueOf(c2);
			message+= " dans deux cases de la ligne "+CaseEnCours.getYSearchEdition();
			message+= ELIMINATION_CANDIDATS;
			message+= String.valueOf(candidatAEliminer);
			message+= " dans les autres cases de la ligne.";
			return message;
		}
		if (this instanceof PaireConjugueeEnColonne) {
			message+= COUPLE_CONJUGUEE;
			message+= String.valueOf(c1)+String.valueOf(c2);
			message+= " dans deux cases de la colonne "+CaseEnCours.getXSearchEdition();
			message+= ELIMINATION_CANDIDATS;
			message+= String.valueOf(candidatAEliminer);
			message+= " dans les autres cases de la colonne.";
			return message;
		}
		if (this instanceof PaireConjugueeEnRegion) {
			message+= COUPLE_CONJUGUEE;
			message+= String.valueOf(c1)+String.valueOf(c2);
			message+= " dans deux cases de la région ";
			message+= String.valueOf(Utils.calculNumeroRegion(CaseEnCours.getNumCase()));
			message+= ELIMINATION_CANDIDATS;
			message+= String.valueOf(candidatAEliminer);
			message+= " dans les autres cases de la région.";
			return message;
		}
		if (this instanceof AbsenceCandidatEnColonneDansLesAutresRegions) {
			message+= " Candidat ";
			message+= candidatAEliminer;
			message+= " absent des autres régions de la colonne ";
			message+= CaseEnCours.getXSearchEdition();
			message+= ELIMINATION_CANDIDATS;
			message+= " dans les autres colonnes de la région.";
			return message;
		}
		if (this instanceof AbsenceCandidatEnLigneDansLesAutresRegions) {
			message+= " Candidat ";
			message+= candidatAEliminer;
			message+= " absent des autres régions de la ligne ";
			message+= CaseEnCours.getYSearchEdition();
			message+= ELIMINATION_CANDIDATS;
			message+= " dans les autres lignes de la région.";
			return message;
		}
		if (this instanceof TripletteCandidatsEnLigne) {
			message+= " Triplette ";
			message+= String.valueOf(c1)+String.valueOf(c2)+String.valueOf(c3);
			message+= " dans trois cases de la ligne, élimination candidat ";
			message+= String.valueOf(candidatAEliminer);
			return message;
		}
		if (this instanceof CandidatDansColonneUniqueDuneRegion) {
			message+= " Candidat ";
			message+= String.valueOf(candidatAEliminer);
			message+= " dans colonne unique de la région.";
			return message;
		}
		message+=", solution="+solution;
		return message;
	}
	
}