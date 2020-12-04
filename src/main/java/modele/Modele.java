package modele;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import controleur.Controle;
import resolution.*;

public class Modele {
	private Controle controle;
	private Grille grille;
	private ArrayList<MethodeResolution> listeMethodes;
	
	public Modele(Controle controle) {
		this.controle = controle;
		
        grille =new Grille(this);
        InitialiseurDeGrille initialiseurDeGrille  = new InitialiseurDeGrille(grille);
        try {
			initialiseurDeGrille.init("C:\\Users\\heynerr\\Documents\\W-Workspace\\Sudoku\\init7.sud");
		} catch (Exception e) {
			Logger.getLogger(Grille.class.getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
		}
        initialiseurDeGrille.calculTousLesCandidats();
        
	    listeMethodes = new ArrayList<>();
	    listeMethodes.add(new CandidatUniqueDansCase(this,grille));
	    listeMethodes.add(new CandidatUniqueDansLigne(this,grille));
	    listeMethodes.add(new CandidatUniqueDansColonne(this,grille));
	    listeMethodes.add(new CandidatUniqueDansRegion(this,grille));
	    //listeMethodes.add(new AbsenceCandidatEnColonneDansLesAutresRegions(this, grille));
	    listeMethodes.add(new PaireCandidats2CasesColonne(this, grille));
	    listeMethodes.add(new PaireConjugueeEnLigne(this, grille));
	    listeMethodes.add(new PaireConjugueeEnColonne(this, grille));
	    listeMethodes.add(new PaireConjugueeEnRegion(this, grille));
	    
	}


	public Grille getGrille() {return grille;}
	public Controle getControle() {return controle;}
	
	public void detecteSuivant(boolean goPourChangement) {
		int i =0;
		boolean trouve = false;
		
		do { trouve = listeMethodes.get(i++).detecteSuivant(goPourChangement);} 
			while (i<listeMethodes.size() && !trouve);
        if (!trouve) javax.swing.JOptionPane.showMessageDialog(null,"Fin algorithme !");
        //else System.out.println("Sortie détection, i="+String.valueOf(i)+ " numcase="+String.valueOf(CaseEnCours.getNumCase()));
        
	}
	

	
}
