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
		
        grille =new Grille(this);
	    grille.init("C:\\Users\\heynerr\\Documents\\W-Workspace\\Sudoku\\init67-41.sud");
	    
	    listeMethodes = new ArrayList<MethodeResolution>();
	    listeMethodes.add(new CandidatUniqueDansCase(this,grille));
	    listeMethodes.add(new CandidatUniqueDansLigne(this,grille));
	    listeMethodes.add(new CandidatUniqueDansColonne(this,grille));
	    listeMethodes.add(new CandidatUniqueDansRegion(this,grille));
	    listeMethodes.add(new AbsenceCandidatEnColonneDansLesAutresRegions(this, grille));
	    listeMethodes.add(new PaireCandidats2CasesColonne(this, grille));
	    
	}


	public Grille getGrille() {return grille;}
	public Controle getControle() {return controle;}
	
	public void detecteSuivant(boolean goPourChangement) {
		int i =0;
		boolean trouve = false;
		
		do { trouve = listeMethodes.get(i++).detecteSuivant(goPourChangement);} 
			while (i<listeMethodes.size() && !trouve);
        if (!trouve) javax.swing.JOptionPane.showMessageDialog(null,"Fin algorithme !");
	}
	

	
}
