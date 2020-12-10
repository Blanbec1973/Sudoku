package modele;

import java.util.ArrayList;
import controleur.Controle;
import resolution.*;

public class Modele {
	private Controle controle;
	private Grille grille;
	private ArrayList<MethodeResolution> listeMethodes;
	
	public Modele(Controle controle) {
		this.controle = controle;
		
        grille =new Grille();
        InitialiseurDeGrille initialiseurDeGrille  = new InitialiseurDeGrille(grille);
        initialiseurDeGrille.init("C:\\Users\\heynerr\\Documents\\W-Workspace\\Sudoku\\init67-40.sud");
        initialiseurDeGrille.calculTousLesCandidats();
        
	    listeMethodes = new ArrayList<>();
	    listeMethodes.add(new CandidatUniqueDansCase(this,grille));
	    listeMethodes.add(new CandidatUniqueDansLigne(this,grille));
	    listeMethodes.add(new CandidatUniqueDansColonne(this,grille));
	    listeMethodes.add(new CandidatUniqueDansRegion(this,grille));
	    listeMethodes.add(new PaireCandidats2CasesColonne(this, grille));
	    listeMethodes.add(new PaireConjugueeEnLigne(this, grille));
	    listeMethodes.add(new PaireConjugueeEnColonne(this, grille));
	    listeMethodes.add(new PaireConjugueeEnRegion(this, grille));
	    listeMethodes.add(new AbsenceCandidatEnColonneDansLesAutresRegions(this, grille));
	    listeMethodes.add(new AbsenceCandidatEnLigneDansLesAutresRegions(this, grille));
	    listeMethodes.add(new PaireCandidats2CasesLigne(this, grille));
	    listeMethodes.add(new TripletteCandidatsEnLigne(this, grille));
	}


	public Grille getGrille() {return grille;}
	public Controle getControle() {return controle;}
	
	public void detecteSuivant(boolean goPourChangement) {
		int i =0;
		boolean trouve = false;
		
		do { 
			trouve = listeMethodes.get(i++).detecteSuivant(goPourChangement);
		} while (i<listeMethodes.size() && !trouve);
        if (!trouve) javax.swing.JOptionPane.showMessageDialog(null,"Fin algorithme !"); 
	}
	
	public void setValeurCaseEnCours(int solution, String message) {
		grille.setValeurCaseEnCours(solution);
		grille.elimineCandidatsCaseTrouvee(CaseEnCours.getXSearch(), CaseEnCours.getYSearch(), solution);
		controle.demandeRefreshGrille(grille);
		controle.demandeAfficheCommande(message);
		controle.demandeIncrementRangResolution();
	}
	
	public void elimineCandidatCaseEnCours(int candidatAEliminer, String message) {
		grille.getCaseEnCours().elimineCandidat(candidatAEliminer);
		controle.demandeRefreshAffichageCase(CaseEnCours.getXSearch(), CaseEnCours.getYSearch());
		controle.demandeAfficheCommande(message);
		controle.demandeIncrementRangResolution();
	}
	
	public void elimineCandidatCase(int candidatAEliminer, int x, int y, String message) {
		grille.getCase(x,y).elimineCandidat(candidatAEliminer);
		controle.demandeRefreshAffichageCase(x, y);
		controle.demandeAfficheCommande(message);
		controle.demandeIncrementRangResolution();		
		
	}
}
