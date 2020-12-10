package modele;

import java.util.ArrayList;

public class Historisation {
	private ArrayList <Grille> histoGrille;
	
	public Historisation() {
		histoGrille = new ArrayList <>();
	}
	
	public Grille getHistoGrille(int i) {return histoGrille.get(i);}
	public ArrayList<Grille> getHistoGrille() {return histoGrille;}
	
	public void historiseGrille(Grille grille) {
		histoGrille.add(new Grille());
		this.copyGrille(grille, histoGrille.get(histoGrille.size()-1));
	}
	
	public void supprimeDerniereGrille(Grille grille) {
		histoGrille.remove(histoGrille.size()-1);
		this.copyGrille(histoGrille.get(histoGrille.size()-1), grille);
	}
	
	private void copyGrille(Grille source, Grille cible) {
		// grille : tableau de Cases + liste casesATrouver
		//     Case : numCase, xCase, yCase, valeur, region, etatCase, candidats
		//			Candidats : boolean[] candidats, nombreCandidats;
		
		for (int numCaseATrouver : source.getCasesAtrouver()) {
			cible.getCasesAtrouver().add(numCaseATrouver);
		}
		
		for (int i=1;i<82;i++) {		
			
			if (source.getCase(i).isCaseInitiale()) {
				cible.getCase(i).setValeurCase(source.getCase(i).getValeur());
				cible.getCase(i).setCaseInitiale();
			}
			
			if (source.getCase(i).isCaseTrouvee()) {
				cible.getCase(i).setValeurCase(source.getCase(i).getValeur());
			}
			
			this.copyCandidats(source.getCase(i).getCandidats(), cible.getCase(i).getCandidats());				                                                          
		}
	}

	private void copyCandidats(CandidatsCase candidats, CandidatsCase candidats2) {
		for (int i=0;i<10;i++) {
			if (candidats.isCandidat(i)) 
				candidats2.setCandidat(i);
			else
				candidats2.elimineCandidat(i);
		}
	}

	public void rechargeDerniereGrille(Grille grille) {
		// TODO Auto-generated method stub
		
	}



}
