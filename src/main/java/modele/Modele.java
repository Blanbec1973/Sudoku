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
	    grille.init("C:\\Users\\heynerr\\Documents\\W-Workspace\\Sudoku\\init7.sud");
	    
	    listeMethodes = new ArrayList<MethodeResolution>();
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
        else System.out.println("Sortie détection, i="+String.valueOf(i)+ " numcase="+String.valueOf(CaseEnCours.getNumCase()));
        
	}
	

	
}
