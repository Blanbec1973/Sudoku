package resolution;

import model.grille.CandidatsCase;
import model.grille.CaseEnCours;
import model.grille.Grille;

import java.util.ArrayList;

//Méthodes de résolution par ordre hiérarchique :
//Méthodes qui permettent de trouver une case : 
//    CandidatUniqueDansCase    : La case n'a plus qu'un candidat
//	  CandidatUniqueDansLigne   : un candidat de la case n'existe pas dans les autres cases de la ligne
//    CandidatUniqueDansColonne : un candidat de la case n'existe pas dans les autres cases de la colonne
//    CandidatUniqueDansRegion  : un candidat de la case n'existe pas dans les autres cases de la région
//
//Méthodes qui permettent d'éliminer des candidats :
//  PaireCandidats2CasesColonne : paire de candidats seulement dans 2 cases d'un colonne.
//	Recherche d'absence de candidats en colonne dans les autres régions.
//	Recherche de triplette de candidat dans 3 cases en ligne : 
//	Case en cours avec 2 candidats seulement : 
//     PaireConjugueeEnLigne   : une case de le ligne comporte les mêmes 2 candidats que la caseEnCours
//     PaireConjugueeEnColonne : une case de le colonne comporte les mêmes 2 candidats que la caseEnCours
//     PaireConjugueeEnRegion  : une case de le région comporte les mêmes 2 candidats que la caseEnCours
// Candidat dans colonne unique d'une région
// Candidat dans ligne unique d'une région


public abstract class MethodeResolution {
	protected final Grille grille;
	protected final ArrayList<CandidatsCase> tabCandidats;

	protected int c1;
	protected int c2;
	protected int c3;
	protected int x2;
	protected int x3;
	protected int y2;

	protected int y3;
	protected boolean caseTrouvee;
	protected int numCaseAction;
	protected int candidatAEliminer;
	protected int solution;

	public int getC1() {return c1;}
	public int getC2() {return c2;}
	public int getC3() {return c3;}
	public int getCandidatAEliminer() {return candidatAEliminer;}
	
	protected MethodeResolution(Grille grille) {
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
			trouve = this.traiteCaseEnCours(goPourChangement);
			i+=1;
		}
		return trouve ;
	}
	
	public boolean isCaseTrouvee() {return caseTrouvee;}
	public int getNumCaseAction() {return numCaseAction;}
	public int getSolution() {return solution;}
	
	public abstract boolean traiteCaseEnCours(boolean goPourChangement);

	public String getSimpleName() {
		return this.getClass().getSimpleName();
	}
	
}